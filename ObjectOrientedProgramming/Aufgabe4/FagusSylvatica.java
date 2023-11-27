import java.util.Random;

public class FagusSylvatica extends Fagacea implements Domestic {
    private float latitude;
    private float longitude;
    static Random RANDOM = new Random();
    static float LONG_MIN = 9.54f;
    static float LONG_MAX = 17.11f;
    static float LAT_MIN = 46.43f;
    static float LAT_MAX = 49;


    public FagusSylvatica() {
        super("Fagus Sylvatica", 1, 0.2f, 0.4f, false);
        this.latitude = RANDOM.nextFloat()*(LAT_MAX - LAT_MIN)+LAT_MIN;
        this.longitude = RANDOM.nextFloat()*(LONG_MAX - LONG_MIN)+LONG_MIN;
    }

    /*
        latitude gibt latitude zurück;
        Wert zwischen LAT_MIN und LAT_MAX zurückgeben;
    */
    @Override
    public float latitude() {
        return this.latitude;
    }

    /*
        longitude gibt longitude zurück;
        Wert zwischen LONG_MIN und LONG_MAX zurückgeben;
    */
    @Override
    public float longitude() {
        return this.longitude;
    }
}
