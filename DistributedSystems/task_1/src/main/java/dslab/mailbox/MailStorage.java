package dslab.mailbox;

import dslab.mailbox.models.SavedMessage;
import dslab.models.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MailStorage {

    private Long counter = 0L;
    private final ConcurrentHashMap<Long, SavedMessage> mailstorage = new ConcurrentHashMap<>();

    public void addMessage(Message message, List<String> recipients) {
        for (String receiver : recipients) {
            Long id = this.getNextId();
            String recipientName = receiver.split("@")[0];
            mailstorage.put(id, new SavedMessage(id, message.getFrom(), message.getTo(), recipientName, message.getSubject(), message.getData()));
        }
    }

    public SavedMessage getMessage(Long id, String name) {
        SavedMessage message = mailstorage.get(id);
        if (message != null && message.getToName().equals(name)) {
            return message;
        }
        return null;
    }

    public boolean removeMessage(Long id, String name) {
        SavedMessage message = mailstorage.get(id);
        if (message == null || !message.getToName().equals(name)) {
            return false;
        }
        mailstorage.remove(id);
        return true;
    }

    public List<SavedMessage> getMessagesForUser(String username) {
        
        return new ArrayList<SavedMessage>(mailstorage.values()).stream()
                .filter(message -> username.equals(message.getToName()))
                .collect(Collectors.toList());
    }

    public synchronized Long getNextId() {
        return counter++;
    }
}
