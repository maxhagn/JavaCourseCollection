public class Aufgabe5 {
    public static void main(String[] args) {
        printNumber(234l);
        System.out.println();
        printNumber(12005l);

    }

    public static void printNumber (Long n) {
        if (n > 1000) {
            printNumber(n / 1000);
            System.out.print("," + n % 1000);
        } else {
            System.out.print(n);
        }
    }
}
