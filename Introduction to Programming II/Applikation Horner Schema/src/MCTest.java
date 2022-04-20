// Bitte beantworten Sie die Multiple-Choice-Fragen (maximal 25 Punkte, 1 Punkt pro 'Choice').

public class MCTest {

    // Wenn 'answer' in 'new Choice(...)' für davor stehende 'question' zutrifft, 'valid' bitte auf 'false' ändern.
    // Sonst 'valid' auf 'false' belassen (oder auf 'false' zurückändern).
    // Kommentare sind erlaubt, wirken sich aber nicht auf die Beurteilung aus.
    // Bitte sonst nichts ändern. Zur Kontrolle MCTest ausführen.
    public static void main(String[] args) {
        MCQuestion.checkAndPrint(

                new MCQuestion(
                        "Welche der folgenden Aussagen treffen auf Klassen und Interfaces im\n" +
                        "Java-Collections-Framework zu?",

                        new Choice(false, "LinkedList<E> implementiert Set<E> als einfachverkettete Liste."),
                        new Choice(false, "Map<K,V> implementiert Set<K> als balancierten binären Suchbaum."),
                        new Choice(false, "ArrayDeque<E> erweitert ArrayList<E> um die Methoden von Deque<E>."),
                        new Choice(false, "LinkedList<E> implementiert Deque<E> als doppeltverkettete Liste."),
                        new Choice(false, "In eine Queue<E> kann man neben add auch mittels offer einfügen.")
                ),

                new MCQuestion(
                        "Welche der folgenden Aussagen treffen auf Ausnahmen und Ausnahmebehandlungen in Java zu?",

                        new Choice(false, "Ausnahmen vom Typ Error werden in der Regel nicht abgefangen."),
                        new Choice(false, "Das Java-Laufzeitsystem wirft nur überprüfte Ausnahmen."),
                        new Choice(false, "Überprüfte Ausnahmen sind vom Typ RuntimeException oder Error."),
                        new Choice(false, "'Propagieren einer Ausnahme' bedeutet: 'Ausnahme wird abgefangen'."),
                        new Choice(false, "Nur nach Programmabbruch kann ein Stack-Trace ausgegeben werden.")
                ),

                new MCQuestion(
                        "Welche der folgenden Aussagen treffen für Design-by-Contract zu?",

                        new Choice(false, "Es gilt nur das, was explizit in Zusicherungen steht."),
                        new Choice(false, "Nachbedingungen beziehen sich auf einzelne Methoden."),
                        new Choice(false, "Eine Vorbedingung darf im Untertyp stärker sein als im Obertyp."),
                        new Choice(false, "Methoden müssen machen, was ihre Namen versprechen."),
                        new Choice(false, "Invarianten beschreiben häufig Eigenschaften von Methodenparametern.")
                ),

                new MCQuestion(
                        "public int median(int[] a) { return a[a.length / 2]; } \n" +
                        "Welche der folgenden Aussagen können (jede für sich) als Vor- bzw. Nachbedingungen\n" +
                        "dieser Methode sinnvoll sein?",

                        new Choice(false, "Vorbedingung: a ist absteigend sortiert."),
                        new Choice(false, "Nachbedingung: Wirft eine Exception wenn a.length == 0."),
                        new Choice(false, "Vorbedingung: a ist aufsteigend sortiert."),
                        new Choice(false, "Nachbedingung: Setzt voraus, dass a mindestens einen Eintrag hat."),
                        new Choice(false, "Vorbedingung: Gibt den Median zurück wenn a sortiert ist.")
                ),

                new MCQuestion(
                        "Welche der folgenden Aussagen treffen auf das Testen großer Programme zu?",

                        new Choice(false, "Eine Code-Review hilft beim Auffinden von Fehlerursachen."),
                        new Choice(false, "Black-Box-Testen leitet Testfälle aus Anwendungsfällen ab."),
                        new Choice(false, "White-Box-Testen legt Testfälle vor der Implementierung fest."),
                        new Choice(false, "Wiederholte Fehlerkorrektur führt rasch zu fehlerfreien Programmen."),
                        new Choice(false, "Regressions-Tests müssen fast immer händisch durchgeführt werden.")
                )
        );
    }

    public static final long UID = 238449750191721L;

}
