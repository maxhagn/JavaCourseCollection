package dslab.nameserver;


public class ZoneNotRegisteredException extends Exception {
    private static final long serialVersionUID = 1L;

    public ZoneNotRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZoneNotRegisteredException(String message) {
        super(message);
    }
}