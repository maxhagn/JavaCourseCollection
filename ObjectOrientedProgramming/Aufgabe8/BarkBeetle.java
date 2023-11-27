import java.util.*;

public class BarkBeetle implements Runnable {
    private Thread barkBeetle; // barkBeetle == null || barkBeetle == thread
    private final Tree currHabitat; // currHabitat != null
    private final Simulation simulation; // simulation != null
    private int genCountOverall; // genCountOverall >= 0
    private int genCount; // genCount >= 0
    private Tree nextFreeTree1; // nextFreeTree1 != null
    private Tree nextFreeTree2; // nextFreeTree2 != null


    // Ein Objekt von BarkBeetle wird erstellt und mit einer simulation, x, y und genCountOverall instanziert;
    // simulation != null && x >= 0 && y >= 0 && genCountOverall >= 0
    // Ein Objekt von BarkBeetle wurde erstellt und besitzt simulation, x, y und genCountOverall
    public BarkBeetle(Simulation simulation, int x, int y, int genCountOverall) {
        this.simulation = simulation;
        this.currHabitat = simulation.getSeg(x, y);
        this.genCountOverall = genCountOverall;
        this.genCount = 0;
        this.currHabitat.setVal('0');
    }

    // run() führt einen BarkBeetle Thread aus
    @Override
    public void run() {
        barkBeetle = Thread.currentThread();
        currHabitat.setBarkBeetleThread(barkBeetle);
        while (!Thread.currentThread().isInterrupted()) {

            if (genCountOverall < 32) {
                getNextTrees();
                genCountOverall++;
                genCount++;
                if (genCount >= 3) {
                    currHabitat.dies();
                    currHabitat.setBarkBeetleThread(null);
                    this.barkBeetle.interrupt();
                    simulation.remove(this);
                    break;
                } else {
                    if (nextFreeTree1 != null) {
                        ArrayList<Tree> locks = new ArrayList<Tree>();
                        locks.add(nextFreeTree1);
                        locks.add(nextFreeTree2);
                        Collections.sort(locks);
                        synchronized (nextFreeTree1) {
                            if (nextFreeTree1.getVal() == '*' ) {
                                BarkBeetle b = new BarkBeetle(simulation, nextFreeTree1.getXpos(), nextFreeTree1.getYpos(), genCountOverall);
                                nextFreeTree1.setBarkBeetleThread(simulation.newBarkBeetle(b));
                                nextFreeTree1.setBarkBeetle(b);
                                nextFreeTree1.setVal('0');
                            }
                        }
                        synchronized (nextFreeTree2) {
                            if (nextFreeTree2.getVal() == '*' ) {
                                BarkBeetle b = new BarkBeetle(simulation, nextFreeTree2.getXpos(), nextFreeTree2.getYpos(), genCountOverall);
                                nextFreeTree2.setBarkBeetleThread(simulation.newBarkBeetle(b));
                                nextFreeTree1.setBarkBeetle(b);
                                nextFreeTree2.setVal('0');
                            }
                        }
                    }
                    long waitTime = (long) (Math.random() * 45 + 5);
                    try {
                        Thread.sleep(waitTime);
                        simulation.output();
                    } catch (InterruptedException e) {
                        break;
                    }
                }

            } else {
                simulation.endAllThreads();
                System.out.println("Final forest-situation");
                simulation.output();
                simulation.statistics();
            }
        }
    }


    // getNextTrees() setzt this.nextFreeTree1 und this.nextFreeTree2 auf den nächsten Nachbarn
    private void getNextTrees() {
        List<Tree> neighbours = currHabitat.getNeighbours();
        List<Tree> allFreeNeighbours = new ArrayList<>();
        for (Tree n : neighbours) {
            if (n.getVal() == '*') {
                allFreeNeighbours.add(n);
            }
        }
        List<Tree> allNeighboursNoDuplicates = new ArrayList<>(new HashSet<>(allFreeNeighbours));
        if (allFreeNeighbours.size() >= 2) {
            Random random = new Random();
            int size = allFreeNeighbours.size() - 1;
            nextFreeTree1 = allFreeNeighbours.get(random.nextInt(size));
            allFreeNeighbours.remove(nextFreeTree1);
            size = allFreeNeighbours.size() - 1;
            if (size == 0) {
                nextFreeTree2 = allFreeNeighbours.get(0);
            } else {
                nextFreeTree2 = allFreeNeighbours.get(random.nextInt(size));
            }
        } else {
            nextFreeTree1 = null;
            nextFreeTree2 = null;
        }
    }

    // endThread() beendet einen barkBeetle Thread
    public void endThread() {
        if (barkBeetle != null) {
            barkBeetle.interrupt();
        }
    }


    /*
        Gibt einen String der alle statistischen Werte enthält zurück
    */
    @Override
    public String toString() {
        return "Bark Beetle: \nCurrent habitat: " + currHabitat.getXpos() + ", " + currHabitat.getYpos() + "\n" +
                "Generations: " + genCount + "\n";
    }
}
