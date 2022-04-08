// Objects of class 'Participations2' contain participations from several races . he implementation uses a binary search tree as
// associative data structure, using 'racer' as key; the data associated with the key is a 'Participations1' object containing
// all participations of this racer.

public class Participations2 {

    private class Node {
        private String key;
        private Participations1 data;

        private Node left;
        private Node right;

        public Node(String key, Participations1 data) {
            this.key = key;
            this.data = data;
        }

        @Override
        public String toString() {

            String currentItem = data == null ? "null" : String.valueOf(data);
            String leftItem = left == null ? "null" : left.data == null ? "null" : String.valueOf(left.data);
            String rightItem = right == null ? "null" : right.data == null ? "null" : String.valueOf(right.data);

            return String.format("MyNode(Item: %s, Left: %s, Right: %s)", currentItem, leftItem, rightItem);
        }

        public void add(Participation participation) {
            if (participation != null && key != null) {
                if (data == null) {
                    data = new Participations1();
                }
                if (participation.getRacer() != null) {
                    int compareValue = key.compareTo(participation.getRacer());
                    if (compareValue == 0) {
                        data.add(participation);
                    } else if (compareValue < 0) {
                        if (right == null) {
                            right = getNewNodeFromParticipation(participation);
                        } else {
                            right.add(participation);
                        }
                    } else {
                        if (left == null) {
                            left = getNewNodeFromParticipation(participation);
                        } else {
                            left.add(participation);
                        }
                    }
                }
            }
        }

        public void printRecursiv() {
            if (left != null) {
                left.printRecursiv();
            }
            data.print();
            if (right != null) {
                right.printRecursiv();
            }
        }

        public void printRecursiv(int x) {
            if (left != null) {
                left.printRecursiv(x);
            }
            data.print(x);
            if (right != null) {
                right.printRecursiv(x);
            }
        }

        public void printRecursiv(String x, String y) {
            if (left != null) {
                left.printRecursiv(x, y);
            }
            data.print(x, y);
            if (right != null) {
                right.printRecursiv(x, y);
            }
        }

        public Participation findRecursiv(String r) {
            if (key.equalsIgnoreCase(r)) {
                return data.lookupRacer(r);
            }
            if (left != null) {
                Participation leftValue = left.findRecursiv(r);
                if (leftValue != null)
                    return leftValue;
            }
            if (right != null) {
                return right.findRecursiv(r);
            }
            return null;
        }

        public String getKey() {
            return key;
        }

        public Participations1 getValue() {
            return data;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

    }

    private Node getNewNodeFromParticipation(Participation participation) {
        Participations1 participations1 = new Participations1();
        participations1.add(participation);
        return new Node(participation.getRacer(), participations1);
    }

    private Node root = null;

    // Constructor
    public Participations2(int n) { }
    public Participations2() { }

    // Adds p to participation2
    public void add(Participation p) {
        if (p != null) {
            if (root == null) {
                root = getNewNodeFromParticipation(p);
            } else {
                root.add(p);
            }
        }
    }
    
    // Print the entries in the following order: The participations of different racers are printed in the order given by compareTo,
    // with x being printed before y if x.compareTo(y)<0. The participations of the same racer are printed in the order of insertion.
    public void print() {
        root.printRecursiv();
    }
    
    // print the entries with bibnumber<=x in the same order as used by print() in Participations2
    void print(int x) {
        if (root != null) root.printRecursiv(x);
    }

    public void print(String x, String y) {
        if (root != null) root.printRecursiv(x, y);
    }

    // first out from participation2 where the 'racer' equals 'r'
    public Participation lookupRacer(String r) {
        if (root != null) {
            return root.findRecursiv(r);
        } else {
            return null;
        }
    }

    public static void main(String[] args) { }
}
