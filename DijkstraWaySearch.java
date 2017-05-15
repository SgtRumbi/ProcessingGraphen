/**
 * Created by js on 15.05.17.
 */
public class DijkstraWaySearch extends GraphOperation<Knoten> {
    public DijkstraWaySearch(Knoten[] knoten, int[][] adjazenzMatrix) {
        super.cacheGraph(knoten, adjazenzMatrix);
    }

    public void start(int startIndex, int zielIndex) {
        // Init:
        besucht = new boolean[this.knoten.length];

        privs = new Knoten[this.knoten.length];
        dists = new int[this.knoten.length];

        for(int i = 0; i < super.knoten.length; ++i) {
            dists[i] = Integer.MAX_VALUE;
        }
        dists[startIndex] = 0;

        // Alg:
        for(int i = 0; i < super.knoten.length; ++i) {
            besucht[i] = true;

            for(int j = 0; j < super.knoten.length; ++j) {
                if(!besucht[j] && connectionExists(i, j)) {
                    // dists[j] = dists[i] + adjazenzMatrix[i][j];
                    int dist = dists[i] + adjazenzMatrix[i][j];
                    if(dist < dists[j]) {
                        dists[j] = dist;
                        privs[j] = this.knoten[i];
                        System.out.printf("Found smaller weight (%d) for node %s!\n", this.dists[j], super.knoten[j].name);
                    }
                }
            }
        }

        System.out.println("log.");
    }

    private String currentWay;
    private int currentLength;
    private boolean[] besucht;
    private Knoten[] privs;
    private int[] dists;
}
