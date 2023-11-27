import java.util.Random;

public class Fagus implements Tree {

    private float treeHeight; // Höhe eines Fagus
    private float shadowLeavesProportion; // Anteil der Schattenblätter eines Fagus

    /*
       Das Objekt Fagus wird instanziert und mit angegebenen Daten befüllt;
       this.treeHeight >= 5 && this.treeHeight <= 65; this.shadowLeavesProportion >= 0 && this.shadowLeavesProportion <= 1;
       Ein instanziertes Objekt von Fagus wird zurückgegeben.
    */
    public Fagus( float treeHeight, float shadowLeavesProportion ) {
        this.treeHeight = treeHeight;
        this.shadowLeavesProportion = shadowLeavesProportion;
    }

    /*
       Das Objekt Fagus wird instanziert und mit zufälligen Werten befüllt;
       this.treeHeight >= 5 && this.treeHeight <= 65; this.shadowLeavesProportion >= 0 && this.shadowLeavesProportion <= 1.
    */
    public Fagus() {
        Random random;
        random = new Random();
        this.treeHeight = 5 + random.nextFloat() * 60 ;
        this.shadowLeavesProportion = random.nextFloat();
    }

    /*
        Gibt Relation<Fagus, Fagus> zurück;
        Relation<Fagus, Fagus> zurückgegeben;
    */
    static Relation<Fagus, Fagus> relation() {
        Relation<Fagus, Fagus> r = new Relation<Fagus, Fagus>( ) {
            int counter = 0;

            /*
                Gibt Wahrheitswert zurück, ob ein Baum von Typ Fagus mit einem anderen Baum von Typ Fagus in Relation stehen;
                f1 != null && f2 != null;
                Gibt true zurück, wenn f1 größer ist als f2 und f1 einen kleineren Anteil an Schattenblättern hat;
            */
            @Override
            public boolean related( Fagus f1, Fagus f2 ) {
                counter++;
                return f1.height() > f2.height() && f1.shadowLeavesProportion < f2.shadowLeavesProportion;
            }

            /*
                gibt r.counter zurück;
                Positiven Wert zurückgeben;
            */
            @Override
            public int invoked( ) {
                return counter;
            }
        };
        return r; 
    }


    /*
        height gibt treeHeight zurück;
        Positiven Wert zurückgeben;
    */
    @Override
    public float height( ) {
        return treeHeight;
    }

}
