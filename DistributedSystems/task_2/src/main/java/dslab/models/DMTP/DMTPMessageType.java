package dslab.models.DMTP;

public enum DMTPMessageType {
    //ClientCommands
    BEGIN("begin"),
    TO("to"),
    FROM("from"),
    SUBJECT("subject"),
    DATA("data"),
    HASH("hash"),
    SEND("send"),
    QUIT("quit"),

    //ServerCommands
    OK("ok"),
    ERROR("error");

    private String type;

    DMTPMessageType(String typeString) {
        this.type = typeString;
    }

    public String getType() {
        return this.type;
    }

    public static DMTPMessageType fromString(String text) {
        for (DMTPMessageType b : DMTPMessageType.values()) {
            if (b.type.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}