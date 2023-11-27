package dslab.transfer.forwarder;

import dslab.exceptions.ProtocolViolationException;
import dslab.models.DMTP.DMTPMessage;
import dslab.models.DMTP.DMTPMessageType;
import dslab.models.Message;
import dslab.util.Config;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.stream.Collectors;

public class DMTPTransferService {

    private final Config domainConfig = new Config("domains");
    private final Config componentConfig;
    private final String componentId;

    public DMTPTransferService(String componentId) {
        this.componentId = componentId;
        this.componentConfig = new Config(componentId);
    }

    public void forward(Message message) {
        List<String> errorRecipients = new ArrayList<>();
        List<String> servers = new ArrayList<>();
        for (String recipient: message.getTo()) {
                String[] splitAddress = recipient.split("@");
                if (splitAddress.length != 2) {
                    errorRecipients.add(recipient);
                } else if (!servers.contains(splitAddress[1])) {
                    servers.add(splitAddress[1]);
                }
        }
        for (String server: servers) {
            try {
                String resolvedServer = domainConfig.getString(server);
                this.sendToMailbox(message, resolvedServer, server, errorRecipients);
            } catch (ProtocolViolationException | MissingResourceException e) {
                errorRecipients.addAll(filterForServer(message.getTo(), server));
            }
        }

        if(errorRecipients.size() > 0) {
            System.out.println("recipients with errors: " + errorRecipients.size());
            this.errorToSender(message.getFrom(), errorRecipients);
        }
    }

    private List<String> filterForServer(List<String> adresses, String server) {
        return adresses.stream()
                .map(address -> address.split("@"))
                .filter(splitAddress -> splitAddress.length > 1 &&
                        splitAddress[1].equals(server))
                .map(splitAddress -> String.join("@", splitAddress))
                .collect(Collectors.toList());
    }


    private void sendToMailbox(Message message, String serverIpPort, String serverName, List<String> errorRecipients) throws ProtocolViolationException {
        String[] serverIpPortSplit = serverIpPort.split(":");
        Socket socket = null;
        try {
            socket = new Socket(serverIpPortSplit[0], Integer.parseInt(serverIpPortSplit[1]));
            sendMessageToSocket(message, serverName, errorRecipients, socket);
            String localServer = socket.getLocalAddress().getHostAddress() + ":" + componentConfig.getString("tcp.port");
            reportToMonitoring(localServer, message.getFrom());
        } catch (IOException e) {
           errorRecipients.addAll(filterForServer(message.getTo(), serverName));
        } finally {
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // Ignored because we cannot handle it
                }
            }
        }
    }

    private void sendMessageToSocket(Message message, String serverName, List<String> errorRecipients, Socket socket) throws IOException, ProtocolViolationException {
        System.out.println("forwarding message to mailserver");
        BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());

        DMTPMessage initResponse = new DMTPMessage(serverReader.readLine());
        if (!DMTPMessageType.OK.equals(initResponse.getType()) && "DMTP".equals(initResponse.getData())) {
            throw new ProtocolViolationException();
        } else {
            writeDMTPMessageToWriter(serverWriter, new DMTPMessage(DMTPMessageType.BEGIN));
            throwExceptionIfNotOk(new DMTPMessage(serverReader.readLine()));
            writeDMTPMessageToWriter(serverWriter, new DMTPMessage(DMTPMessageType.FROM, message.getFrom()));
            throwExceptionIfNotOk(new DMTPMessage(serverReader.readLine()));
            writeDMTPMessageToWriter(serverWriter, new DMTPMessage(DMTPMessageType.TO, message.getTo()));
            DMTPMessage response = new DMTPMessage(serverReader.readLine());
            if(DMTPMessageType.ERROR.equals(response.getType())) {
                String[] rejectedNames = response.getData().replace("unknown recipient", "").strip().split(",");
                errorRecipients.addAll(List.of(rejectedNames).stream().map(name -> String.format("%s@%s", name, serverName)).collect(Collectors.toList()));
            } else {
                throwExceptionIfNotOk(response);
            }
            writeDMTPMessageToWriter(serverWriter, new DMTPMessage(DMTPMessageType.SUBJECT, message.getSubject()));
            throwExceptionIfNotOk(new DMTPMessage(serverReader.readLine()));
            writeDMTPMessageToWriter(serverWriter, new DMTPMessage(DMTPMessageType.DATA, message.getData()));
            throwExceptionIfNotOk(new DMTPMessage(serverReader.readLine()));
            writeDMTPMessageToWriter(serverWriter, new DMTPMessage(DMTPMessageType.SEND));
            throwExceptionIfNotOk(new DMTPMessage(serverReader.readLine()));
            writeDMTPMessageToWriter(serverWriter, new DMTPMessage(DMTPMessageType.QUIT));
            serverReader.readLine();
        }
    }

    private void writeDMTPMessageToWriter(PrintWriter writer, DMTPMessage msg) {
        writer.write(msg.toMessageString() + "\r\n");
        writer.flush();
    }

    private void throwExceptionIfNotOk(DMTPMessage message) throws ProtocolViolationException {
        if(!DMTPMessageType.OK.equals(message.getType())) {
            throw new ProtocolViolationException();
        }

    }

    private void reportToMonitoring(String server, String fromAddress){
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            byte[] buffer = (server + " " + fromAddress).getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
                    InetAddress.getByName(componentConfig.getString("monitoring.host")),
                    componentConfig.getInt("monitoring.port"));
            socket.send(packet);
        } catch (UnknownHostException e) {
            System.out.println("Cannot connect to Monitoring-Server: " + e.getMessage());
        } catch (SocketException e) {
            System.out.println("SocketException to Monitoring Server: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException while sending to Monitoring Server: " + e.getMessage());
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }

    private void errorToSender(String senderAddress, List<String> failedAddresses) {
        String[] splitSender = senderAddress.split("@");
        if (splitSender.length == 2) {
            String serverIpPort = domainConfig.getString(splitSender[1]);
            if (serverIpPort != null) {
                Message errorMessage = new Message();
                errorMessage.setFrom(componentId);
                errorMessage.setTo(senderAddress);
                errorMessage.setSubject("Error delivering message");
                errorMessage.setData("Could not deliver your message to the following Recipients: " + String.join(", ", failedAddresses));
                try {
                    this.sendToMailbox(errorMessage, serverIpPort, splitSender[1], new ArrayList<>());
                } catch (ProtocolViolationException e) {
                    //ignore errors when sending error mails
                }
            }
        }
    }
}
