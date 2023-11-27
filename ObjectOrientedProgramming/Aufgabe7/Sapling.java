public abstract class Sapling {

    private Coordinate coordinate; // coordinate != null

    /*
       Ein Objekt von Sapling wird erstellt und mit einem Objekt coordinate instanziert;
       coordinate != null;
       Ein Objekt von Sapling wurde erstellt und besitzt ein coordinate Attribute.
    */
    public Sapling( Coordinate coordinate ) {
        this.coordinate = coordinate;
    }

    /*
       Ein Jungbaum wächst
    */
    public void grow() {

    }

    /*
      Gibt für einen Sapling einen Wahrheitswert zurück, der besagt ob Sapling diese Schattenart mag;
      t != null;
      Ein Wahrheitswert wird zurückgegeben;
   */
    public boolean likes(ShadowType t){
        return true;
    }

    /*
       Gibt einen Ganzzahlwert zurück, der besagt wie gut ein Sapling ist;
       check != null;
       Ein Ganzzahlenwert >= 0 wird zurückgegeben;
    */
    public int likelyToRemove(boolean check){
        return 0;
    }

    /*
       Gibt für einen Sapling einen Wahrheitswert zurück, der besagt um welche Art Baum es sich handelt;
       Ein Wahrheitswert wird zurückgegeben;
    */
    public boolean betulaOrFagus() {
        return false;
    }

    /*
       Gibt für einen Sapling einen Wahrheitswert zurück, der besagt ob es sich um einen priorisierten Baum;
       Ein Wahrheitswert wird zurückgegeben;
    */
    public boolean isPrioritized() {
        return false;
    }

    /*
       Gibt für einen Sapling den etablierten Baum zurück (also einen ShadowType);
       Ein ShadowType wird zurückgegeben;
    */
    public ShadowType getGrownShadowType(){
        return new OpenArea();
    }

    /*
       Gibt coordinate des Objekts zurück;
    */
    public Coordinate getCoordinate( ) {
        return coordinate;
    }

    /*
       Der Jungbaum wird als String zurückgegeben;
    */
    @Override
    public String toString( ) {

        return "Sapling{" +
                "coordinate=" + coordinate +
                '}';
    }

}
