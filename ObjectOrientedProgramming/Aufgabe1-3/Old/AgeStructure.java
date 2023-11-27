package Old;

import java.util.HashMap;

public class AgeStructure implements Cloneable {

    private double treePopulation;
    private HashMap<Integer, Double> ageStructure;

    public AgeStructure(double treePopulation, HashMap<Integer, Double> ageStructure) {
        this.treePopulation = treePopulation;
        this.ageStructure = ageStructure;
    }

    // calculate the new age structure of the forest
    public void age(double loss) {
        int maxAge = this.ageStructure.size();
        HashMap<Integer, Double> tmp = new HashMap<Integer, Double>();
        for (int i = 0; i < maxAge; i++) {
            tmp.put(i + 1, this.ageStructure.get(i) * (1 - loss));
        }
        tmp.put(0, loss * this.treePopulation);

        this.ageStructure = tmp;
        this.treePopulation = this.ageStructure.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public double calculateHealth() {
        double mean = this.ageStructure.values().stream().mapToDouble(Double::doubleValue).average().orElse(0);
        double dif = 0;
        double summedDif = 0;

        // take all trees under age 250
        int size = 250;
        if (this.ageStructure.size() <= 250) {
            size = this.ageStructure.size();
        }

        int gaps = 0;
        for (int i = 1; i < size; i++) {
            if ( this.ageStructure.get(i) == 0 ) {
                gaps++;
            }
            dif = this.ageStructure.get(i - 1) - this.ageStructure.get(i);
            summedDif += Math.abs(dif);
        }

        summedDif = summedDif / size;
        double funcVal = 0.4;
        double valueDif = mean / summedDif * funcVal;

        return ((valueDif < 0.25) ? 0.25 : (valueDif > 1) ? 1 : valueDif);
    }

    // harvests all trees that are older than startAge
    public double harvest(int startAge) {
        double sum = 0;
        for (int i = this.ageStructure.size()-1; i > startAge ; i--) {
            sum += this.ageStructure.get(i);
            this.ageStructure.remove(i);
        }
        return sum;
    }

    @Override
    public String toString() {
        return String.format("%s %s", treePopulation, ageStructure);
    }

    public AgeStructure clone() throws CloneNotSupportedException {
        return (AgeStructure) super.clone();
    }
}
