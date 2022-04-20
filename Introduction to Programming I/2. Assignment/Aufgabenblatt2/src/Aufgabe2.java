import java.util.Scanner;

/*
    Aufgabe 2) Verschachtelte Schleifen und Verzweigungen
*/
public class Aufgabe2 {

    public static void main(String[] args) {

        //TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        System.out.println("Aufgabe 2\n");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Hoehe der Raute angeben: ");
        int figHeight = scanner.nextInt();

        if (figHeight > 0 && figHeight % 2 != 0) {
            int count = figHeight * (figHeight + 1);
            for (int i = 0; i < count; i++) {
                int hRue =  i / (figHeight+1);
                int vRue = (i % (figHeight+1));
                if (i % (figHeight+1) == 0) System.out.print('\n');
                if (vRue < Math.abs(hRue - figHeight / 2) || (figHeight - vRue) < Math.abs(hRue - figHeight / 2)) System.out.print('+');
                else if ((vRue == Math.abs(hRue - figHeight / 2) || (figHeight - vRue) == Math.abs(hRue - figHeight / 2)) && Math.abs(hRue - figHeight / 2) == 0) System.out.print('|');
                else if (vRue == Math.abs(hRue - figHeight / 2)) System.out.print((hRue > figHeight / 2) ? '\\' : '/');
                else if ((figHeight - vRue) == Math.abs(hRue - figHeight / 2)) System.out.print((hRue > figHeight / 2) ? '/' : '\\');
                else System.out.print('O');
            }
        } else {
            System.out.println("Die Höhe der Raute muss ungerade positive Werte haben!");
        }

        System.out.println("");

        /*
        Zusatzfragen
        1. Kann man das Beispiel mit einer einzelnen Schleife loesen? Wenn ja, wie und an welchen Stellen muesten Sie Code Aenderungen durchfuehren?
        Ja das Beispiel kann nur mit einer Schleife geloest werden -> siehe Loesung oben
        */
    }
}

