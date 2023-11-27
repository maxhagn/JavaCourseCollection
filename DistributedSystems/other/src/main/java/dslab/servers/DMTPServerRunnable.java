package dslab.servers;

import dslab.exceptions.ProtocolViolationException;
import dslab.exceptions.UserAbortException;
import dslab.models.DMTP.DMTPMessage;
import dslab.models.DMTP.DMTPMessageType;
import dslab.models.Message;
import dslab.util.DMTPUtils.DMTPServerCommandService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DMTPServerRunnable implements Runnable {
    private final Socket socket;
    boolean running = true;
    PrintWriter writer;
    BufferedReader reader;
    private final DMTPServerCommandService dmtpServerCommandService = new DMTPServerCommandService();

    public DMTPServerRunnable(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        this.running = true;
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream());
            respondOK("DMTP");
            while (this.running) {
                try {
                    DMTPMessage input = new DMTPMessage(this.reader.readLine());
                    if (input.getType() == DMTPMessageType.BEGIN) {
                        respondOK();
                        this.assembleMessages();
                    } else if (input.getType() == DMTPMessageType.QUIT) {
                        respondOKBye();
                        this.running = false;
                    } else if (input.getType() != null) {
                        respondError("Must 'begin' message first");
                    } else {
                        throw new ProtocolViolationException();
                    }
                } catch (ProtocolViolationException e) {
                    this.respondError("protocol error");
                    this.running = false;
                } catch (UserAbortException e) {
                    this.respondOKBye();
                    this.running = false;
                }
            }
        } catch (SocketException e) {
            System.out.println("DMTP-Conncetion closed - Shutting down DMTP-Thread");
        } catch (IOException e) {
            System.out.println("IO-exception: " + e.getMessage());
        } finally {
            if (writer != null) {
                this.writer.close();
            }
            try {
                if (reader != null) {
                    this.reader.close();
                }
            } catch (IOException e) {
                System.out.println("IO-exception on closing DMTP reader " + e.getMessage());
            }
        }
    }


    private void respondOK() {
        this.respondOK(null);
    }

    private void respondOK(String data) {
        writeLineToWriter(dmtpServerCommandService.getOkResponse(data));
    }

    private void respondOKBye() {
        writeLineToWriter(dmtpServerCommandService.getOkByeResponse());
    }

    private void respondError(String error) {
        writeLineToWriter(dmtpServerCommandService.getErrorResponse(error));
    }

    private void respondError(List<String> errors) {
        writeLineToWriter(dmtpServerCommandService.getErrorResponse(String.join(", ", errors)));
    }

    private void writeLineToWriter(String msg) {
        this.writer.write(msg + "\r\n");
        this.writer.flush();
    }

    public void assembleMessages() throws IOException {
        boolean constructingMessage = true;
        Message message = new Message();
        while (constructingMessage) {
            DMTPMessage incomingMessage = new DMTPMessage(this.reader.readLine());
            switch (incomingMessage.getType()) {
                case FROM:
                    message.setFrom(incomingMessage.getData());
                    this.respondOK();
                    break;
                case TO:
                    List<String> recipients = List.of(incomingMessage.getData().split(","));
                    List<String> acceptedRecipients = filterAcceptedRecipients(recipients);
                    List<String> rejectedRecipients = filterRejectedRecipients(recipients);
                    message.setTo(recipients);
                    if (rejectedRecipients.size() == 0) {
                        this.respondOK(String.valueOf(acceptedRecipients.size()));
                    } else {
                        List<String> rejectedRecipientsNames = rejectedRecipients.stream()
                                .map(recipient -> recipient.split("@")[0])
                                .collect(Collectors.toList());
                        this.respondError("unknown recipient " + String.join(",", rejectedRecipientsNames));
                    }
                    break;
                case SUBJECT:
                    message.setSubject(incomingMessage.getData());
                    this.respondOK();
                    break;
                case DATA:
                    message.setData(incomingMessage.getData());
                    this.respondOK();
                    break;
                case SEND:
                    List<String> errors = message.checkForErrors();
                    if (errors.size() > 0) {
                        this.respondError(errors);
                    } else {
                        this.respondOK();
                        this.messageReceived(message);
                        constructingMessage = false;
                    }
                    break;
                case QUIT:
                    throw new UserAbortException();
                default:
                    throw new ProtocolViolationException();
            }
        }
    }

    protected List<String> filterAcceptedRecipients(List<String> recipients) {
        return recipients;
    }

    protected List<String> filterRejectedRecipients(List<String> recipients) {
        return Collections.emptyList();
    }

    protected abstract void messageReceived(Message message);
}
