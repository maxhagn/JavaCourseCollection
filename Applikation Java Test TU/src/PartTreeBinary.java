// An object of class 'PartTreeBinary' represents a nonempty node
// in a binary search tree, using 'racer' as key.
// Variables and expressions of type 'PartTreeNodable' and its
// subtypes are never null.  Instead, an empty subtree is
// represented by a 'PartTreeNull' object.

public class PartTreeBinary implements PartTreeNodable {

    private String key;
    private Participations1 data;

    private PartTreeNodable left = PartTreeNull.NIL;
    private PartTreeNodable right = PartTreeNull.NIL;

    public PartTreeBinary(String key) {
        this.key = key;
        this.data = new Participations1();
    }

    @Override
    public PartTreeNodable add(Participation p) {
        if (p != null && key != null) {
            if (p.getRacer() != null) {
                if (key.compareTo(p.getRacer()) > 0) {
                    left = left.add(p);
                } else if (key.compareTo(p.getRacer()) == 0) {
                    data.add(p);
                } else {
                    right = right.add(p);
                }
            }
        }

        return this;
    }

    @Override
    public void print() {
        System.out.println(toString());
    }

    @Override
    public Participation lookupRacer(String r) {
        if (key.equalsIgnoreCase(r)) {
            return data.lookupRacer(r);
        }

        Participation leftValue = left.lookupRacer(r);
        if (leftValue != null) return leftValue;

        return right.lookupRacer(r);
    }

    @Override
    public String toString() {
        return String.format("%s%s%s", left.toString(), data.toString(), right.toString());
    }

    @Override
    public String toString(String x, String y) { return String.format("%s%s%s", left.toString(x, y), data.toString(x, y), right.toString(x, y)); }

    public static void main(String[] args) { }
}
