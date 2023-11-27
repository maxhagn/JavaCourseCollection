package Tree;

import java.util.Random;

public class OakTree extends Tree{

    /*
        Das Objekt OakTree wird mit zufälligen Werten instanziert
     */
    public OakTree(){
        this.randomize();
    }


    /*
        randomize belegt ein Objekt von OakTree mit zufälligen Werten;
        Alle Variablen von OakTree werden mit zufäligen Werten befüllt
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
        this.temperatureResistance = random.nextFloat() * 0.5f + 0.5f;
        this.droughtResistance = random.nextFloat() * 0.55f + 0.45f;
        this.co2Stock = height / 10;
        this.windResistance = random.nextFloat() * 0.5f + 0.5f;
        this.optimalGrowthHeight = 1.5f;
        this.optimalTemperature = random.nextFloat() * 5 + 25;
        this.optimalWaterNeed = (int) (random.nextFloat() * 150 + 500);
    }

    /*
        randomizeNew belegt ein Objekt von OakTree mit zufälligen Werten;
        Alle Variablen von OakTree werden mit zufäligen Werten befüllt
    */
    public void randomizeNew() {
        Random random = new Random();
        this.age = 0;
        this.height = 0;
        this.health = random.nextFloat() * 0.05f + 0.25f;
        this.temperatureResistance = random.nextFloat() * 0.5f + 0.5f;
        this.droughtResistance = random.nextFloat() * 0.65f + 0.35f;
        this.co2Stock = 0;
        this.windResistance = random.nextFloat() * 0.5f + 0.5f;
        this.optimalGrowthHeight = 1.5f;
        this.optimalTemperature = random.nextFloat() * 5 + 25;
        this.optimalWaterNeed = (int) (random.nextFloat() * 150 + 500);
    }
}
