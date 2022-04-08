import java.util.Objects;

/* This class is part of software for ski racing administration. */
public class Participation {

    private String race;
    private String racer;
    private int bibnumber;

    public Participation(String race, String racer, int bibnumber) {
        this.race = race;
        this.racer = racer;
        this.bibnumber = bibnumber;
    }

    public String getRace() {
        return race;
    }

    public String getRacer() {
        return racer;
    }

    public int getBibnumber() {
        return bibnumber;
    }

    public void print() {
        System.out.print(toString());
    }

    @Override
    public String toString() {
        return String.format("%d %s (%s)", bibnumber, racer, race);
    }


    // In addition to the standard requirements for equals, a participation is equal to another object of class Participation if
    // and only if the 'racer's are equal and the 'race's are equal.
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Participation)) return false;
        Participation p = (Participation) o;

        if (this.getRacer().compareTo(p.getRacer()) == 0 && this.getRace().compareTo(p.getRace()) == 0) {
            return true;
        } else {
            return false;
        }
    }

    // Computes a hash code for 'this' that satisfies the requirements for hash codes
    @Override
    public int hashCode() {
        return Objects.hash(getRace(), getRacer());
    }

    public static void main(String[] args) {
        Participation t0 = new Participation("1", "R1", 2);
        Participation t1 = new Participation("3", "R3", 3);
        Participation t2 = new Participation("4", "R4", 4);
        Participation t3 = new Participation("1", "R1", 2);

        System.out.println(t0.equals(t2));
        System.out.println(t0.equals(t3));
        System.out.println(t0.equals(t1));
        System.out.println(t0.equals(t0));

        System.out.println(t0.hashCode());
        System.out.println(t1.hashCode());
        System.out.println(t2.hashCode());
        System.out.println(t3.hashCode());
    }

}
