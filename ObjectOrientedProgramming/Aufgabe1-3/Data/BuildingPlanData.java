package Data;

import java.util.Random;

public class BuildingPlanData {
    private float buildingArea; // buildingArea >= -100 && buildingArea <= 200
    private float walkwayArea; // walkwayArea >= -100 && walkwayArea <= 200

    /*
       Das Objekt BuildingPlanData wird instanziert
    */
    public BuildingPlanData() {
        this.randomize();
    }

    /*
        Aktuelle buildingArea wird zurückgegeben
    */
    public float getBuildingArea() {
        return buildingArea;
    }

    /*
        Aktuelle walkwayArea wird zurückgegeben
    */
    public float getWalkwayArea() {
        return walkwayArea;
    }

    /*
        randomize belegt ein Objekt von BuildingPlanData mit zufälligen Werten;
        buildingPlanData wird mit zufälligen Werten befüllt
    */
    private void randomize() {
        Random random = new Random();
        int buildingRandom = random.nextInt(10); // buildingRandom >= 0 && buildingRandom <= 10
        int walkwayRandom = random.nextInt(10); // walkwayRandom >= 0 && walkwayRandom <= 10

        if (buildingRandom > 8) {
            this.buildingArea = random.nextFloat()*300-100;
        } else {
            this.buildingArea = 0;
        }

        if (walkwayRandom > 8) {
            this.walkwayArea = random.nextFloat()*300-100;
        } else {
            this.walkwayArea = 0;
        }
    }
}
