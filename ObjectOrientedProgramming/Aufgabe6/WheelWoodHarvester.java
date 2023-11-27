public class WheelWoodHarvester implements Harvester {

    private static LinkedList allWheelHarvester = new LinkedList(); //darf nur Objekte vom Typ WheelWoodHarvester enthalten

    private ToolAttachment toolAttachment; // Werkzeugaufsatz des Radernter
    private float operatingHours; // Anzahl der Betriebsstunden des Radernters: operatingHours >= 0
    private float distance; // Anzahl der Distanz des Radernters: distance >= 0
    private int id; // Id des Radernters: id >= 0

    /*
       Das Objekt WheelWoodHarvester wird erstellt und mit einer bestimmten Id und einem Werkzeugaufsatz instanziert;
       Die Id muss in der Liste allWheelHarvester unique sein
       id >= 0;
       Das Radernter Objekt wurde erstellt und besitzt eine Id und einen Werkzeugaufsatz.
    */
    public WheelWoodHarvester(ToolAttachment ta, int id) {
        Node currentNode = allWheelHarvester.head;
        while (currentNode != null && currentNode.hasNext()) {
            currentNode = currentNode.getNext();
            if (((WheelWoodHarvester) currentNode.getData()).getId() == id)
                throw new IllegalArgumentException("Es gibt bereits einen Holzvollernter mit dieser Id");
        }
        this.toolAttachment = ta;
        this.id = id;
        allWheelHarvester.add(this);
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
       Gibt den eingesetzten Werkzeugaufsatz des Radernters zurück;
    */
    @Override
    public ToolAttachment getToolAttachment() {
        return toolAttachment;
    }

    /*
       Gibt die Id des Radernters zurück;
    */
    @Override
    public int getId() {
        return this.id;
    }

    /*
       Gibt die Distanz des Radernters zurück;
    */
    public float getDistance() {
        return this.distance;
    }

    /*
       Erhöht die Distanz des Radernters um distance;
       distance >= 0;
       Die Distanz this.distance wurde um distance erhöht;
    */
    public void raiseDistance(float distance) {
        this.distance += distance;
    }

    /*
       Gibt die Liste aller Radernter zurück;
    */
    public static LinkedList getAllWheelHarvester(){
        return allWheelHarvester;
    }

}
