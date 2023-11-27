public class Fagus extends ShadowWood {

    /*
       Ein Objekt von Fagus wird erstellt und mit einem Gleikommawert height und einem Objekt coordinate instanziert;
       height >= 0; coordinate != null;
       Ein Objekt von Fagus wurde erstellt und besitzt height und coordinate Attribute.
    */
    public Fagus( float height, Coordinate coordinate  ) {
        super( height, coordinate );
    }

    /*
       Gibt für einen Fagus einen Wahrheitswert zurück, der besagt ob Fagus diese Schattenart mag;
       t != null;
       Ein Wahrheitswert wird zurückgegeben;
    */
    public boolean likes(ShadowType t){
        return t.getShadow() == 1 || t.getShadow() == 2;
    }

    /*
       Gibt für Fagus einen Ganzzahlwert zurück, der besagt wie gut ein Fagus ist;
       check != null;
       Ein Ganzzahlenwert >= 0 wird zurückgegeben;
    */
    public int likelyToRemove(boolean check){
        return (int)super.getHeight();
    }

    /*
       Gibt für einen Fagus den etablierten Baum zurück (also einen ShadowType);
       Ein ShadowType wird zurückgegeben;
    */
    public ShadowType getGrownShadowType(){
        return new BelowFagus();
    }

    /*
       Gibt für einen Fagus einen Wahrheitswert zurück, der besagt um welche Art Baum es sich handelt;
       Ein Wahrheitswert wird zurückgegeben;
    */
    public boolean betulaOrFagus() {
        return true;
    }

    /*
       Gibt für einen Fagus einen Wahrheitswert zurück, der besagt ob es sich um einen priorisierten Baum;
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
        return super.toString() + String.format("%20s", "Fagus");
    }
}
