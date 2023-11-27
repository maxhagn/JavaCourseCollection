public class Cutter implements ToolAttachment {
    private float maxLength; // maxLenght >= 0

    /*
       Ein Objekt von Cutter wird erstellt und mit einer maxLength instanziert;
       this.maxLength >= 0;
       Ein Objekt von Cutter wurde erstellt und besitzt eine maxLength
    */
    public Cutter(float maxLength) {
        this.maxLength = maxLength;
    }

    /*
       Gibt die maxThickness des Objekts zurück;
    */
    public float getMaxLength() {
        return this.maxLength;
    }

}
