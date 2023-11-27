package dslab.client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;
import java.util.stream.Collectors;

import at.ac.tuwien.dsg.orvell.Shell;
import at.ac.tuwien.dsg.orvell.StopShellException;
import at.ac.tuwien.dsg.orvell.annotation.Command;
import dslab.ComponentFactory;
import dslab.exceptions.ProtocolViolationException;
import dslab.models.DMAP.DMAPMessage;
import dslab.models.DMAP.DMAPMessageType;
import dslab.models.DMTP.DMTPMessage;
import dslab.models.DMTP.DMTPMessageType;
import dslab.models.Message;
import dslab.util.Config;
import dslab.util.EncryptionUtils;
import dslab.util.Keys;
import dslab.util.MessageUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class MessageClient implements IMessageClient, Runnable {

    private final MessageUtils messageUtils;
    private final Shell shell;
    private final String componentId;
    private final Config config;
    private final InputStream in;
    private final PrintStream out;
    private Socket mailboxSocket;
    private BufferedReader mailboxReader;
    private PrintWriter mailboxWriter;

    //Security
    private IvParameterSpec aesIv = null;
    private SecretKey secKey = null;

    /**
     * Creates a new client instance.
     *
     * @param componentId the id of the component that corresponds to the Config resource
     * @param config the component config
     * @param in the input stream to read console input from
     * @param out the output stream to write console output to
     */
    public MessageClient(String componentId, Config config, InputStream in, PrintStream out) {
        this.messageUtils = new MessageUtils("keys/hmac.key");
        this.componentId = componentId;
        this.config = config;
        this.in = in;
        this.out = out;
        shell = new Shell(in, out);
        shell.register(this);
        shell.setPrompt(componentId + "> ");
    }

    @Override
    public void run() {
        try {
            String username = config.getString("mailbox.user");
            String password = config.getString("mailbox.password");
            this.mailboxSocket = new Socket(config.getString("mailbox.host"), config.getInt("mailbox.port"));
            this.mailboxReader = new BufferedReader(new InputStreamReader(this.mailboxSocket.getInputStream()));
            this.mailboxWriter = new PrintWriter(this.mailboxSocket.getOutputStream());
            DMAPMessage initResponse = new DMAPMessage(this.mailboxReader.readLine());
            if (!DMAPMessageType.OK.equals(initResponse.getType()) && "DMAP2.0".equals(initResponse.getData())) {
                throw new ProtocolViolationException();
            }
            this.authenticateDMAP();
            this.writeDMAPMessageToWriter(mailboxWriter, new DMAPMessage(DMAPMessageType.LOGIN, String.join(" ", username, password)));
            this.throwExceptionIfNotOk(decrypt(mailboxReader.readLine()));
            this.out.println("Successfully logged into mailserver as user: " + username);
            shell.run();
        } catch (IOException e) {
            this.out.println("Connection to Mailbox could not be established");
        }
    }

    @Override
    @Command
    public void inbox() {
        if(!checkMailserverConnection()) {
            return;
        }
        try {
            List<String> messageIds = readMessageListIds();
            if(messageIds == null) {
                return;
            } else if(messageIds.size() == 0) {
                out.println("No messages in mailbox");
                return;
            }
            Map<String, Message> messages = new HashMap<>();
            for (String messageId: messageIds) {
                messages.put(messageId, getMessageFromMailbox(messageId));
            }
            printMessageList(messages);
        } catch (IOException e) {
            out.println("error communicating with mailserver");
        }
    }

    @Override
    @Command
    public void delete(String id) {
        if(!checkMailserverConnection()) {
            return;
        }
        writeDMAPMessageToWriter(this.mailboxWriter, new DMAPMessage("delete " + id) );
        try {
            out.println(this.decryptStringIfPossible(mailboxReader.readLine()));
        } catch (IOException e) {
            out.println("error communicating with mailbox");
        }
    }

    @Override
    @Command
    public void verify(String id) {
        if(!checkMailserverConnection()) {
            return;
        }
        Message message = getMessageFromMailbox(id);
        if(message == null) {
            return;
        }
        if(messageUtils.checkHash(message)) {
            out.println("ok");
        } else {
            out.println("error");
        }
    }

    @Override
    @Command
    public void msg(String to, String subject, String data) {Message message = new Message();
        message.setFrom(config.getString("transfer.email"));
        message.setTo(to);
        message.setSubject(subject);
        message.setData(data);
        message.setHash(this.messageUtils.calculateHash(message));
        List<String> messageErrors = message.checkForErrors();
        if(messageErrors.size() > 0) {
            out.println("error invalid message: " + String.join(",", messageErrors));
            return;
        }
        try {
            Socket transferSocket = new Socket(config.getString("transfer.host"), config.getInt("transfer.port"));
            BufferedReader transferReader = new BufferedReader(new InputStreamReader(transferSocket.getInputStream()));
            PrintWriter transferWriter = new PrintWriter(transferSocket.getOutputStream());

            this.sendMessageToTransfer(message, transferReader, transferWriter);
            transferSocket.close();
            out.println("Message sent");
        } catch (IOException e) {
            out.println("error connecting to Transferserver - Message could not be sent");
        }
    }

    @Override
    @Command
    public void shutdown() {
        try {
            writeDMAPMessageToWriter(this.mailboxWriter, new DMAPMessage("logout") );
            this.mailboxReader.readLine();
            writeDMAPMessageToWriter(this.mailboxWriter, new DMAPMessage("quit") );
            this.mailboxReader.readLine();
        } catch (IOException e) {
            // ignore failing to properly disconnect
        }
        throw new StopShellException();
    }

    public static void main(String[] args) throws Exception {
        IMessageClient client = ComponentFactory.createMessageClient(args[0], System.in, System.out);
        client.run();
    }

    private void printMessageList(Map<String, Message> messages) {
        for (String messageId: messages.keySet()) {
            Message message = messages.get(messageId);
            out.println("-------Message Id: " + messageId + "--------------");
            out.println("From: " + message.getFrom());
            out.println("To: " + String.join(",", message.getTo()));
            out.println("Subject: " + message.getSubject());
            out.println("Data: " + message.getData());
        }
    }

    private List<String> readMessageListIds() throws IOException {
        List<String> messageIds = new ArrayList<>();
        boolean listFinished = false;
        writeDMAPMessageToWriter(this.mailboxWriter, new DMAPMessage("list"));
        String response = this.decryptStringIfPossible(this.mailboxReader.readLine());
        String[] responseSplit = response.split("\\s", 2);
        while (!listFinished) {
            if("error".equals(responseSplit[0])) {
                this.out.println(response);
                return null;
            } else if("ok".equals(responseSplit[0])) {
                listFinished = true;
            } else if (!responseSplit[0].isBlank()){
                messageIds.add(responseSplit[0]);
                response = this.decryptStringIfPossible(this.mailboxReader.readLine());
                responseSplit = response.split("\\s", 2);
            }  else {
                response = this.decryptStringIfPossible(this.mailboxReader.readLine());
                responseSplit = response.split("\\s", 2);
            }
        }
        return messageIds;
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

    private Message getMessageFromMailbox(String id) {
        Message message = new Message();
        writeDMAPMessageToWriter(this.mailboxWriter, new DMAPMessage("show " + id) );
        try {
            String response = this.decryptStringIfPossible(this.mailboxReader.readLine());
            String[] responseSplit = response.split("\\s", 2);
            if("error".equals(responseSplit[0])) {
                this.out.println(response);
                return null;
            }
            boolean messageFinished = false;
            while (!messageFinished) {
                addFieldToMessage(responseSplit[0], responseSplit[1], message);
                response = this.decryptStringIfPossible(this.mailboxReader.readLine());
                responseSplit = response.split("\\s", 2);
                if("error".equals(responseSplit[0])) {
                    this.out.println(response);
                    return null;
                }
                if("ok".equals(responseSplit[0])) {
                    messageFinished = true;
                }
            }
        } catch (IOException e) {
            out.println("error communicating with mailbox");
        }
        return message;
    }

    private Message addFieldToMessage(String type, String data, Message message) {
        switch (type) {
            case "from":
                message.setFrom(data);
                break;
            case "to":
                message.setTo(data);
                break;
            case "subject":
                message.setSubject(data);
                break;
            case "data":
                message.setData(data);
                break;
            case "hash":
                message.setHash(data);
                break;
        }
        return message;
    }

    private void sendMessageToTransfer(Message message, BufferedReader serverReader, PrintWriter serverWriter) throws IOException, ProtocolViolationException {
        DMTPMessage initResponse = new DMTPMessage(serverReader.readLine());
        if (!DMTPMessageType.OK.equals(initResponse.getType()) && "DMTP2.0".equals(initResponse.getData())) {
            throw new ProtocolViolationException();
        } else {
            writeDMTPMessageToWriter(serverWriter, new DMTPMessage(DMTPMessageType.BEGIN));
            throwExceptionIfNotOk(new DMTPMessage(serverReader.readLine()));
            writeDMTPMessageToWriter(serverWriter, new DMTPMessage(DMTPMessageType.FROM, message.getFrom()));
            throwExceptionIfNotOk(new DMTPMessage(serverReader.readLine()));
            writeDMTPMessageToWriter(serverWriter, new DMTPMessage(DMTPMessageType.TO, message.getTo()));
            throwExceptionIfNotOk(new DMTPMessage(serverReader.readLine()));
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

    private void writeDMAPMessageToWriter(PrintWriter writer, DMAPMessage msg) {
        if(this.aesIv != null && this.secKey != null){
            try {
                msg = encrypt(msg);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writer.write(msg.toMessageString() + "\r\n");
        writer.flush();

    }

    private void throwExceptionIfNotOk(DMAPMessage message) throws ProtocolViolationException {
        if(!DMAPMessageType.OK.equals(message.getType())) {
            //delete
            out.println(message.toMessageString());
            throw new ProtocolViolationException();
        }
    }

    private boolean checkMailserverConnection() {
        if(mailboxSocket.isClosed()) {
            out.println("Connection to Mailbox-server lost - please restart");
            return false;
        }
        return true;
    }

    private void authenticateDMAP() {
        SecureRandom sr = new SecureRandom();
        this.writeDMAPMessageToWriter(mailboxWriter, new DMAPMessage(DMAPMessageType.STARTSECURE));
        try {
            DMAPMessage msg = new DMAPMessage(this.mailboxReader.readLine());
            //Create client challenge & secret key & iv
            PublicKey publicKey = Keys.readPublicKey(new File("keys/client/"+msg.getData()+"_pub.der"));

            byte[] clientSecret = new byte[32];
            sr.nextBytes(clientSecret);
            byte[] clientSecretEncoded = Base64.getEncoder().encode(clientSecret);

            SecretKey secretKey = EncryptionUtils.generateSecretKey();
            byte[] secretKeyEncoded = Base64.getEncoder().encode(secretKey.getEncoded());

            byte[] iv = new byte[16];
            sr.nextBytes(iv);
            IvParameterSpec ivObj = new IvParameterSpec(iv);
            byte[] ivEncoded = Base64.getEncoder().encode(iv);

            String handshake3String = String.join(" ", "ok", new String(clientSecretEncoded, "ASCII"), new String(secretKeyEncoded, "ASCII"), new String(ivEncoded, "ASCII"));
            byte[] handshake3 = EncryptionUtils.encrypt(handshake3String, publicKey);
            this.writeDMAPMessageToWriter(mailboxWriter, new DMAPMessage(DMAPMessageType.NONE, new String(Base64.getEncoder().encode(handshake3), "ASCII")));

            //wait for response
            String respString = this.mailboxReader.readLine();
            byte[] resp = Base64.getDecoder().decode(respString.getBytes());
            resp = EncryptionUtils.decryptSymmetrical(resp, new SecretKeySpec(secretKey.getEncoded(), "AES"), ivObj);
            resp = Base64.getDecoder().decode(new String(resp));

            if(Arrays.equals(resp, clientSecret)){
                this.writeDMAPMessageToWriter(mailboxWriter, new DMAPMessage(DMAPMessageType.NONE, Base64.getEncoder().encodeToString(EncryptionUtils.encryptSymmetrical("ok", secretKey, ivObj))));//ok
                this.aesIv = ivObj;
                this.secKey = new SecretKeySpec(secretKey.getEncoded(), "AES");
            }else{
                this.writeDMAPMessageToWriter(mailboxWriter, new DMAPMessage(DMAPMessageType.NONE, Base64.getEncoder().encodeToString(EncryptionUtils.encryptSymmetrical("quit", this.secKey, this.aesIv))));//quit
                this.aesIv = null;
                this.secKey = null;
                throw new ProtocolViolationException();
            }

        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
            this.out.println(e.getMessage());
            throw new ProtocolViolationException();
        }
    }


    private DMAPMessage decrypt(String message) {
        return new DMAPMessage(decryptStringIfPossible(message));
    }

    private String decryptStringIfPossible(String message) {
        if(this.aesIv != null && this.secKey != null) {
            try {
           return new String(EncryptionUtils.decryptSymmetrical(Base64.getDecoder().decode(message.getBytes(StandardCharsets.US_ASCII)), this.secKey, this.aesIv), StandardCharsets.US_ASCII);
            } catch (BadPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
                this.out.println("error unable to decrypt message properly");
            }
        }
        return message;
    }

    private DMAPMessage encrypt(DMAPMessage message) throws IOException {
        try {
            return new DMAPMessage(DMAPMessageType.NONE, Base64.getEncoder().encodeToString(EncryptionUtils.encryptSymmetrical(message.getType() + " " + message.getData(), this.secKey, this.aesIv)));
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
            this.out.println(e.getMessage());
        }
        return message;
    }

}
