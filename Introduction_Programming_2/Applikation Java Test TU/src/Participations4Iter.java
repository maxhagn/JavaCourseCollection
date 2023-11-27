public class Participations4Iter implements PartIterator {
    // This class implements an iterator for the class Participations4

    private Participations4 data;
    private int next = 0;
    private PartIterator current;

    public Participations4Iter(Participations4 data) {
        this.data = data;
        current = getNext();
    }
    
    // returns the next 'Participation' object in the container that
    // 'this' iterates over.
    @Override
    public Participation next() {
        Participation next = null;
        if (current != null) {
            next = current.next();
            if (!current.hasNext()) {
                current = getNext();
            }
        }
        return next;
    }

    // returns true if there is another 'Participation' object that
    // this.next() can return.
    @Override
    public boolean hasNext() {
        return current != null;
    }

    private PartIterator getNext() {
        while (next < data.getHashTable().length && data.getHashTable()[next] == null && !data.getHashTable()[next].iterator().hasNext())
            next++;
        if (next > data.getHashTable().length - 1) {
            return null;
        } else {
            PartIterator nextIterator = data.getHashTable()[next].iterator();
            next++;
            return nextIterator;
        }
    }
}
