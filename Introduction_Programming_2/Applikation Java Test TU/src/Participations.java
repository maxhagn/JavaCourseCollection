import java.util.Arrays;

/* Objects of class 'Participations' contain participations from several races.  The implementation uses an array. */

public class Participations implements PartIterable {

    private Participation[] participations;

    // Constructor
    public Participations(int n) {
        this.participations = new Participation[n];
    }

        // Creates a 'Participations' object that contains all participations where race.compareTo(r1)>=0 and race.compareTo(r2)<=0.
    public Participations(Participations l, String r1, String r2) {
        this.participations = new Participation[l.MyRemoveNull().length];

        int count = 0;

        for(int i = 0; i < l.MyRemoveNull().length; i++) {
            if(l.MyRemoveNull()[i].getRace().compareTo(r1) >= 0
                && l.MyRemoveNull()[i].getRace().compareTo(r2) <= 0){
                this.participations[count++] = l.MyRemoveNull()[i];
            }
        }

    }


    public Participation[] getParticipations() {
        return participations;
    }


    @Override
    public PartIterator iterator() {
        return new ParticipationsIter(this);
    }

    //Adhoc
    public PartIterator iterator(int x, String y) {
        Participations p = new Participations(participations.length);
        for (int i = 0; i < participations.length; i++){
            if(participations[i] != null && participations[i].getBibnumber() <= x && participations[i].getRacer().compareTo(y) <= 0){
                p.add(participations[i]);
            }
        }

        p.print();

        return new ParticipationsIter(p);
    }


    // Adds p to participation.
    public void add(Participation p) {
        //  other classes
        if (this.participations[this.participations.length - 1] != null) {
            this.participations = Arrays.copyOf(this.participations, this.participations.length + 1);
        }

        for (int i = 0; i < this.participations.length; i++) {
            if (this.participations[i] == null) {
                this.participations[i] = p;
                break;
            }
        }
    }

    // Helper, prints the in the parameter given array
    private void MyPrintArray(Participation[] clean) {
        for (int i = 0; i < clean.length; i++) {
            clean[i].print();
            System.out.println(" ");
        }
    }

    // Helper, remove null from array to prevent null pointer exception
    private Participation[] MyRemoveNull() {
        int realLength = 0;

        for (int i = 0; i < this.participations.length; i++) {
            if (this.participations[i] == null) {
                realLength = i;
                break;
            }
        }
        return Arrays.copyOf(this.participations, realLength);
    }
    
    // Print the filled entries in the order of insertion
    public void print() {
        System.out.println("__________________________ \n print \n");
        MyPrintArray(MyRemoveNull());
    }

    // Returns the first participation first out
    public Participation lookupRacer(String r) {
        System.out.println("__________________________ \n search result \n");
        for(int i = 0; i <= this.participations.length; i++) {
            if (this.participations[i].getRacer().equals(r)) {
                return this.participations[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("__________________________ \n print with iterator \n");
        Participations p = new Participations(100);
        p.add(new Participation("Lienz 2011 Ladies' Slalom 1", "R1", 2));
        p.add(new Participation("Lienz 2012 Ladies' Slalom 2", "R2", 3));
        p.add(new Participation("Lienz 2013 Ladies' Slalom 3", "R3", 1));
        p.add(new Participation("Lienz 2013 Ladies' Slalom 4", "R4", 4));
        p.add(new Participation("Lienz 2013 Ladies' Slalom 5", "R5", 5));
        p.add(new Participation("Lienz 2013 Ladies' Slalom 6", "R6", 6));
        p.add(new Participation("Lienz 2013 Ladies' Slalom 7", "R7", 7));

        PartIterator iterator=p.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("__________________________ \n print with iterator ADHOC \n");
        PartIterator iterator2=p.iterator(2, "A");
        while(iterator2.hasNext()){
            System.out.println(iterator2.next());
        }
    }

}
