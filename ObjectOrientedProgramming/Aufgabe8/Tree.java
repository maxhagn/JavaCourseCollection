import java.util.LinkedList;
import java.util.List;

public class Tree implements Comparable<Tree> {
    private final Tree[][] forest; // forest != null
    private char val; //' ','*','0','+'
    private final int xpos; // >= 0
    private final int ypos; // >= 0
    private Boolean isDead; // isDead != null
    private Thread barkBeetleThread; //barkBeetleThread
    private Thread antBeetleThread; //antBeetleThread
    private BarkBeetle barkBeetle; //barkBeetle
    private AntBeetle antBeetle; //antBeetle

    // Ein Objekt von Tree wird erstellt und mit Werten instanziert;
    // (val == ' ' || val == '*' || val == '0' || val == '+') && x >= 0 && y >= 0 && forest != null;
    // Ein Objekt von Tree wurde erstellt und besitzt val, xpos, ypos, forest
    public Tree(char val, int x, int y, Tree[][] forest) {
        this.xpos = x;
        this.ypos = y;
        this.val = val;
        this.forest = forest;
        this.isDead = false;
    }

    // Gibt bis zu acht Trees (die Nachbarn) in Form einer List<Tree> zurück
    // Ein Objekt vom Typ List<Tree> mit einer .size() von 0 bis 8 wird zurückgegeben
    public List<Tree> getNeighbours() {
        LinkedList<Tree> neighbours = new LinkedList<>();
        //Vert/Horiz neighbours
        if (xpos != 0 && getSeg(xpos - 1, ypos).val != '+')
            neighbours.add(getSeg(xpos - 1, ypos));
        if (xpos != forest[0].length - 1 && getSeg(xpos + 1, ypos).val != '+')
            neighbours.add(getSeg(xpos + 1, ypos));
        if (ypos != 0 && getSeg(xpos, ypos - 1).val != '+')
            neighbours.add(getSeg(xpos, ypos - 1));
        if (ypos != forest.length - 1 && getSeg(xpos, ypos + 1).val != '+')
            neighbours.add(getSeg(xpos, ypos + 1));
        // Diag neighbours
        if (xpos != 0 && ypos != 0 && getSeg(xpos - 1, ypos - 1).val != '+')
            neighbours.add(getSeg(xpos - 1, ypos - 1));
        if (xpos != forest[0].length - 1 && ypos != 0 && getSeg(xpos + 1, ypos - 1).val != '+')
            neighbours.add(getSeg(xpos + 1, ypos - 1));
        if (xpos != forest[0].length - 1 && ypos != 0 && getSeg(xpos + 1, ypos - 1).val != '+')
            neighbours.add(getSeg(xpos + 1, ypos - 1));
        if (xpos != 0 && ypos != forest.length - 1 && getSeg(xpos - 1, ypos + 1).val != '+')
            neighbours.add(getSeg(xpos - 1, ypos + 1));
        return neighbours;
    }

    // Gibt val des Tree zurück;
    public char getVal(){
        return this.val;
    }

    // Ändert val des Tree;
    // val == ' ' || val == '*' || val == '0' || val == '+';
    // val wird auf neuen Wert gesetzt;
    public void setVal(char c){
        this.val = c;
    }

    // Gibt xpos des Tree zurück;
    public int getXpos(){
        return this.xpos;
    }

    // Gibt ypos des Tree zurück;
    public int getYpos(){
        return this.ypos;
    }

    // Ändert barkBeetleThread des Tree;
    // barkBeetle != null
    // barkBeetleThread wird auf neuen Wert gesetzt;
    public void setBarkBeetleThread(Thread barkBeetle) {
        this.barkBeetleThread = barkBeetle;
    }

    // Ändert antBeetleThread des Tree;
    // antBeetle != null
    // antBeetleThread wird auf neuen Wert gesetzt;
    public void setAntBeetleThread(Thread antBeetle) {
        this.antBeetleThread = antBeetle;
        this.setVal('+');
        if(this.barkBeetleThread != null && barkBeetleThread.isAlive()){
            this.barkBeetleThread.interrupt();
            this.barkBeetleThread = null;
        }

        if(this.barkBeetleThread != null){
            this.barkBeetleThread = null;
        }
    }

    // Ändert antBeetle des Tree;
    // antBeetle != null
    // antBeetle wird auf neuen Wert gesetzt;
    public void setAntBeetle(AntBeetle antBeetle) {
        this.antBeetle = antBeetle;
    }

    // Ändert barkBeetle des Tree;
    // barkBeetle != null
    // barkBeetle wird auf neuen Wert gesetzt;
    public void setBarkBeetle(BarkBeetle barkBeetle) {
        this.barkBeetle = barkBeetle;
    }

    // Gibt barkBeetle des Tree zurück;
    public BarkBeetle getBarkBeetle() {
        return barkBeetle;
    }

    // Rekursiv: Gibt eine String representation von allen Tree Objekten zurück;
    // Eine Zeichenkette aller Tree-Objekte;
    public synchronized String output() {
        if (xpos != this.forest[0].length - 1) {
            return this + getSeg(xpos + 1, ypos).output();
        } else {
            if (ypos != forest.length - 1) {
                return this + "\n" + getSeg(0, ypos + 1).output();
            } else {
                return "" + this;
            }
        }
    }

    // Ändert isDead und val von Tree;
    public void dies() {
        this.isDead = true;
        this.setVal(' ');
    }

    // Gibt Zeichenkettenwert von val zurück;
    @Override
    public String toString() {
        return String.valueOf(this.val);
    }

    // Gibt einen neuen Tree mit xpos == x && ypos == y zurück;
    // x >= 0 && y >= 0;
    // Tree wird zurückgegeben
    private Tree getSeg(int x, int y) {
        return forest[y][x];
    }

    // Unterbricht barkBeetleThread und löscht die Referenz;
    public void moveAntBeetle() {
        if (barkBeetleThread != null) {
            barkBeetleThread.interrupt();
            setBarkBeetleThread(null);
        }
    }


    // Vergelicht den Tree mit einem anderen auf Grund von xpos und ypos;
    // tree != null;
    // Gibt -1, 0 oder 1 zurück;
    @Override
    public int compareTo(Tree tree) {
        if (this.ypos < tree.ypos) {
            return -1;
        } else if (this.ypos == tree.ypos) {
            return this.xpos < tree.xpos ? -1 : 1;
        } else {
            return 1;
        }
    }
}