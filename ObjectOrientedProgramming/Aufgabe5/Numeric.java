public class Numeric implements Relation<Integer, Integer> {

    private Integer c = 1; // kontrollwert c der größer als 0 ist
    private int counter = 0; // zähler, wie oft related() aufgerufen wurde

    /*
       Das Objekt Numeric wird instanziert und mit angegebenen Daten befüllt;
       c > 0;
       Der Integer c wird zu dem Objekt hinzugefügt.
    */
    public Numeric(Integer c){
        if(c > 0) {
            this.c = c;
        }
    }

    /*
       related prüft ob zwei Objekte von Typ Integer gleich sind;
       i != null && i2 != null;
       Ein Wahrheitswert, der bestimmt ob zwei Integer Objekte verwandt sind, wird zurückgegeben.
    */
    @Override
    public boolean related(Integer i, Integer i2) {
        this.counter++;
        return Math.abs(i-i2) <= this.c;
    }

    /*
        invoked gibt counter zurück;
        Positiven Wert zurückgeben;
    */
    @Override
    public int invoked( ) {
        return this.counter;
    }

}
