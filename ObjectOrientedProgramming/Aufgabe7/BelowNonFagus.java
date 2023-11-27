public class BelowNonFagus extends ShadowType {
    private final int shadow = 2;

    /*
       Gibt shadow des Objekts zurück;
    */
    public int getShadow(){
        return this.shadow;
    }

    /*
       Gibt die Klasse als String aus;
    */
    @Override
    public String toString( ) {
        return String.format("%15s", "BelowNonFagus");
    }

}
