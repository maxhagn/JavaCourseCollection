/*
    Aufgabe 3) Ein-/Zweidimensionale Arrays - Arbeiten mit String-Arrays
*/
public class Aufgabe3 {
    
    private static String[] extractSentences(String longString) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        int sentences = 0;
        for (int i = 0; i < longString.length(); i++) {
            if (longString.charAt(i) == '.') sentences++;
        }

        if (sentences == 0) {
            return null;
        }

        String[] result = new String[sentences];
        int counter = 0;
        String sub = "";
        for (int i = 0; i < longString.length(); i++) {
            if ( i == 0 || longString.charAt(i) == '.') { result[counter] = ""; }
            sub += longString.charAt(i);
            if (longString.charAt(i) == '.') {
                result[counter++] = sub;
                sub = "";

            }

        }
        return result;
    }
    
    private static void printArray(String[] workArray) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        if (workArray != null) {
            for (int i = 0; i < workArray.length; i++) {
                System.out.println(workArray[i]);
            }
        }
    }
    
    private static String[][] countSentenceLength(String[] workArray) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        if (workArray != null) {
            String[][] result = new String[2][workArray.length];
            for (int i = 0; i < workArray.length; i++) {
                result[0][i] = workArray[i];
                result[1][i] = Integer.toString(workArray[i].length());
            }
            return result;
        } else {
            return null;
        }
    }
    
    private static void printArray(String[][] workArray) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        if (workArray != null) {
            for (int i = 0; i < workArray[1].length; i++) {
                System.out.println(workArray[0][i] + " --> " + workArray[1][i]);
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println(" Test 1 ");
        String[] sentences1 = extractSentences("Ich gehe jetzt einkaufen.Das Auto ist rot.Morgen gehen wir ins Kino.");
        printArray(sentences1);
        String[][] countedWordLength1 = countSentenceLength(sentences1);
        printArray(countedWordLength1);
        System.out.println();

        System.out.println(" Test 2 ");
        String[] sentences2 = extractSentences("Ich bin der erste Satz.Ich bin der zweite Satz ohne Punkt");
        printArray(sentences2);
        String[][] countedWordLength2 = countSentenceLength(sentences2);
        printArray(countedWordLength2);
        System.out.println();

        System.out.println(" Test 3 ");
        String[] sentences3 = extractSentences("Das ist ein Satz ohne Punkt");
        printArray(sentences3);
        String[][] countedWordLength3 = countSentenceLength(sentences3);
        printArray(countedWordLength3);
    }

    /* Zusatzfragen
        1. Wie wird ein String-Array initialisiert?
           mit null
        2. Was ist der Unterschied zwischen String myString = ""; und String myString = null;?
           null wird beim concat als "null" aufgefasst, deswegen muss der komplette Wert überschrieben werden und die Character können nicht einfach angehängt werden.
           Null Pointer ist bis zur ersten Operation kein richtiger Wert sondern nur eine Referenz

     */

}


