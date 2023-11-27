import java.util.LinkedList;

public class Test {

    /*
     * ANMERKUNG:
     * Gruppennummer: K1
     * Aufgabenteilung:
     * - Grundstruktur, Zusicherungen (Christoph Diepolder)
     * - Grundstruktur, Simulation (Pascal Poremba)
     * - Test.java, Zusicherungen (Max Hagn)
     */

    public static void main(String[] args) {
        long waitTime = 1500;

        // ANMERKUNG: Testcase 1

        Forest forest_large = new Forest( createForestWithTrees( 12,14 ) );
        Simulation simulation_3 = new Simulation( forest_large );


        LinkedList<AntBeetle> antBeetleList_3 = new LinkedList<>(  );
        antBeetleList_3.add( new AntBeetle( simulation_3, 3,3) );
        LinkedList<BarkBeetle> barkBeetleList_3 = new LinkedList<>(  );
        barkBeetleList_3.add( new BarkBeetle( simulation_3, 9,4, 0) );
        barkBeetleList_3.add( new BarkBeetle( simulation_3, 11,9, 0) );
        barkBeetleList_3.add( new BarkBeetle( simulation_3, 4,3, 0) );

        forest_large.output();
        simulation_3.startSimulation( antBeetleList_3, barkBeetleList_3 );

        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            return;
        }


        // ANMERKUNG: Testcase 2
        Forest forest_medium = new Forest( createForestWithTrees( 10,10 ) );

        Simulation simulation_2 = new Simulation( forest_medium );


        LinkedList<AntBeetle> antBeetleList_2 = new LinkedList<>(  );
        antBeetleList_2.add( new AntBeetle( simulation_2, 5,3) );
        antBeetleList_2.add( new AntBeetle( simulation_2, 6,4) );
        LinkedList<BarkBeetle> barkBeetleList_2 = new LinkedList<>(  );
        barkBeetleList_2.add( new BarkBeetle( simulation_2, 6,5, 0) );

        forest_medium.output();
        simulation_2.startSimulation( antBeetleList_2, barkBeetleList_2 );

        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            return;
        }

        // ANMERKUNG: Testcase 3 starting with high generation
        Forest forest_small = new Forest( createForestWithTrees( 6,9 ) );

        Simulation simulation_1 = new Simulation( forest_small );


        LinkedList<AntBeetle> antBeetleList_1 = new LinkedList<>(  );
        antBeetleList_1.add( new AntBeetle( simulation_1, 3,3) );
        antBeetleList_1.add( new AntBeetle( simulation_1, 4,5) );

        LinkedList<BarkBeetle> barkBeetleList_1 = new LinkedList<>(  );
        barkBeetleList_1.add( new BarkBeetle( simulation_1, 2,2,26) );

        simulation_1.startSimulation( antBeetleList_1, barkBeetleList_1 );


    }

    // createForestWithTrees() erstellt einen Wald der eine bestimmte Anzahl an Bäumen enthält;
    // height >= 0 && height <= 40 &&  width >= 0 && width <= 40;
    // Ein char[][] das mit Bäumen befüllt wurde, wurde zurückgegeben
    private static char[][] createForestWithTrees( int height, int width ){
        char[][] result = new char[height][width];
        char tree = '*';

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result[i][j] = tree;
            }
        }

        return result;
    }
}
