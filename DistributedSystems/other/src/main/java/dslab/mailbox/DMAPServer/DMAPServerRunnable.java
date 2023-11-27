package dslab.mailbox.DMAPServer;

import dslab.exceptions.ProtocolViolationException;
import dslab.exceptions.UserAbortException;
import dslab.mailbox.MailStorage;
import dslab.mailbox.models.SavedMessage;
import dslab.models.DMAP.DMAPMessage;
import dslab.models.DMAP.DMAPMessageType;
import dslab.util.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DMAPServerRunnable implements Runnable {
    private final Socket socket;
    private final Config config;
    private final Config userConfig;
    private final MailStorage mailStorage;
    boolean running = true;
    PrintWriter writer;
    BufferedReader reader;
    Map<String, String> userData = new HashMap<>();

    public DMAPServerRunnable(Socket socket, String componentId, MailStorage mailStorage) {
        this.socket = socket;
        this.config = new Config(componentId);
        this.mailStorage = mailStorage;
        this.userConfig = new Config(config.getString("users.config"));
        for (String user : userConfig.listKeys()) {
            userData.put(user, userConfig.getString(user));
        }
    }

    public void run() {
        this.running = true;
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream());
            respondOK("DMAP");
            while (this.running) {
                try {
                    DMAPMessage input = new DMAPMessage(this.reader.readLine());
                    if (input.getType() == DMAPMessageType.LOGIN) {
                        String[] logindata = input.getData().split("\\s");
                        if (logindata.length != 2) { // incorrect format
                            respondError("username and password provided in invalid format");
                        } else if (!userData.containsKey(logindata[0])) { //username wrong
                            respondError("user does not exist");
                        } else if (userData.get(logindata[0]).equals(logindata[1])) {
                            // username and password correct
                            respondOK();
                            this.userLoggedIn(logindata[0]);
                        } else {
                            respondError("password is incorrect");
                        }
                    } else if (input.getType() == DMAPMessageType.QUIT) {
                        respondOKBye();
                        this.running = false;
                    } else {
                        respondError("must 'login' first");
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
            this.running = false;
            shutdown();
            System.out.println("DMAP Connection Closed");
        } catch (IOException e) {
            if (this.running) {
                System.out.println("IO-exception on mailserver-socket: " + e.getMessage());
            }
        } finally {
            this.writer.close();
            try {
                this.reader.close();
            } catch (IOException e) {
                System.out.println("IO-exception on closing of mailserver-socket: " + e.getMessage());
            }
        }
    }

    private void shutdown() {
        this.writer.close();
        try {
            this.reader.close();
        } catch (IOException e) {
            System.out.println("IO-exception on closing of mailserver-socket: " + e.getMessage());
        }
    }

    private void respondOK() {
        this.respondOK(null);
    }

    private void respondOK(String data) {
        if (data != null) {
            writeLineToWriter("ok " + data);
        } else {
            writeLineToWriter("ok");
        }
    }

    private void respondOKBye() {
        respondOK("bye");
    }

    private void respondError(String error) {
        writeLineToWriter("error " + error);
    }

    private void writeLineToWriter(String msg) {
        this.writer.write(msg + "\r\n");
        this.writer.flush();
    }

    private void userLoggedIn(String username) throws IOException {
        boolean loggedIn = true;
        while (loggedIn) {
            DMAPMessage incomingMessage = new DMAPMessage(this.reader.readLine());
            switch (incomingMessage.getType()) {
                case LOGIN:
                    this.respondError("already logged in");
                    break;
                case LIST:
                    List<SavedMessage> messages = mailStorage.getMessagesForUser(username);
                    this.writeLineToWriter(savedMessageListToStringResponse(messages));
                    break;
                case SHOW:
                    try {
                        SavedMessage showMessage = mailStorage.getMessage(Long.valueOf(incomingMessage.getData()), username);
                        if (showMessage != null) {
                            this.writeLineToWriter(savedMessageToStringResponse(showMessage));
                        } else {
                            this.writeLineToWriter("error no message found with given ID");
                        }
                    } catch (NumberFormatException e) {
                        this.respondError("invalid id");
                    }
                    break;
                case DELETE:
                    boolean deleted = mailStorage
                            .removeMessage(Long.valueOf(incomingMessage.getData()),
                                    username);
                    if (deleted) {
                        this.respondOK();
                    } else {
                        respondError("unknown message id");
                    }
                    break;
                case LOGOUT:
                    this.respondOK();
                    loggedIn = false;
                    break;
                case QUIT:
                    throw new UserAbortException();
                default:
                    throw new ProtocolViolationException();
            }
        }
    }

    private String savedMessageListToStringResponse(List<SavedMessage> messages) {
        StringBuilder result = new StringBuilder();
        for (SavedMessage message : messages) {
            result.append(String.format("%d\t%s %s\r\n", message.getId(), message.getFrom(), message.getSubject()));
        }
        return result.toString().stripTrailing();
    }

    private String savedMessageToStringResponse(SavedMessage message) {
        return String.format("from %s\r\nto %s\r\nsubject %s\r\ndata %s",
                message.getFrom(),
                String.join(",", message.getTo()),
                message.getSubject(),
                message.getData());
    }
}
