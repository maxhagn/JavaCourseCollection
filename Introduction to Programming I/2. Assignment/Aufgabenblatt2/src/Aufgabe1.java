/*
    Aufgabe 1) Verschachtelung von Schleifen - Harshad-Zahlen - String Manipulation
*/
public class Aufgabe1 {

    public static void main(String[] args) {

        // Teilaufgabe A
        System.out.println("Aufgabe 1 Teilaufgabe A\n");
        int maxNumber = 1000;
        System.out.println("Harshad-Zahlen: ");
        for (int j = 1, i = 1; j <= maxNumber; j++, i++) {
            int querSum = 0;
            if (i < 10) {
                querSum = i;
            } else {
                while (i > 0) {
                    querSum += i % 10;
                    i /= 10;
                }
            }
            if (j % querSum == 0) {
                System.out.println(j);
            }
            i = j;
        }

        /*
            Zusatzfragen
            1. Welche Art von Schleife(n) verwenden Sie fuer die Loesung dieses Beispiels? Begruenden Sie Ihre Wahl.
               Verwendet wurde eine for Schleife, um über die Zahlen 1-maxNumber zu iterieren. Die erste Variable gibt den Index an, die zweite wird verwendet,
               um die einzelnen Ziffern zu finden und eine Summe dieser zu bilden. Bei der suche nach den Ziffern, wird i veraendert, um bei der naechsten Iteration
               wieder auf die richtige Zahl zugreifen zu koennen, wird i am Ende der Schleife wieder auf den aktuellen Index j gesetzt. Bessere Option waere es,
               die Suche nach Ziffern in eine eigene Methode auszulagern oder weitere Klassen auf der Java API zu verwenden. Die While Schleife wird verwendet, um
               so lange durch i zu iterieren, bis i = 0 ist und somit keine weiteren Ziffern hat. If Verzweigungen werden verwendet, um einfache Abfragen zu taetigen.
        */

        // Teilaufgabe B
        System.out.println("\n\nAufgabe 1 Teilaufgabe B\n");
        String erg = "";
        for (int i = 1; i <= 500; i++) {
            if (i % 15 == 0) {
                erg += "#" + i;
            }
        }
        System.out.println(erg);
        String saveErg = erg;

        // Mit Replace
        System.out.println(erg.replace("1", "!").replace("5", "$"));


        // Ohne Replace
        for (int i = 0; i < erg.length(); i++) {
            if (erg.charAt(i) == '1') {
                erg = erg.substring(0, i) + "!" + erg.substring(i + 1);
            }
            if (erg.charAt(i) == '5') {
                erg = erg.substring(0, i) + "$" + erg.substring(i + 1);
            }
        }
        System.out.println(erg);


        /*
            Zusatzfragen
            1. Wie muessen Sie Ihr Programm aendern, damit Sie alle Vorkommen von ’2’ durch die Zeichen "**" ersetzen koennen?
            2. Wie muessen Sie Ihr Programm aendern, damit Sie alle Vorkommen von "10" durch das Zeichen ’+’ ersetzen koennen?
         */

        System.out.println("\n\nAufgabe 1 Teilaufgabe B Zusatzfragen\n");
        erg = saveErg;
        for (int i = 0; i < erg.length(); i++) {
            if (erg.charAt(i) == '1' && erg.charAt(i + 1) == '0') {
                erg = erg.substring(0, i) + "+" + erg.substring(i + 2);
            }
            if (erg.charAt(i) == '1') {
                erg = erg.substring(0, i) + "!" + erg.substring(i + 1);
            }
            if (erg.charAt(i) == '2') {
                erg = erg.substring(0, i) + "**" + erg.substring(i + 1);
            }
            if (erg.charAt(i) == '5') {
                erg = erg.substring(0, i) + "$" + erg.substring(i + 1);
            }
        }
        System.out.println(erg);

    }
}

