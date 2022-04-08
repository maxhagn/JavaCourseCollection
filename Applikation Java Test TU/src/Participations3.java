// Objects of class 'Participations3' contain participations from several races. The implementation uses a binary search tree as
// associative data structure, using nodes that implement 'PartTreeNodable'.  The tree uses 'racer' as key, and a 'Participations1' object as
// value (as does Participations2).

public class Participations3 {

    private PartTreeNodable root = PartTreeNull.NIL;

    // Constructor
    public Participations3() { }

    // Adds p to Participation3
    public void add(Participation p) {
        root = root.add(p);
    }
    
    // Print the participations in the same order and format as in Participations2
    public void print() {
        root.print();
    }

    // first added out from participation3 where the 'racer' equals 'r'
    public Participation lookupRacer(String r) { return root.lookupRacer(r); }

    // returns a string same as print() --> In other words, print() can be implemented by just outputting the string produced by toString().
    @Override
    public String toString() {
        //  related classes as appropriate; observe the restrictions of PartTreeNodable and the classes that implement it.
        return root.toString();
    }

    // Ad Hoc Method, Checks racer and race smaller than given ones
    public String toString(String x, String y) {
        return root.toString(x, y);
    }

    public static void main(String[] args) { }
}
