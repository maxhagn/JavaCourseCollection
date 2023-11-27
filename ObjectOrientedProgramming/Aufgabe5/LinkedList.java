class Node<T> {

    private T data; // enthält die Daten eines Elements
    private Node<T> next; // hält das nächste Element

    /*
       Das Objekt Node wird instanziert.
    */
    Node(T d)
    {
        data = d;
        next = null;
    }

    /*
        Nächstes Element wird zurückgegeben.
    */
    public Node<T> getNext(){
        return this.next;
    }

    /*
        hasNext liefert einen Wahrheitswert zurück, der bestimmt ob ein nächstes Element existiert.
    */
    public boolean hasNext(){
        return this.next != null;
    }

    /*
        Daten des aktuellen Elements werden zurückgegeben.
    */
    public T getData(){
        return this.data;
    }

    /*
        setNext setzt das nächste Element auf die mitgegebene Node x.
    */
    public void setNext(Node<T> x){
        this.next = x;
    }

    /*
       setData setzt die Daten eines aktuellen Elements.
    */
    public void setData(T x){
        this.data = x;
    }

}

public class LinkedList<T> {
    Node<T> head; // enthält das erste Element einer Liste

    /*
       add fügt eines neues Element in die Liste ein;
       data != null;
       Ein neues Element wird in die Liste aufgenommen, ist der head der Liste leer wird das Element als head definiert.
    */
    public boolean add(T data)
    {
        Node<T> new_Node = new Node<T>(data);

        if (this.head == null) {
            Node<T> helper_Node = new Node<T>(null);
            this.head = helper_Node;
            this.head.setNext(new_Node);
        }
        else {
            Node<T> last = this.head;
            while (last.getNext() != null) {
                last = last.getNext();
            }

            last.setNext(new_Node);
        }
        return true;
    }

    /*
       remove entfernt ein Element aus einer Liste;
       key != null;
       das mitgegebene Objekt wird, falls es existiert, aus der Liste entfernt.
    */
    public boolean remove(T key)
    {
        Node<T> prev = new Node<>(null);
        prev.setNext(head);
        Node<T> next = head.getNext();
        Node<T> temp = head;
        boolean exists = false;
        if (head.getData() == key) {
            head = head.getNext();
            exists = true;
        }

        while (temp.getNext() != null) {
            if (String.valueOf(temp.getData()).equals(String.valueOf(key))) {
                prev.setNext(next);
                exists = true;
                break;
            }
            prev = temp;
            temp = temp.getNext();
            next = temp.getNext();
        }

        if (!exists && String.valueOf(temp.getData()).equals(String.valueOf(key))) {
            prev.setNext(null);
            exists = true;
        }
        else {
            System.out.println("Given Value is not present in linked list");
        }

        return exists;
    }
}
