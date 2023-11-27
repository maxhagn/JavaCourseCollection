public class Quercus extends LightWood {

    /*
       Ein Objekt von Quercus wird erstellt und mit einem Ganzzahlenwert leaves und einem Objekt coordinate instanziert;
       leaves >= 0; coordinate != null;
       Ein Objekt von Quercus wurde erstellt und besitzt leaves und coordinate Attribute.
    */
    public Quercus( int leaves, Coordinate coordinate  ) {
        super( leaves, coordinate );
    }

    /*
       Gibt für einen Quercus einen Wahrheitswert zurück, der besagt ob Quercus diese Schattenart mag;
       t != null;
       Ein Wahrheitswert wird zurückgegeben;
    */
    public boolean likes(ShadowType t){
        return t.getShadow() == 3;
    }

    /*
       Gibt einen Ganzzahlwert zurück, der besagt wie gut ein Quercus ist;
       check != null;
       Ein Ganzzahlenwert >= 0 wird zurückgegeben;
    */
    public int likelyToRemove(boolean check){
        if (check) {
            return 999999999;
        }
        return 9999999 + super.getLeaves();
    }

    /*
      Gibt für einen Quercus den etablierten Baum zurück (also einen ShadowType);
      Ein ShadowType wird zurückgegeben;
   */
    public ShadowType getGrownShadowType(){
        return new BelowNonFagus();
    }

    /*
       Gibt für einen Quercus einen Wahrheitswert zurück, der besagt um welche Art Baum es sich handelt;
       Ein Wahrheitswert wird zurückgegeben;
    */
    public boolean betulaOrFagus() {
        return false;
    }

    /*
       Gibt für einen Quercus einen Wahrheitswert zurück, der besagt ob es sich um einen priorisierten Baum;
       Ein Wahrheitswert wird zurückgegeben;
    */
    public boolean isPrioritized() {
        return true;
    }

    /*
       Der String "Quercus" wird zurückgegeben
    */
    @Override
    public String toString() {
        return super.toString() + String.format("%20s", "Quercus");
    }
}
