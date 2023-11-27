import java.util.Objects;

public class Coordinate {

    private int latitude; //latitude >= 0
    private int longitude; // longtitude >= 0

    /*
       Ein Objekt von Coordinate wird erstellt und mit einem Ganzzahlenwer latitude und einem Ganzzahlenwert longtitude instanziert;
       latitude >= 0; longtitude >= 0;
       Ein Objekt von Coordinate wurde erstellt und besitzt latitude und longtitude Attribute.
    */
    public Coordinate( int latitude, int longitude ) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /*
       Gibt für eine Coordinate zurück, ob sie gleich einem anderen Objekt ist;
       o != null;
       Ein Wahrheitswert wird zurückgegeben;
    */
    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Coordinate ) ) return false;
        Coordinate that = ( Coordinate ) o;
        return latitude == that.latitude &&
                longitude == that.longitude;
    }

    /*
       Gibt für eine Coordinate einen hash code zurück;
       Ein Ganzzahlenwert wird zurückgegeben;
    */
    @Override
    public int hashCode( ) {
        return Objects.hash( latitude, longitude );
    }

    /*
       Gibt die Klasse als String aus;
    */
    @Override
    public String toString( ) {
        return String.format("%10s%10s", latitude, longitude);
    }

    /*
       Gibt latitude des Objekts zurück;
    */
    public int getLatitude( ) {
        return latitude;
    }

    /*
       Gibt longtitude des Objekts zurück;
    */
    public int getLongitude( ) {
        return longitude;
    }

}
