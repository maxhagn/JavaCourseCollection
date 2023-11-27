import java.lang.reflect.Type;

public class Forestry {

    private static LinkedList allForestrys = new LinkedList(); //darf nur Objekte vom Typ Forestry enthalten
    private static LinkedList allWheelHarvesters = WheelWoodHarvester.getAllWheelHarvester();
    private static LinkedList allWalkHarvesters = WalkWoodHarvester.getAllWalkHarvester();

    private String name; // name != null
    private LinkedList wheelHarvesters;
    private LinkedList walkingHarvesters;
    private int numberOfChopper; // numberOfChopper >= 0
    private int numberOfWheelerWithCutter; // numberOfWheelerWithCutter >= 0
    private int numberOfWalkerWithCutter; // numberOfWalkerWithCutter >= 0

    /*
       Forestry instanziert das Objekt Forestry und gibt diesem einen Namen;
       name != null;
       Ein instanziertes Objekt von Forstbetrieb wird zurückgegeben
    */
    public Forestry(String name) {
        Node currentNode = allForestrys.head;
        while (currentNode != null && currentNode.hasNext()) {
            currentNode = currentNode.getNext();
            if (((Forestry) currentNode.getData()).getName() == name)
                throw new IllegalArgumentException("Es gibt bereits einen Forstbertieb mit diesem Namen");
        }
        this.name = name;
        allForestrys.add(this);
        this.walkingHarvesters = new LinkedList();
        this.wheelHarvesters = new LinkedList();
        this.numberOfChopper = 0;
        this.numberOfWheelerWithCutter = 0;
        this.numberOfWalkerWithCutter = 0;
    }

    /*
       getName retourniert den Namen des Forestry Objekts
    */
    public String getName() {
        return this.name;
    }

    /*
       addHarvester fügt einen Harvester mit einer id zu einer geeigneten Liste hinzu;
       id >= 0;
       Der Harvester wurde zu einer Liste hinzugefügt, wobei die Zugehörigkeit zu einer Liste vom jeweiligen Typ abhängt
    */
    public void addHarvester(int id) {
        WheelWoodHarvester wheelHarvester = findWheelHarvester(id);
        if (wheelHarvester != null) {
            this.wheelHarvesters.add(wheelHarvester);
        }
        WalkWoodHarvester walkHarvester = findWalkHarvester(id);
        if (walkHarvester != null) {
            this.walkingHarvesters.add(walkHarvester);
        }
    }

    /*
       removeHarvester entfernt einen Harvester mit einer id aus der Liste;
       id >= 0;
       Der Harvester mit id wurde aus der Liste entfernt
    */
    public void removeHarvester(int id) {
        WheelWoodHarvester wheelHarvester = findWheelHarvester(id);
        if (wheelHarvester != null) {
            this.wheelHarvesters.remove(wheelHarvester);
        }
        WalkWoodHarvester walkHarvester = findWalkHarvester(id);
        if (walkHarvester != null) {
            this.walkingHarvesters.remove(walkHarvester);
        }
    }

    /*
       addHarvester fügt einen Harvester h zu einer geeigneten Liste hinzu;
       h != null;
       Der Harvester wurde zu einer Liste hinzugefügt, wobei die Zugehörigkeit zu einer Liste vom jeweiligen Typ abhängt
    */
    public void addHarvester(Harvester h) {
        if (h instanceof WalkWoodHarvester) {
            this.walkingHarvesters.add(h);
            if (h.getToolAttachment() instanceof Cutter) {
                this.numberOfWheelerWithCutter++;
            } else {
                this.numberOfChopper++;
            }
        } else {
            this.wheelHarvesters.add(h);
            if (h.getToolAttachment() instanceof Cutter) {
                this.numberOfWalkerWithCutter++;
            } else {
                this.numberOfChopper++;
            }
        }
    }

    /*
       raiseOperatingHours steigert die Anzahl der Arbeitsstunden eines Harvesters mit angegebener id
       id >= null && hours >= 0;
       Die Arbeitsstunden eines Harvesters mit angegebener id wurden erhöht
    */
    public void raiseOperatingHours(int id, float hours) {
        WheelWoodHarvester wheelHarvester = findWheelHarvester(id);
        if (wheelHarvester != null) {
            wheelHarvester.raiseOperatingHours(hours);
        }
        WalkWoodHarvester walkHarvester = findWalkHarvester(id);
        if (walkHarvester != null) {
            walkHarvester.raiseOperatingHours(hours);
        }
    }

    /*
       getOperatingHours gibt die Arbeitsstunden eines Harvesters h zurück;
       h != null;
       Die Arbeitsstunden eines Harvesters h wurden zurückgegeben
    */
    public float getOperatingHours(Harvester h) {
        return h.getOperatingHours();
    }

    /*
       getOperatingHours gibt die Arbeitsstunden eines Harvesters mit angegebener id zurück;
       id >= 0;
       Die Arbeitsstunden eines Harvesters mit angegebener id wurden zurückgegeben
    */
    public float getOperatingHours(int id) {
        WheelWoodHarvester wheelHarvester = findWheelHarvester(id);
        if (wheelHarvester != null) {
            return wheelHarvester.getOperatingHours();
        }
        WalkWoodHarvester walkHarvester = findWalkHarvester(id);
        if (walkHarvester != null) {
            return wheelHarvester.getOperatingHours();
        }
        return 0;
    }

    /*
       changeToolAttachment ändert das ToolAttachment t eines Harvesters mit angegebener id
       id >= 0 && t != null;
       Das ToolAttachment t eines Harvesters mit angegebener id wurde gewechselt
    */
    public void changeToolAttachment(int id, ToolAttachment t) {
        WheelWoodHarvester wheelHarvester = findWheelHarvester(id);
        if (wheelHarvester != null) {
            wheelHarvester.changeToolAttachment(t);
        }
        WalkWoodHarvester walkHarvester = findWalkHarvester(id);
        if (walkHarvester != null) {
            walkHarvester.changeToolAttachment(t);
        }
    }

    /*
       getMaxTreeLength gibt die maximale Baumhöhe eines Harvesters h zurück;
       h != null;
       Die maximale Baumhöhe eines Harvesters h wurde zurückgegeben
    */
    public float getMaxTreeLength(Harvester h) {
        return h.getMaxTreeLength();
    }

    /*
       getMaxTreeLength gibt die maximale Baumhöhe eines Harvesters mit angegebener id zurück;
       id >= 0;
       Die maximale Baumhöhe eines Harvesters mit angegebener id wurde zurückgegeben
    */
    public float getMaxTreeLength(int id) {
        WheelWoodHarvester wheelHarvester = findWheelHarvester(id);
        if (wheelHarvester != null) {
            return wheelHarvester.getMaxTreeLength();
        }
        WalkWoodHarvester walkHarvester = findWalkHarvester(id);
        if (walkHarvester != null) {
            return walkHarvester.getMaxTreeLength();
        }
        return 0;
    }

    /*
       getMaxTreeThickness gibt die maximale Baumbreite eines Harvesters zurück;
       h != null;
       Die maximale Baumbreite eines Harvesters h wurde zurückgegeben
    */
    public float getMaxTreeThickness(Harvester h) {
        return h.getMaxTreeThickness();
    }

    /*
       getMaxTreeThickness gibt die maximale Baumbreite eines Harvesters mit angegebener id zurück;
       id >= null;
       Die maximale Baumbreite eines Harvesters h wurde zurückgegeben
    */
    public float getMaxTreeThickness(int id) {
        WheelWoodHarvester wheelHarvester = findWheelHarvester(id);
        if (wheelHarvester != null) {
            return wheelHarvester.getMaxTreeThickness();
        }
        WalkWoodHarvester walkHarvester = findWalkHarvester(id);
        if (walkHarvester != null) {
            return walkHarvester.getMaxTreeThickness();
        }
        return 0;
    }

    /*
       getSteps retourniert die Schrittanzahl die ein WalkWoodHarvester mit angegebener id zurückgelegt hat;
       id >= 0;
       Die Schrittanzahl eines WalkWoodHarvester mit angegebener id wurde retourniert
    */
    public int getSteps(int id) {
        WheelWoodHarvester wheelHarvester = findWheelHarvester(id);
        if (wheelHarvester != null) {
            return 0;
        }
        WalkWoodHarvester walkHarvester = findWalkHarvester(id);
        if (walkHarvester != null) {
            return walkHarvester.getSteps();
        }
        return 0;
    }

    /*
       raiseSteps erhöht die Anzahl an Schritten die ein WalkWoodHarvester h zurückgelegt hat;
       h != null && steps >= 0;
       Die Schrittanzahl eines WalkWoodHarvester h wurde erhöht
    */
    public void raiseSteps(WalkWoodHarvester h, int steps) {
        h.raiseSteps(steps);
    }

    /*
       raiseSteps erhöht die Anzahl an Schritten die ein WalkWoodHarvester mit angegebener id zurückgelegt hat;
       h >= 0 && steps >= 0;
       Die Schrittanzahl eines WalkWoodHarvester mit angegebener id wurde erhöht
    */
    public void raiseSteps(int id, int steps) {
        WalkWoodHarvester walkHarvester = findWalkHarvester(id);
        if (walkHarvester != null) {
            walkHarvester.raiseSteps(steps);
        }
    }

    /*
       getDistance retourniert die Distanz die ein WheelWoodHarvester h zurückgelegt hat;
       h != null;
       Die zurückgelegte Distanz eines WheelWoodHarvester h wurde retourniert
    */
    public float getDistance(WheelWoodHarvester h) {
        return h.getDistance();
    }

    /*
       getDistance retourniert die Distanz die ein WheelWoodHarvester mit angegebener id zurückgelegt hat;
       id >= null;
       Die zurückgelegte Distanz eines WheelWoodHarvester mit angegebener id wurde retourniert
    */
    public float getDistance(int id) {
        WheelWoodHarvester wheelHarvester = findWheelHarvester(id);
        if (wheelHarvester != null) {
            return wheelHarvester.getDistance();
        }
        return 0;
    }

    /*
       raiseDistance erhöht die Distanz die ein WheelWoodHarvester mit angegebener id zurückgelegt hat;
       id >= 0 && dist >= 0;
       Die Distanz eines WheelWoodHarvester mit angegebener id wurde erhöht
    */
    public void raiseDistance(int id, int dist) {
        WheelWoodHarvester wheelHarvester = findWheelHarvester(id);
        if (wheelHarvester != null) {
            wheelHarvester.raiseDistance(dist);
        }
    }

    /*
       getAverageOperatingHours retourniert die durchschnittlichen Arbeitsstunden aller Arbeiter
    */
    public float getAverageOperatingHours() {
        return (this.getAverage(wheelHarvesters, 0) + this.getAverage(walkingHarvesters, 0)) / (this.getLength(wheelHarvesters) + this.getLength(walkingHarvesters));
    }

    /*
       getAverageOperatingHoursCutter retourniert die durchschnittlichen Arbeitsstunden aller Arbeiter die das ToolAttachement Cutter verwenden
    */
    public float getAverageOperatingHoursCutter() {
        return (this.getAverage(wheelHarvesters, 1) + this.getAverage(walkingHarvesters, 1)) / (this.getLength(wheelHarvesters) + this.getLength(walkingHarvesters));
    }

    /*
       getAverageOperatingHoursChopper retourniert die durchschnittlichen Arbeitsstunden aller Arbeiter die das ToolAttachement Chopper verwenden
    */
    public float getAverageOperatingHoursChopper() {
        return (this.getAverage(wheelHarvesters, 2) + this.getAverage(walkingHarvesters, 2)) / (this.getLength(wheelHarvesters) + this.getLength(walkingHarvesters));
    }

    /*
       getAverageOperatingHoursWalk retourniert die durchschnittlichen Arbeitsstunden aller Arbeiter die zu Fuß unterwegs sind
    */
    public float getAverageOperatingHoursWalk() {
        return (this.getAverage(walkingHarvesters, 3));
    }

    /*
       getAverageOperatingHoursWheel retourniert die durchschnittlichen Arbeitsstunden aller Arbeiter die mit einem Fahrzeug unterwegs sind
    */
    public float getAverageOperatingHoursWheel() {
        return (this.getAverage(wheelHarvesters, 3));
    }

    /*
       getAverageDistance retourniert die durchschnittlich zurückgelegte Distanz der Arbeiter mit Fahrzeugen
    */
    public float getAverageDistance() {
        return getAverage(wheelHarvesters, 4);
    }

    /*
       getAverageDistanceCutter retourniert die durchschnittlich zurückgelegte Distanz der Arbeiter mit Fahrzeugen und ToolAttachment Cutter
    */
    public float getAverageDistanceCutter() {
        return getAverage(wheelHarvesters, 5);
    }

    /*
       getAverageDistanceChopper retourniert die durchschnittlich zurückgelegte Distanz der Arbeiter mit Fahrzeugen und ToolAttachment Chopper
    */
    public float getAverageDistanceChopper() {
        return getAverage(wheelHarvesters, 6);
    }

    /*
       getAverageSteps retourniert die durchschnittlich zurückgelegten Schritte der Arbeiter die zu Fuß unterwegs sind
    */
    public float getAverageSteps() {
        return getAverage(walkingHarvesters, 7);
    }

    /*
       getAverageStepsCutter retourniert die durchschnittlich zurückgelegte Distanz der Arbeiter die zu Fuß unterwegs sind und das ToolAttachment Cutter verwenden
    */
    public float getAverageStepsCutter() {
        return getAverage(walkingHarvesters, 8);
    }

    /*
       getAverageStepsChopper retourniert die durchschnittlich zurückgelegte Distanz der Arbeiter die zu Fuß unterwegs sind und das ToolAttachment Chopper verwenden
    */
    public float getAverageStepsChopper() {
        return getAverage(walkingHarvesters, 9);
    }

    /*
       minLength retourniert den niedrigsten Baum aller Arbeiter
    */
    public float minLength() {
        float minWheeler = this.findMinLengthOfList(this.wheelHarvesters);
        float minWalker = this.findMinLengthOfList(this.walkingHarvesters);
        return Math.min(minWheeler, minWalker);
    }

    /*
       minLengthWheel retourniert den niedrigsten Baum aller Arbeiter die mit Fahrzeugen unterwegs sind
    */
    public float minLengthWheel() {
        if (this.numberOfWheelerWithCutter == 0) {
            return 0;
        }
        return this.findMinLengthOfList(this.wheelHarvesters);
    }

    /*
       minLengthWalk retourniert den niedrigsten Baum aller Arbeiter die zu Fuß unterwegs sind
    */
    public float minLengthWalk() {
        if (this.numberOfWalkerWithCutter == 0) {
            return 0;
        }
        return this.findMinLengthOfList(this.walkingHarvesters);
    }

    /*
       maxLength retourniert den höchsten Baum aller Arbeiter
    */
    public float maxLength() {
        float minWheeler = this.findMaxLengthOfList(this.wheelHarvesters);
        float minWalker = this.findMaxLengthOfList(this.walkingHarvesters);
        return Math.max(minWheeler, minWalker);
    }

    /*
       maxLengthWheel retourniert den höchsten Baum aller Arbeiter die mit Fahrzeugen unterwegs sind
    */
    public float maxLengthWheel() {
        return this.findMaxLengthOfList(this.wheelHarvesters);
    }

    /*
       maxLengthWalk retourniert den höchsten Baum aller Arbeiter die zu Fuß unterwegs sind
    */
    public float maxLengthWalk() {
        return this.findMaxLengthOfList(this.walkingHarvesters);
    }

    /*
       getAverageThickness retourniert die durchscnittliche Breite aller Bäume
    */
    public float getAverageThickness() {
        this.numberOfChopper = 0;
        float currentThickness = this.getSumOfListThickness(wheelHarvesters);
        currentThickness += this.getSumOfListThickness(walkingHarvesters);
        return currentThickness / this.numberOfChopper;
    }

    /*
       getAverageThicknessWalk retourniert die durchscnittliche Breite der Bäume aller Arbeiter die zu Fuß unterwegs sind
    */
    public float getAverageThicknessWalk() {
        return getAverage(walkingHarvesters, 10);
    }

    /*
       getAverageThicknessWheel retourniert die durchscnittliche Breite der Bäume aller Arbeiter die mit Fahrzeugen unterwegs sind
    */
    public float getAverageThicknessWheel() {
        return getAverage(wheelHarvesters, 11);
    }

    /*
       findMinLengthOfList sucht nach dem kleinsten Wert in einer Liste;
       list != null;
       Der kleinste Wert in einer Liste wird zurückgegeben
    */
    private float findMinLengthOfList(LinkedList list) {
        Node currentNode = list.head;
        if (currentNode != null) {
            currentNode = currentNode.getNext();
            if (currentNode != null) {
                float currentMinLength = ((Harvester) currentNode.getData()).getMaxTreeLength();

                while (currentNode != null && currentNode.hasNext()) {
                    currentNode = currentNode.getNext();
                    Harvester harvester = (Harvester) currentNode.getData();
                    if (harvester.getToolAttachment() instanceof Cutter && harvester.getMaxTreeLength() < currentMinLength) {
                        currentMinLength = this.getMaxTreeLength(harvester);
                    }
                }

                return currentMinLength;
            }
        }
        return 0;
    }

    /*
       findMinLengthOfList sucht nach dem größten Wert in einer Liste;
       list != null;
       Der größte Wert in einer Liste wird zurückgegeben
    */
    private float findMaxLengthOfList(LinkedList list) {
        float currentMaxLength = 0;
        Node currentNode = list.head;
        while (currentNode != null && currentNode.hasNext()) {
            currentNode = currentNode.getNext();
            Harvester harvester = (Harvester) currentNode.getData();
            if (harvester.getToolAttachment() instanceof Cutter && harvester.getMaxTreeLength() > currentMaxLength) {
                currentMaxLength = this.getMaxTreeLength(harvester);
            }
        }
        return currentMaxLength;
    }

    /*
       getSumOfListThickness summiert alle Durchmesser aller Bäume in der angegebenen Liste;
       list != null;
       Der Durchmesser aller Bäume die in der Liste enthalten sind wurde zurückgegeben
    */
    private int getSumOfListThickness(LinkedList list) {
        int currentThickness = 0;
        Node currentNode = list.head;
        while (currentNode != null && currentNode.hasNext()) {
            currentNode = currentNode.getNext();
            Harvester harvester = (Harvester) currentNode.getData();
            if (harvester.getToolAttachment() instanceof Chopper) {
                currentThickness += this.getMaxTreeThickness(harvester);
                this.numberOfChopper++;
            }
        }
        return currentThickness;
    }

    /*
       getAverage gibt den Durchschnitt verschiedener Typen zurück;
       list != null && id >= 0 && id <= 11;
       Der Durchschnitt der Liste wurde zurückgegeben
    */
    private float getAverage(LinkedList linkedList, int id) {
        Node currentNode = linkedList.head;
        float sum = 0;
        float div = 0;

        while (currentNode != null && currentNode.hasNext()) {
            currentNode = currentNode.getNext();
            switch (id) {
                case 0:
                    sum += ((Harvester) currentNode.getData()).getOperatingHours();
                    break;
                case 1:
                    if (((Harvester) currentNode.getData()).getToolAttachment() instanceof Cutter) {
                        sum += ((Harvester) currentNode.getData()).getOperatingHours();
                        div += 1;
                    }
                    break;
                case 2:
                    if (((Harvester) currentNode.getData()).getToolAttachment() instanceof Chopper) {
                        sum += ((Harvester) currentNode.getData()).getOperatingHours();
                        div += 1;
                    }
                    break;
                case 3:
                    sum += ((Harvester) currentNode.getData()).getOperatingHours();
                    div += 1;
                    break;
                case 4:
                    sum += ((WheelWoodHarvester) currentNode.getData()).getDistance();
                    div += 1;
                    break;
                case 5:
                    if (((Harvester) currentNode.getData()).getToolAttachment() instanceof Cutter) {
                        sum += ((WheelWoodHarvester) currentNode.getData()).getDistance();
                        div++;
                    }
                    break;
                case 6:
                    if (((Harvester) currentNode.getData()).getToolAttachment() instanceof Chopper) {
                        sum += ((WheelWoodHarvester) currentNode.getData()).getDistance();
                        div++;
                    }
                    break;
                case 7:
                    sum += ((WalkWoodHarvester) currentNode.getData()).getSteps();
                    div += 1;
                    break;
                case 8:
                    if (((Harvester) currentNode.getData()).getToolAttachment() instanceof Cutter) {
                        sum += ((WalkWoodHarvester) currentNode.getData()).getSteps();
                        div++;
                    }
                    break;
                case 9:
                    if (((Harvester) currentNode.getData()).getToolAttachment() instanceof Chopper) {
                        sum += ((WalkWoodHarvester) currentNode.getData()).getSteps();
                        div++;
                    }
                    break;
                case 10:
                    if (((Harvester) currentNode.getData()).getToolAttachment() instanceof Chopper) {
                        sum += ((WalkWoodHarvester) currentNode.getData()).getMaxTreeThickness();
                        div++;
                    }
                    break;
                case 11:
                    if (((Harvester) currentNode.getData()).getToolAttachment() instanceof Chopper) {
                        sum += ((WheelWoodHarvester) currentNode.getData()).getMaxTreeThickness();
                        div++;
                    }
                    break;
                default:
                    return -1;
            }
        }

        if (div >= 0) {
            return sum / div;
        } else {
            return sum;
        }
    }

    /*
       getLength gibt die Anzahl der Elemente in einer Liste zurück;
       list != null;
       Die Größe der Liste wurde zurückgegeben
    */
    private int getLength(LinkedList list) {
        Node currentNode = list.head;
        int counter = 0;
        while (currentNode != null && currentNode.hasNext()) {
            currentNode = currentNode.getNext();
            counter++;
        }
        return counter;
    }

    /*
       getAllForestrys retourniert allForestrys;
    */
    public static LinkedList getAllForestrys() {
        return allForestrys;
    }

    /*
       findWheelHarvester sucht einen WheelWoodHArvester mit angegebener id;
       id >= 0;
       Ein WheelWoodHarvester wurde zurückgegeben
    */
    private static WheelWoodHarvester findWheelHarvester(int harvesterId) {
        WheelWoodHarvester harvester = null;
        Node currentNode = allWheelHarvesters.head;
        while (currentNode != null && currentNode.hasNext()) {
            currentNode = currentNode.getNext();
            if (((WheelWoodHarvester) currentNode.getData()).getId() == (harvesterId)) {
                harvester = (WheelWoodHarvester) currentNode.getData();
            }
        }
        return harvester;
    }

    /*
       findWalkHarvester sucht einen WalkWoodHarvester mit angegebener id;
       id >= 0;
       Ein WalkWoodHarvester wurde zurückgegeben
    */
    private static WalkWoodHarvester findWalkHarvester(int harvesterId) {
        WalkWoodHarvester harvester = null;
        Node currentNode = allWalkHarvesters.head;
        while (currentNode != null && currentNode.hasNext()) {
            currentNode = currentNode.getNext();
            if (((WalkWoodHarvester) currentNode.getData()).getId() == (harvesterId)) {
                harvester = (WalkWoodHarvester) currentNode.getData();
            }
        }
        return harvester;
    }

    /*
        Gibt einen String der alle statistischen Werte enthält zurück
    */
    @Override
    public String toString() {
        return "Forestry{" + "\n" +
                "name='" + name + '\'' + "\n" +
                "average operating hours total=" + getAverageOperatingHours() + '\'' + "\n" +
                "average operating hours cutter=" + getAverageOperatingHoursCutter() + '\'' + "\n" +
                "average operating hours chopper=" + getAverageOperatingHoursChopper() + '\'' + "\n" +
                "average operating hours walker=" + getAverageOperatingHoursWalk() + '\'' + "\n" +
                "average operating hours wheel=" + getAverageOperatingHoursWheel() + '\'' + "\n" +
                "average operating hours cutter=" + getAverageOperatingHoursCutter() + '\'' + "\n" +
                "average distance total=" + getAverageDistance() + '\'' + "\n" +
                "average distance cutter=" + getAverageDistanceCutter() + '\'' + "\n" +
                "average distance chopper=" + getAverageDistanceChopper() + '\'' + "\n" +
                "average steps total=" + getAverageSteps() + '\'' + "\n" +
                "average steps cutter=" + getAverageStepsCutter() + '\'' + "\n" +
                "average steps chopper=" + getAverageStepsChopper() + '\'' + "\n" +
                "minimal tree length total=" + minLength() + '\'' + "\n" +
                "minimal tree length wheel=" + minLengthWheel() + '\'' + "\n" +
                "minimal tree length walker=" + minLengthWalk() + '\'' + "\n" +
                "maximal tree length total=" + maxLength() + '\'' + "\n" +
                "maximal tree length wheel=" + maxLengthWheel() + '\'' + "\n" +
                "maximal tree length walker=" + maxLengthWalk() + '\'' + "\n" +
                "average thickness total=" + getAverageThickness() + '\'' + "\n" +
                "average thickness wheel=" + getAverageThicknessWheel() + '\'' + "\n" +
                "average thickness total=" + getAverageThicknessWalk() + '\'' + "\n" +
                '}';
    }
}
