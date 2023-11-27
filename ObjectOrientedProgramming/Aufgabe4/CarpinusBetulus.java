import java.util.Random;

public class CarpinusBetulus extends Tree implements ContinentalClimate, Domestic {
    private float latitude;
    private float longitude;
    static Random RANDOM = new Random();
    static float LONG_MIN = 9.54f;
    static float LONG_MAX = 17.11f;
    static float LAT_MIN = 46.43f;
    static float LAT_MAX = 49;



    public CarpinusBetulus() {
        super("Carpinus Betulus", 0.3f, 0.8f, 0.8f, true);
        this.latitude = RANDOM.nextFloat()*(LAT_MAX - LAT_MIN)+LAT_MIN;
        this.longitude = RANDOM.nextFloat()*(LONG_MAX - LONG_MIN)+LONG_MIN;
    }

    /*
        incidence liefert einen Wert zurück der auskunft über vermehrtes (1) und vermindertes(-1) vorkommen in Kontinentalem Klima gibt;
        Wert von 0 bis 2;
    */
    @Override
    public float incidence() {
        return 1.9f;
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
