/*
    Aufgabe 2) Zweidimensionale Arrays und Methoden - Vier Gewinnt
*/

import java.awt.*;

public class Aufgabe2 {

    // generates the game board Array
    private static int[][] genGameBoard(int x, int y) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        return new int[x][y];
    }

    // draws the new game board
    private static void drawGameBoard(int[][] currentGameBoard, int oneSquareSize) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        // draw blue background
        StdDraw.clear(StdDraw.BLUE);

        // draw points
        for (int i = 0; i < currentGameBoard.length; i++) {
            for (int j = 0; j < currentGameBoard[i].length; j++) {

                // color points for player or empty
                switch (currentGameBoard[i][j]) {
                    case 0:
                        StdDraw.setPenColor(Color.GRAY);
                        break;
                    case 1:
                        StdDraw.setPenColor(Color.RED);
                        break;
                    case 2:
                        StdDraw.setPenColor(Color.YELLOW);
                        break;
                    default:
                        break;
                }

                StdDraw.filledCircle((i * oneSquareSize) + (oneSquareSize / 2f), (j * oneSquareSize) + (oneSquareSize / 2f), oneSquareSize / 3f);
            }
        }
    }

    // adds a new move
    private static boolean move(int[][] currentGameBoard, int player, int col) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        for (int j = 0; j < currentGameBoard[col].length; j++) {
            if (currentGameBoard[col][j] == 0) {
                currentGameBoard[col][j] = player;
                return true;
            }
        }
        return false;
    }

    // check if a player won the game
    private static boolean checkGameStatus(int[][] currentGameBoard, int player) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        // iterates through whole game board
        for (int i = 0; i < currentGameBoard.length; i++) {
            for (int j = 0; j < currentGameBoard[i].length; j++) {
                if (currentGameBoard[i][j] == player) {

                    // vertical win
                    for (int vCount = 1; j + vCount <= currentGameBoard[i].length-1; vCount++) {
                        if (currentGameBoard[i][j+vCount]==player) {
                            vCount++;
                        } else {
                            break;
                        }
                        if (vCount>=4) {
                            return true;
                        }
                    }

                    // horizontal win
                    for (int hCount = 1; i + hCount <= currentGameBoard.length-1; hCount++) {
                        if (currentGameBoard[i+hCount][j]==player) {
                            hCount++;
                        } else {
                            break;
                        }
                        if (hCount>=4) {
                            return true;
                        }
                    }

                    // diagonal win
                    for (int d1Count = 1; i + d1Count <= currentGameBoard.length-1 &&
                            j + d1Count <= currentGameBoard[i].length-1; d1Count++) {
                        if (currentGameBoard[i+d1Count][j+d1Count]==player) {
                            d1Count++;
                        } else {
                            break;
                        }
                        if (d1Count>=4) {
                            return true;
                        }
                    }

                    // diagonal win
                    for (int d2Count = 1; i - d2Count >=0 &&
                            j + d2Count <= currentGameBoard[i].length-1; d2Count++) {
                        if (currentGameBoard[i-d2Count][j+d2Count]==player) {
                            d2Count++;
                        } else {
                            break;
                        }
                        if (d2Count>=4) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        
        // canvas settings
        int rowsGameBoard = 6;
        int colsGameBoard = 7;
        int oneSquareSize = 50;
        int width = oneSquareSize * colsGameBoard;
        int height = oneSquareSize * rowsGameBoard;

        // general std draw settings
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.setFont(new Font("Arial", Font.BOLD, 30));
        StdDraw.enableDoubleBuffering();
        
        // game variables
        int[][] myGameBoard = genGameBoard(colsGameBoard, rowsGameBoard);
        boolean fullBoard = false;
        boolean isWon = false;
        int player = 1;
        int fieldsUsed = 0;
        
        // initial draw of the game board
        drawGameBoard(myGameBoard, oneSquareSize);
        StdDraw.show();
        
        // game play starts
        System.out.println("Player " + player + (player == 1 ? " (RED)" : " (YELLOW)") + " has to make a move!");

        while (!fullBoard && !isWon) {
            if (StdDraw.isMousePressed()) {
                // TODO: Implementieren Sie hier die fehlende Spiellogik
                // if column not full -> make a new move
                if (move(myGameBoard, player, (int)(StdDraw.mouseX() / oneSquareSize))) {
                    drawGameBoard(myGameBoard, oneSquareSize);
                    fieldsUsed++;

                    isWon = checkGameStatus(myGameBoard, player);
                    fullBoard = fieldsUsed == rowsGameBoard * colsGameBoard;

                    if (!isWon && !fullBoard) {
                        player = (player == 1) ? 2 : 1;
                        System.out.println("Player " + player + (player == 1 ? " (RED)" : " (YELLOW)") + " has to make a move!");
                    }
                }

                // text if column is full
                else
                {
                    StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
                    StdDraw.text(width / 2f, height / 2f, "Column already full!");
                }

                StdDraw.show();
                StdDraw.pause(500);

            }
        }

        // print text if won
        if (isWon) {
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.text(width / 2f, height / 2f, ("Player " + player + (player == 1 ? " (RED)" : " (YELLOW)") + " won!"));
            StdDraw.show();
        }

        // print text if board full
        if (fullBoard) {
            StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
            StdDraw.text(width / 2f, height / 2f, "Board full!!");
            StdDraw.show();
        }
    }
}



