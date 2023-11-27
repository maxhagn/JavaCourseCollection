public class QuercusRobur extends Quercus {

    private String description; // Beschreibung der Wiederstandsfähigkeit gegenüber Klimaschwankungen

    /*
       Das Objekt QercusRobur wird instanziert und mit angegebenen Daten befüllt;
       Ein instanziertes Objekt von QuercusRobur wird zurückgegeben.
    */
    public QuercusRobur( String description ) {
        super( );
        this.description = description;
    }

    /*
        resistance gibt description zurück;
        Zeichenwert zurückgeben;
    */
    public String resistance() {
       return description;
    }
}
