import java.util.Hashtable;

public class Participations4 implements PartIterable {
    // Objects of class 'Participations3' contain participations from
    // several races.  The implementation uses a hash table as follows:
    //   - the tuple ('racer','race') is the key (i.e., what the 'equals'
    //     method of 'Participation' implements);
    //   - the values of the hash table are 'Participations' objects.
    // However, the array of the hash table contains 'Participations1'
    // objects.  'Participation' objects with the same index in the
    // array are inserted in the same 'Participations1' object.
    // This variant of hash table implementation is known as separate chaining.

    // This implementation does not use classes from the Collections
    // Framework (e.g., HashMap)
    // <https://docs.oracle.com/javase/8/docs/technotes/guides/collections/overview.html>.

    // Assignment 5.2

    private Participations1[] hashTable;

    public Participations1[] getHashTable() {
        return hashTable;
    }

    // Creates an empty hash table with room for n 'Participation1' objects
    public Participations4(int n) {
        hashTable = new Participations1[n];

    }

    @Override
    public PartIterator iterator() {
        return new Participations4Iter(this);
    }

    // Adds p to 'this'.
    public void add(Participation p) {
        int index = Math.abs(p.hashCode()) % hashTable.length;
        if (hashTable[index] == null) {
            Participations1 newParticipations = new Participations1();
            newParticipations.add(p);
            hashTable[index] = newParticipations;
        } else {
            hashTable[index].add(p);
        }
    }
    
    // returns a string that contains the participations in arbitrary
    // order, each participation in the format produced by print() in
    // Participation, followed by a newline.
    @Override
    public String toString() {
        String result = "";
        for (Participations1 current : hashTable) {
            if (current != null)
                result += current.toString();
        }
        return result;
    }

    // Print the participations in the format produced by 'toString()'.
    public void print() {
        System.out.println(toString());
    }

    // Returns the first participation (the one that was inserted
    // earliest) in 'this' that equals() 'p'.  If there is no such
    // participation, return null.
    public Participation lookupRacer(Participation p) {
        int hashValue = Math.abs(p.hashCode()) % hashTable.length;
        if (hashTable[hashValue] != null)
            return hashTable[hashValue].first();
        return null;
    }

    // Fragen:

    // 1) Wenn in so eine Hash-Tabelle mit n 'Participations1'-Listen
    // m unterschiedliche Participation-Einträge eingefügt werden, wie
    // gross ist die durchschnittliche Länge l der Listen?  Warum kann
    // der durchschnittliche Zugriff deutlich länger dauern als ein
    // durchschnittlicher Zugriff auf eine Liste der Länge l plus der
    // Aufwand, bis die Liste erreicht ist?  Überlegen Sie sich einen
    // besonders langsamen Fall, und wann der garantiert auftritt.
    // Worauf sollte man daher bei der Verwendung von Hash-Tabellen
    // achten?
    
    // 2) Vergleichen Sie diese Art der Kollisionsbehandlung mit der im
    // Skriptum gezeigten. Was sind die Vor- und Nachteile der Methoden?

    // 3) Vergleichen Sie die Klassen
    // 'Participations' bis 'Participations4'.  Was sind die
    // Gemeinsamkeiten und Unterschiede im Verhalten (nicht in der
    // Implementierung). Überlegen Sie sich, wie Sie diese drei
    // Klassen in einer Typhierarchie organisieren würden; welche
    // Typen übernehmen welche Eigenschaften von den übergeordneten
    // Typen, und welche fügen sie hinzu?


    // This method is only for testing.
    // Alternatively, you can put the tests in additional classes.
    public static void main(String[] args) {

        Participations4 participations4=new Participations4(10);
        participations4.add(new Participation("Lienz 2011 Ladies' Slalom 1", "R1", 2));
        participations4.add(new Participation("Lienz 2012 Ladies' Slalom 2", "R2", 3));
        participations4.add(new Participation("Lienz 2013 Ladies' Slalom 3", "R3", 1));
        participations4.add(new Participation("Lienz 2013 Ladies' Slalom 4", "R4", 4));
        participations4.add(new Participation("Lienz 2013 Ladies' Slalom 5", "R5", 5));
        participations4.add(new Participation("Lienz 2013 Ladies' Slalom 6", "R6", 6));
        participations4.add(new Participation("Lienz 2013 Ladies' Slalom 7", "R7", 7));

        System.out.println("__________________________ \n print the added participations \n");
        participations4.print();

        System.out.println("__________________________ \n print racer R5 \n");
        System.out.println(participations4.lookupRacer(new Participation("Lienz 2013 Ladies' Slalom 5", "R5", 5)));

        System.out.println("__________________________ \n print with iterator \n");
        PartIterator iterator=participations4.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
