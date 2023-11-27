import java.math.BigDecimal;
import java.math.BigInteger;

/*
    Aufgabe 1) Variablen, Datentypen, Typumwandlungen und Strings
*/
public class Aufgabe1 {

    public static void main(String[] args) {
        // Teilaufgabe A
        System.out.println("Aufgabe 1 Teilaufgabe A \n");

        /*
        a)
        Deklarieren und initialisieren Sie je eine Variable 1234e-3f, 1.23f, 1.234e3, 43.21, 1234L, 0xa, 014, ’Z’, ’ ’
        sicher, dass die Variablen dieselben Typen wie diese Literale haben.
        */

        float a = 1234e-3f;
        float b = 1.23f;
        double c = 1.234e3;
        double d = 43.21;
        long e = 1234L;
        int f = 0xa;
        int g = 014;
        char h = 'Z';
        char i = ' ';


        /*
        b) Legen Sie zusaetzlich zu den bereits deklarierten Variablen noch eine byte-Variable mit dem Wert 123,
        sowie eine short-Variable mit dem Wert 4321 an.
        */

        byte j = 123;
        short k = 4321;


        /*
        c) Erzeugen Sie durch Verwendung des + - Operators einen String, der die Werte in den zuvor beschriebenen Variablen enthaelt,
        jeweils getrennt durch einen Beistrich (", "). Geben Sie den String mittels System.out.println(...) aus.
         */

        System.out.println("String: " + a + ", " + b + ", " + c + ", " + d + ", " + e + ", " + f + ", " + g + ", " + h + ", " + i + ", " + j + ", " + k);


        /*
        d) Berechnen Sie das ganzzahlige Produkt aller Werte in diesen Variablen, wobei die Nachkommastellen vor der Multiplikation
        abgeschnitten werden sollen. Vermeiden Sie einen Ueberlauf des Ergebnisses. Geben Sie das Produkt mittels System.out.println(...) aus.
         */


        BigInteger product = BigInteger.valueOf((long) a).multiply(BigInteger.valueOf((long) b)).multiply(BigInteger.valueOf((long) c)).multiply(BigInteger.valueOf((long) d)).multiply(BigInteger.valueOf(e)).multiply(BigInteger.valueOf((long) f)).multiply(BigInteger.valueOf((long) g)).multiply(BigInteger.valueOf((long) h)).multiply(BigInteger.valueOf((long) i)).multiply(BigInteger.valueOf((long) j)).multiply(BigInteger.valueOf((long) k));
        System.out.println("Produkt: " + product);


        /*
        e) Berechnen Sie die Summe aller Werte in diesen Variablen und wandeln Sie das Ergebnis am Ende der Berechnung in eine ganze Zahl um.
        Geben Sie die ganzzahlige Summe mittels System.out.println(...) aus.
         */

        System.out.println("Summe in Int: " + (int) (a + b + c + d + e + f + g + h + i + j + k));


        /* 
        f) Wandeln Sie jeden Wert in den Variablen in einen Wert vom Typ byte um und berechnen Sie die Summe der umgewandelten Werte. 
        Geben Sie die Summe mittels System.out.println(...) aus.
         */

        System.out.println("Summe in Byte: " + (byte) a + (byte) b + (byte) c + (byte) d + e + (byte) f + (byte) g + (byte) h + (byte) i + j + (byte) k);

        /*
        Zusatzaufgaben Teilaufgabe A


        1. Warum ist der aus den Variablen erzeugte String nicht gleich zu den vorgegebenen Literalen 1234e-3f, 1.23f, 1.234e3, 43.21, 1234L, 0xa, 014, ’Z’, ’ ’?
           Weil die Hex und Octal  als Dezimalzahlen interpretiert werden und die "f" und "L" nur als Suffix angegeben werden muessen, bei der Ausgabe werden die Suffixe weggelassen.

        2. Wodurch ergeben sich die Unterschiede zwischen den beiden Summen?
           Bei  der Summation im Integer Bereich werden alle Zahlen addiert. In Bytes werden die Zahlen zuerst in Bytes umgerechnet und anschliessend einfach aneinandergehängt.


        3. Wann wird ein Wert automatisch in einen Wert eines anderen Typs umgewandelt, und wann  muss eine Umwandlung explizit durchgefuehrt werden (Cast)?  
           Automatisch: Bei der Aufgabe durch System.out.println, Wenn ein Wert gecastet wird und der andere nicht, wir der andere automatisch anders interpretiert.
           Explizit: Wenn z.B. ein Integer als String ausgegeben werden soll oder wenn die Nachkommerstellen eines Floats abgeschnitten werden sollen.

        4. Erzeugen Sie zusaetzlich eine Variable st vom Typ String und initialisieren Sie diese mit "Lehrveranstaltung". Fueren Sie danach System.out.println(st + i + c)
           sowie System.out.println(i + c + st) aus, wobei i fuer eine Variable vom Typ int, und c fuer eine Variable vom Typ char steht. Warum sind die Ausgaben vor und
           nach dem String "Lehrveranstaltung" unterschiedlich?
           Da die Ausgabe linksassoziativ aufgefuehrt wird, wird bei der ersten Ausgabe der String zuerst aufgegeben und der Integer und das Char an den String drangehaengt und gecastet.
           Bei der zweiten Ausgabe wird zuerst der Wert von Int mit Char addiert und anschließend der String drangehaengt.
        */

        String st = "\nLehrveranstaltung";
        int varI = 5;
        char varC = 'F';

        System.out.println("Zusatzaufgabe 4");
        System.out.println(st + varI + varC);
        System.out.println(varI + varC + st);



        // Teilaufgabe B
        System.out.println("\n\nAufgabe 1 Teilaufgabe B \n");


        String sentence = "Aufgabenblatt1 besteht aus 4 Aufgaben!";
        String sentenceNull = "";
        System.out.println("Satz: " + sentence);
        
        /*
        a) Verwenden Sie eine Methode, die Ihnen das Zeichen an der Stelle mit dem Index 10 zurueckliefert.
        */

        System.out.println("Index 10: " + sentence.charAt(10));


        /*
        b) Verwenden Sie eine Methode, die Ihnen die Laenge (Anzahl der Zeichen) des Strings zurueckgibt.
        */

        System.out.println("Laenge des Strings: " + sentence.length());


        /*
        c) Verwenden Sie eine Methode, die prueft, ob sentence leer ist.
        */

        System.out.println("Ist Sting leer? " + sentence.isEmpty());
        System.out.println("Ist Leerstring leer? " + sentenceNull.isEmpty());


        /*
        d) Verwenden Sie eine Methode, die prueft, ob sentence mit "Auf" anfaengt.
        */
        System.out.println("Beginnt String mit 'Auf' " + sentence.startsWith("Auf"));
        System.out.println("Beginnt Leerstring mit 'Auf' " + sentenceNull.startsWith("Auf"));


        /*
        e) Verwenden Sie eine Methode, die Ihnen die Mooeglichkeit gibt, Substrings zu extrahieren. Geben Sie i) den Substring ab Index 27
        */

        System.out.println("String von Index 27 -: " + sentence.substring(27));


        /*
        ii) den Substring von Index 0 bis Index 13 aus.
        */

        System.out.println("String von Index 0 - 13: " + sentence.substring(0, 13));


        /*
        f) Verwenden Sie eine Methode, die es Ihnen ermoeglicht, Strings zu kombinieren. Verketten Sie den
           String in der Variable sentence und den zuvor extrahierten String von Index 0 bis 13.
        */

        System.out.println("String zusammen mit Index 0 - 13: " + sentence.concat(sentence.substring(0, 13)));


        /*
        g) Verwenden Sie eine Methode, die jedes ’e’ innerhalb des Strings durch ein ’X’ ersetzt.
        */

        System.out.println("Alle 'e' durch 'X' ersetzt: " + sentence.replace('e', 'X'));



        /*
        h) Verwenden Sie eine Methode, die die Zeichenkette "aus" durch die Zeichenkette "AUS" ersetzt.
        */

        System.out.println("'aus' durch 'AUS' ersetzt: " + sentence.replace("aus", "AUS"));


        /*
        i) Verwenden Sie eine Methode, die alle Buchstaben in einem String in Großbuchstaben um- wandelt. Verwenden Sie zur Demonstration den extrahierten String von Index 27 bis 37.
        */

        System.out.println("Alles Letter in Uppercase: " + sentence.toUpperCase());


        /*
        j) Verwenden Sie eine Methode, die alle Buchstaben in einem String in Kleinbuchstaben um- wandelt. Verwenden Sie zur Demonstration den String sentence.
         */

        System.out.println("Alles Letter in Lowercase: " + sentence.toLowerCase());

        /*
        Zusatzaufgaben Teilaufgabe A

        1. Wie kann man einen Substring extrahieren, wenn die Methode substring(...) nicht zur Verfuegung steht? Wie wuerden Sie in diesem Fall vorgehen?
           Eine Moeglichkeit wäre die einzelnen Chars in einem Array zu speichern und dann nur die gewuenschten Array Indices ausgeben.
        */
    }
}




