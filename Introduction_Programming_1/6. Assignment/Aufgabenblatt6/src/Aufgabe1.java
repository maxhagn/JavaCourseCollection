/*
    Aufgabe 1) Zweidimensionale Arrays und Rekursion - Mini-Paint
*/

import java.awt.*;

public class Aufgabe1 {
    
    private static void floodFill(int[][] picArray, int sx, int sy) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        // return conditions
        // check if point is colored
        if (picArray[picArray.length - 1 - sy][sx] != 0) {
            return;
        }
        // check if point is outside area
        if (sx <= 0 || sy <= 0 || sx >= picArray.length - 1 || sy >= picArray[0].length - 1) {
            return;
        }

        // no condition met -> color point
        StdDraw.point(sx, sy);
        picArray[picArray.length - 1 - sy][sx] = 1;

        // recursive call to check other points
        floodFill(picArray, sx + 1, sy);
        floodFill(picArray, sx - 1, sy);
        floodFill(picArray, sx, sy + 1);
        floodFill(picArray, sx, sy - 1);
    }
    
    // draw lines -> was given
    private static void paintLine(int[][] picArray, int[] xClick, int[] yClick) {
        int x0 = xClick[0];
        int x1 = xClick[1];
        int y0 = yClick[0];
        int y1 = yClick[1];
        
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int xd = x0 < x1 ? 1 : -1;
        int yd = y0 < y1 ? 1 : -1;
        
        int e = dx - dy;
        int et;
        
        picArray[picArray.length - 1 - y0][x0] = 1;
        StdDraw.point(x0, y0);
        System.out.println("Point: x:" + x0 + " y:" + y0);
        
        while (x0 != x1 || y0 != y1) {
            et = 2 * e;
            if (et > -dy) {
                e -= dy;
                x0 += xd;
            }
            if (et < dx) {
                e += dx;
                y0 += yd;
            }
            picArray[picArray.length - 1 - y0][x0] = 1;
            StdDraw.point(x0, y0);
            System.out.println("Point: x:" + x0 + " y:" + y0);
        }
    }
    
    public static void main(String[] args) {
        // general std draw settings
        int width = 300;
        int height = 250;
        int squareSize = 50;
        StdDraw.setCanvasSize(width - 1, height - 1);
        StdDraw.setXscale(0, width - 1);
        StdDraw.setYscale(0, height - 1);
        StdDraw.setPenRadius(0.001);
        StdDraw.enableDoubleBuffering();
        
        // draw color buttons
        Color[] colors = new Color[]{StdDraw.RED, StdDraw.GREEN, StdDraw.BLUE, StdDraw.YELLOW, StdDraw.CYAN};
        for (int i = 0; i < colors.length; i++) {
            StdDraw.setPenColor(colors[i]);
            StdDraw.filledSquare(width - squareSize / 2 - 1, squareSize / 2 + i * squareSize , squareSize / 2);
        }

        // draw lines around color buttons
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.0082);
        StdDraw.line(width - squareSize + 1, 0, width - squareSize + 1, height - 1);
        for (int i = 1; i < 5; i++) {
            StdDraw.line(width - squareSize + 1, i * squareSize - 1, width - 1, i * squareSize - 1);
        }

        // create x,yClick array, picArray and isRunning boolean
        StdDraw.setPenRadius(0.001);
        int[] xClick = new int[2];
        int[] yClick = new int[2];
        int[][] picArray = new int[height][width - squareSize];
        boolean isRunning = true;
        StdDraw.show();
        
        while (isRunning) {
            if (StdDraw.isMousePressed()) {
                // TODO: Implementieren Sie hier die fehlende Funktionalität für das Mini-Paint
                int x = (int) StdDraw.mouseX();
                int y = (int) StdDraw.mouseY();

                // check if a button was clicked
                if (x > width - squareSize) {
                    StdDraw.setPenColor(colors[(y / squareSize)]);

                    // check if click is in draw area
                    while (x < 0 || x > width - squareSize || y < 0 || y > height - 1) {
                        while (!StdDraw.isMousePressed()) {
                            x = (int) StdDraw.mouseX();
                            y = (int) StdDraw.mouseY();
                        }
                    }

                    // fill area
                    floodFill(picArray, x, y);
                }

                else {
                    xClick[0] = xClick[1];
                    yClick[0] = yClick[1];
                    xClick[1] = x;
                    yClick[1] = y;
                }

                // draw black line if no color button was clicked
                StdDraw.setPenColor(Color.black);
                paintLine(picArray, xClick, yClick);
                StdDraw.show();
                StdDraw.pause(200);
            }
        }
    }
    
}


