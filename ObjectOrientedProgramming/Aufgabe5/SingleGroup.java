import java.util.Iterator;

public class SingleGroup<X> implements Group<X,X> {
    private LinkedList<X> container = new LinkedList<X>(); // container speichert Elemente von Typ X
    private int counter = 0; // zähler, wie oft related() aufgerufen wurde

    /*
       add fügt ein Objekt in container ein;
       x != null;
       Ein Objekt von Typ X wird der Liste hinzugefügt.
    */
    @Override
    public boolean add( X x ) {
        return container.add(x);
    }

    /*
       related prüft ob zwei Objekte gleich sind;
       x != null && x2 != null;
       Gibt true zurück, wenn x und x2 gleich sind. Andernfalls false.
    */
    @Override
    public boolean related( X x, X x2 ) {
        this.counter++;
        return x==x2;
    }

    /*
        gibt this.counter zurück;
        Positiven Wert zurückgeben;
    */
    @Override
    public int invoked( ) {
        return this.counter;
    }

    /*
        gibt Iterator zurück, der über alle Elemente von container iteriert;
        Iterator zurückgegeben
    */
    @Override
    public Iterator<X> iterator( ) {

        return new Iterator<X>() {
            private Node<X> node = container.head;
            @Override
            public boolean hasNext() {
                return node.hasNext();
            }

            @Override
            public X next() {
                if(hasNext()){
                    node = node.getNext();
                    return node.getData();
                }
                return null;
            }

            public void remove(){
                container.remove(node.getData());
            }
        };
    }

}
