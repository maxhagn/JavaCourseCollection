/*
    Aufgabe 1) Code-Analyse - Eindimensionale Arrays
*/
public class Aufgabe1 {
    
    private static void genArray(int[] filledArray) {
        int value = 1;
        for (int i = 0; i < filledArray.length; i++) {
            filledArray[i] = value;
            value += 3;
        }
    }
    
    private static void printFilteredArrayContent(int[] workArray) {
        int[] copiedArray = workArray;
        for (int i = 0; i < copiedArray.length; i++) {
            if (copiedArray[i] % 4 == 0) {
                copiedArray[i] = 0;
            }
        }
        printArray(copiedArray);
    }
    
    private static void genNewArrayContent(int[] workArray) {
        int[] helpArray = new int[15];
        int value = 2;
        for (int i = 0; i < helpArray.length; i++) {
            helpArray[i] = value;
            value += 5;
        }
        workArray = helpArray;
        printArray(workArray);
    }
    
    private static void printArray(int[] workArray) {
        for (int i = 0; i < workArray.length; i++) {
            System.out.print(workArray[i] + " ");
        }
        System.out.println();
    }

    /*
    private static void printArray(int[] workArray) {
        for (int i = 0; i <= workArray.length; i++) {
            System.out.print(workArray[i] + " ");
        }
        System.out.println();
    }
    */
    
    public static void main(String[] args) {
        int[] filledArray = new int[15];
        genArray(filledArray);
        printArray(filledArray);
        
        printFilteredArrayContent(filledArray);
        printArray(filledArray);
        
        filledArray[filledArray.length - 1] = 333;
        printArray(filledArray);
        
        genNewArrayContent(filledArray);
        printArray(filledArray);
    }
    
    //**************************************************************************
    //**** Notizen und Fragebeantwortungen bitte hier unterhalb durchführen! ***
    //**************************************************************************
    //Frage 1:
    //Die Methode length liefert die Länge des Arrays, da aber bei 0 begonnen wird zu zählen wird versucht
    //die letzte Ziffer an die letzte position + 1 zu schreiben, das wirft eine Exeption, da die Länge des Arrays
    //nicht ausreicht -> kann verhindert werden durch length - 1 oder das löschen des = bei der Bedingung
    //Frage 2:
    //Weil die Methode von Type Void ist und Void keinen Rückgabewert liefert. Das Array wird trotzdem befüllt,
    //da das Array schon vor der Methode definiert wurde und somit direkt dieses zuvor definiete Array befüllt.
    //Frage 3:
    //Copied Array verwendet die selbe Referenz, sprich workarray wird ebenfalls verändert
    //Frage 4:
    //weil die referenz nur in der Methode geändert wird, im Main bleibt die Referenz bestehen


    /*
        Zusatzfragen
        1. Welchen Datentyp muss der Indexausdruck haben, mit dem die Position in einem Array berechnet wird?
           Integer
        2. Müssen Sie ein Array initialisieren?
           Die Länge des Arrays muss angegeben werden, der Inhalt wird selbst befüllt, String z.B. mit null
        3. Wie kann die Länge eines Arrays verändert werden?
           Direkt soweit nicht, indirekt könnte ein neues Array angelegt werden und daraufhin die Daten vom vorigen kopiert werden
        4. Wie gehen Sie vor, wenn Sie ein int-Array kopieren müssen?
           die Funktion .clone() klont, wie der Name schon sagt ein Objekt
    */
}

