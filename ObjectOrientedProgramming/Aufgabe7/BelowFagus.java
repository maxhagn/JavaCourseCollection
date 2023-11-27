public class BelowFagus extends ShadowType {
    private final int shadow = 1; // shadow = 1

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
        return String.format("%12s", "BelowFagus");
    }

}
