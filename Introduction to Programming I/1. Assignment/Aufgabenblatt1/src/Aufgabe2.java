/*
    Aufgabe 2) Verzweigungen
*/
public class Aufgabe2 {
    
    public static void main(String[] args) {
        
        //A) Gewichtskategorisierung
        System.out.println("Aufgabe 2 Teilaufgabe A \n");

        /*
        a) Implementieren Sie ein Programm, das ein gegebenes Gewicht (int weight in kg) einer Kategorie zuordnet.
           Die Kategorisierung der Gewichte ist in Tabelle 1 aufgelistet. In der dritten Spalte finden Sie den String,
           der bei einem bestimmten Gewicht ausgegeben werden soll.
         */

        int weight  = 15;
        String resultString = "Gewicht --> ";

        /*
        b) Implementieren Sie diese Gewichtskategorisierung mittels if-Anweisungen auf zwei verschiedene Arten:
        1. Verschachtelung einfacher if-Anweisungen (ohne else-if)
        */

        if (weight < 0) {
            resultString += "Falsche Eingabe";
        }
        if (weight >= 0 && weight < 5) {
            resultString += "Extra Leicht";
        }
        if (weight >= 5 && weight <= 9) {
            resultString += "Leicht";
        }
        if (weight >= 10 && weight <= 24) {
            resultString += "Mittelschwer";
        }
        if (weight >= 25 && weight <= 49) {
            resultString += "Schwer";
        }
        if (weight >= 50) {
            resultString += "Extra Schwer";
        }

        System.out.println("Gewicht mit if: " + resultString);



        /*
        2. Mehrfachverzweigung (mit else-if)
        */
        resultString = "Gewicht --> ";

        if (weight < 0) {
            resultString += "Falsche Eingabe";
        } else if (weight < 5) {
            resultString += "Extra Leicht";
        } else if (weight <= 9) {
            resultString += "Leicht";
        } else if (weight <= 24) {
            resultString += "Mittelschwer";
        } else if (weight <= 49) {
            resultString += "Schwer";
        } else {
            resultString += "Extra Schwer";
        }

        System.out.println("Gewicht mit else if: " + resultString);

        /*
        Zusatzaufgaben Teilaufgabe A

        1. Darf eine if-Anweisung ohne else-Zweig verwendet werden?
           Ja ein If darf ohne else verwendet werden, allerdings bietet es sich an, um ungewollte Werte abzufangen und eine alternative Aufgabe zu liefern
        */


        
        //B) Zahlenumrechner: Hexadezimalzahlen zwischen 0x0 und 0xF werden auf 4 Bits abgebildet.
        System.out.println("\n\nAufgabe 2 Teilaufgabe A \n");

        /*
        a) Implementieren Sie einen Zahlenumrechner, der fuer eine hexadezimale Zahl int hexNumber die dazugehoerige Binaerzahl ausgibt.
        b) Verwenden Sie fuer die Implementierung eine switch-Anweisung.
        c) Es sollen die Zahlen von 0x0 bis 0xF auf 0000 bis 1111 abgebildet werden koennen.
        d) Bei einer ungueltigen hexadezimalen Zahl soll ein entsprechender Hinweistext ausgegeben werden, ansonsten "<hexNumber> entspricht <binaryNumber>". Beispiel: 0xA entspricht 1010.
           Fuer die Formatierung der hexadezimalen Zahl koennen Sie den Aufruf String.format("0x%X", hexNumber) verwenden.
        */

        int hexNumber = 0xA;
        int binaryNumber = 0;

        switch (hexNumber) {
            case 0: binaryNumber = 0; break;
            case 1: binaryNumber = 1; break;
            case 2: binaryNumber = 10; break;
            case 3: binaryNumber = 11; break;
            case 4: binaryNumber = 100; break;
            case 5: binaryNumber = 101; break;
            case 6: binaryNumber = 110; break;
            case 7: binaryNumber = 111; break;
            case 8: binaryNumber = 1000; break;
            case 9: binaryNumber = 1001; break;
            case 10: binaryNumber = 1010; break;
            case 11: binaryNumber = 1011; break;
            case 12: binaryNumber = 1100; break;
            case 13: binaryNumber = 1101; break;
            case 14: binaryNumber = 1110; break;
            case 15: binaryNumber = 1111; break;
            default:
                System.out.println("Die eingegebene Hex Zahl ist ungültig");
        }

        System.out.println(String.format("0x%X", hexNumber) + " entspricht " + binaryNumber);


        /*
        Zusatzaufgaben Teilaufgabe B

        1. Darf man generell das break in den einzelnen case-Zweigen weglassen?
           Dürfen natürlich (es wirft definitiv keinen Error), aber das Programm springt zum naechsten case und sucht nach dem naechsten Break,
           deswegen kommt vermutlich eine falsche bzw. keine gewollt richtige Loesung heraus. Andererenfalls kann es durchaus
           gewollte sein keinen Break zu setzen, um alles ab einem bestimmten Case bis zu einem bestimmten Case aufzugeben.
        2. Muss bei der Verwendung der switch-Anweisung ein default-Zweig implementiert werden?
           Nein auch hier muss natuerlich kein Default Wert initialisiert werden. Es ist durchaus sinnvoll, um unvorhersehbare Werte abzufangen
           und somit eine Fehlermeldung oder einen von der ProgrammierIn vorgegebenen Wert zu verwenden.
        3. Sind if- und switch-Anweisungen gegeneinander austauschbar?
           Theoretisch schon, teilweise ergibt es sich auf dem Kontext eine bestimmte Variante zu verwenden.
        */
    }
}

