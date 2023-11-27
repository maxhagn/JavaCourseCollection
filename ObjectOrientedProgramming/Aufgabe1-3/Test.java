import Model.Model;
import Old.AgeStructure;
import Old.ConditionChange;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/*
    ANMERKUNG:
    Gruppennummer: K1
    Aufgabenverteilung:
        Christoph:
          -  Temperatur und Water Multiplikator (verringern das Wachstum der Bäume)
          -  Storm Multiplikator (Nimmt Festmeter bei starkem Sturm weg)
          -  Tree Reproduktion (Neue Bäume werden bei freiem Platz gepflanzt)
          -  CO_2 (Bäume und Waldboden können CO2 speichern)
        Maximilian:
          -  Test.java, Grundgerüst und Beziehungen zwischen Objekten
          -  Sun Hour Multiplikator (verringern das Wachstum der Bäume)
          -  BuildingPlanData Logik (Gebäude und Wege können gebaut und abgerissen werden, dafür wird ein Bauplan für x Jahre verwendet)
          -  Generische Harvest Funktion (der Förster kann Bäume auswählen, die in einem bestimmten Abstand gefällt werden)
        Pascal:
          -  ClimateDataCollection (Wetterdaten zu einem Zeitpunkt x)
          -  verschiedene Tree Arten können (Spezialtypen von Bäumen befinden sich im Wald und werden gepflanzt)
          -  randomize Funktionen (zum Testen können alle Objekte zufällig generiert werden)
 */

public class Test {
    public static void main(String[] args) {
        Model testEasyModel = new Model(5000,240, 12);
        testEasyModel.startSimulation(12);
    }
}

