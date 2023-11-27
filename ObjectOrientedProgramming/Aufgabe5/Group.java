import java.util.Iterator;

public interface Group<X,Y> extends Iterable<X>, Relation<X,Y> {
    /*
     * von Iterable, Relation
     * Alle Klassen die das Interface Group implementieren müssen die Iterable und Relation Funktionalität implementieren
     */

    /**
     * Add Element to Group
     *
     * @return if the element was successfully added
     */
    public boolean add(X x);

    /**
     * Checks if two Elements are related to each other
     *
     * @return if the elements are related to each other
     */
    @Override
    public boolean related(X x, Y y);

    /**
     * Iterator returns an iterator of the group
     *
     * @return an instance of iterator
     */
    @Override
    public Iterator<X> iterator();

}
