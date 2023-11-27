package Forest;

import java.util.Random;

public class ForestSoil {
    private double co2Stock; // co2Stock >= 0
    private float waterReserves; // waterReserves >= 0
    private float area; // area >= 0
    private float walkwayArea; // walkwayArea >= 0
    private float buildingArea; //buildingArea >= 0

    /*
       Das Objekt ForestSoil wird instanziert
       treesCount > 0;
       ForestSoil wird mit zufälligen Werten instanziert
    */
    public ForestSoil(int treesCount) {
        this.randomize(treesCount);
    }

    /*
       removeWaterFromReserve fügt waterReserves zu ForestSoil hinzu;
       rainfall >= 0
    */
    public void addWaterToReserve(float rainFall) {
        float currentWaterReservePerMeter = this.waterReserves/this.getTreeArea(); // currentWaterReservePerMeter >= 0
        float maxWaterReservePerMeter = 500; // maxWaterReservePerMeter == 500
        float newWaterReservePerMeter = currentWaterReservePerMeter + rainFall; // newWaterReservePerMeter >= 0

        if ( newWaterReservePerMeter > maxWaterReservePerMeter ) { newWaterReservePerMeter = maxWaterReservePerMeter; }
        this.waterReserves = newWaterReservePerMeter * this.getTreeArea();
    }

    /*
       removeWaterFromReserve entfernt waterReserves von ForestSoil;
       amount >= 0
    */
    public void removeWaterFromReserve(float amount) {
        this.waterReserves -= amount;
    }

    /*
       buildBuildings fügt buildingArea zu ForestSoil hinzu
    */
    public void buildBuildings(float newBuildingArea) {
        this.buildingArea = this.buildingArea + newBuildingArea;
    }

    /*
        buildWalkways fügt walkwayArea zu ForestSoil hinzu
    */
    public void buildWalkways(float newWalkwayArea) {
        this.walkwayArea = this.walkwayArea + newWalkwayArea;
    }

    /*
        addCo2 fügt co2Stock zu ForestSoil hinzu;
        deadWood >= 0;
    */
    public void addCo2(double deadWood) {
        this.co2Stock += deadWood;
    }

    /*
        Die area des ForestSoil wird zurückgeben
    */
    public float getArea() {
        return area;
    }

    /*
        getTreeArea gibt die Fläche die von Bäumen verbraucht wird, zurück;
        area > (buildingArea + walkwayArea);
        Die von Gebäuden und Pfaden bereinigte Waldfläche wird zurückgegeben;
        (area - buildingArea - walkwayArea) >= 0
    */
    public float getTreeArea() {
        return area - buildingArea - walkwayArea;
    }

    /*
        Die walkwayArea des ForestSoil wird zurückgeben
    */
    public float getWalkwayArea() { return walkwayArea; }

    /*
        Die buildingArea des ForestSoil wird zurückgeben
    */
    public float getBuildingArea() {
        return buildingArea;
    }

    /*
        Der co2Stock des ForestSoil wird zurückgeben
    */
    public double getCo2Stock() { return co2Stock; }

    /*
        Die waterReserves des ForestSoil wird zurückgeben
    */
    public float getWaterReserves() { return waterReserves; }

    /*
        randomize belegt ein Objekt von ForestSoil mit zufälligen Werten;
        treesCount > 0;
        Alle Variablen von ForestSoil werden mit zufäligen Werten befüllt
    */
    private void randomize(int treesCount) {
        Random random = new Random();
        this.co2Stock = treesCount * random.nextFloat()*4+2;
        this.walkwayArea = treesCount / 100f * random.nextFloat()*4+2;
        this.buildingArea = treesCount / 100f * random.nextFloat()*4+2;
        this.area = (treesCount * (random.nextFloat()*1+4.5f)) + this.walkwayArea + this.buildingArea + 100;
        this.waterReserves = this.area * random.nextFloat()*350+150;
    }
}
