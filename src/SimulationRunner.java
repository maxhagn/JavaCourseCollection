import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SimulationRunner {
    public static void main(String[] args) {
        IndexedTree indexedTree = new IndexedTree();
        String activeCollectionPointListPath = System.getProperty("user.dir") + "/data/initial-collectors-0.csv";
        initActiveCollectionPoints(activeCollectionPointListPath, indexedTree);
        String queryListPath = System.getProperty("user.dir") + "/data/queries-0.txt";

        try (Scanner s = new Scanner(new File(queryListPath), "UTF-8"))
        {
            String name;

            while(s.hasNext())
            {
                try
                {
                    name = s.next();
                    indexedTree.handleQuery(name);
                }
                catch (Exception e)
                {
                    System.err.println("Error handling input of query file");
                    e.printStackTrace();
                    System.exit(4);
                }
            }
        }

        catch (FileNotFoundException e)
        {
            System.err.println("Couldn't find sim file");
            e.printStackTrace();
            System.exit(3);
        }

        System.out.println("Min size: ");
        System.out.println(indexedTree.getMinSize());
        System.out.println("Max size: ");
        System.out.println(indexedTree.getMaxSize());
        System.out.println("Last deactivated: ");
        System.out.println(indexedTree.getLastDeactivated());
        System.out.println("Last activated: ");
        System.out.println(indexedTree.getLastActivated());
    }

    private static void initActiveCollectionPoints(String path, IndexedTree collectionPointsTree)
    {
        try (Scanner s = new Scanner(new File(path), "UTF-8"))
        {
            String name;
            int numQueries;
            int numQueriesSinceSplit;

            s.useDelimiter("[;, \\n]");
            while(s.hasNext())
            {
                try
                {
                    name = s.next();
                    numQueries = s.nextInt();
                    numQueriesSinceSplit = s.nextInt();

                    collectionPointsTree.addNode(name, new int[]{numQueries, numQueriesSinceSplit, 0});
                }
                catch (Exception e)
                {
                    System.err.println("Some kind of error while initializing tree (probably reading from file), Stacktrace: ");
                    e.printStackTrace();
                    System.exit(2);
                }
            }

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        collectionPointsTree.treeSize();
    }
}
