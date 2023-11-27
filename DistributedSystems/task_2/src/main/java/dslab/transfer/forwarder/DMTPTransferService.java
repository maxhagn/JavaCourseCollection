package dslab.transfer.forwarder;

import dslab.exceptions.ProtocolViolationException;
import dslab.models.DMTP.DMTPMessage;
import dslab.models.DMTP.DMTPMessageType;
import dslab.models.Message;
import dslab.nameserver.INameserverRemote;
import dslab.util.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.Collectors;

public class DMTPTransferService {

    private final Config domainConfig = new Config("domains");
    private final Config componentConfig;
    private final String componentId;
    private final INameserverRemote rootNameserver;

    public DMTPTransferService(String componentId, INameserverRemote rootNameserver) {
        this.componentId = componentId;
        this.rootNameserver = rootNameserver;
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
                String resolvedServer;
                if ( rootNameserver != null ) {
                    resolvedServer = domainLookup(server);
                } else {
                    resolvedServer = domainConfig.getString(server);
                }
                if(resolvedServer == null) {
                    throw new DomainNotFoundException("Domain could not be resolved");
                }
                this.sendToMailbox(message, resolvedServer, server, errorRecipients, false);
            } catch (ProtocolViolationException | MissingResourceException | DomainNotFoundException e) {
                errorRecipients.addAll(filterForServer(message.getTo(), server));
            }
        }

        if(errorRecipients.size() > 0) {
            System.out.println("recipients with errors: " + errorRecipients.size());
            this.errorToSender(message.getFrom(), errorRecipients);
        }
    }

    private String domainLookup( String domain ) throws DomainNotFoundException {
        List<String> splitDomain = Arrays.asList(domain.split("\\."));
        Collections.reverse(splitDomain);
        INameserverRemote current = rootNameserver;
        for (String zone : splitDomain) {
            try {
                if (!zone.equals(splitDomain.get(splitDomain.size()-1))) {
                    current  = current.getNameserver(zone);
                } else {
                    return current.lookup(zone);
                }

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        throw new DomainNotFoundException("Mailbox Server could not be located");
    }

    private List<String> filterForServer(List<String> adresses, String server) {
        return adresses.stream()
                .map(address -> address.split("@"))
                .filter(splitAddress -> splitAddress.length > 1 &&
                        splitAddress[1].equals(server))
                .map(splitAddress -> String.join("@", splitAddress))
                .collect(Collectors.toList());
    }


    private void sendToMailbox(Message message,
                               String serverIpPort,
                               String serverName,
                               List<String> errorRecipients,
                               boolean isErrorMessage) throws ProtocolViolationException {
        String[] serverIpPortSplit = serverIpPort.split(":");
        Socket socket = null;
        try {
            socket = new Socket(serverIpPortSplit[0], Integer.parseInt(serverIpPortSplit[1]));
            if(isErrorMessage) {
                message.setFrom("mailer@["+socket.getLocalAddress().getHostAddress()+"]");
            }
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
        if (!DMTPMessageType.OK.equals(initResponse.getType()) && "DMTP2.0".equals(initResponse.getData())) {
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
            writeDMTPMessageToWriter(serverWriter, new DMTPMessage(DMTPMessageType.HASH, message.getHashBase64()));
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
            String serverIpPort = null;
            if ( rootNameserver != null ) {
                try {
                    serverIpPort = domainLookup(splitSender[1]);
                } catch (DomainNotFoundException e) {
                    System.out.println("Requested Domain not found.");
                }
            } else {
                serverIpPort = domainConfig.getString(splitSender[1]);
            }
            if (serverIpPort != null) {
                Message errorMessage = new Message();
                errorMessage.setTo(senderAddress);
                errorMessage.setSubject("Error delivering message");
                errorMessage.setData("Could not deliver your message to the following Recipients: " + String.join(", ", failedAddresses));
                try {
                    this.sendToMailbox(errorMessage, serverIpPort, splitSender[1], new ArrayList<>(), true);
                } catch (ProtocolViolationException e) {
                    //ignore errors when sending error mails
                }
            }
        }
    }
}
