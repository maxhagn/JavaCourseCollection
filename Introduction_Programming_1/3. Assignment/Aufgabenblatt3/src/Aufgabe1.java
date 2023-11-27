/*
    Aufgabe 1) Methoden rufen Methoden auf und überladen von Methoden
*/
public class Aufgabe1 {

    public static void main(String[] args) {
        // Test Methods
        System.out.println(calcProduct(1, 2));
        System.out.println(calcProduct(2.0001, 40.0001));
        System.out.println(calcProduct(4, 8));
        System.out.println(calcProduct(4.0001, 8));

        System.out.println(mult(4, 2));
        System.out.println(multNew(4, 2));

        System.out.println(mult(8, 3));
        System.out.println(multNew(8, 3));

    }

    // TODO: Implementieren Sie hier Ihre Lösung für "calcProduct"
    // Calc Product Methoden
    public static int calcProduct(int a, int b) {
        return a * b;
    }

    public static double calcProduct(double a, double b) {
        return a * b;
    }


    public static int mult(int m, int n) {
        int result = 0;
        while (n > 0) {
            result += m;
            n--;
        }
        return result;
    }

    // TODO: Implementieren Sie hier Ihre Lösung für die Methode "multNew"
    // neue mult - Methode
    public static int multNew(int m, int n) {
        int result = 0;

        while (status(n)) {
            result = add(result, m);
            n = subOne(n);
        }

        return result;
    }

    // Hilfs-Methoden mult
    public static boolean status(int n) {
        return n > 0;
    }

    public static int add(int result, int m) {
        return result + m;
    }

    public static int subOne(int n) {
        return n - 1;
    }

    /*
        Zusatzfragen

        1. Warum koennnen in einem Programm zwei Methoden mit gleichem Namen vorkommen?
           Das System nennt sich Überladen. Typliste der formalen Parameter muss aber unterschiedlich sein (mindestens eine der folgenden Bedingungen)
                − Unterschiedliche Typen
                − Unterschiedliche Reihenfolge der Typen
                − Unterschiedliche Anzahl der Typen
        2. Was passiert, wenn Sie die Methode calcProduct mit einem int-Wert und einem double- Wert aufrufen?
           Der Integer Wert wird zu einem Double, da der Bit Bereich des Doubles groeßer ist und ein int locker in
           ein Double hineinpasst und konvertiert (gecastet) werden kann

    */
}
