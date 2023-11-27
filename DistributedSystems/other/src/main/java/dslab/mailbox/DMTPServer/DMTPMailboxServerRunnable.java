package dslab.mailbox.DMTPServer;

import dslab.mailbox.MailStorage;
import dslab.models.Message;
import dslab.servers.DMTPServerRunnable;
import dslab.util.Config;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DMTPMailboxServerRunnable extends DMTPServerRunnable {
    private final Config componentConfig;
    private final Config userConfig;
    private final List<String> knownMailboxNames;
    private final String domain;
    private final MailStorage mailStorage;

    public DMTPMailboxServerRunnable(Socket socket, String componentId, MailStorage mailStorage) {
        super(socket);
        componentConfig = new Config(componentId);
        this.mailStorage = mailStorage;
        userConfig = new Config(componentConfig.getString("users.config"));
        knownMailboxNames = new ArrayList<>(userConfig.listKeys());
        domain = componentConfig.getString("domain");
    }


    @Override
    protected List<String> filterAcceptedRecipients(List<String> recipients) {
        return recipients.stream()
                .map(recipient -> recipient.split("@"))
                .filter(splitRecipient -> splitRecipient.length == 2 &&
                        domain.equals(splitRecipient[1]) &&
                        knownMailboxNames.contains(splitRecipient[0]))
                .map(splitRecipient -> String.join("@", splitRecipient))
                .collect(Collectors.toList());
    }

    @Override
    protected List<String> filterRejectedRecipients(List<String> recipients) {
        return recipients.stream()
                .map(recipient -> recipient.split("@"))
                .filter(splitRecipient -> splitRecipient.length == 2 &&
                        domain.equals(splitRecipient[1]) &&
                        ! knownMailboxNames.contains(splitRecipient[0]))
                .map(splitRecipient -> String.join("@", splitRecipient))
                .collect(Collectors.toList());
    }

    @Override
    protected void messageReceived(Message message) {
        System.out.println("Received message: ");
        System.out.println(message.toString());
        mailStorage.addMessage(message, filterAcceptedRecipients(message.getTo()));
    }
}
