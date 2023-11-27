import java.awt.*;

/*
    Aufgabe 2) Eindimensionale Arrays - Schreiben, Lesen und Verwenden
*/
public class Aufgabe2 {
    
    private static int[] genRandomArray(int numValues, int maxValue) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        int[] result = new int[numValues];
        for (int i = 0; i < result.length; i++) {
            result[i] = (int)(Math.random() * maxValue);
        }
        return result;
    }
    
    private static int getMaxValueFromArray(int[] array) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Method
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > result) {
                result = array[i];
            }
        }
        return result;
    }
    
    private static int[] calcStatistics(int[] array) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        int maxValue = getMaxValueFromArray(array);
        System.out.println(maxValue);
        int[] result = new int[maxValue+1];
        for(int i = 0; i < array.length; i++) {
            result[array[i]]++;
        }
        return result;
    }
    
    private static void drawBarChart(int[] array) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        //General Settings
        int width = 600, height = 400;
        double barWidth = width / array.length, maxValue = getMaxValueFromArray(array);
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.enableDoubleBuffering();

        Font font = new Font("Avenir Next", Font.TRUETYPE_FONT, 12);
        StdDraw.setFont(font);

        for(int i = 0; i < array.length; i++) {
            double barHeight = (height / maxValue) * array[i];
            StdDraw.setPenColor(Color.green);
            StdDraw.filledRectangle(barWidth/2 + barWidth * i,barHeight/2,barWidth/2,barHeight/2);
            StdDraw.setPenColor(Color.black);
            StdDraw.rectangle(barWidth/2 + barWidth * i,barHeight/2,barWidth/2,barHeight/2);
            StdDraw.text(barWidth/2 + barWidth * i, 10, Integer.toString(i));
            StdDraw.text(barWidth/2 + barWidth * i, barHeight - 10 , Integer.toString(array[i]));
        }
    }
    
    private static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        //Test 1: 50 Werte im Intervall von [0,20[
        int[] myArray = genRandomArray(50, 20);
        int[] myStat = calcStatistics(myArray);
        print(myArray);
        print(myStat);
        drawBarChart(myStat);
        StdDraw.show();

        //Test 2: 100 Werte im Intervall von [0,10[
        StdDraw.pause(10000);
        myArray = genRandomArray(100, 10);
        myStat = calcStatistics(myArray);
        print(myArray);
        print(myStat);
        drawBarChart(myStat);
        StdDraw.show();
    }
}

