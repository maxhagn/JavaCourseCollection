import java.awt.*;

/*
    Aufgabe 4) Dynamische Elemente in Verbindung mit StdDraw und Schleifen
*/
public class Aufgabe4 {

    public static void main(String[] args) {
        System.out.println("Aufgabe 4\n");


        /*
        a) Implementieren Sie Kreise wie in Abbildung 2a–2c gezeigt, die sich durch das Bild bewegen. Der rote Kreis startet links unten und bewegt sich nach rechts oben. Der blaue Kreis startet links oben und bewegt sich nach rechts unten.
        b) Setzen Sie die Gro ̈ße des Ausgabefensters auf 500×500 Pixel.
        c) Setzen Sie den Radius der Kreise auf 10 Pixel.
        d) Verwenden Sie als Wartezeit zwischen zwei Bildern und somit zwischen zwei Kreispositionen 5ms. Dazu kann die Methode StdDraw.pause(int t) verwendet werden. Die Methode StdDraw.clear() hilft Ihnen dabei das Ausgabefenster zu leeren, damit Sie die Kreise neu zeichnen ko ̈nnen.
        e) Nachdem die Kreise am rechten Rand angekommen sind, sollen diese sich auf der selben Bahn wieder zuru ̈ck bewegen, bis diese ihre urspru ̈ngliche Startposition erreicht haben.
        f) Fuer die Implementierung soll eine einzige Schleife verwendet werden.
        */


        // Initialising
        int width = 500;
        int height = 500;
        int pause = 5;
        int radius= 10;
        int x = radius;
        int y = radius;
        int v = 1;

        StdDraw.setCanvasSize(width,height);
        StdDraw.setXscale(0,width);
        StdDraw.setYscale(0,height);

        StdDraw.enableDoubleBuffering();


        while (x + v != radius) {
            StdDraw.clear();
            if (x + v == width - radius) v = -1;
            if (x + v == radius) v = 1;

            // Blue Circle
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.filledCircle(x, height - y, radius);

            // Red Circle
            StdDraw.setPenColor(Color.RED);
            StdDraw.filledCircle(x, y, radius);

            y += v; x += v;

            StdDraw.show();
            StdDraw.pause(pause);
        }

        /*
        Zusatzaufgaben

        1. Ist bei diesem Beispiel die Verwendung einer while-Schleife von Vorteil, oder ist fuer diese Aufgabenstellung die for-Schleife zu bevorzugen?
           Ja, da wir mit der while Schleife eine einfache Endlosschleife konstruieren können, for würde Sinn machen, wenn wir die Bedigung kennen wuerden.
        */
    }
}

