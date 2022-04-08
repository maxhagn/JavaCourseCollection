import java.lang.reflect.Array;
import java.util.Arrays;

public class Startlist {

    private Participation[] participations;


    // Constructor
    public Startlist(int raceCount) {
        this.participations = new Participation[raceCount];
    }

    // adds p to startlist
    public void add(Participation p) {
        if (this.participations[this.participations.length - 1] != null) {
            this.participations = Arrays.copyOf(this.participations, this.participations.length + 1);
        }

        for (int i = 0; i < this.participations.length; i++) {
            if (this.participations[i] == null) {
                this.participations[i] = p;
                break;
            }
        }
    }

    // Helper, prints the in the parameter given array
    private void MyPrintArray(Participation[] clean) {
        for (int i = 0; i < clean.length; i++) {
            clean[i].print();
            System.out.println(" ");
        }

    }

    // Helper, sorts the array with insertion sort
    private void MySort() {
        Participation tmp;
        for (int i = 1; i < this.participations.length; i++) {
            if (this.participations[i] != null) {
                for (int j = i; j > 0; j--) {
                    if (this.participations[j].getBibnumber() < this.participations[j - 1].getBibnumber()) {
                        tmp = this.participations[j];
                        this.participations[j] = this.participations[j - 1];
                        this.participations[j - 1] = tmp;
                    }
                }
            }
        }
    }

    // Helper, remove null from array to prevent null pointer exception
    private Participation[] MyRemoveNull() {
        int realLength = 0;

        for (int i = 0; i < this.participations.length; i++) {
            if (this.participations[i] == null) {
                realLength = i;
                break;
            }
        }

        return Arrays.copyOf(this.participations, realLength);
    }

    // Helper, swap two participatios
    private Participation[] MySwap(Participation[] input, int first, int last) {
        Participation[] result = Arrays.copyOf(input, input.length);
        Participation tmp = result[first];
        result[first] = result[last];
        result[last] = tmp;

        return result;
    }

    // Helper, calculates factorial
    private double MyFactorial(int num) {
        double result = 1;

        for (int i = num; i > 0; i--) {
            result *= i;
        }

        return result;
    }

    // Print the filled entries in an arbitrary order
    public void print() {
        System.out.println("__________________________ \n print arbitrary \n");
        MyPrintArray(MyRemoveNull());
    }


    // Print the filled entries in the order of increasing bibnumbers
    public void printOrdered() {
        System.out.println("__________________________ \n print ordered \n");
        MySort();
        MyPrintArray(MyRemoveNull());
    }

    // Print all the permutations of the start list, with each permutation followed by an empty line.
    public void printPermutations() {
        System.out.println("__________________________ \n print permutations \n");
        printPermutations(0, MyRemoveNull());
    }

    // void printPermutations with parameter
    private void printPermutations(double index, Participation[] last) {
        double fact = MyFactorial(MyRemoveNull().length);

        if ((int) fact == index) {
            return;
        } else {
            System.out.println("Permutation " + (int) (index + 1) + " \n ");
            last = MySwap(last, ((int) ((index) % MyRemoveNull().length)), (int) ((index + 1) % MyRemoveNull().length));
            MyPrintArray(last);

        }

        printPermutations(++index, last);

    }

    public static void main(String[] args) { }
}
