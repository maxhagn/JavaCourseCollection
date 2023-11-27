public class WalkWoodHarvester implements Harvester {

    private static LinkedList allWalkHarvester = new LinkedList(); //darf nur Objekte vom Typ WalkWoodHarvester enthalten

    private ToolAttachment toolAttachment; // Werkzeugaufsatz des Schreiternters
    private float operatingHours; // Anzahl der Betriebsstunden des Schreiternters: operatingHours >= 0
    private int steps; // Anzahl der Schritte des Schreiternters: steps >= 0
    private int id; // Id des Schreiternters: id >= 0

    /*
       Das Objekt WalkWoodHarvester wird erstellt und mit einer bestimmten Id und einem Werkzeugaufsatz instanziert;
       Die Id muss in der Liste allWalkHarvester unique sein
       id >= 0;
       Das Schreiternter Objekt wurde erstellt und besitzt eine Id und einen Werkzeugaufsatz.
    */
    public WalkWoodHarvester(ToolAttachment ta, int id) {
        Node currentNode = allWalkHarvester.head;
        while (currentNode != null && currentNode.hasNext()) {
            currentNode = currentNode.getNext();
            if (((WalkWoodHarvester) currentNode.getData()).getId() == id)
                throw new IllegalArgumentException("Es gibt bereits einen Holzvollernter mit dieser Id");
        }
        this.toolAttachment = ta;
        this.id = id;
        allWalkHarvester.add(this);
    }

    /*
       Erhöht die Betriebsstunden um hours Stunden;
       hourse >= 0
       Die Betriebsstunden this.operatingHours wurden um hours Stunden erhöht;
    */
    @Override
    public void raiseOperatingHours(float hours) {
        this.operatingHours += hours;
    }

    /*
       Gibt die Betriebsstunden zurück;
    */
    @Override
    public float getOperatingHours() {
        return this.operatingHours;
    }

    /*
       Wechselt den Werkzeugaufsatz auf den Aufsatz t;
       this.toolAttachment wurde auf t geändert;
    */
    @Override
    public void changeToolAttachment(ToolAttachment t) {
        this.toolAttachment = t;
    }

    /*
       Gibt die maximale Baumlänge des Werkzeugaufsatzes zurück;
    */
    @Override
    public float getMaxTreeLength() {
        if (this.toolAttachment instanceof Cutter) {
            return ((Cutter) this.toolAttachment).getMaxLength();
        }
        return 0;
    }

    /*
       Gibt die maximale Baumbreite des Werkzeugaufsatzes zurück;
    */
    @Override
    public int getMaxTreeThickness() {
        if (this.toolAttachment instanceof Chopper) {
            return ((Chopper) this.toolAttachment).getMaxThickness();
        }
        return 0;
    }

    /*
       Gibt den eingesetzten Werkzeugaufsatz des Schreiternters zurück;
    */
    @Override
    public ToolAttachment getToolAttachment() {
        return toolAttachment;
    }

    /*
       Gibt die Id des Schreiternters zurück;
    */
    @Override
    public int getId() {
        return this.id;
    }

    /*
       Gibt die Schrittanzahl des Schreiternters zurück;
    */
    public int getSteps() {
        return this.steps;
    }

    /*
       Erhöht die Schrittanzahl des Schreiternters um steps;
       steps >= 0;
       Die Schrittanzahl this.steps wurde um steps erhöht;
    */
    public void raiseSteps(float steps){
        this.steps += steps;
    }

    /*
       Gibt die Liste aller Schreiternter zurück;
    */
    public static LinkedList getAllWalkHarvester(){
        return allWalkHarvester;
    }

}
