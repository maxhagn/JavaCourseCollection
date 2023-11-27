public class Chopper implements ToolAttachment {
    private int maxThickness; // maxThickness >= 0

    /*
       Ein Objekt von Chopper wird erstellt und mit einer maxThickness instanziert;
       this.maxThickness >= 0;
       Ein Objekt von Chopper wurde erstellt und besitzt eine maxThickness.
    */
    public Chopper(int maxThickness) {
        this.maxThickness = maxThickness;
    }

    /*
       Gibt die maxThickness des Objekts zurück;
    */
    public int getMaxThickness() {
        return this.maxThickness;
    }

}
