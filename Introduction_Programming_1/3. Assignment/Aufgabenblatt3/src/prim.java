public class prim {
    public static void main(String[] args) {
        // Grenze bis zu der Primzahlen gesucht werden
        int n = 1000000000;
        int counter = 0;
        for (int i = 2; i <= n; i++) {
            boolean isPrimzahl = true;
            for (int j = 2; j < i && isPrimzahl; j++) {
                if ((i % j) == 0) {
                    isPrimzahl = false;
                }
            }
            if (isPrimzahl) {
                counter++;
                System.out.println(counter);
            }
        }
    }
}
