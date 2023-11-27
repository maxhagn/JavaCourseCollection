package Old;// Object for a Old.ConditionChange for one year

public class ConditionChange {
    private final double growthRate;
    private final double lossRate;

    public ConditionChange(double growthRate, double lossRate) {
        this.growthRate = growthRate;
        this.lossRate = lossRate;
    }


    public double getGrowthRate( ) {
        return growthRate;
    }
    public double getLossRate( ) {
        return lossRate;
    }
}
