package dslab.models.DMAP;

public enum DMAPMessageType {
    //ClientCommands
    STARTSECURE("startsecure"),
    OK("ok"), //3 different types of ok commands
    NONE("none"),
    LOGIN("login"),
    LIST("list"),
    SHOW("show"),
    DELETE("delete"),
    LOGOUT("logout"),
    QUIT("quit"),
    ERROR("error");


    private String type;

    DMAPMessageType(String typeString) {
        this.type = typeString;
    }

    public String getType() {
        return this.type;
    }

    public static DMAPMessageType fromString(String text) {
        for (DMAPMessageType b : DMAPMessageType.values()) {
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