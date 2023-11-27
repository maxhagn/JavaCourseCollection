public class Test {
    //


    /*
     * ANMERKUNG:
     * Gruppennummer: K1
     * Aufgabenteilung:
     * - Grundkonzept, Grundstruktur (Christoph Diepolder)
     * - Grundkonzept, Zusicherungen (Pascal Poremba)
     * - Grundkonzept, Überarbeitung, Test (Max Hagn)
     *
     * Keine Untertypbeziehungen für
     *  - LightDemanding & CarpinusBetulus
     *    Begründung: Da Gemeine Hainbuchen gemäß Angabe Beschattungen gut vertragen und deshalb nicht vom Typ einer
     *    Lichtbaumart sein sollen.
     *  - LightDemanding & FagusSylvatica
     *    Begründung: Da Rotbuchen gemäß Angabe starke Beschattungen gut vertragen und deshalb nicht vom Typ einer
     *    Lichtbaumart sein sollen.
     *  - Fagacea & CarpinusBetulus
     *    Begründung: Da Gemeine Hainbuchen gemäß Angabe zur Familie der Birkengewächse gehören und nicht zur Familie
     *    der Buchengewächse (Fagacea).
     *  - Quercus & FagusSylvatica
     *    Begründung: Sowohl Quercus als auch FagusSylvatica gehören zwar zu den Buchengewächsen, dennoch geht aus der
     *    Angabe nicht hervor, dass die Rotbuche auch zur Gattung der Eichen gehört.
     *  - Quercus & CarpinusBetulus
     *    Begründung: Da Gemeine Hainbuchen zu einer anderen Familie als Eichen gehören kann hier auch keine
     *    Untertypenbeziehung bestehen.
     *  - ContinentalClimate & FagusSylvatica
     *    Begründung: Da Rotbuchen gemäß Angabe vor allem in den ozeanisch beeinflussten Teilen Mitteleuropas stark
     *    vertreten sind, können sie nicht vom Typ einer Art sein die unter Kontinentalem Einfluss heimisch ist.
     */

    public static void main(String[] args) {
        Tree tree;
        tree = new CarpinusBetulus();
        assert ((CarpinusBetulus) tree).incidence() == 1.9f;
        tree = new FagusSylvatica();
        assert ((FagusSylvatica) tree).latitude() >= 46.43f;
        tree = new QuercusPetraea();
        assert ((QuercusPetraea) tree).incidence() == 0.8f;
        tree = new QuercusRobur();
        assert ((QuercusRobur) tree).incidence() == 1.8f;
    }
}
