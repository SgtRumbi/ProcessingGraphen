public class Graph
{
    int[][] adjazenzMatrix;
    Knoten[] knoten;
    
    Graph(int[][] matrix, Knoten[] knoten) {
        this.adjazenzMatrix = matrix;
        this.knoten = knoten;
    }
    
    public int[][] gibMatrix() {
        return adjazenzMatrix;
    }
}
