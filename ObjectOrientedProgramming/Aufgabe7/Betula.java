public class Betula extends LightWood {

    /*
       Ein Objekt von Betula wird erstellt und mit einem Ganzzahlenwert leaves und einem Objekt coordinate instanziert;
       leaves >= 0; coordinate != null;
       Ein Objekt von Betula wurde erstellt und besitzt leaves und coordinate Attribute.
    */
    public Betula( int leaves, Coordinate coordinate  ) {
        super( leaves, coordinate );
    }

    /*
       Gibt für einen Betula einen Wahrheitswert zurück, der besagt ob Betula diese Schattenart mag;
       t != null;
       Ein Wahrheitswert wird zurückgegeben;
    */
    public boolean likes(ShadowType t){
        return t.getShadow() == 3;
    }

    /*
       Gibt einen Ganzzahlwert zurück, der besagt wie gut ein Betula ist;
       check != null;
       Ein Ganzzahlenwert >= 0 wird zurückgegeben;
    */
    public int likelyToRemove(boolean check){
        return super.getLeaves();
    }

    /*
       Gibt für einen Betula den etablierten Baum zurück (also einen ShadowType);
       Ein ShadowType wird zurückgegeben;
    */
    public ShadowType getGrownShadowType(){
        return new BelowNonFagus();
    }

    /*
       Gibt für einen Betula einen Wahrheitswert zurück, der besagt um welche Art Baum es sich handelt;
       Ein Wahrheitswert wird zurückgegeben;
    */
    public boolean betulaOrFagus() {
        return true;
    }

    /*
       Gibt für einen Betula einen Wahrheitswert zurück, der besagt ob es sich um einen priorisierten Baum;
       Ein Wahrheitswert wird zurückgegeben;
    */
    public boolean isPrioritized() {
        return false;
    }

    /*
       Gibt die Klasse als String aus;
    */
    @Override
    public String toString() {
        return super.toString() + String.format("%20s", "Betula");
    }
}
