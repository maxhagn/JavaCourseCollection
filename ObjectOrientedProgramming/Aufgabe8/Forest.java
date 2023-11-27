public class Forest{
    private final Tree[][] forest; // forest != null

    // Ein Objekt von Forest wird erstellt und mit einem zweidimensionalen Array forest instanziert;
    // forest != null;
    // Ein Objekt von Forest wurde erstellt und besitzt forest
    public Forest(char[][] forest) {
        this.forest = new Tree[forest.length][forest[0].length];
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest[i].length; j++) {
                this.forest[i][j] = new Tree(forest[i][j], j, i, this.forest);
            }
        }
    }

    // getSeg() gibt ein Segment an einer spezifizierten Stelle zurück;
    // x >= 0 && y >= 0;
    // Ein Segment an der Stelle x und y wurde zurückgegeben
    public Tree getSeg(int x, int y) {
        return this.forest[y][x];
    }

    // output() gibt den ersten Baum auf der Console aus
    public void output() {
        System.out.println(forest[0][0].output());
        System.out.println();
    }

}
