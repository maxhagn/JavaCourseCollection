// Objects of class 'Participations1' contain participations from several races.  The implementation uses a linked list.

public class Participations1 implements PartIterable {
    // Node
    private class Node<T> {
        private T data;
        private Node<T> child;
        private Node<T> parent;

        private Node(T data, Node<T> parent, Node<T> child) {
            this.data = data;
            this.child = child;
            this.parent = parent;
        }

        @Override
        public String toString() {
            String currentItem = data == null ? "null" : String.valueOf(data);
            String nextItem = child == null ? "null" : child.data == null ? "null" : String.valueOf(child.data);
            String previousItem = parent == null ? "null" : parent.data == null ? "null" : String.valueOf(parent.data);
            return String.format("Node(Item: %s, Next: %s, Previous: %s)", currentItem, nextItem, previousItem);
        }

        private T getData() {
            return data;
        }

        private Node<T> getChild() {
            return child;
        }

        private Node<T> getNext() { return child.child; }

        private void setNext(Node<T> next) { this.child.child = next; }

        private void setChild(Node<T> child) {
            this.child = child;
        }

    }


    // Set first and last Node to null
    private Node<Participation> first = null;
    private Node<Participation> last = null;

    // Constructor
    public Participations1(int n) { }
    public Participations1() { }



    @Override
    public PartIterator iterator() {
        return new Participations1Iterator(first);
    }

    class Participations1Iterator implements PartIterator {

        private Node<Participation> listNode;

        public Participations1Iterator(Node<Participation> listNode) {
            this.listNode = listNode;
        }

        @Override
        public Participation next() {
            if(listNode == null)
                return null;
            Participation result = listNode.data;
            listNode = listNode.child;
            return result;
        }

        @Override
        public boolean hasNext() {
            return listNode != null;
        }
    }



    // Adds p to participation1
    public void add(Participation p) {
        Node<Participation> current = last;
        Node<Participation> newNode = new Node<>(p, current, null);
        last = newNode;

        if (current == null) {
            first = newNode;
        } else {
            current.setChild(newNode);
            current.child = first;
        }
    }
    
    // Print the entries in the order of insertion
    public void print() {
        Node<Participation> current = first;

        while (current != null) {
            if (current.getData() != null) {
                current.getData().print();
                System.out.println();
            }

            current = current.getChild();
        }
    }

    // Last out from participation1 where the 'racer' equals 'r',  No? --> null
    public Participation lookupRacer(String r) {
        Node<Participation> foundParticipation = lookupRacerNode(r);
        if (foundParticipation == null) {
            return null;
        } else {
            return foundParticipation.getData();
        }
    }

    private Node<Participation> lookupRacerNode(String r) {
        Node<Participation> currentNode = first;
        while (currentNode != null) {
            if (currentNode.getData() != null && currentNode.getData().getRacer().equals(r)) {
                return currentNode;
            }
            currentNode = currentNode.getChild();
        }
        return null;
    }

    // Last out from participation1, No? --> null
    public Participation first() {
        Node<Participation> current = first;

        if ( current == null ) {
            return null;
        } else {
            return current.getData();
        }
    }

    // print the entries with bibnumber<=x in the order of insertion
    void print(int x) {
        Node<Participation> current = first;

        if (current != null) {
            while (current != null) {
                if (current.getData() != null && current.getData().getBibnumber() <= x) {
                    current.getData().print();
                    System.out.println();
                }
                current = current.getChild();
            }
        }
    }

    void print(String x, String y) {
        Node<Participation> currentNode = first;
        while (currentNode != null) {
            if (currentNode.getData() != null && currentNode.getData().getRace().compareTo(x) < 0 && currentNode.getData().getRacer().compareTo(y) < 0) {
                currentNode.getData().print();
                System.out.println();
            }
            currentNode = currentNode.getChild();
        }
    }

    //Ad Hoc Method, print the entries with bibnumber < y and racer <= y
    void print(String x, int y){
        Node<Participation> current = first;

        if (current != null) {
            while (current != null) {
                if (current.getData() != null && current.getData().getBibnumber() < y && current.getData().getRacer().compareTo(x) <= 0) {
                    current.getData().print();
                    System.out.println();
                }
                current = current.getChild();
            }
        }
    }

    // Insert p immediately after the last node in participation1 where race is equal to r, no? --> insert before first
    public void addAfter(String r, Participation p) {
        Node<Participation> add = new Node<>(p, last, null);
        Node<Participation> current = first;
        Node<Participation> found = null;
        Node<Participation> addParent = null;

        while (current != null) {
            if (current.data.getRace().compareTo(r) == 0) {
                found = current;
            }
            current = current.getChild();
        }

        if (found == null) {
            addParent = first;
            first = add;
            first.setChild(addParent);
        } else {
            addParent = found.getChild();
            found.setChild(add);
            found.setNext(addParent);
        }
    }

    // Insert p immediately before the first in participation1 where race is equal to r, no? --> insert after last
    public void addBefore(String r, Participation p) {
        Node<Participation> add = new Node<>(p, last, null);
        Node<Participation> current = first;

        while (current.getChild() != null) {
            if (current.getChild().data.getRace().compareTo(r) == 0) {
                break;
            }
            current = current.getChild();
        }

        if (current.getChild() == null) {
            this.add(p);
        } else {
            add.setChild(current.getChild());
            current.setChild(add);

        }
    }

    // Delete every entry in participation1 where race is equal to r.
    public void remove(String r) {
        Node<Participation> current = first;

        while (current.getChild() != null) {
            if (current.getChild().data.getRace().compareTo(r) == 0) {
                if (current.getChild() == null) return;
                if (current.getNext() == null) {
                    current.setChild(null);
                } else {
                    current.setChild(current.getNext());
                }
            }
            current = current.getChild();
        }
    }

    // Converts Participation to String for call in PartTreeBinary
    @Override
    public String toString() {
        String result = "";
        Node<Participation> current = first;

        while (current != null) {
            if (current.getData() != null) {
                result += String.format("%s \n", current.getData().toString());
            }
            current = current.getChild();
        }
        return result;
    }

    // Ad Hoc Method, Converts Participation to String for call in PartTreeBinary where race < x and racer < y
    public String toString(String x, String y) {
        String result = "";
        Node<Participation> current = first;

        while (current != null) {
            if ( current.getData().getRace().compareTo(x) < 0 && current.getData().getRacer().compareTo(y) < 0) {
                result += current.getData().getBibnumber() + " " + current.getData().getRacer() + " (" + current.getData().getRace() + ") \n\n";
            }

            current = current.getChild();
        }

        return result;
    }

    public static void main(String[] args) { }
}
