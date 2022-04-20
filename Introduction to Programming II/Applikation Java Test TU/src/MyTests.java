// Test Cases for the whole environment
public class MyTests {
    public static void main(String[] args) {
        /*
        System.out.println("Tests for Stack 1");
        CharStack stack1 = new CharStack();

        System.out.println(stack1.isEmpty());
        stack1.push('A');
        stack1.push('B');
        stack1.push('C');
        stack1.push('D');

        System.out.println(stack1.isEmpty());

        System.out.println(stack1.pop());
        System.out.println(stack1.pop());
        System.out.println(stack1.pop());
        System.out.println(stack1.pop());

        System.out.println(stack1.isEmpty());


        System.out.println("\nTests for Stack 2");
        CharStack stack2 = new CharStack();

        System.out.println(stack2.isEmpty());
        stack2.push('A');
        stack2.push('B');
        stack2.push('C');
        stack2.push('D');

        System.out.println(stack2.isEmpty());

        System.out.println(stack2.pop());
        System.out.println(stack2.pop());

        stack2.push('X');
        stack2.push('Y');

        System.out.println(stack2.isEmpty());

        System.out.println(stack2.pop());
        System.out.println(stack2.pop());

        System.out.println(stack2.isEmpty());


        Startlist mystartlist = new Startlist(100);
        mystartlist.add(new Participation("Lienz 2011 Ladies' Slalom", "Annemarie", 2));
        mystartlist.add(new Participation("Lienz 2012 Ladies' Slalom", "Mustermann", 3));
        mystartlist.add(new Participation("Lienz 2013 Ladies' Slalom", "Peter", 1));


        mystartlist.print();
        mystartlist.printOrdered();
        mystartlist.printPermutations();

        Participations participations11 = new Participations(100);
        participations11.add(new Participation("Lienz 2011 Ladies' Slalom 1", "Annemarie", 2));
        participations11.add(new Participation("Lienz 2012 Ladies' Slalom 2", "Mustermann", 3));
        participations11.add(new Participation("Lienz 2013 Ladies' Slalom 3", "Peter", 1));
        participations11.add(new Participation("Lienz 2013 Ladies' Slalom 4", "Melanie", 4));

        participations11.print();
        participations11.lookupRacer("Peter").print();

        Participations participations2 = new Participations(participations11,"Lienz 2013 Ladies' Slalom 3", "Lienz 2013 Ladies' Slalom 4");
        participations2.print();

        Participation test1 = new Participation("Lienz 2011 Ladies' Slalom", "Mikaela Shiffrin", 40);
        test1.print();

        */

        Participations1 participations1 = new Participations1(10);
        participations1.add(new Participation("Lienz 2011 Ladies' Slalom 1", "R1", 2));
        participations1.add(new Participation("Lienz 2012 Ladies' Slalom 2", "R2", 3));
        participations1.add(new Participation("Lienz 2013 Ladies' Slalom 3", "R3", 1));
        participations1.add(new Participation("Lienz 2013 Ladies' Slalom 4", "R4", 4));
        participations1.add(new Participation("Lienz 2013 Ladies' Slalom 5", "R5", 5));
        participations1.add(new Participation("Lienz 2013 Ladies' Slalom 6", "R6", 6));
        participations1.add(new Participation("Lienz 2013 Ladies' Slalom 7", "R7", 7));

        System.out.println("__________________________ \n print the added participations \n");
        participations1.print();

        System.out.println("__________________________ \n print first participation \n");
        participations1.first().print();

        System.out.println("__________________________ \n print participations with bibnumber <= 1 \n");
        participations1.print(1);

        System.out.println("__________________________ \n print participations with bibnumber <= 5 \n");
        participations1.print(5);

        System.out.println("__________________________ \n add race xxx after race 3 \n");
        participations1.addAfter( "Lienz 2013 Ladies' Slalom 3", new Participation("Lienz 2013 Ladies' Slalom xxx", "R7", 99));
        participations1.print();

        System.out.println("__________________________ \n add race yyy after race 3 \n");
        participations1.addAfter( "Lienz 2013 Ladies' Slalom 3", new Participation("Lienz 2013 Ladies' Slalom yyy", "R7", 99));
        participations1.print();

        System.out.println("__________________________ \n remove race xxx \n");
        participations1.remove( "Lienz 2013 Ladies' Slalom xxx");
        participations1.print();

        System.out.println("__________________________ \n add race xxx before race 3 \n");
        participations1.addBefore( "Lienz 2013 Ladies' Slalom 3", new Participation("Lienz 2013 Ladies' Slalom xxx", "R7", 99));
        participations1.print();

        //Ad Hoc Test
        System.out.println("__________________________ \n (AD Hoc) print participations with bibnumber < 5 and Racer <= R5 \n");
        participations1.print("R5", 5);

        System.out.println("__________________________ \n (AD Hoc) print participations with bibnumber < 1 and Racer <= R3 \n");
        participations1.print("R3", 1);

        System.out.println("__________________________ \n (AD Hoc) print participations with bibnumber < 7 and Racer <= R3 \n");
        participations1.print("R3", 7);

        /*

        Participations3 test = new Participations3();
        test.add(new Participation("1", "R1", 2));
        test.add(new Participation("2", "R2", 3));
        test.add(new Participation("3", "R3", 1));
        test.add(new Participation("xx", "R4", 4));
        test.add(new Participation("4", "R4", 4));
        test.add(new Participation("xx", "R4", 4));
        test.add(new Participation("5", "R5", 5));
        test.add(new Participation("6", "R6", 6));
        test.add(new Participation("7", "R7", 7));

        System.out.println("Print all with print");
        test.print();

        System.out.println("Print all with toString");
        System.out.println(test.toString());

        System.out.println("Print all with toString with bib < 7 & racer < R5");
        System.out.println(test.toString("7", "R5"));

        System.out.println("Lookup R4");
        System.out.println(test.lookupRacer("R4"));
        */
    }
}
