/**
 * Created by js on 15.05.17.
 */

/**
 * Einfache Graph-Operation/Suche...
 *
 * @param <K> Typ des Knotens
 */
public abstract class GraphOperation<K> {
    public void cacheGraph(K[] knoten, int[][] matrix) {
        this.knoten = knoten;
        this.adjazenzMatrix = matrix;
    }

    public boolean connectionExists(int a, int b) {
        return(adjazenzMatrix[a][b] > 0 || adjazenzMatrix[b][a] > 0);
    }

    public int lenFromTo(int a, int b) {
        return(adjazenzMatrix[a][b]);
    }

    public void setMatrix(int[][] matrix) {
        this.adjazenzMatrix = matrix;
    }

    public int[][] gibMatrix() {
        return(adjazenzMatrix);
    }

    public void setKnoten(K[] knoten) {
        this.knoten = knoten;
    }

    public K[] gibKnoten() {
        return(knoten);
    }

    protected K[] knoten;
    protected int[][] adjazenzMatrix;
}
