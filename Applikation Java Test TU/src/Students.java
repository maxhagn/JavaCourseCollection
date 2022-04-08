public class Students {

    private class Node {
        private String data;
        private Node next;
        private Node prev;


        private Node(String data, Node prev, Node next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public String getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setData(String data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    private Node first = null;
    private Node last = null;

    public Students( int i ){ }
    public Students(){}

    public void add(String data) {
        Node current = last;
        Node newNode = new Node(data, current, null);
        last = newNode;

        if (current == null) {
            first = newNode;
        } else {
            current.setNext(newNode);
        }
    }

    public void remove(String data) {
        Node current = first;
        while (current != null) {
            if( current.getData().compareTo(data) == 0 ) {
                if ( current == first ) {
                    first = current.getNext();
                    first.setPrev(null);
                } else if ( current == last ) {
                    last = current.getPrev();
                    last.setNext(null);
                } else {
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev());
                }
            }
            current = current.getNext();
        }
    }

    public void insertBefore(String before, String newData) {
        Node current = first;
        while (current != null) {
            if( current.getData().compareTo(before) == 0 ) {
                Node newNode = new Node(newData, null, current);

                if ( current == first ) {
                    first = newNode;
                    current.setPrev(newNode);
                } else {
                    current.getPrev().setNext(newNode);
                    current.setPrev(newNode);
                }

                break;
            }
            current = current.getNext();
        }
    }

    public void insertAfter(String after, String newData) {
        Node current = first;
        while (current != null) {
            if( current.getData().compareTo(after) == 0 ) {
                Node newNode = new Node(newData, current, null);

                if ( current == last ) {
                    last = newNode;
                    current.setNext(newNode);
                } else {
                    current.getNext().setPrev(newNode);
                    newNode.setNext(current.getNext());
                    current.setNext(newNode);


                }

                break;
            }
            current = current.getNext();
        }
    }

    public void print() {
        System.out.println("Print - Ausgabe: ");
        Node current = first;
        while (current != null) {
            System.out.println( current.getData() + ". Eintrag" );
            current = current.getNext();
        }
    }

    public static void main(String[] args) {
        Students student = new Students(10);
        student.add("1");
        student.add("2");
        student.add("3");
        student.add("3");
        student.add("4");
        student.print();
        student.insertAfter("3", "10");
        student.insertBefore("1", "1000");
        student.print();
        student.add("5");
        student.add("6");
        student.add("7");
        student.add("8");
        student.print();
    }
}
