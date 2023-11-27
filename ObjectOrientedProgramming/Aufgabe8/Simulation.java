import java.util.LinkedList;
import java.util.List;

public class Simulation {

    private final Forest forest; // forest != null
    private List<AntBeetle> antBeetles; //
    private List<BarkBeetle> barkBeetles;

    // Ein Objekt von Simulation wird erstellt und mit einem forest instanziert;
    // forest != null
    // Ein Objekt von Simulation wurde erstellt und besitzt forest
    public Simulation(Forest forest) {
        this.forest = forest;
    }

    // output() gibt eine Zeichenkette auf der Console aus
    public void statistics() {
        System.out.println("Statistics of simulation:");
        synchronized (antBeetles) {
            for (AntBeetle a : antBeetles) {
                System.out.println(a);
            }
        }
        synchronized (barkBeetles) {
            for (BarkBeetle b : barkBeetles) {
                System.out.println(b);
            }
        }
    }

    // output() gibt this.forest auf der Console aus
    public void output() {
        forest.output();
    }

    // endAllThreads() beendet alle aktiven Threads
    public void endAllThreads() {
        synchronized (barkBeetles){
            for (BarkBeetle b : barkBeetles) {
                b.endThread();
            }
        }

        synchronized (antBeetles) {
            for (AntBeetle a : antBeetles) {
                a.endThread();
            }
        }

    }

    /*
       Das AntBeetle Objekt a wird der Liste antBeetles hinzugefügt und der neue Thread dieses Objekts wird gestartet;
       a != null;
       Das Antbeetle Objekt a wurde der Liste antBeetles hinzugefügt und der neue Thread dieses Objekts wurde gestartet;
       t != null;
    */
    public Thread newAntBeetle(AntBeetle a){
        synchronized (antBeetles){
            this.antBeetles.add(a);
        }

        Thread t = new Thread(a, "Antbeetle");
        t.start();
        return t;
    }

    /*
       Das BarkBeetle Objekt a wird der Liste barkBeetles hinzugefügt und der neue Thread dieses Objekts wird gestartet;
       b != null;
       Das AntBeetle Objekt a wurde der Liste barkBeetles hinzugefügt und der neue Thread dieses Objekts wurde gestartet;
       t != null;
    */
    public Thread newBarkBeetle(BarkBeetle b){
        synchronized (barkBeetles){
            this.barkBeetles.add(b);
        }

        Thread t = new Thread(b, "Barkbeetle");
        t.start();
        return t;
    }

    /*
       Das BarkBeetle Objekt barkBeetle wird aus Liste barkBeetles entfernt und alle threads werden beendet, sollte die Liste danach leer sein;
       barkBeetle != null;
       Das BarkBeetle Objekt barkBeetle wurde aus der Liste barkBeetles entfernt;
    */
    public void remove(BarkBeetle barkBeetle) {
        synchronized (this.barkBeetles) {
            this.barkBeetles.remove(barkBeetle);
        }

        if (this.getBarkBeetles().size() <= 0) {
            this.endAllThreads();
            System.out.println("Final forest-situation");
            this.output();
            this.statistics();
        }
    }

    /*
       Das AntBeetle Objekt antBeetle wird aus Liste barkBeetles entfernt;
       antBeetle != null;
       Das AnteBeetle Objekt antBeetle wurde aus der Liste antBeetles entfernt;
    */
    public void remove(AntBeetle antBeetle) {
        synchronized (this.antBeetles) {
            this.antBeetles.remove(antBeetle);
        }
    }

    /*
       Die Liste barkBeetles des simulation Objekts this wird zurückgegeben;
    */
    public List<BarkBeetle> getBarkBeetles() {
        return barkBeetles;
    }

    // getSeg() gibt ein Segment an einer spezifizierten Stelle zurück;
    // x >= 0 && y >= 0;
    // Ein Segment an der Stelle x und y wurde zurückgegeben
    public Tree getSeg(int x, int y) {
        return forest.getSeg(x, y);
    }

    // startSimulation() startet eine Simulation mit bestimmten Einstellungen;
    // antBeetles != null && barkBeetles != null;
    // Eine Simulation wurde gestartet
    public void startSimulation(LinkedList<AntBeetle> antBeetles, LinkedList<BarkBeetle> barkBeetles) {
        this.antBeetles = antBeetles;
        this.barkBeetles = barkBeetles;
        synchronized (antBeetles){
            for (AntBeetle a : antBeetles) {
                new Thread(a, "Antbeetle").start();
            }
        }
        synchronized (barkBeetles){
            for (BarkBeetle b : barkBeetles) {
                new Thread(b, "Barkbeetle").start();
            }
        }

    }

}
