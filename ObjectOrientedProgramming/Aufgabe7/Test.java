public class Test {
    /*
     * ANMERKUNG:
     * Gruppennummer: K1
     * Aufgabenteilung:
     * - Grundstruktur, Funktionen fill(), grow(), get(), print() (Gemeinsam)
     * - Testsfälle, Zusicherungen (Christoph Diepolder)
     * - Funktionen thin(), establish(), cut() (Pascal Poremba)
     * - Objektstruktur, Zusicherungen (Max Hagn)
     */

    public static void main(String[] args) {
        int latitude = 10;
        int longitude = 10;

        LightWood quercus1 = new Quercus(123, new Coordinate(10 ,10));
        LightWood quercus2 = new Quercus(34, new Coordinate(9 ,9));
        LightWood quercus3 = new Quercus(333, new Coordinate(8 ,8));
        LightWood quercus4 = new Quercus(90, new Coordinate(7 ,7));

        LightWood b1 = new Betula(124, new Coordinate(10 ,10));
        LightWood b2 = new Betula(355, new Coordinate(9 ,9));
        LightWood b3 = new Betula(76, new Coordinate(8 ,8));
        LightWood b4 = new Betula(322, new Coordinate(7 ,7));

        ShadowWood c1 = new CarpinusBetulus(1.5f, new Coordinate(10 ,10));
        ShadowWood c2 = new CarpinusBetulus(1.5f, new Coordinate(9 ,9));
        ShadowWood c3 = new CarpinusBetulus(3.2f, new Coordinate(8 ,8));


        ShadowWood fagus1 = new Fagus(1.3f, new Coordinate(10, 10));
        ShadowWood fagus2 = new Fagus(4.1f, new Coordinate(9, 9));
        ShadowWood fagus3 = new Fagus(5.0f, new Coordinate(8, 8));
        ShadowWood fagus4 = new Fagus(3.3f, new Coordinate(7, 7));


        Forest forest = new Forest(latitude, longitude);
        forest.fill();
        forest.print();
        forest.grow();
        forest.thin();
        System.out.println();
        forest.print();
        System.out.println();

        forest.getShadowTypeHashMap().put(new Coordinate(10, 10), new BelowFagus());
        forest.getSaplingList().add(b1);
        forest.getSaplingList().add(c1);
        forest.getSaplingList().add(fagus1);
        forest.getSaplingList().add(quercus1);
        System.out.println("Forest at coordinate X: 10 Y: 10 with shadow type BelowFagus before growing");
        System.out.println();
        forest.printTreesAtCoordinate(10 ,10);
        forest.grow();
        System.out.println();
        System.out.println("Forest at coordinate X: 10 Y: 10 with shadow type BelowFagus after thinning");
        System.out.println();
        forest.printTreesAtCoordinate(10 ,10);
        System.out.println();
        System.out.println("Forest at coordinate X: 10 Y: 10 with shadow type BelowFagus after thinning -> only one Fagus left");
        System.out.println();
        forest.thin();
        forest.printTreesAtCoordinate(10, 10);
        System.out.println();
        System.out.println();
        System.out.println();


        forest.getShadowTypeHashMap().put(new Coordinate(9, 9), new BelowNonFagus());
        forest.getSaplingList().add(b2);
        forest.getSaplingList().add(c2);
        forest.getSaplingList().add(fagus2);
        forest.getSaplingList().add(quercus2);
        System.out.println("Forest at coordinate X: 9 Y: 9 with shadow type BelowNonFagus before cutting");
        System.out.println();
        forest.printTreesAtCoordinate(9, 9);
        System.out.println();
        System.out.println("Forest at coordinate X: 9 Y: 9 after cutting -> New shadow type OpenArea");
        System.out.println();
        forest.cut(9, 9);
        System.out.println((forest.getShadowTypeHashMap().get(new Coordinate(9, 9)).toString()));
        System.out.println();
        System.out.println("Forest at coordinate X: 9 Y: 9 with shadow type OpenArea after thinning -> only Betulae and Querci left");
        forest.thin();
        System.out.println();
        forest.printTreesAtCoordinate(9, 9);
        System.out.println();


        forest.getShadowTypeHashMap().put(new Coordinate(8, 8), new BelowNonFagus());
        forest.getSaplingList().add(b3);
        forest.getSaplingList().add(c3);
        forest.getSaplingList().add(fagus3);
        forest.getSaplingList().add(quercus3);
        System.out.println("Forest at coordinate X: 8 Y: 8 with shadow type BelowNonFagus before establishing");
        System.out.println();
        forest.printTreesAtCoordinate(8, 8);
        System.out.println();
        System.out.println("Forest at coordinate X: 8 Y: 8 with shadow type BelowNonFagus after establishing "
                            + "-> stays BelowNonFagus becaus CarpinusBetelus is prioritized: CarpinusBetelus is removed");
        forest.establish(8, 8);
        System.out.println();
        forest.printTreesAtCoordinate(8, 8);
        System.out.println();


        forest.getShadowTypeHashMap().put(new Coordinate(7, 7), new BelowNonFagus());
        forest.getSaplingList().add(b4);
        forest.getSaplingList().add(fagus4);
        forest.getSaplingList().add(quercus4);
        System.out.println("Forest at coordinate X: 7 Y: 7 with shadow type BelowNonFagus before establishing");
        System.out.println();
        forest.printTreesAtCoordinate(7, 7);
        System.out.println();
        System.out.println("Forest at coordinate X: 7 Y: 7 with shadow type BelowNonFagus after establishing "
                + "-> new shadow type BelowFagus");
        forest.establish(7, 7);
        System.out.println();
        forest.printTreesAtCoordinate(7, 7);

    }
}
