// Bitte beantworten Sie die Multiple-Choice-Fragen (maximal 25 Punkte, 1 Punkt pro 'Choice').

public class MCTest {

    // Wenn 'answer' in 'new Choice(...)' für davor stehende 'question' zutrifft, 'valid' bitte auf 'true' ändern.
    // Sonst 'valid' auf 'false' belassen (oder auf 'false' zurückändern).
    // Kommentare sind erlaubt, wirken sich aber nicht auf die Beurteilung aus.
    // Bitte sonst nichts ändern. Zur Kontrolle MCTest ausführen.
    public static void main(String[] args) {
        MCQuestion.checkAndPrint(

                new MCQuestion(
                        "Welche der folgenden Aussagen treffen in Bezug auf Algorithmen und Datenstrukturen zu?",

                        new Choice(true, "Absicherung gegen schlechte Datenverteilung kann die Laufzeit erhöhen."),
                        new Choice(false, "Bei unbekannter Datenverteilung gehen wir von Zufallsverteilung aus."),
                        new Choice(false, "Hash-Tabellen eignen sich vor allem gut für geordnete Daten."),
                        new Choice(true, "Arrays sind sehr effizient wenn normale Arrayzugriffe ausreichen."),
                        new Choice(false, "Mergesort wird auf linearen Listen häufiger eingesetzt als Quicksort.")
                ),

                new MCQuestion(
                        "Welche der folgenden Aussagen treffen auf die notwendige Überprüfung von Eingabedaten zu?",

                        new Choice(true, "Reparaturversuche nicht plausibler Daten können gefährlich sein."),
                        new Choice(false, "Alle Daten von außerhalb des Programms müssen überprüft werden."),
                        new Choice(false, "Alle Parameter einer Methode müssen in der Methode überprüft werden."),
                        new Choice(false, "Java-Objekte vom Typ Pattern lesen nur überprüfte Daten ein."),
                        new Choice(true, "Plausibilitätsprüfungen sollen direkt nach der Eingabe erfolgen.")
                ),

                new MCQuestion(
                        "Welche der folgenden Aussagen treffen auf die Einhaltung von Zusicherungen\n" +
                        "entsprechend Design-by-Contract zu?",

                        new Choice(true, "Server müssen für die Einhaltung ihrer Nachbedingungen sorgen."),
                        new Choice(true, "Clients können sich auf die Einhaltung von Nachbedingungen verlassen."),
                        new Choice(false, "Invarianten müssen zu ausnahmslos jedem Zeitpunkt erfüllt sein."),
                        new Choice(false, "Clients müssen für die Einhaltung von Invarianten sorgen."),
                        new Choice(false, "Vor Methodenaufrufen müssen alle Invarianten erfüllt sein.")
                ),

                new MCQuestion(
                        "Welche der folgenden Hoare-Tripel gelten (für Anweisungen in Java)?",

                        new Choice(false, "{true} x = y>z ? y : z; {x>=y}"),
                        new Choice(false, "{x==6} x++; {x==5}"),
                        new Choice(false, "{true} x = y<z ? y : z; {x>=y}"),
                        new Choice(false, "{y<0} while (++y <= 0) x--; {y>0}"),
                        new Choice(false, "{x>0} while (x>0) x--; {x>0}")
                ),

                new MCQuestion(
                        "Welche der folgenden Aussagen treffen auf das Testen großer Programme zu?",

                        new Choice(false, "Regressions-Tests müssen fast immer händisch durchgeführt werden."),
                        new Choice(true, "Eine Code-Review hilft beim Auffinden von Fehlerursachen."),
                        new Choice(false, "Anwender sind wegen möglicher Verfälschungen nicht einzubeziehen."),
                        new Choice(false, "Wiederholte Fehlerkorrektur führt rasch zu fehlerfreien Programmen."),
                        new Choice(true, "Grey-Box-Testen legt Testfälle vor der Implementierung fest.")
                )
        );
    }

    public static final long UID = 238423280279724L;

}
