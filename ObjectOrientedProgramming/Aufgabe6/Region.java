public class Region {

    private static LinkedList allRegions = new LinkedList(); //darf nur Objekte vom Typ Region enthalten
    private static LinkedList allForestrys = Forestry.getAllForestrys(); //Liste aller Forstbetriebe überhaupt mit einzigartigen Namen

    private String name; // Name einer spezifischen Region
    private LinkedList forestries; // Liste aller Forstbetriebe der Region

    /*
       Das Objekt Region wird erstellt und mit einem bestimmten Namen instanziert;
       Der Name muss in der Liste allRegions unique sein
       Das Region Objekt wurde erstellt und besitzt einen Namen und eine leere Liste von Forstbetrieben
    */
    public Region( String name ) {
        Node currentNode = allRegions.head;
        while (currentNode != null && currentNode.hasNext()) {
            currentNode = currentNode.getNext();
            if (((Region) currentNode.getData()).getName() == name)
                throw new IllegalArgumentException("Es gibt bereits eine Region mit diesem Namen");
        }
        allRegions.add(this);
        this.name = name;
        this.forestries = new LinkedList();
    }

    /*
       Der name der Region wird zurückgegeben
    */
    public String getName(){
        return name;
    }

    /*
       Ein Forstbetrieb mit dem Namen f wird in die Liste this.forestries hinzugefügt, sofern er in allForestries enthalten ist.
    */
    public void addForestry(String f) {
        Forestry forestry = findForestry(f);
        if (f == null) return;
        this.forestries.add(forestry);
    }

    public void addForestry(Forestry f){
        this.forestries.add(f);
    }


    public void removeForestry(Forestry f){
        this.forestries.remove(f);
    }

    /*
       Ein Forstbetrieb mit dem Namen f wird aus der Liste this.forestries entfernt, sofern er in allForestries enthalten ist.
    */
    public void removeForestry(String f){
        Forestry forestry = findForestry(f);
        if (f == null) return;
        this.forestries.remove(forestry);
    }

    /*
       Alle Forstbetrieben der Region werden ausgegeben.
    */
    public void printForestrys() {
        float counter = 0;
        Node currentNode = this.forestries.head;
        while (currentNode.hasNext()) {
            currentNode = currentNode.getNext();
            Forestry f = (Forestry) currentNode.getData();
            System.out.println(f.toString());
        }
    }

    /*
       Sucht einen Forstbetrieb in der Liste AllForestrys mit dem namen forstryName.
       Der Rückgabewert ist null, wenn kein Forstbetrieb mit dem namen frestryName enthalten ist.
    */
    private static Forestry findForestry(String forestryName) {
        Forestry forestry = null;
        Node currentNode = allForestrys.head;
        while (currentNode != null && currentNode.hasNext()) {
            currentNode = currentNode.getNext();
            if (((Forestry) currentNode.getData()).getName().equals(forestryName)) {
                forestry = (Forestry) currentNode.getData();
            }
        }
        return forestry;
    }

}
