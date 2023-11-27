/*
    Aufgabe 4) Rekursion und Zeichnen mit StdDraw
*/
public class Aufgabe4 {

    // Kons
    private static final int RADIUS = 10;

    private static void printCirclesInLine(int val, int x, int y) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        if (val == 0) {
            return;
        }

        if (val % 2 == 0) {
            StdDraw.setPenColor(StdDraw.ORANGE);
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
        }

        StdDraw.filledCircle(x, y, RADIUS);
        StdDraw.filledCircle(x, y + RADIUS * 2, RADIUS);
        printCirclesInLine(--val, x + RADIUS * 2, y);
    }

    private static void printShape(int val, int max, int y) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        if (val == max) {
            printCirclesInLine(val, RADIUS, y);
            return;
        } else {
            int y2 = y + (max - val) * RADIUS * 8;
            printCirclesInLine(val, RADIUS, y);
            printCirclesInLine(val, RADIUS, y2);
        }
        printShape(++val, max, y + RADIUS * 4);
    }

    public static void main(String[] args) {
        // General StdDraw Settings
        int pixelWidth = 200;
        int pixelHeight = 760;
        StdDraw.setCanvasSize(pixelWidth, pixelHeight);
        StdDraw.setXscale(0, pixelWidth);
        StdDraw.setYscale(0, pixelHeight);
        StdDraw.enableDoubleBuffering();

        // Creates graphical Output
        // Erzeugt Abbildung 2a
        printShape(1, 10, RADIUS);
        StdDraw.show();
        StdDraw.pause(3000);
        StdDraw.clear();

        // Erzeugt Abbildung 2b
        printShape(1, 5, RADIUS);
        StdDraw.show();
        StdDraw.pause(3000);
        StdDraw.clear();

        // Erzeugt Abbildung 2c
        printShape(1, 1, RADIUS);
        StdDraw.show();
        StdDraw.pause(3000);
        StdDraw.clear();

        // Erzeugt Abbildung 2d
        printShape(3, 9, RADIUS);
        StdDraw.show();
    }
}

