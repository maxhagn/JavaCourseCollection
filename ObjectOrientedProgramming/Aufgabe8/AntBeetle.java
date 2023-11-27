import java.util.*;

public class AntBeetle implements Runnable {


    private Thread antBeetle; // antBeetle == null || antBeetle == thread
    private Tree currHabitat; // currHabitat != null
    private final Simulation simulation; // simulation != null
    private int stepsWithoutEating; // stepsWithoutEating >= 0
    private int steps; // steps >= null
    private Tree nextTreeToMigrate; // nextTreeToMigrate != null
    private Tree nextTreeToPopulate; // nextTreeToPopulate != null

    /*
       Ein Objekt von AntBeetle wird erstellt und mit einer simulation, x und y instanziert;
       simultation != null && x >= 0 && y >= 0;
       Ein Objekt von AntBeetle wurde erstellt und besitzt simulation, x und y
    */
    public AntBeetle(Simulation simulation, int x, int y) {
        this.simulation = simulation;
        this.currHabitat = simulation.getSeg(x, y);
        stepsWithoutEating = 0;
        this.currHabitat.setVal('+');
        steps = 0;
    }

    // run() führt einen AntBeetle Thread aus
    @Override
    public void run() {
        antBeetle = Thread.currentThread();
        currHabitat.setAntBeetleThread(Thread.currentThread());
        boolean hasEaten = false;
        while (!Thread.currentThread().isInterrupted()) {
            getNextTrees();
            steps++;
            if (stepsWithoutEating >= 3) {
                currHabitat.setAntBeetleThread(null);
                //Thread.currentThread().interrupt();
                this.antBeetle.interrupt();
                simulation.remove(this);
                break;
            } else {
                if (nextTreeToMigrate != null) {
                    ArrayList<Tree> locks = new ArrayList<Tree>();
                    locks.add(nextTreeToMigrate);
                    locks.add(nextTreeToMigrate);
                    Collections.sort(locks);
                    synchronized (nextTreeToMigrate) {
                        if (nextTreeToMigrate.getVal() == '*' ||  nextTreeToMigrate.getVal() == '0') {
                            if (nextTreeToMigrate.getVal() == 'O') {
                                hasEaten = true;
                            }
                            Tree oldHabitat = currHabitat;
                            oldHabitat.setVal('*');
                            currHabitat = nextTreeToMigrate;
                            //nextTreeToMigrate.setAntBeetle(Thread.currentThread());
                            currHabitat.setAntBeetleThread(this.antBeetle);
                            currHabitat.setAntBeetle(this);
                            currHabitat.setVal('+');
                            currHabitat.moveAntBeetle();
                            this.simulation.remove(currHabitat.getBarkBeetle());
                        }
                    }

                    synchronized (nextTreeToPopulate) {
                        if (nextTreeToPopulate != null && (nextTreeToPopulate.getVal() == '*' ||  nextTreeToPopulate.getVal() == '0')) {
                            AntBeetle a = new AntBeetle(simulation, nextTreeToPopulate.getXpos(), nextTreeToPopulate.getYpos());
                            nextTreeToPopulate.setAntBeetleThread(simulation.newAntBeetle(a));
                            nextTreeToPopulate.setAntBeetle(a);
                            nextTreeToPopulate.setVal('+');
                            nextTreeToPopulate.moveAntBeetle();
                            this.simulation.remove(nextTreeToPopulate.getBarkBeetle());
                        }
                    }

                }
                long waitTime = (long) (Math.random() * 1000 + 5);
                if (!hasEaten) {
                    stepsWithoutEating++;
                }
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    // getNextTrees() setzt this.nextFreeTree1 und this.nextFreeTree2 auf den nächsten Nachbarn
    private void getNextTrees() {
        List<Tree> neighbours = currHabitat.getNeighbours();
        List<Tree> allFreeNeighbours = new ArrayList<>();
        for (Tree n : neighbours) {
            if (n.getVal() == '*' || n.getVal() == '0') {
                allFreeNeighbours.add(n);
            }
        }
        if (allFreeNeighbours.size() >= 2) {
            Random random = new Random();
            int size = allFreeNeighbours.size() - 1;
            nextTreeToMigrate = allFreeNeighbours.get(random.nextInt(size));
            allFreeNeighbours.remove(nextTreeToMigrate);
            size = allFreeNeighbours.size() - 1;
            if (size == 0) {
                nextTreeToPopulate = allFreeNeighbours.get(0);
            } else {
                nextTreeToPopulate = allFreeNeighbours.get(random.nextInt(size));
            }
        } else {
            nextTreeToMigrate = null;
            nextTreeToPopulate = null;
        }
    }

    // endThread() beendet einen antBeetle Thread
    public void endThread() {
        if (antBeetle != null) {
            Thread.currentThread().interrupt();
        }
    }

    /*
        Gibt einen String der alle statistischen Werte enthält zurück
    */
    @Override
    public String toString() {
        return "Ant Beetle: \nCurrent habitat: " + currHabitat.getXpos() + ", " + currHabitat.getYpos() + "\n" +
                "Simulation-Steps: " + steps + "\n";
    }
}
