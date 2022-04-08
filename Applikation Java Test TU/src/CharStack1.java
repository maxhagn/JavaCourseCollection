// Objects of CharStack1 are stacks holding chars.  The stack is
// implemented using a linked list.

public class CharStack1 {

    private class Node<T> {
        private T data;
        private Node<T> child;
        private Node<T> parent;

        private Node(T data, Node<T> parent, Node<T> child) {
            this.data = data;
            this.child = child;
            this.parent = parent;
        }

        public T getData() {
            return data;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }

        public Node<T> getChild() {
            return child;
        }
    }

    private int size = 0;
    private Node<Character> first = null;
    private Node<Character> last = null;

    // Constructor
    public CharStack1() { }

    // Push c on charstack1
    public void push(char c) {
        Node<Character> current = first;
        Node<Character> newNode = new Node<>(c, null, current);
        first = newNode;

        if (current == null) {
            last = newNode;
        } else {
            current.setParent(newNode);
        }

        size++;
    }

    // Pop the most recent character that was pushed, but has not been popped yet
    public char pop() {
        Node<Character> current = first;
        if (current == null) {
            return ' ';
        }

        Character data = current.getData();
        Node<Character> child = current.getChild();
        first = child;
        if (child == null) {
            last = null;
        } else {
            child.setParent(null);
        }

        size--;
        return data;
    }

    // returns true if all characters pushed on charstack1 have been popped.
    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) { }
}
