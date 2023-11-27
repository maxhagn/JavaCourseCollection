class Node {

    private Object data; // enthält die Daten eines Elements
    private Node next; // hält das nächste Element

    /*
       Das Objekt Node wird instanziert.
    */
    Node(Object d)
    {
        data = d;
        next = null;
    }

    /*
        Nächstes Element wird zurückgegeben.
    */
    public Node getNext(){
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
    public Object getData(){
        return this.data;
    }

    /*
        setNext setzt das nächste Element auf die mitgegebene Node x.
    */
    public void setNext(Node x){
        this.next = x;
    }

    /*
       setData setzt die Daten eines aktuellen Elements.
    */
    public void setData(Object x){
        this.data = x;
    }

}
