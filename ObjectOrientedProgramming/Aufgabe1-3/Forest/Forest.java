package Forest;

import Data.ClimateDataCollection;
import Tree.*;


import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Forest {
    private ConcurrentLinkedQueue<Tree> trees;
    private ForestSoil soil; // soil != null

    /*
       Das Objekt Forest wird instanziert;
       treesCount > 0;
       Forest wird mit zufälligen Werten instanziert
    */
    public Forest(int treesCount) {
        this.trees = new ConcurrentLinkedQueue<>();
        this.soil = new ForestSoil(treesCount);
        this.randomize(treesCount);
    }

    /*
        age berechnet den Zustand eines Forest nach einer Zeiteinheit unter gegebenen Bedingungen;
        by > 0 && by <= 1; climateDataCollection != null; currentSeason != null;
        Alle trees werden gealtert
    */
    private void age(float by, ClimateDataCollection climateDataCollection, String currentSeason) {
        float waterPerTree = this.soil.getWaterReserves() / this.trees.size(); // waterPerTree >= 0
        for (Tree currentTree : this.trees) {
            currentTree.age(by, climateDataCollection, this, currentSeason, waterPerTree);
            if (currentTree.getHeight() < 0) {
                this.trees.remove(currentTree);
            }
        }
    }

    /*
        evolve führt Events auf trees and soil aus
        climateDataCollection != null; simulationDensity > 0 && simulationDensity <= 365; currentPeriod > 0 && currentPeriod <= 365
        Alle trees und soil sind verändert
    */
    public void evolve(ClimateDataCollection climateDataCollection, int simulationDensity, int currentPeriod) {
        String season = getSeason(currentPeriod, simulationDensity);

        this.soil.addWaterToReserve(climateDataCollection.getRainfall());

        this.age(1f / simulationDensity, climateDataCollection, season);

        Random random = new Random();
        float freeForestArea = this.soil.getTreeArea() - (random.nextFloat()*1+4.5f)*trees.size();
        if (freeForestArea > (random.nextFloat()*1+4.5f)) {
            int newTreeCount = (int)(freeForestArea / getAreaPerTree());
            for ( int i = 1; i <= newTreeCount; i++ ) {
                if ( random.nextInt(75) + ((1-getAverageHealth())*100/75)*100 > 50 ) {
                    Tree newTree;
                    float randomType = random.nextFloat();

                    if (randomType <= 0.33) {
                        newTree = new LarchTree();
                    } else if (randomType <= 0.66) {
                        newTree = new OakTree();
                    } else {
                        newTree = new SpruceTree();
                    }

                    newTree.randomizeNew();

                    this.trees.add(newTree);
                }
            }
        }
    }

    /*
        build verändert soil und kann Elemente aus trees entfernen
        newBuildingArea ?; newWalkwayArea ?;
        soil attribute nehmen zu oder nehmen ab; trees size nimmt ab oder bleibt gleich
    */
    public void build( float newBuildingArea, float newWalkwayArea ) {
        this.soil.buildBuildings(newBuildingArea);
        this.soil.buildWalkways(newWalkwayArea);
        float sumBuildingSpace = newBuildingArea + newWalkwayArea;
        float treesToCut = sumBuildingSpace / this.getAreaPerTree();

        if ( sumBuildingSpace > 0 ) {
            Random random = new Random();
            for ( int i = 0; i <= (int)treesToCut; i++) {
                if ( trees.size() > 0 ) {
                    int randomValue = random.nextInt(this.trees.size());
                    this.trees.remove(this.trees.stream().skip(randomValue).findFirst().orElse(this.trees.peek()));
                }
            }
        }
    }

    /*
        harvest entfert Elemente aus trees
        ageGate >= 0; healthGate >= 0.25 && healthGate <= 1; heightGate >= 0;
        trees ohne die entfernten Elemente
    */
    public void harvest( int ageGate, float healthGate, float heightGate ) {
        this.trees.removeIf(currentTree -> currentTree.getHeight() > heightGate && currentTree.getHealth() > healthGate && currentTree.getAge() > ageGate);
    }

    /*
        getAreaPerTree berechnet einen Wert anhand von soil und trees;
        this.trees.size() != 0; Fehler: DivisionByZeroException
    */
    private float getAreaPerTree() {
        return this.soil.getTreeArea() / this.trees.size();
    }

    /*
        getSeason gibt berechnet einen String der die aktuelle Zeit wiederspiegelt
        currentPeriod > 0 && currentPeriod <= 365; simulationDensity > 0 && simulationDensity <= 365;

    */
    private String getSeason(int currentPeriod, int simulationDensity) {
        String periodIdentifier;
        if (simulationDensity < 12) {
            if (currentPeriod <= simulationDensity / 4) {
                periodIdentifier = "winter";
            } else if (currentPeriod <= simulationDensity * 2 / 4) {
                periodIdentifier = "spring";
            } else if (currentPeriod <= simulationDensity * 3 / 4) {
                periodIdentifier = "summer";
            } else {
                periodIdentifier = "fall";
            }
        } else {
            if (currentPeriod <= simulationDensity / 12) {
                periodIdentifier = "january";
            } else if (currentPeriod <= simulationDensity * 2 / 12) {
                periodIdentifier = "february";
            } else if (currentPeriod <= simulationDensity * 3 / 12) {
                periodIdentifier = "march";
            } else if (currentPeriod <= simulationDensity * 4 / 12) {
                periodIdentifier = "april";
            } else if (currentPeriod <= simulationDensity * 5 / 12) {
                periodIdentifier = "may";
            } else if (currentPeriod <= simulationDensity * 6 / 12) {
                periodIdentifier = "june";
            } else if (currentPeriod <= simulationDensity * 7 / 12) {
                periodIdentifier = "july";
            } else if (currentPeriod <= simulationDensity * 8 / 12) {
                periodIdentifier = "august";
            } else if (currentPeriod <= simulationDensity * 9 / 12) {
                periodIdentifier = "september";
            } else if (currentPeriod <= simulationDensity * 10 / 12) {
                periodIdentifier = "october";
            } else if (currentPeriod <= simulationDensity * 11 / 12) {
                periodIdentifier = "november";
            } else {
                periodIdentifier = "december";
            }


        }

        return periodIdentifier;
    }

    /*
        Das aktuelle soil Objekt wird zurückgegeben
    */
    public ForestSoil getSoil() {
        return soil;
    }

    /*
        Das aktuelle trees Objekt wird zurückgegeben
    */
    public int getTreeSize() {
        return trees.size();
    }

    /*
        Die Höhe aller Bäume wird zurückgegen
    */
    public double getTotalTreeHeight() {
        return trees.stream().mapToDouble(Tree::getHeight).sum();
    }

    /*
        Die durchschnittliche Gesundheit der Bäume wird zurückgeben;
        this.trees.size() != 0; Fehler: DivisionByZeroException
    */
    public double getAverageHealth() {
        return trees.stream().mapToDouble(Tree::getHealth).sum() / this.getTreeSize();
    }

    /*
        Das durchschnittliche Alter der Bäume wird zurückgeben;
        this.trees.size() != 0; Fehler: DivisionByZeroException
    */
    public double getAverageTreeAge() {
        return trees.stream().mapToDouble(Tree::getAge).sum() / this.getTreeSize();
    }

    /*
        Aktueller co2Stock wird zurückgegeben
    */
    public double getTotalCo2Stock() {
        return soil.getCo2Stock() + trees.stream().mapToDouble(Tree::getCo2Stock).sum();
    }

    /*
        Alle Werte des aktuellen Objektes werden in einem String konkatiniert
        year >= 0;
        Gibt einen String der alle Werte des Objektes Forest enthält zurück
    */
    public String beautify(int year) {
        return String.format("%6s%20s%24.2f%24.2f%16.2f%20.2f%20.2f%20.2f%20.2f", year,
                this.getTreeSize(),
                this.getTotalTreeHeight(),
                this.getAverageTreeAge(),
                this.getAverageHealth(),
                this.getTotalCo2Stock(),
                this.getSoil().getArea(),
                this.getSoil().getBuildingArea(),
                this.getSoil().getWalkwayArea()
        );
    }

    /*
        Gibt einen String der alle Bäume des Forests zurück
    */
    @Override
    public String toString() {
        return String.format("%s", trees);
    }

    /*
        randomize belegt ein Objekt von Forest mit zufälligen Werten;
        treesCount > 0;
        Alle Varianlen von Forest werden mit zufäligen Werten befüllt
    */
    private void randomize(int treesCount) {
        Random random = new Random();
        for (int i = 1; i <= treesCount; i++) {
            float randomType = random.nextFloat();
            Tree newTree;

            if (randomType <= 0.33) {
                newTree = new LarchTree();
            } else if (randomType <= 0.66) {
                newTree = new OakTree();
            } else {
                newTree = new SpruceTree();
            }

            this.trees.add(newTree);
        }
    }
}
