public interface Relation<X,Y> {
    /*
     * Alle Klassen die das Interface Relation implementieren müssen die Funktionen related und invoked implementieren
     */

    /**
     * Checks if two Elements are related to each other
     *
     * @return if the elements are related to each other
     */
    public boolean related( X x, Y y );

    /**
     * Invoked returns a counter, which counts the executions of the related function
     *
     * @return int value with the current counter value
     */
    public int invoked( );

}
