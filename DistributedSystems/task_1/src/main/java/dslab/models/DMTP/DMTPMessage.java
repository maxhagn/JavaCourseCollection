package dslab.models.DMTP;

import dslab.exceptions.ProtocolViolationException;

import java.util.List;

public class DMTPMessage {

    private DMTPMessageType type;
    private String data;

    public DMTPMessageType getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    public void setType(DMTPMessageType type) {
        this.type = type;
    }

    public void setData(String data) {
        this.data = data;
    }

    public DMTPMessage(String input) {
        if(input == null) {
            throw new ProtocolViolationException();
        }
        String[] inputSplit = input.split("\\s");
        this.type = DMTPMessageType.fromString(inputSplit[0]);
        if(this.type == null) {
            throw new ProtocolViolationException();
        }
        this.data = input.substring(inputSplit[0].length()).strip();
    }

    public DMTPMessage(DMTPMessageType type) {
        this.type = type;
        this.data = "";
    }

    public DMTPMessage(DMTPMessageType type, String data) {
        this.type = type;
        this.data = data;
    }

    public DMTPMessage(DMTPMessageType type, List<String> data) {
        this.type = type;
        this.data = String.join(",", data);
    }

    public String toMessageString() {
        if(data == null || data.isEmpty()) {
            return this.type.toString();
        }
        return this.type.toString() + " " + this.data;
    }
}
