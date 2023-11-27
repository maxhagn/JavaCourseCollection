import java.util.Random;

abstract class Tree {
    private String designation;
    private float health;
    private float estimatedHeight;
    private int age;
    private float density;
    private float browsingRes;
    private float pruningRes;
    private boolean forestryUsable;
    static Random RANDOM = new Random();

    public Tree(String designation, float density, float browsingRes, float pruningRes, boolean forestryUsable) {
        this.designation = designation;
        this.health = RANDOM.nextFloat() * 0.75f + 0.25f;
        this.age = RANDOM.nextInt(200);
        if (this.age >= 80) {
            this.estimatedHeight = 80 * (RANDOM.nextFloat() * 0.3f + 0.2f) + (this.age - 80) * (RANDOM.nextFloat() * 0.1f + 0.1f);
        } else {
            this.estimatedHeight = this.age * (RANDOM.nextFloat() * 0.3f + 0.2f);
        }
        this.density = density;
        this.browsingRes = browsingRes;
        this.pruningRes = pruningRes;
        this.forestryUsable = forestryUsable;
    }

    /*
        species liefert eine Zeichenkette, welche die Spezies des Baumes beschreibt;
        designation wird zurückgegeben;
    */
    public String species() {
        return this.designation;
    }

    /*
        size liefert den Wert estimatedHeight, welcher die Höhe des Baumes beschreibt;
        Wert größer 0 wird zurückgegeben;
    */
    public float size() {
        return this.estimatedHeight;
    }

    /*
        changeSize ändert den Wert in estimatedHeight um einen bestimmten Wert;
        value >= -estimatedHeight;
        estimatedHeight wird verändert;
    */
    public void changeSize(float value) {
        if (value <= 0) {
            this.estimatedHeight -= value;
        } else {
            this.estimatedHeight += value;
        }
    }
}
