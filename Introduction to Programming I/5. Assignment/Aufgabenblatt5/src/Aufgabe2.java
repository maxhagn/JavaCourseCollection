/*
    Aufgabe 2) Rekursion mit Strings
*/
public class Aufgabe2 {

    private static String insertIndex(String s) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        if (s != null) {
            if (s.length() == 0) {
                return s;

            } else if (s.length() == 1) {
                return s.length() - 1 + s;

            } else {
                return insertIndex(s.substring(0, s.length() - 1)) + (s.length() - 1) + s.charAt(s.length() - 1);

            }
        }
        return "Falsche Eingabe!";
    }

    private static String mixStrings(String s1, String s2) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        if (s1 != null && s2 != null) {
            if (s1.length() == 0) {
                return s1;
            }

            int length = s1.length();

            while (length > s2.length()) {
                length -= s2.length();
            }

            return mixStrings(s1.substring(0, s1.length() - 1), s2) + s1.substring(s1.length() - 1) + s2.charAt(length - 1);

        } else {
            return "Falsche Eingabe!";
        }
    }

    private static String shiftMinCharLeft(String s) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        if (s != null) {
            if (s.length() == 0) {
                return "";
            }

            if (s.length() == 1) {
                return s;
            }

            String previous = shiftMinCharLeft(s.substring(0, s.length() - 1));

            if (s.charAt(s.length() - 1) < previous.charAt(0)) {
                return s.charAt(s.length() - 1) + s.substring(0, s.length() - 1);
            } else {
                return previous + s.charAt(s.length() - 1);
            }


        } else {
            return "Falsche Eingabe!";
        }
    }

    public static void main(String[] args) {
        System.out.println("insertIndex");
        System.out.println(insertIndex("Hallo"));
        System.out.println(insertIndex("Fahrkarten!"));
        System.out.println(insertIndex(""));
        System.out.println();

        System.out.println("mixStrings");
        System.out.println(mixStrings("GROSS", "klein"));
        System.out.println(mixStrings("ABC", "klein"));
        System.out.println(mixStrings("GROESSER", "klein"));
        System.out.println();

        System.out.println("shiftMinCharLeft");
        System.out.println(shiftMinCharLeft("xdbycfjadfmk"));
        System.out.println(shiftMinCharLeft("bcdefghijklmnoa"));
        System.out.println(shiftMinCharLeft(""));

        a(3);
    }

    public static void a(int n) {
        if (n >= 0) {
            b(n - 1);
            System.out.print("a");
            a(n - 2);
        }
    }

    public static void b(int n) {
        if (n >= 0) {
            b(n - 2);
            System.out.print("b");
            c(n);
        }
    }

    public static void c(int n) {
        if (n >= 0) {
            a(n - 2);
            System.out.print("c");
        }
    }

}


