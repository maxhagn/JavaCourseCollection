package dslab.mailbox.DMAPServer;

import dslab.exceptions.ProtocolViolationException;
import dslab.exceptions.UserAbortException;
import dslab.mailbox.MailStorage;
import dslab.mailbox.models.SavedMessage;
import dslab.models.DMAP.DMAPMessage;
import dslab.models.DMAP.DMAPMessageType;
import dslab.util.Config;
import dslab.util.Keys;
import dslab.util.EncryptionUtils;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class DMAPServerRunnable implements Runnable {
    private final Socket socket;
    private final String componentId;
    private final Config config;
    private final Config userConfig;
    private final MailStorage mailStorage;
    boolean running = true;
    PrintWriter writer;
    BufferedReader reader;
    Map<String, String> userData = new HashMap<>();

    //Security
    private IvParameterSpec aesIv = null;
    private SecretKey secKey = null;

    public DMAPServerRunnable(Socket socket, String componentId, MailStorage mailStorage) {
        this.socket = socket;
        this.componentId = componentId;
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
            respondOK("DMAP2.0");
            while (this.running) {
                try {
                    String incMsg = this.reader.readLine();
                    DMAPMessage input;
                    if(this.aesIv != null && this.secKey != null){
                        input = this.decrypt(incMsg);
                    }else{
                        input = new DMAPMessage(incMsg);
                    }
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
                    } else if (input.getType() == DMAPMessageType.STARTSECURE) {
                        //Start with the hanshake
                        initiateHandshake();
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
        System.out.println("error "+error);
        writeLineToWriter("error " + error);
    }

    private void writeLineToWriter(String msg) {
        if(this.aesIv != null && this.secKey != null){
            try {
                List<String> lines = Arrays.asList(msg.split("\r\n"));
                for (String line : lines) {
                    this.writer.write(Base64.getEncoder().encodeToString(EncryptionUtils.encryptSymmetrical(line.getBytes("ASCII"), this.secKey, this.aesIv)) + "\r\n");
                }
            } catch (IOException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
                this.respondError("unable to decrypt message properly");
            }
        }else{
            this.writer.write(msg + "\r\n");
        }
        this.writer.flush();

    }

    private void userLoggedIn(String username) throws IOException {
        boolean loggedIn = true;
        while (loggedIn) {
            String incMsg = this.reader.readLine();
            DMAPMessage incomingMessage;
            if(this.aesIv != null && this.secKey != null){
                incomingMessage = this.decrypt(incMsg);
            } else {
                incomingMessage = new DMAPMessage(incMsg);
            }
            switch (incomingMessage.getType()) {
                case STARTSECURE:
                    this.respondError("connection already established");
                    break;
                case LOGIN:
                    this.respondError("already logged in");
                    break;
                case LIST:
                    List<SavedMessage> messages = mailStorage.getMessagesForUser(username);
                    this.writeLineToWriter(savedMessageListToStringResponse(messages));
                    respondOK();
                    break;
                case SHOW:
                    try {
                        SavedMessage showMessage = mailStorage.getMessage(Long.valueOf(incomingMessage.getData()), username);
                        if (showMessage != null) {
                            this.writeLineToWriter(savedMessageToStringResponse(showMessage));
                            respondOK();
                        } else {
                            this.writeLineToWriter("error no message found with given ID");
                        }
                    } catch (NumberFormatException e) {
                        this.respondError("invalid id");
                    }
                    break;
                case DELETE:
                    try {
                        boolean deleted = mailStorage
                                .removeMessage(Long.valueOf(incomingMessage.getData()),
                                        username);
                        if (deleted) {
                            this.respondOK();
                        } else {
                            respondError("unknown message id");
                        }
                    } catch (NumberFormatException e) {
                        respondError("invalid id format");
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
        return String.format("from %s\r\nto %s\r\nsubject %s\r\ndata %s\r\nhash %s",
                message.getFrom(),
                String.join(",", message.getTo()),
                message.getSubject(),
                message.getData(),
                Base64.getEncoder().encodeToString(message.getHash()));
    }

    private void initiateHandshake() throws IOException {
        this.respondOK(this.componentId);
        int handshakeStep = 3;
        while (handshakeStep <= 5){
            String incomingMessage = this.reader.readLine();
            switch (handshakeStep){
                case 3:
                    //decode client challenge, get secret-key and iv
                    handshakeStep3(incomingMessage);
                    handshakeStep=5;
                    break;
                case 5:
                    //get the ok message and establish secure channel
                    try {
                        if(!incomingMessage.equals(Base64.getEncoder().encodeToString(EncryptionUtils.encryptSymmetrical("ok", this.secKey, this.aesIv)))){
                            this.aesIv = null;
                            this.secKey = null;
                            throw new ProtocolViolationException();
                        }
                    } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
                        this.respondError("unable to decrypt message properly");
                        throw new ProtocolViolationException();
                    }
                    handshakeStep++;
                    break;
            }
        }
    }

    private void handshakeStep3(String message) throws IOException {
        PrivateKey privateKey = Keys.readPrivateKey(new File("keys/server/"+componentId+".der"));
        //Decode
        try {
            byte[] decodedMessage = Base64.getDecoder().decode(message.getBytes());
            String decryptedMessage = EncryptionUtils.decrypt(decodedMessage, privateKey);

            String[] messageArray = decryptedMessage.split("\\s");

            if (messageArray.length != 4){
                this.respondError("wrong number of arguments for handshake");
            } else {
                byte[] clientSecret = Base64.getDecoder().decode(messageArray[1].getBytes("ASCII"));
                byte[] secretKey = Base64.getDecoder().decode(messageArray[2].getBytes("ASCII"));
                byte[] iv = Base64.getDecoder().decode(messageArray[3].getBytes("ASCII"));

                //Set global params
                this.secKey = new SecretKeySpec(secretKey, "AES");
                this.aesIv = new IvParameterSpec(iv);
                byte[] response = clientSecret;
                response = EncryptionUtils.encryptSymmetrical(response, this.secKey, this.aesIv);
                String responseString = Base64.getEncoder().encodeToString(response);

                writeLineToWriter(Base64.getEncoder().encodeToString(clientSecret));
            }
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
            this.respondError("unable to decrypt message properly");
            throw new ProtocolViolationException();
        }

    }

    private DMAPMessage decrypt(String message) throws IOException {
        try {
            DMAPMessage tmp = new DMAPMessage(new String(EncryptionUtils.decryptSymmetrical(Base64.getDecoder().decode(message.getBytes("ASCII")), this.secKey, this.aesIv), "ASCII"));
            return tmp;
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
            this.respondError("unable to decrypt message properly");
        }
        return new DMAPMessage(message);
    }

    private DMAPMessage encrypt(DMAPMessage message) throws IOException {
        try {
            return new DMAPMessage(DMAPMessageType.NONE, Base64.getEncoder().encodeToString(EncryptionUtils.encryptSymmetrical(message.getType() + " " + message.getData().getBytes("ASCII"), this.secKey, this.aesIv)));
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
            this.respondError("unable to decrypt message properly");
        }
        return message;
    }
}