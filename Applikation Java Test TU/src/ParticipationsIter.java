import java.util.Iterator;

public class ParticipationsIter implements PartIterator {
    // This class implements an iterator for the class Participations
    
    // TODO: Introduce (private) object variables as needed.
    private Participations data;
    private int next;

    public ParticipationsIter(Participations data) {
        this.data = data;
        this.next = getNext();
    }

    // returns the next 'Participation' object in the container that
    // 'this' iterates over.
    @Override
    public Participation next() {
        Participation result = null;
        if (next < data.getParticipations().length) {
            result = data.getParticipations()[next];
            next++;
            next = getNext();
        }
        return result;
    }

    // returns true if there is another 'Participation' object that
    // this.next() can return.
    @Override
    public boolean hasNext() {
        return next < data.getParticipations().length;
    }

    private int getNext() {
        while (next < data.getParticipations().length && data.getParticipations()[next] == null) {
            next++;
        }

        return next;
    }
}
