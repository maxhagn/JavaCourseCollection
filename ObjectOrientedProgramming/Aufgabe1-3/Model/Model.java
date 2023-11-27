package Model;

import Data.BuildingPlanData;
import Data.ClimateDataCollection;
import Forest.Forest;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Model {

    private Forest forest; // forest != null
    private ConcurrentLinkedQueue<BuildingPlanData> buildingPlanData;
    private ConcurrentLinkedQueue<ClimateDataCollection> climateDataCollections;

    /*
        Model erstellt eine Instanz von Model und befüllt dieses mit den angegebenen Parametern;
        forest != null; climateDataCollections != null;
        Neue Instanz von Model-Klasse wurde erstellt
    */
    public Model(Forest forest, ConcurrentLinkedQueue<ClimateDataCollection> climateDataCollections) {
        this.forest = forest;
        this.climateDataCollections = climateDataCollections;
    }

    /*
        Model erstellt eine Instanz von Model;
        treeCount > 0; climateDataCollectionCount > 0; simulationDensity > 0 && simulationDensity <= 365;
        Neue Instanz von Model-Klasse wurde erstellt
    */
    public Model(int treeCount, int climateDataCollectionCount, int simulationDensity) {
        this.forest = new Forest(treeCount);
        this.climateDataCollections = new ConcurrentLinkedQueue<>();
        this.buildingPlanData = new ConcurrentLinkedQueue<>();
        this.randomize(treeCount, climateDataCollectionCount, simulationDensity);
    }

    /*
        startSimulation iteriert über alle Zeiteinheiten und führt Events aus;
        simulationDensity > 0 && simulationDensity <= 365;
        Elemente in climateDataCollections und buildingPlanData werden verbraucht; forest verändert sich nach gegebenen Bedingungen
    */
    public void startSimulation(int simulationDensity) {
        int simulationYears = this.climateDataCollections.size() / simulationDensity;

        System.out.println("==========================================================================================================================================================================");
        System.out.println("                                                                                    Simulation");
        System.out.println("==========================================================================================================================================================================");
        System.out.format("%6s%20s%24s%24s%16s%17s%20s%20s%20s\n", "Zeitpunkt", "Anzahl Bäume", "Festmeter Gesamt", "Durchschnitt Alter", "Gesundheit", "CO2 Vorrat", "Größe", "Gebäudegröße", "Wegegröße");
        System.out.println(this.forest.beautify(0));

        for (int currentYear = 1; currentYear <= simulationYears; currentYear++) {
            for (int currentPeriod = 1; currentPeriod <= simulationDensity; currentPeriod++) {
                if (!this.climateDataCollections.isEmpty()) {
                    ClimateDataCollection currentClimateDataCollection = this.climateDataCollections.poll();

                    forest.evolve(currentClimateDataCollection, simulationDensity, currentPeriod);
                }
            }

            int reservesSpaceForTrees = 1000;
            if (this.forest.getTreeSize() > reservesSpaceForTrees) {
                BuildingPlanData currentBuildingPlanData = this.buildingPlanData.poll();
                if ( currentBuildingPlanData != null && this.forest.getSoil().getBuildingArea()+currentBuildingPlanData.getBuildingArea() > 0 && this.forest.getSoil().getWalkwayArea()+currentBuildingPlanData.getWalkwayArea() > 0 ) {
                    forest.build(currentBuildingPlanData.getBuildingArea(),currentBuildingPlanData.getWalkwayArea());
                }
            }

            this.forest.harvest(150,0.75f,0);

            System.out.println(this.forest.beautify(currentYear));
        }
    }

    /*
        randomize belegt ein Objekt von Modell mit zufälligen Werten;
        treeCount > 0; climateDataCollectionCount > 0; simulationDensity > 0 && simulationDensity <= 365
        forest, climateDataCollections und buildingPlanData werden mit zufälligen Werten befüllt
    */
    private void randomize(int treeCount, int climateDataCollectionCount, int simulationDensity) {
        int periodCounter = 0; // periodCounter >= 0; periodCounter erhöht sich mit jedem Schleifendurchlauf um 1
        float climateChangeFactor = 0; // climateChangeFactor >= 0; climateChangeFactor erhöht sich nach simulationDensity Schleifendurchläufen um 0.1
        for (int i = 1; i <= climateDataCollectionCount; i++) {
            periodCounter++;
            Random random = new Random();
            ClimateDataCollection newClimateDataCollection = new ClimateDataCollection(simulationDensity, periodCounter, climateChangeFactor);
            this.climateDataCollections.add(newClimateDataCollection);

            if (i % simulationDensity == 0) {
                BuildingPlanData newBuildingPlanData = new BuildingPlanData();
                this.buildingPlanData.add(newBuildingPlanData);
                periodCounter = 0;
                climateChangeFactor += 0.1;
            }
        }
    }
}


