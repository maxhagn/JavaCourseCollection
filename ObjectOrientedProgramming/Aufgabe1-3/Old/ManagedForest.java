package Old;

import java.util.concurrent.LinkedBlockingQueue;

public class ManagedForest {

    private final ForestCondition forestCondition;
    private boolean harvest = false;
    private int yearCount = 0;
    private boolean highLoss;

    public ManagedForest(ForestCondition forestCondition) {
        this.forestCondition = forestCondition;
    }

    public void startSimulation(LinkedBlockingQueue<ConditionChange> simulationValues, int years) {
        System.out.println("Simulation für einen bewirtschafteten Wald");
        System.out.format("%5s%18s%16s%16s%16s%16s%16s\n", "Jahr", "Baumbestand", "Altersstruktur", "Gesundheit", "Zielbestand", "Ernte", "CO2 Vorrat");


        for (int i = 0; i <= years; i++) {
            if (!simulationValues.isEmpty()) {

                // take change rates from simulation data
                ConditionChange currentChange = simulationValues.poll();
                double loss, growth, lossAbsolute;
                double lossHarvest = 0;
                double takenWood = 0;
                double treePopulation = this.forestCondition.getTreePopulation();

                this.harvest = false;

                // calculate the absolute loss and growth for one specific year
                loss = currentChange.getLossRate() * this.forestCondition.getHealth();
                growth = currentChange.getGrowthRate() * this.forestCondition.getTargetStock() - loss * this.forestCondition.getTreePopulation();

                // calculate this year's natural loss
                double lossNatural = this.forestCondition.getTreePopulation() * loss;

                // changes Old.AgeStructure for aging trees
                this.forestCondition.getAgeStructure().age(loss);

                // if high loss rate was triggered, add 5 fm every year
                if (highLoss) {
                    if (this.forestCondition.getTargetStock() >= 350) {
                        highLoss = false;
                    } else {
                        if (this.forestCondition.getTargetStock() + 5 > 350) {
                            this.forestCondition.setTargetStock(350);
                        } else {
                            this.forestCondition.setTargetStock(this.forestCondition.getTargetStock() + 5);
                        }
                    }
                }

                // setting new tree population under consideration of this year's growth
                this.forestCondition.setTreePopulation(this.forestCondition.getTreePopulation() + growth);

                // case of unplanned harvest: calculate new age structure, new tree population and wood which was taken from the forest
                if (loss >= 0.1 && loss < 0.3) {
                    this.forestCondition.setHarvest(this.forestCondition.getAgeStructure().harvest(45));
                    this.forestCondition.setTreePopulation(this.forestCondition.getTreePopulation() - this.forestCondition.getHarvest());
                    lossHarvest = this.forestCondition.getHarvest() * 1 / 2;
                    lossNatural *= 0.5;
                    takenWood = lossHarvest + lossNatural;
                    this.harvest = true;
                }

                if (loss < 0.1 && !harvest) {
                    yearCount++;
                } else {
                    yearCount = 0;
                }

                // case of planed harvest: calculate new age structure, new tree population and wood which was taken from the forest
                if (yearCount == 11) {
                    this.forestCondition.setHarvest(this.forestCondition.getAgeStructure().harvest(75));
                    this.forestCondition.setTreePopulation(this.forestCondition.getTreePopulation() - this.forestCondition.getHarvest());
                    lossHarvest = this.forestCondition.getHarvest() * 1 / 3;
                    takenWood = this.forestCondition.getHarvest() * 2 / 3;
                    this.harvest = true;
                    yearCount = 0;
                }

                if (!harvest) {
                    this.forestCondition.setHarvest(0);
                }

                // calculate new loss under consideration of this year's natural loss and loss of harvesting.
                lossAbsolute = lossHarvest + lossNatural + takenWood;
                double lossOverall = lossAbsolute / treePopulation;

                // set co2 stock
                this.forestCondition.setCo2Stock(this.forestCondition.getCo2Stock() + growth);

                // calculate extra co2 stock with loss overall
                if (lossOverall >= 0.3) {
                    this.highLoss = true;
                    this.forestCondition.setTargetStock(this.forestCondition.getTargetStock() * (1 - lossOverall));
                    this.forestCondition.setCo2Stock(this.forestCondition.getCo2Stock() * (1 - lossOverall / 2));
                } else {
                    this.forestCondition.setCo2Stock(this.forestCondition.getCo2Stock() - takenWood);
                }

                // calculate the current health
                this.forestCondition.setHealth(this.forestCondition.getAgeStructure().calculateHealth());

                // print after 100 years each
                if (i % 100 == 0) {
                    System.out.format("%5d", i);
                    System.out.println(this.forestCondition.toString());
                }
            }

        }
    }
}
