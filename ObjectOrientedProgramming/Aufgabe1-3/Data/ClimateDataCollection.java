package Data;
import java.util.Random;

public class ClimateDataCollection {
    private static final Random random = new Random();
    private float minTemperature; // minTemperature >= -6 && minTemperature <= 16
    private float maxTemperature; // maxTemperature >= 1 && maxTemperature <= 30
    private float averageTemperature; // averageTemperature >= -2.5 && averageTemperature <= 23
    private float rainfall; // rainfall >= 0
    private float hoursOfSunshine; // hoursOfSunshine >= 0
    private float windForce; // windForce >= 0

    /*
       Das Objekt ClimateDataCollection wird instanziert
    */
    public ClimateDataCollection(int simulationDensity, int periodCounter, float climateChangeFactor) {
        this.randomize(simulationDensity, periodCounter, climateChangeFactor);
    }

    /*
        Aktuelle minTemperature wird zurückgegeben
    */
    public float getMinTemperature() {
        return minTemperature;
    }

    /*
        Aktuelle maxTemperature wird zurückgegeben
    */
    public float getMaxTemperature() {
        return maxTemperature;
    }

    /*
        Aktuelle averageTemperature wird zurückgegeben
    */
    public float getAverageTemperature() {
        return averageTemperature;
    }

    /*
        Aktuelle rainfall wird zurückgegeben
    */
    public float getRainfall() {
        return rainfall;
    }

    /*
        Aktuelle hoursOfSunshine wird zurückgegeben
    */
    public float getHoursOfSunshine() {
        return hoursOfSunshine;
    }

    /*
        Aktuelle windForce wird zurückgegeben
    */
    public float getWindForce() {
        return windForce;
    }

    /*
        randomize belegt ein Objekt von ClimateDataCollection mit zufälligen Werten;
        simulationDensity > 0 && simulationDensity <= 365; periodCounter > 0; climateChangeFactor >= 0;
        ClimateDataCollection wird je nach Tag, Monat oder Jahreszeit mit zufälligen Daten befüllt
    */
    private void randomize(int simulationDensity, int periodCounter, float climateChangeFactor) {
        float seasonLength = simulationDensity / 4f; // seasonLength >= 0 && seasonLength <= 91.25
        if (simulationDensity < 12) {
            if (periodCounter <= seasonLength) {
                this.winterValues(climateChangeFactor, simulationDensity);
            } else if (periodCounter <= 2 * seasonLength) {
                this.springValues(climateChangeFactor, simulationDensity);
            } else if (periodCounter <= 3 * seasonLength) {
                this.summerValues(climateChangeFactor, simulationDensity);
            } else {
                this.fallValues(climateChangeFactor, simulationDensity);
            }
        } else {
            seasonLength = simulationDensity / 12f; // seasonLength >= 0 && seasonLength <= 30.42
            if (periodCounter <= seasonLength) {
                this.januaryValues(climateChangeFactor, simulationDensity);
            } else if (periodCounter <= 2 * seasonLength) {
                this.februaryValues(climateChangeFactor, simulationDensity);
            } else if (periodCounter <= 3 * seasonLength) {
                this.marchValues(climateChangeFactor, simulationDensity);
            } else if (periodCounter <= 4 * seasonLength) {
                this.aprilValues(climateChangeFactor, simulationDensity);
            } else if (periodCounter <= 5 * seasonLength) {
                this.mayValues(climateChangeFactor, simulationDensity);
            } else if (periodCounter <= 6 * seasonLength) {
                this.juneValues(climateChangeFactor, simulationDensity);
            } else if (periodCounter <= 7 * seasonLength) {
                this.julyValues(climateChangeFactor, simulationDensity);
            } else if (periodCounter <= 8 * seasonLength) {
                this.augustValues(climateChangeFactor, simulationDensity);
            } else if (periodCounter <= 9 * seasonLength) {
                this.septemberValues(climateChangeFactor, simulationDensity);
            } else if (periodCounter <= 10 * seasonLength) {
                this.octoberValues(climateChangeFactor, simulationDensity);
            } else if (periodCounter <= 11 * seasonLength) {
                this.novemberValues(climateChangeFactor, simulationDensity);
            } else {
                this.decemberValues(climateChangeFactor, simulationDensity);
            }
        }
    }

    /*
        Zufallszahl wird zurückgegeben
    */
    private float getRandomWindForce() {
        if (random.nextFloat() > 0.7) {
            if (random.nextFloat() > 0.6) {
                return random.nextFloat() * 5 + 15;
            } else {
                return random.nextFloat() * 6 + 8;
            }
        } else {
            return random.nextFloat() * 7;
        }
    }

    /*
        Zufallszahl wird zurückgegeben
    */
    private float getRandomExtreme() {
        if (random.nextFloat() > 0.7) {
            return random.nextFloat() * 5 + 6;
        } else {
            return 0;
        }
    }

    /*
        Das Objekt ClimateDataCollection wird mit zufälligen Werten befüllt;
        climateChangeFactor >= 0; simulationDensity > 0 && simulationDensity <= 365;
        Das Objekt wird mit typischen Wetterdaten für die Jahreszeit Winter befüllt
    */
    private void winterValues(float climateChangeFactor, int simulationDensity) {
        this.minTemperature = random.nextFloat() * 4 - 6 - getRandomExtreme() + climateChangeFactor; //-6 bis -2
        this.maxTemperature = random.nextFloat() * 7 + 1 + getRandomExtreme() + climateChangeFactor; //1 - 8
        this.averageTemperature = (this.maxTemperature + this.minTemperature) / 2;
        this.rainfall = (random.nextFloat() * 10 + 73) * 4 / simulationDensity;
        this.hoursOfSunshine = (random.nextFloat() * 80 + 353) * 4 / simulationDensity;
        this.windForce = this.getRandomWindForce();
    }

    /*
        Das Objekt ClimateDataCollection wird mit zufälligen Werten befüllt;
        climateChangeFactor >= 0; simulationDensity > 0 && simulationDensity <= 365;
        Das Objekt wird mit typischen Wetterdaten für die Jahreszeit Frühling befüllt
    */
    private void springValues(float climateChangeFactor, int simulationDensity) {
        this.minTemperature = random.nextFloat() * 8 - 3 - getRandomExtreme() + climateChangeFactor; //-3 bis 5
        this.maxTemperature = random.nextFloat() * 8 + 9 + getRandomExtreme() + climateChangeFactor; //9 bis 17
        this.averageTemperature = (this.maxTemperature + this.minTemperature) / 2;
        this.rainfall = (random.nextFloat() * 10 + 128) * 4 / simulationDensity;
        this.hoursOfSunshine = (random.nextFloat() * 80 + 713) * 4 / simulationDensity;
        this.windForce = getRandomWindForce();
    }

    /*
        Das Objekt ClimateDataCollection wird mit zufälligen Werten befüllt;
        climateChangeFactor >= 0; simulationDensity > 0 && simulationDensity <= 365;
        Das Objekt wird mit typischen Wetterdaten für die Jahreszeit Sommer befüllt
    */
    private void summerValues(float climateChangeFactor, int simulationDensity) {
        this.minTemperature = random.nextFloat() * 3 + 13 - getRandomExtreme() + climateChangeFactor; //13 bis 16
        this.maxTemperature = random.nextFloat() * 2 + 33 + getRandomExtreme() + climateChangeFactor; //33 bis 35
        this.averageTemperature = (this.maxTemperature + this.minTemperature) / 2;
        this.rainfall = (random.nextFloat() * 60 + 362) * 4 / simulationDensity;
        this.hoursOfSunshine = (random.nextFloat() * 80 + 700) * 4 / simulationDensity;
        this.windForce = getRandomWindForce();
    }

    /*
        Das Objekt ClimateDataCollection wird mit zufälligen Werten befüllt;
        climateChangeFactor >= 0; simulationDensity > 0 && simulationDensity <= 365;
        Das Objekt wird mit typischen Wetterdaten für die Jahreszeit Herbst befüllt
    */
    private void fallValues(float climateChangeFactor, int simulationDensity) {
        this.minTemperature = random.nextFloat() * 9 + 3 - getRandomExtreme() + climateChangeFactor; //3 bis 12
        this.maxTemperature = random.nextFloat() * 14 + 9 + getRandomExtreme() + climateChangeFactor; //9 bis 23
        this.averageTemperature = (this.maxTemperature + this.minTemperature) / 2;
        this.rainfall = (random.nextFloat() * 60 + 176) * 4 / simulationDensity;
        this.hoursOfSunshine = (random.nextFloat() * 80 + 335) * 4 / simulationDensity;
        this.windForce = getRandomWindForce();
    }

    /*
        Das Objekt ClimateDataCollection wird mit zufälligen Werten befüllt;
        climateChangeFactor >= 0; simulationDensity > 0 && simulationDensity <= 365;
        Das Objekt wird mit typischen Wetterdaten für den Monat Januar befüllt
    */
    private void januaryValues(float climateChangeFactor, int simulationDensity) {
        this.minTemperature = random.nextFloat() * 3 - 6 - getRandomExtreme() + climateChangeFactor; //-6 bis -3
        this.maxTemperature = random.nextFloat() * 3 + 2 + getRandomExtreme() + climateChangeFactor; //2 - 5
        this.averageTemperature = (this.maxTemperature + this.minTemperature) / 2;
        this.rainfall = (random.nextFloat() * 100 + 670) * 12 / simulationDensity; //24mm
        this.hoursOfSunshine = (random.nextFloat() * 40 + 118) * 12 / simulationDensity; //4.6h
        this.windForce = this.getRandomWindForce();
    }

    /*
        Das Objekt ClimateDataCollection wird mit zufälligen Werten befüllt;
        climateChangeFactor >= 0; simulationDensity > 0 && simulationDensity <= 365;
        Das Objekt wird mit typischen Wetterdaten für den Monat Februar befüllt
    */
    private void februaryValues(float climateChangeFactor, int simulationDensity) {
        this.minTemperature = random.nextFloat() * 3 - 4 - getRandomExtreme() + climateChangeFactor; //-4 bis -1
        this.maxTemperature = random.nextFloat() * 3 + 4 + getRandomExtreme() + climateChangeFactor; //4 - 7
        this.averageTemperature = (this.maxTemperature + this.minTemperature) / 2;
        this.rainfall = (random.nextFloat() * 100 + 610) * 12 / simulationDensity; //22mm
        this.hoursOfSunshine = (random.nextFloat() * 40 + 112) * 12 / simulationDensity; //4.4h
        this.windForce = this.getRandomWindForce();
    }

    /*
        Das Objekt ClimateDataCollection wird mit zufälligen Werten befüllt;
        climateChangeFactor >= 0; simulationDensity > 0 && simulationDensity <= 365;
        Das Objekt wird mit typischen Wetterdaten für den Monat März befüllt
    */
    private void marchValues(float climateChangeFactor, int simulationDensity) {
        this.minTemperature = random.nextFloat() * 3 - 0 - getRandomExtreme() + climateChangeFactor; //0 bis 3
        this.maxTemperature = random.nextFloat() * 3 + 10 + getRandomExtreme() + climateChangeFactor; //10 - 13
        this.averageTemperature = (this.maxTemperature + this.minTemperature) / 2;
        this.rainfall = (random.nextFloat() * 100 + 610) * 12 / simulationDensity; //22mm
        this.hoursOfSunshine = (random.nextFloat() * 40 + 184) * 12 / simulationDensity; //6.8h
        this.windForce = this.getRandomWindForce();
    }

    /*
        Das Objekt ClimateDataCollection wird mit zufälligen Werten befüllt;
        climateChangeFactor >= 0; simulationDensity > 0 && simulationDensity <= 365;
        Das Objekt wird mit typischen Wetterdaten für den Monat April befüllt
    */
    private void aprilValues(float climateChangeFactor, int simulationDensity) {
        this.minTemperature = random.nextFloat() * 3 + 3 - getRandomExtreme() + climateChangeFactor; //3 bis 6
        this.maxTemperature = random.nextFloat() * 3 + 16 + getRandomExtreme() + climateChangeFactor; //16 - 19
        this.averageTemperature = (this.maxTemperature + this.minTemperature) / 2;
        this.rainfall = (random.nextFloat() * 100 + 550) * 12 / simulationDensity; //20mm
        this.hoursOfSunshine = (random.nextFloat() * 40 + 274) * 12 / simulationDensity; //9.8h
        this.windForce = this.getRandomWindForce();
    }

    /*
        Das Objekt ClimateDataCollection wird mit zufälligen Werten befüllt;
        climateChangeFactor >= 0; simulationDensity > 0 && simulationDensity <= 365;
        Das Objekt wird mit typischen Wetterdaten für den Monat Mai befüllt
    */
    private void mayValues(float climateChangeFactor, int simulationDensity) {
        this.minTemperature = random.nextFloat() * 3 + 6 - getRandomExtreme() + climateChangeFactor; //6 bis 9
        this.maxTemperature = random.nextFloat() * 3 + 19 + getRandomExtreme() + climateChangeFactor; //19 - 22
        this.averageTemperature = (this.maxTemperature + this.minTemperature) / 2;
        this.rainfall = (random.nextFloat() * 200 + 1280) * 12 / simulationDensity; //46mm
        this.hoursOfSunshine = (random.nextFloat() * 40 + 277) * 12 / simulationDensity; //9.9h
        this.windForce = this.getRandomWindForce();
    }

    /*
        Das Objekt ClimateDataCollection wird mit zufälligen Werten befüllt;
        climateChangeFactor >= 0; simulationDensity > 0 && simulationDensity <= 365;
        Das Objekt wird mit typischen Wetterdaten für den Monat Juni befüllt
    */
    private void juneValues(float climateChangeFactor, int simulationDensity) {
        this.minTemperature = random.nextFloat() * 3 + 10 - getRandomExtreme() + climateChangeFactor; //10 bis 13
        this.maxTemperature = random.nextFloat() * 3 + 24 + getRandomExtreme() + climateChangeFactor; //24 - 27
        this.averageTemperature = (this.maxTemperature + this.minTemperature) / 2;
        this.rainfall = (random.nextFloat() * 200 + 1220) * 12 / simulationDensity; //44mm
        this.hoursOfSunshine = (random.nextFloat() * 40 + 307) * 12 / simulationDensity; //10.9h
        this.windForce = this.getRandomWindForce();
    }

    /*
        Das Objekt ClimateDataCollection wird mit zufälligen Werten befüllt;
        climateChangeFactor >= 0; simulationDensity > 0 && simulationDensity <= 365;
        Das Objekt wird mit typischen Wetterdaten für den Monat Juli befüllt
    */
    private void julyValues(float climateChangeFactor, int simulationDensity) {
        this.minTemperature = random.nextFloat() * 3 + 12 - getRandomExtreme() + climateChangeFactor; //12 bis 15
        this.maxTemperature = random.nextFloat() * 3 + 27 + getRandomExtreme() + climateChangeFactor; //27 - 30
        this.averageTemperature = (this.maxTemperature + this.minTemperature) / 2;
        this.rainfall = (random.nextFloat() * 150 + 1035) * 12 / simulationDensity; //37mm
        this.hoursOfSunshine = (random.nextFloat() * 40 + 313) * 12 / simulationDensity; //11.1h
        this.windForce = this.getRandomWindForce();
    }

    /*
        Das Objekt ClimateDataCollection wird mit zufälligen Werten befüllt;
        climateChangeFactor >= 0; simulationDensity > 0 && simulationDensity <= 365;
        Das Objekt wird mit typischen Wetterdaten für den Monat August befüllt
    */
    private void augustValues(float climateChangeFactor, int simulationDensity) {
        this.minTemperature = random.nextFloat() * 3 + 13 - getRandomExtreme() + climateChangeFactor; //13 bis 16
        this.maxTemperature = random.nextFloat() * 3 + 27 + getRandomExtreme() + climateChangeFactor; //27 - 30
        this.averageTemperature = (this.maxTemperature + this.minTemperature) / 2;
        this.rainfall = (random.nextFloat() * 100 + 730) * 12 / simulationDensity; //26mm
        this.hoursOfSunshine = (random.nextFloat() * 40 + 316) * 12 / simulationDensity; //11.2h
        this.windForce = this.getRandomWindForce();
    }

    /*
        Das Objekt ClimateDataCollection wird mit zufälligen Werten befüllt;
        climateChangeFactor >= 0; simulationDensity > 0 && simulationDensity <= 365;
        Das Objekt wird mit typischen Wetterdaten für den Monat September befüllt
    */
    private void septemberValues(float climateChangeFactor, int simulationDensity) {
        this.minTemperature = random.nextFloat() * 3 + 9 - getRandomExtreme() + climateChangeFactor; //9 bis 12
        this.maxTemperature = random.nextFloat() * 3 + 21 + getRandomExtreme() + climateChangeFactor; //21 - 24
        this.averageTemperature = (this.maxTemperature + this.minTemperature) / 2;
        this.rainfall = (random.nextFloat() * 100 + 910) * 12 / simulationDensity; //32mm
        this.hoursOfSunshine = (random.nextFloat() * 40 + 259) * 12 / simulationDensity; //9.3h
        this.windForce = this.getRandomWindForce();
    }

    /*
        Das Objekt ClimateDataCollection wird mit zufälligen Werten befüllt;
        climateChangeFactor >= 0; simulationDensity > 0 && simulationDensity <= 365;
        Das Objekt wird mit typischen Wetterdaten für den Monat Oktober befüllt
    */
    private void octoberValues(float climateChangeFactor, int simulationDensity) {
        this.minTemperature = random.nextFloat() * 3 + 4 - getRandomExtreme() + climateChangeFactor; //4 bis 7
        this.maxTemperature = random.nextFloat() * 3 + 15 + getRandomExtreme() + climateChangeFactor; //15 - 18
        this.averageTemperature = (this.maxTemperature + this.minTemperature) / 2;
        this.rainfall = (random.nextFloat() * 100 + 730) * 12 / simulationDensity; //26mm
        this.hoursOfSunshine = (random.nextFloat() * 40 + 160) * 12 / simulationDensity; //6h
        this.windForce = this.getRandomWindForce();
    }

    /*
        Das Objekt ClimateDataCollection wird mit zufälligen Werten befüllt;
        climateChangeFactor >= 0; simulationDensity > 0 && simulationDensity <= 365;
        Das Objekt wird mit typischen Wetterdaten für den Monat November befüllt
    */
    private void novemberValues(float climateChangeFactor, int simulationDensity) {
        this.minTemperature = random.nextFloat() * 3 + 1 - getRandomExtreme() + climateChangeFactor; //1 bis 4
        this.maxTemperature = random.nextFloat() * 3 + 9 + getRandomExtreme() + climateChangeFactor; //9 - 12
        this.averageTemperature = (this.maxTemperature + this.minTemperature) / 2;
        this.rainfall = (random.nextFloat() * 100 + 730) * 12 / simulationDensity; //26mm
        this.hoursOfSunshine = (random.nextFloat() * 40 + 139) * 12 / simulationDensity; //5.3h
        this.windForce = this.getRandomWindForce();
    }

    /*
        Das Objekt ClimateDataCollection wird mit zufälligen Werten befüllt;
        climateChangeFactor >= 0; simulationDensity > 0 && simulationDensity <= 365;
        Das Objekt wird mit typischen Wetterdaten für den Monat Dezember befüllt
    */
    private void decemberValues(float climateChangeFactor, int simulationDensity) {
        this.minTemperature = random.nextFloat() * 3 - 4 - getRandomExtreme() + climateChangeFactor; //-4 bis -1
        this.maxTemperature = random.nextFloat() * 3 + 1 + getRandomExtreme() + climateChangeFactor; //1 - 4
        this.averageTemperature = (this.maxTemperature + this.minTemperature) / 2;
        this.rainfall = (random.nextFloat() * 100 + 820) * 12 / simulationDensity; //29mm
        this.hoursOfSunshine = (random.nextFloat() * 40 + 130) * 12 / simulationDensity; //5h
        this.windForce = this.getRandomWindForce();
    }

    /*
        Gibt einen String der alle Werte des Objektes ClimateDataCollection enthält zurück
    */
    @Override
    public String toString() {
        return "ClimateDataCollection{" +
                "minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", averageTemperature=" + averageTemperature +
                ", rainfall=" + rainfall +
                ", hoursOfSunshine=" + hoursOfSunshine +
                ", windForce=" + windForce +
                '}';
    }
}
