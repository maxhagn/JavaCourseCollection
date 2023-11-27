package Old;

public class ForestCondition implements Cloneable{
    private double treePopulation;
    private AgeStructure ageStructure;
    private double health;
    private double targetStock = 0;
    private double harvest = 0;
    private double co2Stock;


    public ForestCondition(double treePopulation, AgeStructure ageStructure, double health, double targetStock, double harvest, double co2Stock) {
        this.treePopulation = treePopulation;
        this.ageStructure = ageStructure;
        this.health = health;
        this.targetStock = targetStock;
        this.harvest = harvest;
        this.co2Stock = co2Stock;
    }

    public ForestCondition(double treePopulation, AgeStructure ageStructure, double health, double co2Stock) {
        this.treePopulation = treePopulation;
        this.ageStructure = ageStructure;
        this.health = health;
        this.co2Stock = co2Stock;
    }

    public double getTreePopulation() {
        return treePopulation;
    }

    public void setTreePopulation(double treePopulation) {
        this.treePopulation = treePopulation;
    }

    public AgeStructure getAgeStructure() {
        return ageStructure;
    }

    public void setAgeStructure(AgeStructure ageStructure) {
        this.ageStructure = ageStructure;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getTargetStock() {
        return targetStock;
    }

    public void setTargetStock(double targetStock) {
        this.targetStock = targetStock;
    }

    public double getHarvest() {
        return harvest;
    }

    public void setHarvest(double harvest) {
        this.harvest = harvest;
    }

    public double getCo2Stock() {
        return co2Stock;
    }

    public void setCo2Stock(double co2Stock) {
        this.co2Stock = co2Stock;
    }

    @Override
    public String toString() {
        return String.format("%18f%16s%16f%16f%16f%16f", treePopulation, "ageStructure", health, targetStock, harvest, co2Stock);
    }

    public ForestCondition clone() throws CloneNotSupportedException {
        return (ForestCondition) super.clone();
    }
}
