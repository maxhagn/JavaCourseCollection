// Objects of class 'PartTreeNull' represent an empty node in a binary
// search tree, using 'racer' as key.
// Variables and expressions of type 'PartTreeNodable' and its subtypes
// are never null.  Instead, an empty subtree is represented by a
// 'PartTreeNull' object.

public class PartTreeNull implements PartTreeNodable {

    // Constructor
    private PartTreeNull() {}

    // 'NIL' is the only object of type 'PartTreeNull'.
    public static final PartTreeNull NIL = new PartTreeNull();


    @Override
    public PartTreeNodable add(Participation p) {
        PartTreeBinary newNode = new PartTreeBinary(p.getRacer());
        newNode.add(p);
        return newNode;
    }

    @Override
    public void print() { }

    @Override
    public Participation lookupRacer(String r) { return null; }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public String toString(String x, String y) {
        return "";
    }

    public static void main(String[] args) { }
}
