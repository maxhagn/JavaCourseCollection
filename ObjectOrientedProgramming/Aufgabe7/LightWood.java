import java.util.Random;

public abstract class LightWood extends Sapling {

    private int leaves; // leaves >= 0

    /*
       Ein Objekt von LightWood wird erstellt und mit einem Ganzzahlenwert leaves und einem Objekt coordinate instanziert;
       leaves >= 0; coordinate != null;
       Ein Objekt von LightWood wurde erstellt und besitzt leaves und coordinate Attribute.
    */
    public LightWood( int leaves, Coordinate coordinate ) {
        super( coordinate );
        this.leaves = leaves;
    }

    /*
       Gibt leaves des Objekts zurück;
    */
    public int getLeaves() {
        return leaves;
    }

    /*
       Der Jungbaum von Typ LightWood wächst
    */
    @Override
    public void grow() {
        Random random = new Random();
        leaves += random.nextInt(50);
    }

    /*
       Die leaves von LightWood werden als String zurückgegeben
    */
    @Override
    public String toString( ) {
        return String.format("%20s", leaves);
    }
}
