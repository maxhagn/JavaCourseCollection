package dslab.mailbox.models;

import java.util.List;

public class SavedMessage {
    private Long id;
    private String from;
    private String toName;
    private List<String> to;
    private String subject;
    private String data;
    private byte[] hash;

    public SavedMessage(Long id, String from, List<String> to, String toName, String subject, String data, byte[] hash) {
        this.id = id;
        this.from = from;
        this.toName = toName;
        this.to = to;
        this.subject = subject;
        this.data = data;
        this.hash = hash;
    }

    public Long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public List<String> getTo() {
        return to;
    }

    public String getToName() {
        return toName;
    }

    public String getSubject() {
        return subject;
    }

    public String getData() {
        return data;
    }

    public byte[] getHash() {
        return hash;
    }
}