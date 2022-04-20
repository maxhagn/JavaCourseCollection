import java.awt.*;

/*
    Aufgabe 3) StdDraw Bibliothek anwenden und erste Schleifen
*/
public class Aufgabe3 {
    
    public static void main(String[] args) {
        // Aufgabe 3
        System.out.println("Aufgabe 3\n");

        /*
        a) Implementieren Sie ein Programm, welches das in Abbildung 1 gezeigte Bild ausgibt. Im Bild sind alle Maßangaben vorhanden, die notwendig sind, um dieses Bild zu erzeugen.
        b) Setzen Sie die Gro ̈ße des Ausgabefensters auf 400×400 Pixel. Der Punkt P(x = 0,y = 0) befindet sich in der linken unteren Ecke.
        c) Fuer die Realisierung der vertikalen Ringe mit der Teilung in Blau und Gru ̈n wurde die Methode StdDraw.arc(...) verwendet. Verwenden Sie fu ̈r die Ringe und auch horizontalen Kreise geeignete Schleifen.
        */

        // General Settings
        StdDraw.setCanvasSize(400,400);
        StdDraw.setXscale(0,400);
        StdDraw.setYscale(0,400);
        StdDraw.setPenRadius(0.005);

        // Lines
        StdDraw.setPenColor(Color.RED);
        StdDraw.line(0,200,200,0);
        StdDraw.line(0,200,200,400);
        StdDraw.line(200,400,400,200);
        StdDraw.line(400,200,200,0);


        // Rectangle
        StdDraw.setPenColor(Color.CYAN);
        StdDraw.filledRectangle(100,100,40,20 );
        StdDraw.filledRectangle(300,300,20,40 );

        // Ellipse
        StdDraw.setPenColor(Color.MAGENTA);
        StdDraw.filledEllipse(100,300,40,20 );
        StdDraw.filledEllipse(300,100,20,40 );


        // Filled Circles
        for ( int i = 1; i <= 7; i++) {
            if (i == 4) continue;
            StdDraw.setPenColor(Color.ORANGE);
            StdDraw.filledCircle(50 * i, 200,15);
        }

        // Circle Arc
        for ( int i = 1; i <= 7; i++) {
            StdDraw.setPenColor(Color.GREEN);
            StdDraw.arc(200, i * 50, 15, -135 + (45 * (i-1)), 45 + (45 * (i-1)));

            StdDraw.setPenColor(Color.BLUE);
            StdDraw.arc(200, i * 50, 15, 45 + (45 * (i-1)), -135 + (45 * (i-1)));
        }

        /*
        Zusatzaufgaben
        1. Sind verschiedene Arten von Schleifen gegeneinander austauschbar?
           Theoretisch schon, teilweise ergibt es sich auf dem Kontext eine bestimmte Variante zu verwenden.
           For und While sind gegeneinander austauschbar oder if und eine switch
        */
    }
}

