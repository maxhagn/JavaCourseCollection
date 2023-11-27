import java.util.Collection;
import java.util.Collections;

public class LinkedList {
    Node head; // enthält das erste Element einer Liste

    /*
       add fügt eines neues Element in die Liste ein;
       data != null;
       Ein neues Element wird in die Liste aufgenommen, ist der head der Liste leer wird das Element als head definiert.
    */
    public boolean add(Object data)
    {
        Node new_Node = new Node(data);

        if (this.head == null) {
            Node helper_Node = new Node(null);
            this.head = helper_Node;
            this.head.setNext(new_Node);
        }
        else {
            Node last = this.head;
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
    public boolean remove(Object key)
    {
        Node prev = new Node(null);
        prev.setNext(head);
        Node next = head.getNext();
        Node temp = head;
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

        return exists;
    }
}
