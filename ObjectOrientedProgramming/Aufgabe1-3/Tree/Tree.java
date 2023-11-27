package Tree;

import Data.ClimateDataCollection;
import Forest.Forest;

import java.util.Random;

public class Tree {
    protected float age; // age > 0; Erhöht sich pro Aufruf der age Funktion um 0 bis 1
    protected float height; // height > 0
    protected float health; // health >= 0.25 && health <= 1
    protected float co2Stock; // co2Stock >= 0

    protected float temperatureResistance; // temperatureResistance >= 0 && temperatureResistance <= 1.25
    protected float windResistance; // windResistance >= 0 && windResistance <= 1.25
    protected float droughtResistance; // droughtResistance >= 0 && droughtResistance <= 1.25

    protected float optimalGrowthHeight; // optimalGrowthHeight == 1.5
    protected float optimalTemperature; // optimalTemperature >= 25 && optimalTemperature <= 30
    protected float optimalSunHoursPerDay; // optimalSunHoursPerDay >= 0 && optimalSunHoursPerDay <= 24
    protected int optimalWaterNeed; // optimalWaterNeed >= 400 && optimalWaterNeed <= 550

    /*
       Das Objekt Tree wird instanziert
    */
    public Tree() {
        this.randomize();
    }

    /*
       Das Objekt Tree wird instanziert
    */
    public Tree(float age, float height, float health, float co2Stock, float temperatureResistance, float windResistance, int optimalWaterNeed) {
        this.age = age;
        this.height = height;
        this.health = health;
        this.co2Stock = co2Stock;
        this.temperatureResistance = temperatureResistance;
        this.windResistance = windResistance;
        this.optimalWaterNeed = optimalWaterNeed;
    }

    /*
        age berechnet den Zustand eines Tree nach einer Zeiteinheit unter gegebenen Bedingungen;
        by > 0 && by <= 1; conditions != null; forest != null; periodIdentifier muss existieren (getSeason-Methode); waterPerTree >= 0;
        Tree wird gealtert
    */
    public void age(float by, ClimateDataCollection conditions, Forest forest, String periodIdentifier, float waterPerTree) {
        this.age = this.age + by;

        forest.getSoil().removeWaterFromReserve(this.optimalWaterNeed * by);

        float waterMultiplier = this.waterMultiplier(waterPerTree, this.optimalWaterNeed * by); // waterMultiplier >= 0 && waterMultiplier <= 1
        float tempMultiplier = this.tempMultiplier(conditions.getMinTemperature(), conditions.getMaxTemperature(), conditions.getAverageTemperature(), periodIdentifier); // tempMultiplier >= 0 && tempMultiplier <= 1
        float sunHourMultiplier = this.sunHoursMultiplier(conditions.getHoursOfSunshine()); // sunHourMultiplier >= 0 && sunHourMultiplier <= 1

        float totalGrowthMultiplier = waterMultiplier * tempMultiplier * sunHourMultiplier; // totalGrowthMultiplier >= 0 && totalGrowthMultiplier <= 1
        this.height = this.height + this.optimalGrowthHeight * totalGrowthMultiplier * by;

        this.height = this.height * this.calcStormDamage(conditions.getWindForce());

        this.health = 1 - ((waterMultiplier + tempMultiplier + sunHourMultiplier) / 3) / 100 * 75;

        double currentCo2Stock = this.co2Stock; // currentCo2Stock >= 0
        this.co2Stock = height / 10;
        forest.getSoil().addCo2(currentCo2Stock - this.co2Stock);
    }

    /*
        waterMultiplier liefert auf grund von mehreren Faktoren einen Wert zurück;
        waterPerTree >= 0; waterNeeded >= 0;
        Wert zwischen 0 und 1 zurückgeben;
    */
    private float waterMultiplier(float waterPerTree, float waterNeeded) {
        float minWaterNeed = waterNeeded - 0.25f * this.droughtResistance * waterNeeded; // minWaterNeed >= 0 && minWaterNeed <= 1
        if (waterPerTree < minWaterNeed) {
            return waterPerTree / minWaterNeed;
        } else {
            return 1;
        }
    }

    /*
        sunHoursMultiplier liefert auf grund von mehreren Faktoren einen Wert zurück;
        sunHours >= 0 && sunHours <= 24;
        Wert zwischen 0 und 1 zurückgeben;
    */
    private float sunHoursMultiplier(float sunHours) {
        if (sunHours < this.optimalSunHoursPerDay) {
            return sunHours / this.optimalSunHoursPerDay;
        } else {
            return 1;
        }
    }

    /*
        tempMultiplier liefert auf grund von mehreren Faktoren einen Wert zurück;
        periodIdentifier muss existieren (getSeason-Methode);
        Wert zwischen 0.25 und 1 zurückgeben;
    */
    private float tempMultiplier(float min, float max, float avg, String periodIdentifier) {
        float minAllowedTemp = this.optimalTemperature - this.optimalTemperature * this.temperatureResistance; // minAllowedTemp >= 0 && <= 30
        float maxAllowedTemp = this.optimalTemperature + this.optimalTemperature * this.temperatureResistance; // maxAllowedTemp >= 30 && maxAllowedTemp <= 60
        float heatTemp = 35 * this.temperatureResistance; // heatTemp >= 0 && heatTemp <= 35
        float frostTemp = 0 - 5 * this.temperatureResistance; // frostTemp >= -5 && <= 0
        float returnValue = 1; // returnValue >= 0.25 && returnValue <= 1

        if (max > heatTemp) {
            returnValue = returnValue - 0.1f;
        }

        if (min < frostTemp) {
            returnValue = returnValue - 0.1f;

            if (periodIdentifier.equals("spring") || periodIdentifier.equals("fall") || periodIdentifier.equals("september")) {
                returnValue = returnValue - 0.2f;
            }
        }

        float tempDifference = 0;
        if (avg < minAllowedTemp) {
            tempDifference = Math.abs(minAllowedTemp - avg);
        } else if (avg > maxAllowedTemp) {
            tempDifference = Math.abs(maxAllowedTemp - avg);
        }

        returnValue = returnValue - tempDifference / 15;

        if (returnValue < 0.25) {
            return 0.25f;
        } else if (returnValue > 1) {
            return 1;
        } else {
            return returnValue;
        }

    }

    /*
        calcStormDamage berechnet einen Wert aufgrund von dem Parameter und der Random Klasse;
        windforce >= 0;
        Wert zwischen -1 und 1 zurückgeben;
    */
    private float calcStormDamage(float windForce) {
        Random random = new Random();
        float maxWindWithoutLoss = this.windResistance * 15; // maxWindWithoutLoss >= 0 && maxWindWithoutLoss <= 15
        if (windForce > maxWindWithoutLoss) {
            if(windForce > maxWindWithoutLoss*1.85f){
                if(random.nextFloat() <= 0.2f){
                    return -1;
                }
            }
            return maxWindWithoutLoss / windForce;
        } else {
            return 1;
        }
    }

    /*
        Die aktuelle height Variable wird zurückgegeben
    */
    public double getHeight() {
        return this.height;
    }

    /*
        Die aktuelle age Variable wird zurückgegeben
    */
    public float getAge() {
        return age;
    }

    /*
        Die aktuelle health Variable wird zurückgegeben
    */
    public double getHealth() {
        return health;
    }

    /*
        Die aktuelle co2stock Variable wird zurückgegeben
    */
    public double getCo2Stock() {
        return co2Stock;
    }

    /*
        Gibt einen String der einen Baum representiert zurück
    */
    @Override
    public String toString() {
        return "Tree{" +
                "age=" + age +
                ", health=" + health +
                ", temparatureResistance=" + temperatureResistance +
                '}';
    }

    /*
        randomize belegt ein Objekt von Tree mit zufälligen Werten;
        Alle Variablen von Tree werden mit zufäligen Werten befüllt
    */
    private void randomize() {
        Random random = new Random();
        this.age = random.nextInt(200);
        if (this.age >= 80) {
            this.height = 80 * (random.nextFloat() * 0.3f + 0.2f) + (this.age - 80) * (random.nextFloat() * 0.1f + 0.1f);
        } else {
            this.height = this.age * (random.nextFloat() * 0.3f + 0.2f);
        }
        this.health = random.nextFloat() * 0.75f + 0.25f;
        this.temperatureResistance = random.nextFloat();
        this.droughtResistance = random.nextFloat();
        this.co2Stock = height / 10;
        this.windResistance = random.nextFloat() * 1 + 0.5f;
        this.optimalGrowthHeight = 1.5f;
        this.optimalTemperature = random.nextFloat() * 5 + 25;
        this.optimalWaterNeed = (int) (random.nextFloat() * 150 + 400);
    }

    /*
        randomizeNew belegt ein Objekt von Tree mit zufälligen Werten;
        Alle Variablen von Tree werden mit zufäligen Werten befüllt
    */
    public void randomizeNew() {
        Random random = new Random();
        this.age = 0;
        this.height = 0;
        this.health = random.nextFloat() * 0.05f + 0.25f;
        this.temperatureResistance = random.nextFloat() * 0.5f + 0.75f;
        this.droughtResistance = random.nextFloat() * 0.5f + 0.75f;
        this.co2Stock = 0;
        this.windResistance = random.nextFloat() * 0.5f + 0.75f;
        this.optimalGrowthHeight = 1.5f;
        this.optimalTemperature = random.nextFloat() * 5 + 25;
        this.optimalWaterNeed = (int) (random.nextFloat() * 150 + 400);
    }
}
