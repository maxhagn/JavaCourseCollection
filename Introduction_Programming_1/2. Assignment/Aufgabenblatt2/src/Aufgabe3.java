/*
    Aufgabe 3) Verschachtelte Schleifen und Verzweigungen - Optische Täuschung
*/
public class Aufgabe3 {

    public static void main(String[] args) {
        System.out.println("Aufgabe 3\n");

        // General Settings
        int width = 400, height = 440, cubeRow = 10, cubeCol = 11;
        float penRadius = 0.005f, cubeSize = 40, recSize = 10;
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.setPenRadius(penRadius);
        StdDraw.enableDoubleBuffering();


        for (int i = 0; i < cubeRow; i++) {
            System.out.println("index: " + (i + 1));
            for (int j = 0; j < cubeCol; j++) {
                System.out.println("j: " + (j + 1));

                // Draw Cubes
                if ((i + j) % 2 == 0) StdDraw.setPenColor(166, 166, 76);
                else StdDraw.setPenColor(90, 90, 0);
                StdDraw.filledRectangle((i * cubeSize) + (cubeSize / 2), (j * cubeSize) + (cubeSize / 2),
                        cubeSize / 2, cubeSize / 2);

                // Draw Cross
                if (i > 0 && j > 0) {
                    if (((i + j) % 2 == 0 && j < 6) || ((i + j) % 2 != 0 && j > 6) || ((i + j) % 2 != 0 && j == 6)) {
                        StdDraw.setPenColor(255, 255, 255);
                        StdDraw.filledRectangle((i * cubeSize), (j * cubeSize), recSize, recSize / 5);
                        StdDraw.setPenColor(0, 0, 0);
                        StdDraw.filledRectangle((i * cubeSize), (j * cubeSize), recSize / 5, recSize);
                    } else if (((i + j) % 2 != 0 && j < 6) || ((i + j) % 2 == 0 && j > 6) || ((i + j) % 2 == 0 && j == 6)) {
                        StdDraw.setPenColor(255, 255, 255);
                        StdDraw.filledRectangle((i * cubeSize), (j * cubeSize), recSize / 5, recSize);
                        StdDraw.setPenColor(0, 0, 0);
                        StdDraw.filledRectangle((i * cubeSize), (j * cubeSize), recSize, recSize / 5);
                    }
                }
            }
        }
        StdDraw.show();
    }
}



    /*
        Zusatzfrage
        1. Wie muessen Sie den Code schreiben, damit Sie nur durch Aenderung der drei Variablen fuer horizontale Quadrate, fuer vertikale Quadrate
           und fuer die Abmessung der Quadrate ein neues Ergebnis bekommen?
           Dynamisch, sprich die Groeße der Quadrate und die Anzahl der Quadrate muessen in Variablen gespeichert werden.
    */