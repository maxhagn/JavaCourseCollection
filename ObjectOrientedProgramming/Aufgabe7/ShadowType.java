public abstract class ShadowType {

    private int shadow = 0; // shadow >= 1 && shadow <= 3

    /*
       Gibt coordinate des Objekts zurück;
    */
    public int getShadow(){
        return this.shadow;
    }

    /*
       Der ShadowType wird als String zurückgegeben;
    */
    @Override
    public String toString() {
        return "ShadowType{}";
    }
}
