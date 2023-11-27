public interface Harvester {
    /*
     * Alle Klassen die das Interface Holzvollernter implementieren müssen ...
     */

    /*
       Erhöht die Betriebsstunden des Holzernters um hours Stunden;
       hours >= 0;
    */
    void raiseOperatingHours(float hours);

    /*
       Gibt die Betriebsstunden des Holzernters zurück;
    */
    float getOperatingHours();

    /*
       Wechselt den Werkzeugaufsatz auf den Aufsatz t;
    */
    void changeToolAttachment(ToolAttachment t);

    /*
       Gibt die maximale Baumlänge des Werkzeugaufsatzes zurück;
    */
    float getMaxTreeLength();

    /*
       Gibt die maximale Baumbreite des Werkzeugaufsatzes zurück;
    */
    int getMaxTreeThickness();

    /*
       Gibt den eingesetzten Werkzeugaufsatz des Holzernters zurück;
    */
    ToolAttachment getToolAttachment();

    /*
       Gibt die Id des Holzernters zurück;
    */
    int getId();

}
