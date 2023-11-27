public class CarpinusBetulus extends ShadowWood {

    /*
       Ein Objekt von CarpinusBetulus wird erstellt und mit einem Gleikommawert height und einem Objekt coordinate instanziert;
       height >= 0; coordinate != null;
       Ein Objekt von CarpinusBetulus wurde erstellt und besitzt height und coordinate Attribute.
    */
    public CarpinusBetulus( float height, Coordinate coordinate  ) {
        super( height, coordinate );
    }

    /*
       Gibt für einen CarpinusBetulus einen Wahrheitswert zurück, der besagt ob CarpinusBetulus diese Schattenart mag;
       t != null;
       Ein Wahrheitswert wird zurückgegeben;
    */
    public boolean likes(ShadowType t){
        return t.getShadow() == 2;
    }

    /*
       Gibt für CarpinusBetulus einen Ganzzahlwert zurück, der besagt wie gut ein CarpinusBetulus ist;
       check != null;
       Ein Ganzzahlenwert >= 0 wird zurückgegeben;
    */
    public int likelyToRemove(boolean check){
        if (check) {
            return 999999999;
        }
        return 9999999 + (int)super.getHeight();
    }

    /*
       Gibt für einen CarpinusBetulus den etablierten Baum zurück (also einen ShadowType);
       Ein ShadowType wird zurückgegeben;
    */
    public ShadowType getGrownShadowType(){
        return new BelowNonFagus();
    }

    /*
       Gibt für einen CarpinusBetulus einen Wahrheitswert zurück, der besagt um welche Art Baum es sich handelt;
       Ein Wahrheitswert wird zurückgegeben;
    */
    public boolean betulaOrFagus() {
        return false;
    }

    /*
       Gibt für einen CarpinusBetulus einen Wahrheitswert zurück, der besagt ob es sich um einen priorisierten Baum;
       Ein Wahrheitswert wird zurückgegeben;
    */
    public boolean isPrioritized() {
        return true;
    }

    /*
       Gibt die Klasse als String aus;
    */
    @Override
    public String toString() {
        return super.toString() + String.format("%20s", "QarpinusBetulus");
    }
}
