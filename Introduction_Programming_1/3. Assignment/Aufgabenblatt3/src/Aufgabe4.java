/*
    Aufgabe 4) Grafische Aufbereitung v. "GuessingGame" und Verwendung v. Methoden
*/

import java.awt.*;
import java.util.Scanner;

public class Aufgabe4 {

    public static void main(String[] args) {
        int pixelWidth = 300;
        int pixelHeight = 200;
        StdDraw.setCanvasSize(pixelWidth, pixelHeight);
        StdDraw.setXscale(0, pixelWidth);
        StdDraw.setYscale(0, pixelHeight);
        Font font = new Font("Arial", Font.BOLD, 45);
        StdDraw.setFont(font);
        StdDraw.enableDoubleBuffering();

        //TODO: Implementieren Sie hier Ihr "GuessingGame"
        int random = (int) randomNum(), input, counter = 1, maxTry = 8, randomArea = 100;
        do {
            draw(counter, false);
            input = input(counter);
            if (input == Integer.MAX_VALUE) {
                message("Du hast einen String angegeben! \n");
                counter--;
            } else if (input < -randomArea || input > randomArea) {
                message("Deine Zahl war kleiner " + -randomArea + " oder größer " + randomArea + " !!\n");
                counter--;
            } else if (input > random) message("Die gesuchte Zahl ist kleiner \n");
            else if (input < random) message("Die gesuchte Zahl ist größer \n");
            else if (input == random) {
                message("Du hast die richtige Zahl erraten! Gratulation!");
                draw(maxTry + 1, true);
            }

            if (counter == maxTry) {
                message("Du hast alle 8. Versuche verbraucht, das Programm wird beendet.\nDie gesuchte Zahl war die " + random);
                draw(maxTry + 1, false);
            }


            counter++;
        }
        while (random != input && counter <= maxTry);
    }

    //TODO: Implementieren Sie hier Ihre Methoden
    public static double randomNum() {
        return Math.round((Math.random() * 200) - 100);
    }

    public static int input(int counter) {
        message("Versuch: " + counter + " - Gibt eine Zahl zwischen -100 und 100 ein: ");
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return Integer.MAX_VALUE;
        } else return scanner.nextInt();
    }

    public static void message(String mes) {
        System.out.print(mes);
    }

    public static void draw(int counter, boolean won) {
        StdDraw.clear();
        if (!won) {
            switch (counter) {
                case 1:
                    StdDraw.setPenColor(Color.RED);
                    StdDraw.filledRectangle(150, 100, 30,30);
                case 2:
                    StdDraw.setPenColor(Color.GREEN);
                    StdDraw.filledRectangle(135, 100, 5,5);
                case 3:
                    StdDraw.setPenColor(Color.CYAN);
                    StdDraw.filledRectangle(85, 90, 35, 10);
                case 4:
                    StdDraw.setPenColor(Color.CYAN);
                    StdDraw.filledRectangle(215, 90, 35, 10);
                case 5:
                    StdDraw.setPenColor(Color.GRAY);
                    StdDraw.filledCircle(90, 50,30);
                case 6:
                    StdDraw.setPenColor(Color.GRAY);
                    StdDraw.filledCircle(210, 50, 30);
                case 7:
                    StdDraw.setPenColor(Color.LIGHT_GRAY);
                    StdDraw.filledCircle(90, 50,10);
                case 8:
                    StdDraw.setPenColor(Color.LIGHT_GRAY);
                    StdDraw.filledCircle(210, 50, 10);
                    break;
                case 9:
                    StdDraw.setPenColor(Color.RED);
                    StdDraw.text(150, 100, "You LOST!!!");
                default:
                    break;
            }
        } else {
            StdDraw.setPenColor(Color.GREEN);
            StdDraw.text(150, 100, "You WON!!!");
        }
        StdDraw.show();
    }

    /*
        Zusatzfragen

        1. Wie kann man die eingegebenen Daten (bzw. deren Datentypen) unterscheiden?
           In dem man mit hasNextInt oder hasNextString ueberprüft welcher Datentyp angegeben wurde. Ich filtere dannach die falschen Angaben heraus
           und veringere den Counter damit kein Versuch verbraucht wird.
        2. Muss eine ungueltige Eingabe aus dem Input-Stream des Scanners entfernt werden?
           Nein muss nicht entfernt werden, der Scanner verwendet beim nächsten Aufruf das nextInt

    */
}
