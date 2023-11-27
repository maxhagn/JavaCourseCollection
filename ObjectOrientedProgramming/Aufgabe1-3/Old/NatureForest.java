package Old;

import java.util.concurrent.LinkedBlockingQueue;

public class NatureForest {

    private final ForestCondition forestCondition;
    private boolean highLoss;


    public NatureForest(ForestCondition forestCondition) {
        this.forestCondition = forestCondition;
    }

    public void startSimulation(LinkedBlockingQueue<ConditionChange> simulationValues, int years) {

        System.out.println("Simulation naturbelassener Wald:");
        System.out.format("%5s%18s%16s%16s%16s%16s%16s\n", "Jahr", "Baumbestand", "Altersstruktur", "Gesundheit", "Zielbestand", "Ernte", "CO2 Vorrat");

        for (int i = 0; i <= years; i++) {
            if (!simulationValues.isEmpty()) {
                // take change rates from simulation data
                ConditionChange currentChange = simulationValues.poll();
                double loss, growth;

                // calculate the absolute loss and growth for one specific year
                loss = currentChange.getLossRate() * this.forestCondition.getHealth();
                growth = currentChange.getGrowthRate() * this.forestCondition.getTargetStock() - loss * this.forestCondition.getTreePopulation();

                this.forestCondition.setTreePopulation(this.forestCondition.getTreePopulation() + growth);

                // changes Old.AgeStructure for aging trees
                this.forestCondition.getAgeStructure().age(loss);

                // if high loss rate was triggered, add 5 fm every year
                if (highLoss) {
                    if (this.forestCondition.getTargetStock() + 5 >= 250) {
                        highLoss = false;
                        this.forestCondition.setTargetStock(250);
                    } else {
                        this.forestCondition.setTargetStock(this.forestCondition.getTargetStock() + 5);
                    }
                }

                // set co2 stock
                this.forestCondition.setCo2Stock(this.forestCondition.getCo2Stock() + growth);

                // calculate extra co2 stock
                if (loss < 0.3) {
                    this.forestCondition.setCo2Stock(this.forestCondition.getCo2Stock() + this.forestCondition.getTreePopulation() * loss / 3);
                } else {
                    this.highLoss = true;
                    this.forestCondition.setTargetStock(this.forestCondition.getTargetStock() * (1 - loss));
                    this.forestCondition.setCo2Stock(this.forestCondition.getCo2Stock() * (1 - loss / 2));
                }

                // calculate the current health
                this.forestCondition.setHealth(this.forestCondition.getAgeStructure().calculateHealth());

                // print after 100 years
                if (i % 100 == 0) {
                    System.out.format("%5d", i);
                    System.out.println(this.forestCondition.toString());
                }
            }
        }
        System.out.println("\n\n");
    }
}
