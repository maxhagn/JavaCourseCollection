/*
    Aufgabe 4) Zweidimensionale Arrays - Berechnungen
*/
public class Aufgabe4 {
    
    private static int[][] genFilledArray(int centerNumber) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        if (centerNumber > 0 || centerNumber % 2 != 0) {
            int[][] result = new int[centerNumber][centerNumber];
            for (int i = 0; i < centerNumber; i++) {
                for (int j = 0; j < centerNumber; j++) {
                    result[i][j] = (i < (centerNumber / 2) ? i : centerNumber - i - 1) +
                            (j < (centerNumber / 2) ? j : centerNumber - j - 1) + 1;
                }
            }
            return result;
        } else {
            return null;
        }
    }
    
    private static int[][] calcSumInArray(int[][] workArray) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        int centerNumber = workArray.length;

        int[][] result = new int[centerNumber][centerNumber];
        for (int i = 0; i < centerNumber; i++) {
            for (int j = 0; j < centerNumber; j++) {
                int sum = 0;

                sum += (j - 1 >= 0 && i - 1 >= 0) ? workArray[j - 1][i - 1] : 0;
                sum += (j - 1 >= 0) ? workArray[j - 1][i] : 0;
                sum += (j - 1 >= 0 && i + 1 < centerNumber) ? workArray[j - 1][i + 1] : 0;
                sum += (i - 1 >= 0) ? workArray[j][i - 1] : 0;
                sum += workArray[j][i];
                sum += (i + 1 < centerNumber) ? workArray[j][i + 1] : 0;
                sum += (j + 1 < centerNumber && i - 1 >= 0) ? workArray[j + 1][i - 1] : 0;
                sum += (j + 1 < centerNumber) ? workArray[j + 1][i] : 0;
                sum += (j + 1 < centerNumber && i + 1 < centerNumber) ? workArray[j + 1][i + 1] : 0;

                result[j][i] = sum;
            }
        }
        return result;
    }
    
    private static void print(int[][] workArray) {
        if(workArray != null) {
            for (int y = 0; y < workArray.length; y++) {
                for (int x = 0; x < workArray.length; x++) {
                    System.out.print(workArray[y][x] + "\t");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        int[][] myArray;
        int[][] myResultArray;
        // Test für centerNumber = 5
        myArray = genFilledArray(5);
        print(myArray);
        myResultArray = calcSumInArray(myArray);
        print(myResultArray);
        //Test für centerNumber = 9
        myArray = genFilledArray(9);
        print(myArray);
        myResultArray = calcSumInArray(myArray);
        print(myResultArray);
    }
    
    
}
