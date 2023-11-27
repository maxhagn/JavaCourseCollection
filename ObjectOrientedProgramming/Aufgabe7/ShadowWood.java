import java.util.Random;

public abstract class ShadowWood extends Sapling {

    private float height; // height >= 0

    /*
       Ein Objekt von ShadowWood wird erstellt und mit einem Fließkommawert height und einem Objekt coordinate instanziert;
       height >= 0; coordinate != null;
       Ein Objekt von ShadowWood wurde erstellt und besitzt leaves und coordinate Attribute.
    */
    public ShadowWood( float height, Coordinate coordinate ) {
        super( coordinate );
        this.height = height;
    }

    /*
       Gibt height des Objekts zurück;
    */
    public float getHeight() {
        return height;
    }

    /*
      Der Jungbaum von Typ ShadowWood wächst
   */
    @Override
    public void grow() {
        Random random = new Random();
        height += random.nextFloat()*5;
    }

    /*
       Ein Baum des Typs ShadowWood wird als String zurückgegeben;
    */
    @Override
    public String toString( ) {
        return String.format("%20s", height);
    }
}
