public class OpenArea extends ShadowType {

    private final int shadow = 3; // shadow = 3

    /*
       Gibt shadow des Objekts zurück;
    */
    public int getShadow(){
        return this.shadow;
    }

    /*
       Der String "OpenArea" wird zurückgegeben
    */
    @Override
    public String toString( ) {
        return String.format("%10s", "OpenArea");
    }

}
