/*
    Aufgabe 3) Rekursion - Kreuzmuster
*/
public class Aufgabe3 {

    private static void drawCrossPattern(int x, int y, int l, boolean c) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode

        if (l < 16) {
            return;
        }

        // choose pen color
        if (c) {
            StdDraw.setPenColor(StdDraw.RED);
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
        }

        // draw rectangles
        StdDraw.filledRectangle((float)(x + l) / 2, (float)(y + l) / 2, 0.5 * l, 0.025 * l);
        StdDraw.filledRectangle((float)(x + l) / 2, (float)(y + l) / 2, 0.025 * l, 0.5 * l);

        //recursive calls
        drawCrossPattern(x, y, l / 2, !c); // comment for (b)
        drawCrossPattern(x + l, y, l / 2, !c);
        drawCrossPattern(x, y + l, l / 2, !c);
        drawCrossPattern(x + l, y + l, l / 2, !c);
    }

    public static void main(String[] args) {
        // General StdDraw Settings
        int width = 512;
        int height = 512;
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.enableDoubleBuffering();

        drawCrossPattern(0, 0, 512, true);
        StdDraw.show();
    }

    /*  Zusatzfragen

        1. Warum benötigen Sie bei einer Rekursion eine Abbruchbedingung?
           Damit keine endlose Rekursion gebildet wird.

        2. Gibt es eine Limitierung für die Rekursionstiefe?
           Nein, aber in diesem konkreten Fall ist es l < 16, dann wird die Abbruchbedinung ausgeführt

        3. Wie oft wird die Methode drawCrossPattern aufgerufen, wenn als Abbruchbedingung die Auflösungsgrenze von l<16 gewählt wird?
           6 mal wird die Methode aufgerufen.

        4. Wie viele Kreuze werden auf der letzten Rekursionsstufe (die kleinsten Kreuze) gezeichnet?
           4^0 + 4^1 + ... + 4^5 = 1365

        5. Wie müssen Sie Ihr Programm abändern, um das Muster in Abbildung 1b zu erzeugen?
           Zeile 20 kommentieren

    */
}



