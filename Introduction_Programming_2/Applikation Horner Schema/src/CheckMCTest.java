// Bitte nicht ändern. 'CheckMCTest' wird von anderen Klassen verwendet.
public class CheckMCTest {

    public static final String EXPECT =
            " 1. Welche der folgenden Aussagen treffen auf Klassen und Interfaces im\n" +
            "    Java-Collections-Framework zu?\n" +
            "    \n" +
            "    XXXXXXXXX LinkedList<E> implementiert Set<E> als einfachverkettete Liste.\n" +
            "    XXXXXXXXX Map<K,V> implementiert Set<K> als balancierten binären Suchbaum.\n" +
            "    XXXXXXXXX ArrayDeque<E> erweitert ArrayList<E> um die Methoden von Deque<E>.\n" +
            "    XXXXXXXXX LinkedList<E> implementiert Deque<E> als doppeltverkettete Liste.\n" +
            "    XXXXXXXXX In eine Queue<E> kann man neben add auch mittels offer einfügen.\n" +
            "\n" +
            " 2. Welche der folgenden Aussagen treffen auf Ausnahmen und Ausnahmebehandlungen in Java zu?\n" +
            "    \n" +
            "    XXXXXXXXX Ausnahmen vom Typ Error werden in der Regel nicht abgefangen.\n" +
            "    XXXXXXXXX Das Java-Laufzeitsystem wirft nur überprüfte Ausnahmen.\n" +
            "    XXXXXXXXX Überprüfte Ausnahmen sind vom Typ RuntimeException oder Error.\n" +
            "    XXXXXXXXX 'Propagieren einer Ausnahme' bedeutet: 'Ausnahme wird abgefangen'.\n" +
            "    XXXXXXXXX Nur nach Programmabbruch kann ein Stack-Trace ausgegeben werden.\n" +
            "\n" +
            " 3. Welche der folgenden Aussagen treffen für Design-by-Contract zu?\n" +
            "    \n" +
            "    XXXXXXXXX Es gilt nur das, was explizit in Zusicherungen steht.\n" +
            "    XXXXXXXXX Nachbedingungen beziehen sich auf einzelne Methoden.\n" +
            "    XXXXXXXXX Eine Vorbedingung darf im Untertyp stärker sein als im Obertyp.\n" +
            "    XXXXXXXXX Methoden müssen machen, was ihre Namen versprechen.\n" +
            "    XXXXXXXXX Invarianten beschreiben häufig Eigenschaften von Methodenparametern.\n" +
            "\n" +
            " 4. public int median(int[] a) { return a[a.length / 2]; } \n" +
            "    Welche der folgenden Aussagen können (jede für sich) als Vor- bzw. Nachbedingungen\n" +
            "    dieser Methode sinnvoll sein?\n" +
            "    \n" +
            "    XXXXXXXXX Vorbedingung: a ist absteigend sortiert.\n" +
            "    XXXXXXXXX Nachbedingung: Wirft eine Exception wenn a.length == 0.\n" +
            "    XXXXXXXXX Vorbedingung: a ist aufsteigend sortiert.\n" +
            "    XXXXXXXXX Nachbedingung: Setzt voraus, dass a mindestens einen Eintrag hat.\n" +
            "    XXXXXXXXX Vorbedingung: Gibt den Median zurück wenn a sortiert ist.\n" +
            "\n" +
            " 5. Welche der folgenden Aussagen treffen auf das Testen großer Programme zu?\n" +
            "    \n" +
            "    XXXXXXXXX Eine Code-Review hilft beim Auffinden von Fehlerursachen.\n" +
            "    XXXXXXXXX Black-Box-Testen leitet Testfälle aus Anwendungsfällen ab.\n" +
            "    XXXXXXXXX White-Box-Testen legt Testfälle vor der Implementierung fest.\n" +
            "    XXXXXXXXX Wiederholte Fehlerkorrektur führt rasch zu fehlerfreien Programmen.\n" +
            "    XXXXXXXXX Regressions-Tests müssen fast immer händisch durchgeführt werden.\n" +
            "\n";

    public static final long UID = 238449750191721L;

}
