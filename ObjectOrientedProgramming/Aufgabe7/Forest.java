import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Forest {
    private int latitude; // breite des Waldes: latitude > 0
    private int longitude; // länge des Waldes: longitude > 0
    private final int MAX_SAPLINGS_PER_COORD = 5; // Jungbäume pro Koordinate

    private LinkedList<Sapling> saplingList; // Liste von Jungbäumen
    private HashMap<Coordinate,ShadowType> shadowTypeHashMap; // hashmap von Koordinaten und deren Schatten typen

    /*
       Ein Wald wird instanziert.
       latitutde > 0; longitude > 0
       Der Wald wurde instanziert und die shadowTypeHashMap wurde befüllt
    */
    public Forest(int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        saplingList = new LinkedList<Sapling>();
        shadowTypeHashMap = new HashMap<>( );

        for ( int i = 0; i <= this.latitude; i++ ) {
            for ( int j = 0; j <= this.longitude; j++ ) {
                Random random = new Random( );
                float randomNumber = random.nextFloat();
                if (randomNumber <= 0.33) {
                    shadowTypeHashMap.put( new Coordinate( i,j ), new OpenArea() );
                } else if (randomNumber <= 0.66) {
                    shadowTypeHashMap.put( new Coordinate( i,j ), new BelowFagus() );
                } else {
                    shadowTypeHashMap.put( new Coordinate( i,j ), new BelowNonFagus() );
                }
            }
        }
    }

    /*
       shadowTypeHashMap wird zurückgegeben.
    */
    public HashMap<Coordinate, ShadowType> getShadowTypeHashMap() {
        return shadowTypeHashMap;
    }

    /*
       Der Wald wird mit einer zufälligen Anzahl unterschiedlicher Arten von Junbäumen gefüllt.
       saplingList wurde befüllt
    */
    public void fill () {
        Random random = new Random( );
        int treeSize = random.nextInt(20);
        for ( int i = 0; i < treeSize; i++) {

            float randomNumber = random.nextFloat();

            if ( randomNumber >= 0 && randomNumber <= 0.25) {
                Betula betula = new Betula( 100 - random.nextInt(50), new Coordinate(random.nextInt(this.latitude),random.nextInt(this.longitude)) );
                this.saplingList.add( betula );
            } else if ( randomNumber > 0.25 && randomNumber <= 0.5) {
                Quercus quercus = new Quercus(100 - random.nextInt(50), new Coordinate(random.nextInt(this.latitude),random.nextInt(this.longitude)));
                this.saplingList.add( quercus );
            } else if ( randomNumber > 0.5 && randomNumber <= 0.75) {
                CarpinusBetulus carpinusBetulus = new CarpinusBetulus( random.nextFloat()*2+1, new Coordinate(random.nextInt(this.latitude),random.nextInt(this.longitude)));
                this.saplingList.add( carpinusBetulus );
            } else {
                Fagus fagus = new Fagus(random.nextFloat()*2+1, new Coordinate(random.nextInt(this.latitude),random.nextInt(this.longitude)));
                this.saplingList.add( fagus );
            }
        }
    }

    /*
       Alle Jungbäume der Liste saplingList wachsen
    */
    public void grow() {

        for ( Sapling s : this.saplingList )
        s.grow();

    }

    /*
       Die saplingList des Waldes wird zurückgegeben.
    */
    public LinkedList<Sapling> getSaplingList( ) {
        return saplingList;
    }

    /*
       Alle Koordinaten des Waldes werden je nach Beschattungsart und Maximalanzahl ausgedünnt.
       Jungbäume die nicht unter der Beschattungsart ihrer Koordinaten wachsen können wurden
       entfernt und die Baummenge der Koordinaten wurde auf die Maximalanzahl reduziert
    */
    public void thin () {
        ShadowType shadowTmp;
        LinkedList<Sapling> saplingsOnCoord;
        LinkedList<Sapling> saplingsRemove = new LinkedList<>();

        for ( int i = 0; i <= this.latitude; i++ ) {
            for ( int j = 0; j <= this.longitude; j++ ) {
                shadowTmp = this.shadowTypeHashMap.get(new Coordinate(i,j));
                saplingsOnCoord = new LinkedList<>();
                for (Sapling s : this.saplingList) {
                    if(s.getCoordinate().getLatitude() == i && s.getCoordinate().getLongitude() == j){
                        //Beschattungsart check
                        if(!s.likes(shadowTmp)){
                            saplingsRemove.add(s);
                        }else{
                            saplingsOnCoord.add(s);
                        }
                    }
                }
                boolean check = false;
                for (Sapling sr : saplingsOnCoord) {
                    check = sr.betulaOrFagus();
                    if (check) {
                        break;
                    }
                }
                if(saplingsOnCoord.size() > this.MAX_SAPLINGS_PER_COORD){
                    int lowest = 999999999;
                    Sapling stmp = null;
                    int k = 0;
                    while(saplingsOnCoord.size() - k > this.MAX_SAPLINGS_PER_COORD){
                        for (Sapling sr : saplingsOnCoord) {
                            if(sr.likelyToRemove(check) < lowest){
                                stmp = sr;
                                lowest = sr.likelyToRemove(check);
                            }
                        }
                        saplingsRemove.add(stmp);
                        k++;
                    }
                }
            }
        }

        for (Sapling s : saplingsRemove) {
            this.saplingList.remove(s);
        }
    }

    /*
       Der am besten geeigntete Jungbaum der X-Y-Koordinate wird gefunden und die Beschattungsart angepasst.
       x, y > 0
       Die Beschattungsart der Koordinate wurde an die des bestgeeignetsten Baums angepasst und der Baum wurde aus saplingList entfernt
    */
    public void establish(int x, int y) {
        ShadowType shadowTmp = shadowTypeHashMap.get(new Coordinate(x, y));
        int highestScore = 0;
        boolean yetPrioritized = false;
        Sapling stmp = null;
        for (Sapling s : this.saplingList) {
            if(s.getCoordinate().getLatitude() == x && s.getCoordinate().getLongitude() == y) {
                if (s.likes(shadowTmp)) {
                    if (!yetPrioritized) {
                        int score = s.likelyToRemove(false);
                        if (s.likelyToRemove(false) > highestScore || s.isPrioritized()) {
                            stmp = s;
                            highestScore = s.likelyToRemove(false);
                            yetPrioritized = s.isPrioritized();
                        }
                    } else {
                        if (s.likelyToRemove(false) > highestScore && s.isPrioritized()) {
                            stmp = s;
                            highestScore = s.likelyToRemove(false);
                        }
                    }
                }
            }
        }
        if(stmp != null){
            this.saplingList.remove(stmp);
            this.shadowTypeHashMap.put(new Coordinate(x,y), stmp.getGrownShadowType());
        }
    }

    /*
       Die X-Y-Koordinate wird geschlagen und die Beschattungsart auf OpenArea gesetzt.
       x, y > 0
       Die Beschattugnsart der X-Y-Koordinate wurde auf OpenArea gesetzt
    */
    public void cut (int x, int y) {
        shadowTypeHashMap.put( new Coordinate(x,y), new OpenArea());
    }


    /*
       Der Schattentyp der X-Y-Koordinate wird zurückgegeben.
       x, y > 0
    */
    ShadowType get(int x, int y) {
        return this.shadowTypeHashMap.get( new Coordinate( x,y ) );
    }


    /*
       Alle Bäume mit ihren Werten, die Beschattungsart und die Koordinatenwerte der Koordinate werden auf dem Bildschirm angezeigt.
       x, y > 0
    */
    void printTreesAtCoordinate(int x, int y) {
        System.out.format( "%10s%10s\n", "x", "y" );
        System.out.format("____________________________________________________________________\n");
        for ( Sapling s : this.saplingList ) {
            if (s.getCoordinate().getLatitude() == x && s.getCoordinate().getLongitude() == y) {
                System.out.println( s.getCoordinate().toString() + s.toString() + this.get( s.getCoordinate().getLatitude(), s.getCoordinate().getLatitude() ).toString() );
            }
        }
    }

    /*
       Alle Bäume mit ihren Werten, die Beschattungsart und die Koordinatenwerte des Waldes werden auf dem Bildschirm angezeigt.
    */
    void print() {

        System.out.format( "%10s%10s\n", "x", "y" );
        System.out.format("_____________________________________________________________________\n");
        for ( Sapling s : this.saplingList ) {
            System.out.println( s.getCoordinate().toString() + s.toString() + this.get( s.getCoordinate().getLatitude(), s.getCoordinate().getLatitude() ).toString() );
        }
    }
}

