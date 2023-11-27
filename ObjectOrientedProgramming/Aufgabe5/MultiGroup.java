import java.util.Iterator;

public class MultiGroup<X,Y> implements Group<X,Y> {

    private LinkedList<X> container = new LinkedList<X>(); // container speichert Elemente von Typ X
    private final Group<Y,?> a; // gruppe von Typ Group<Y,?>
    private final Relation<X,Y> r; // relation von Typ Relation<X,Y>

    /*
       Das Objekt MultiGroup wird instanziert und mit angegebenen Daten befüllt;
       a != null && r != null;
       Ein Objekt von MultiGroup wird instanziert.
    */
    public MultiGroup( Group<Y,?> a, Relation<X,Y> r ) {
        this.a = a;
        this.r = r;
    }

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
       x != null && y != null;
       Ein Wahrheitswert, der bestimmt ob zwei Objekt verwandt sind, wird zurückgegeben.
    */
    @Override
    public boolean related( X x, Y y ) {
        return r.related(x,y);
    }

    /*
        gibt Iterator zurück, der über alle Elemente von container iteriert, welche mit mindestens einem Element aus dem Container von a eine Relation r hat;
        Iterator zurückgegeben.
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
                //Iterator von a
                Y tmp = null;
                while(hasNext()){
                    node = node.getNext();
                    Iterator<Y> ia = a.iterator();
                    while(ia.hasNext()){
                        tmp = ia.next();
                        if(tmp != null){
                            if(related(node.getData(), tmp)) {
                                return node.getData();
                            }
                        }
                    }
                }
                return null;
            }

            public void remove(){
                container.remove(node.getData());
            }
        };
    }

    /*
        invoked gibt Funktionsrückgabe von r.invoked zurück;
        Positiven Wert zurückgeben;
    */
    @Override
    public int invoked( ) {

        return r.invoked();
    }

}
