package dslab.models.DMAP;

import dslab.exceptions.ProtocolViolationException;

import java.util.List;

public class DMAPMessage {

    private DMAPMessageType type;
    private String data;

    public DMAPMessageType getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    public void setType(DMAPMessageType type) {
        this.type = type;
    }

    public void setData(String data) {
        this.data = data;
    }

    public DMAPMessage(String input) {
        if(input == null) {
            throw new ProtocolViolationException();
        }
        String[] inputSplit = input.split("\\s");
        this.type = DMAPMessageType.fromString(inputSplit[0]);
        if(this.type == null) {
            throw new ProtocolViolationException();
        }
        this.data = input.substring(inputSplit[0].length()).strip();
    }

    public DMAPMessage(DMAPMessageType type) {
        this.type = type;
        this.data = "";
    }

    public DMAPMessage(DMAPMessageType type, String data) {
        this.type = type;
        this.data = data;
    }

    public DMAPMessage(DMAPMessageType type, List<String> data) {
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
