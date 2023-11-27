import java.util.Random;

public class Quercus implements Tree {

    private float treeHeight; // gesamthöhe des Baums
    private float shadowLeavesProportion; // Anteil von Schattenblättern
    private float stemHeight; // stammhöhe des Baums

    /*
       Das Objekt Fagus wird instanziert und mit angegebenen Daten befüllt;
       this.treeHeight >= 5 && this.treeHeight <= 65; this.stemHeight >= 3 && this.stemHeight <= this.treeHeight-8; this.shadowLeavesProportion >= 0 && this.shadowLeavesProportion <= 1;
       Ein instanziertes Objekt von Fagus wird zurückgegeben.
    */
    public Quercus( float treeHeight, float shadowLeavesProportion, float stemHeight ) {
        this.treeHeight = treeHeight;
        this.stemHeight = stemHeight;
        this.shadowLeavesProportion = shadowLeavesProportion;
    }

    /*
       Das Objekt Fagus wird instanziert und mit zufälligen Werten befüllt;
       this.treeHeight >= 8 && this.treeHeight <= 65; this.stemHeight >= 3 && this.stemHeight <= this.treeHeight-8; this.shadowLeavesProportion >= 0 && this.shadowLeavesProportion <= 1.
    */
    public Quercus() {
        Random random;
        random = new Random();
        this.treeHeight = 5 + random.nextFloat() * 60 ;
        this.stemHeight = 3 + random.nextFloat() * (this.treeHeight-5);
        this.shadowLeavesProportion = random.nextFloat();
    }

    /*
        Gibt Relation<? extends Quercus, ? extends Tree> zurück;
        Relation<? extends Quercus, ? extends Tree> zurückgegeben;
    */
    static Relation<? extends Quercus, ? extends Tree> relation() {
        Relation<Quercus, Tree> r = new Relation<Quercus, Tree>( ) {
            int counter = 0;

            /*
                Gibt Wahrheitswert zurück, ob ein Baum von Typ Quercus mit einem anderen Baum beliebigen Typs in Relation stehen;
                q != null && t != null;
                Gibt true zurück, wenn q größer oder gelichgroß als t ist;
            */
            @Override
            public boolean related(Quercus q, Tree t) {
                counter++;
                return q.stemHeight >= t.height();
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
