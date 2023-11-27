package Tree;

import java.util.Random;

public class SpruceTree extends Tree{

    /*
        Das Objekt SpruceTree wird mit zufälligen Werten instanziert
     */
    public SpruceTree(){
        this.randomize();
    }

    /*
        randomize belegt ein Objekt von SpruceTree mit zufälligen Werten;
        Alle Variablen von SpruceTree werden mit zufäligen Werten befüllt
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
        this.optimalGrowthHeight = 1.25f;
        this.optimalTemperature = random.nextFloat() * 5 + 24;
        this.optimalWaterNeed = (int) (random.nextFloat() * 150 + 400);
    }

    /*
        randomizeNew belegt ein Objekt von SpruceTree mit zufälligen Werten;
        Alle Variablen von SpruceTree werden mit zufäligen Werten befüllt
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
        this.optimalGrowthHeight = 1.25f;
        this.optimalTemperature = random.nextFloat() * 5 + 24;
        this.optimalWaterNeed = (int) (random.nextFloat() * 150 + 400);
    }
}
