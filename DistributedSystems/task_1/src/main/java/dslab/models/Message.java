package dslab.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Message {
    String from;
    List<String> to;
    String subject;
    String data;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = Arrays.asList(to.split(","));
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<String> checkForErrors() {
        List<String> errors = new ArrayList<>();
        if(isNullOrEmpty(from)){
            errors.add("FROM must be set");
        }
        if(to == null || to.size() == 0){
            errors.add("TO must be set");
        }
        if(isNullOrEmpty(subject)){
            errors.add("SUBJECT must be set");
        }
        if(isNullOrEmpty(data)){
            errors.add("DATA must be set");
        }
        return errors;
    }

    private boolean isNullOrEmpty(String data) {
        return data == null || data.isEmpty();
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", to=" + to +
                ", subject='" + subject + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
