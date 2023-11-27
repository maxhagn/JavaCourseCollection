public class Test {
    /*
     * ANMERKUNG:
     * Gruppennummer: K1
     * Aufgabenteilung:
     * - Grundstruktur, Forestry Klasse (Gemeinsam)
     * - Objektstruktur (Christoph Diepolder)
     * - Tests (Pascal Poremba)
     * - Zusicherungen (Max Hagn)
     */

    public static void main(String[] args) {
        //Testcase 1
        ToolAttachment cutter1 = new Cutter(20.5f);
        ToolAttachment cutter2 = new Cutter(25.5f);
        ToolAttachment chopper1 = new Chopper(30);
        ToolAttachment chopper2 = new Chopper(45);

        Harvester wheel1 = new WheelWoodHarvester(cutter1,1);
        Harvester wheel2 = new WheelWoodHarvester(chopper1, 2);
        Harvester wheel3 = new WheelWoodHarvester(cutter2, 3);
        Harvester wheel4 = new WheelWoodHarvester(chopper2, 4);
        Harvester wheel5 = new WheelWoodHarvester(cutter1, 5);
        Harvester wheel6 = new WheelWoodHarvester(chopper1, 6);
        Harvester wheel7 = new WheelWoodHarvester(cutter2, 7);
        Harvester wheel8 = new WheelWoodHarvester(chopper2, 8);

        Harvester walk1 = new WalkWoodHarvester(cutter1, 9);
        Harvester walk2 = new WalkWoodHarvester(chopper1, 10);
        Harvester walk3 = new WalkWoodHarvester(cutter2, 11);
        Harvester walk4 = new WalkWoodHarvester(chopper2, 12);
        Harvester walk5 = new WalkWoodHarvester(cutter1, 13);
        Harvester walk6 = new WalkWoodHarvester(chopper1, 14);
        Harvester walk7 = new WalkWoodHarvester(cutter2, 15);
        Harvester walk8 = new WalkWoodHarvester(chopper2, 16);

        Forestry forestry1 = new Forestry("Forestry 1");
        forestry1.addHarvester(1);
        forestry1.addHarvester(2);
        forestry1.addHarvester(9);
        Forestry forestry2 = new Forestry("Forestry 2");
        forestry2.addHarvester(10);
        forestry2.addHarvester(11);
        Forestry forestry3 = new Forestry("Forestry 3");
        forestry3.addHarvester(3);
        forestry3.addHarvester(4);
        forestry3.addHarvester(12);
        Forestry forestry4 = new Forestry("Forestry 4");
        forestry4.addHarvester(5);
        forestry4.addHarvester(6);
        forestry4.addHarvester(13);
        Forestry forestry5 = new Forestry("Forestry 5");
        forestry5.addHarvester(14);
        forestry5.addHarvester(15);
        Forestry forestry6 = new Forestry("Forestry 6");
        forestry6.addHarvester(7);
        forestry6.addHarvester(8);
        forestry6.addHarvester(16);

        Region region1 = new Region("Region 1");
        region1.addForestry("Forestry 1");
        region1.addForestry("Forestry 2");
        region1.addForestry("Forestry 3");
        Region region2 = new Region("Region 2");
        region2.addForestry("Forestry 4");
        region2.addForestry("Forestry 5");
        region2.addForestry("Forestry 6");

        //Tescase 2
        region1.removeForestry("Forestry 1");
        region2.addForestry("Forestry 1");
        region2.removeForestry("Forestry 5");
        region1.addForestry("Forestry 5");

        //Testcase 3
        forestry1.removeHarvester(1);
        forestry2.addHarvester(1);
        forestry4.removeHarvester(5);
        forestry4.removeHarvester(6);
        forestry5.addHarvester(5);
        forestry6.addHarvester(6);

        forestry1.raiseOperatingHours(2, 10);
        forestry1.raiseOperatingHours(9, 22);
        forestry1.raiseDistance(2, 5);
        forestry1.raiseSteps(9, 120);

        forestry2.raiseOperatingHours(1, 99);
        forestry2.raiseOperatingHours(10, 15);
        forestry2.raiseDistance(1, 190);
        forestry2.raiseSteps(10, 40);

        forestry3.raiseOperatingHours(3, 11);
        forestry3.raiseOperatingHours(4, 22);
        forestry3.raiseOperatingHours(12, 33);
        forestry3.raiseDistance(3, 5);
        forestry3.raiseDistance(4, 10);
        forestry3.raiseSteps(12, 20);

        forestry5.raiseOperatingHours(5, 33);
        forestry5.raiseOperatingHours(14, 44);
        forestry5.raiseOperatingHours(15, 55);
        forestry5.raiseDistance(5, 20);
        forestry5.raiseSteps(14, 40);
        forestry5.raiseSteps(15, 60);

        forestry6.raiseOperatingHours(6, 100);
        forestry6.raiseOperatingHours(7, 100);
        forestry6.raiseOperatingHours(16, 100);
        forestry6.raiseDistance(6, 200);
        forestry6.raiseSteps(7, 200);
        forestry6.raiseSteps(16, 200);

        //Testcases 4
        System.out.println("Statistische Werte:");
        region1.printForestrys();
        System.out.println("");
        System.out.println("----------------------------------------------");
        System.out.println("");
        region2.printForestrys();

    }
}