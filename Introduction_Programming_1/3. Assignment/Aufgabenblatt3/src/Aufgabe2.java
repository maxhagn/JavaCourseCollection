/*
    Aufgabe 2) Neugestaltung von Spaghetti-Code durch Methoden
*/
public class Aufgabe2 {

    public static void main(String[] args) {
        // Print Heading to compare the Output
        System.out.println("Spaghetti-Code \n");

        // Declaring vars
        String text1 = "Have a great day!";
        String text2 = "This is a Teststring";
        int num1 = 35061;
        int num2 = 1010;

        // Prints text1 with - between letters
        for (int i = 0; i < text1.length() - 1; i++) {
            System.out.print(text1.charAt(i));
            System.out.print('-');
        }
        System.out.println(text1.charAt(text1.length() - 1));

        // Prints completely useless the num1 with * between digits
        int decades = 1;
        int digitCount = 0;
        while (num1 > decades) {
            decades = decades * 10;
            digitCount++;
        }
        decades /= 10;
        while (num1 > 0) {
            System.out.print(num1 / decades);
            num1 %= decades;
            digitCount--;
            if (num1 > 0) {
                System.out.print('*');
            } else if (digitCount > 0) {
                System.out.println("*0");
            }
            decades /= 10;
        }
        System.out.println();

        // Prints text2 with points between letters
        int len = text2.length() - 1;
        while (len > -1) {
            System.out.print(text2.charAt(text2.length() - 1 - len));
            if (len > 0) {
                System.out.print('.');
            }
            len--;
        }

        // Prints text1 three times with different separators
        System.out.println();
        String separators = "#.+";
        for (int i = 0; i < separators.length(); i++) {
            for (int j = 0; j < text1.length() - 1; j++) {
                System.out.print(text1.charAt(j));
                System.out.print(separators.charAt(i));
            }
            System.out.println(text1.charAt(text1.length() - 1));
        }

        // Prints completely useless the num1 with # between digits
        int dec = 1;
        int numDigits = 0;
        while (num2 > dec) {
            dec *= 10;
            numDigits += 1;
        }
        dec = dec / 10;
        while (num2 > 0) {
            numDigits--;
            System.out.print(num2 / dec);
            num2 = num2 % dec;
            if (num2 > 0) {
                System.out.print('#');
            } else if (numDigits > 0) {
                System.out.print('#');
                System.out.println('0');
            }
            dec /= 10;
        }
        System.out.println();

        // Resetting var just to be sure
        text1 = "Have a great day!";
        text2 = "This is a Teststring";
        num1 = 35061;
        num2 = 1010;

        System.out.println("\n\nNormal code without getting headache \n");
        System.out.println(separate(text1, "-"));
        System.out.println(separate(num1, "*"));
        System.out.println(separate(text2, "."));
        System.out.println(separate(text1, "#"));
        System.out.println(separate(text1, "."));
        System.out.println(separate(text1, "+"));
        System.out.println(separate(num2, "#"));

    }


    //TODO: Implementieren Sie hier Ihre Methoden
    // Separates the String with given separators
    public static String separate(String input, String separator) {
        String erg = "";
        for (int i = 0; i < input.length() - 1; i++) {
            erg = erg.concat(input.charAt(i) + separator);
        }
        erg += input.charAt(input.length() - 1);
        return erg;
    }

    // Separates the Number with given separators
    public static String separate(int input, String separator) {
        String erg = "";
        while (input > 0) {
            erg = (input % 10) + separator + erg;
            input = input / 10;
        }
        return erg.substring(0, erg.length() - 1);
    }

    /*
        Zusatzfragen

        1. Warum sollte man Spaghetti-Code vermeiden?
           Weil schwer nachzuvollziehen, vor allem wenn lange nicht Programmiert wird
        2. Warum sollte man versuchen, duplizierten Code zu vermeiden?
           Weil die Performance und Lesbarkeit zerstört wird
        3. Wie sollten die Namen von Methoden gewaehlt werden?
           So dass die Programmiererin in einem Jahr noch weiß wofür die Methode verwendet wurde.

    */


}
