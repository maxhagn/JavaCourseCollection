import java.util.Arrays;

/*
    Aufgabe 1) Rekursion - Diverse Methoden
*/
public class Aufgabe1 {

    private static int printAndCountNumbers(int x, int y) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        System.out.print(x + " ");
        if (x < y) {
            return x + printAndCountNumbers(x + 1, y);
        } else {
            return x;
        }
    }

    private static int printAndCountNumbersDes(int x, int y) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        System.out.print(y + " ");
        if (y > x) {
            return y + printAndCountNumbersDes(x, y - 1);
        } else {
            return y;
        }
    }

    private static int calcMaxSumTriple(int[] array, int i) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        if (array != null && array.length > 2 && i <= array.length) {
            if (i == array.length - 3) {
                return array[i] + array[i + 1] + array[i + 2];
            }

            int firstPosition = array[i] + array[i + 1] + array[i + 2];
            int secondPosition = calcMaxSumTriple(array, i + 1);

            if (firstPosition > secondPosition) {
                return firstPosition;
            } else {
                return secondPosition;
            }
        } else {
            return -1;
        }
    }

    private static int calcMaxSumTriple(int[] array) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        if (array != null && array.length > 2) {
            if (array.length == 3) {
                return array[0] + array[1] + array[2];
            }

            int firstPosition = array[0] + array[1] + array[2];
            int secondPosition = calcMaxSumTriple(Arrays.copyOfRange(array, 1, array.length));

            if (firstPosition > secondPosition) {
                return firstPosition;
            } else {
                return secondPosition;
            }
        } else {
            return -1;
        }
    }

    private static int findMaxDiff(int[] array, int i) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        if (array != null && array.length > 1) {
            if (i == 1) {
                return Math.abs(array[i] - array[i - 1]);
            }

            int firstPosition = Math.abs(array[i] - array[i - 1]);
            int secondPosition = findMaxDiff(array, i - 1);

            if (firstPosition > secondPosition) {
                return firstPosition;
            } else {
                return secondPosition;
            }
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println("printAndCountNumbers");
        System.out.println(printAndCountNumbers(10, 20));
        System.out.println();

        System.out.println("printAndCountNumbersDes");
        System.out.println(printAndCountNumbersDes(5, 15));
        System.out.println();

        System.out.println("calcMaxSumTriple");
        System.out.println(calcMaxSumTriple(new int[]{1, 4, 8, 3, 7, 3, 8, 2, 7, 4, 3}, 0));
        System.out.println(calcMaxSumTriple(new int[]{1, 4, 8, 3, 7, 1, 8, 7, 3, 4, 3}, 0));
        System.out.println(calcMaxSumTriple(new int[]{7, 5, 3}, 0));
        System.out.println();

        System.out.println(calcMaxSumTriple(new int[]{1, 4, 8, 3, 7, 3, 8, 2, 7, 4, 3}));
        System.out.println(calcMaxSumTriple(new int[]{1, 4, 8, 3, 7, 1, 8, 7, 3, 4, 3}));
        System.out.println(calcMaxSumTriple(new int[]{7, 5, 3}));
        System.out.println();

        System.out.println("findMaxDiff");
        System.out.println(findMaxDiff(new int[]{5, 50, 7, 1, 20}, 4));
        System.out.println(findMaxDiff(new int[]{5, 8, 7, 1, 20}, 2));
        System.out.println(findMaxDiff(new int[]{5, 14, 5, 1, 2, 1, 20}, 6));
        System.out.println(findMaxDiff(new int[]{1, 1, 1, 1, 1, 1, 1}, 6));
        System.out.println(findMaxDiff(new int[]{2, 4}, 1));
    }
}


