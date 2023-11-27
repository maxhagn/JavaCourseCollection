import java.util.Iterator;

public class Test {
    //


    /*
     * ANMERKUNG:
     * Gruppennummer: K1
     * Aufgabenteilung:
     * - Grundkonzept, Grundstruktur, Tests (Christoph Diepolder)
     * - Iteratoren, MultiGroup, SingleGroup (Pascal Poremba)
     * - Grundstruktur, Zusicherungen (Max Hagn)
     */

    public static void main(String[] args) {

        Relation rQ = Quercus.relation();
        Relation rQr = QuercusRobur.relation();
        Relation rF = Fagus.relation();

        Fagus f1 = new Fagus(  );
        Fagus f2 = new Fagus(  );
        Fagus f3 = new Fagus(  );
        Fagus f4 = new Fagus(  );
        Fagus f5 = new Fagus(  );

        Quercus q1 = new Quercus(  );
        Quercus q2 = new Quercus(  );
        Quercus q3 = new Quercus(  );
        Quercus q4 = new Quercus(  );
        Quercus q5 = new Quercus(  );

        QuercusRobur qr1 = new QuercusRobur( "Text 1" );
        QuercusRobur qr2 = new QuercusRobur( "Text 2" );
        QuercusRobur qr3 = new QuercusRobur( "Text 3" );
        QuercusRobur qr4 = new QuercusRobur( "Text 4" );
        QuercusRobur qr5 = new QuercusRobur( "Text 5" );

        SingleGroup<Fagus> sg1 = new SingleGroup( );
        sg1.add(f1);
        sg1.add(f2);
        sg1.add(f3);
        sg1.add(f4);
        sg1.add(f5);

        SingleGroup<QuercusRobur> sg2 = new SingleGroup( );
        sg2.add(qr1);
        sg2.add(qr2);
        sg2.add(qr3);
        sg2.add(qr4);
        sg2.add(qr5);

        SingleGroup<Quercus> sg3 = new SingleGroup( );
        sg3.add(q1);
        sg3.add(q2);
        sg3.add(q3);
        sg3.add(q4);
        sg3.add(q5);

        SingleGroup<Integer> sg4 = new SingleGroup( );
        sg4.add(1);
        sg4.add(2);
        sg4.add(3);
        sg4.add(4);
        sg4.add(5);

        Numeric n = new Numeric(2);





        //Test 1

        //Über Parameter a verbunden
        MultiGroup<Fagus, Fagus> mg1 = new MultiGroup(sg1, rF);
        mg1.add(f1);
        mg1.add(f2);
        mg1.add(f3);
        mg1.add(f4);
        mg1.add(f5);
        
        MultiGroup<QuercusRobur, Fagus> mg2 = new MultiGroup(mg1, rQr);
        mg2.add(qr1);
        mg2.add(qr2);
        mg2.add(qr3);
        mg2.add(qr4);
        mg2.add(qr5);
        
        MultiGroup<QuercusRobur, QuercusRobur> mg4 = new MultiGroup(mg2, rQr);
        mg4.add(qr1);
        mg4.add(qr2);
        mg4.add(qr3);
        mg4.add(qr4);
        mg4.add(qr5);

        MultiGroup<Quercus, QuercusRobur> mg6 = new MultiGroup(mg4, rQ);
        mg6.add(q1);
        mg6.add(q2);
        mg6.add(q3);
        mg6.add(q4);
        mg6.add(q5);

        MultiGroup<Quercus, Quercus> mg7 = new MultiGroup(mg6, rQ);
        mg7.add(q1);
        mg7.add(q2);
        mg7.add(q3);
        mg7.add(q4);
        mg7.add(q5);

        MultiGroup<Quercus, Tree> mg9 = new MultiGroup(mg7, rQ);
        mg9.add(q1);
        mg9.add(q2);
        mg9.add(q3);
        mg9.add(q4);
        mg9.add(q5);

        //Nicht über Parameter a verbunden
        MultiGroup<Quercus, Fagus> mg3 = new MultiGroup(sg3, rQ);
        mg3.add(q1);
        mg3.add(q2);
        mg3.add(q3);
        mg3.add(q4);
        mg3.add(q5);

        MultiGroup<QuercusRobur, Quercus> mg5 = new MultiGroup(sg2, rQ);
        mg5.add(qr1);
        mg5.add(qr2);
        mg5.add(qr3);
        mg5.add(qr4);
        mg5.add(qr5);

        MultiGroup<QuercusRobur, Tree> mg8 = new MultiGroup(sg2, rQ);
        mg8.add(qr1);
        mg8.add(qr2);
        mg8.add(qr3);
        mg8.add(qr4);
        mg8.add(qr5);

        MultiGroup<Integer, Integer> mg10 = new MultiGroup(sg4, n);
        mg10.add(1);
        mg10.add(2);
        mg10.add(3);
        mg10.add(4);
        mg10.add(5);

        //Test 2

        Iterator<Quercus> i = mg9.iterator();
        Quercus q = null;
        while(i.hasNext()){
            q = i.next();
            if(q != null){
                System.out.println(q.height());
                i.remove();
            }
        }


        //Test 3

        MultiGroup<Quercus, Fagus> u = new MultiGroup(sg3, rQ);
        SingleGroup<Tree> v = new SingleGroup();
        Group<QuercusRobur, Fagus> w = new MultiGroup(sg1, rQ);
        w.add(qr1);
        w.add(qr2);
        w.add(qr3);
        w.add(qr4);
        w.add(qr5);

        Iterator i2 = w.iterator();
        QuercusRobur qr = null;
        while(i2.hasNext()){
            qr = (QuercusRobur)i2.next();
            if(qr != null){
                System.out.println(qr.resistance());
                u.add(qr);
                v.add(qr);
            }
        }

        // Test 4
        System.out.println(rQ.invoked());
        System.out.println(rQr.invoked());
        System.out.println(rF.invoked());

        System.out.println("Done....");
    }
}
