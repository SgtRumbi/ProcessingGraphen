/**
 * Created by js on 15.05.17.
 */
public final class DijkstraWaySearch extends GraphOperation<Knoten> {
    public DijkstraWaySearch(Knoten[] knoten, int[][] adjazenzMatrix) {
        super.cacheGraph(knoten, adjazenzMatrix);
    }

    public void start(int startIndex, int zielIndex) {
        this.startIndex = startIndex;
        this.zielIndex = zielIndex;

        // Init:
        besucht = new boolean[this.knoten.length];
        for(int i = 0; i < besucht.length; ++i) {
            besucht[i] = false;
        }

        privs = new int[this.knoten.length];
        for(int i = 0; i < privs.length; ++i) {
            privs[i] = -1;
        }

        dists = new int[this.knoten.length];
        for(int i = 0; i < super.knoten.length; ++i) {
            dists[i] = Integer.MAX_VALUE;
        }
        dists[startIndex] = 0;

        int current = -1;
        // Alg:
        for(int i = 0; i < super.knoten.length; ++i) {
            int minDist = Integer.MAX_VALUE;
            for(int j = 0; j < super.knoten.length; ++j) {
                if(!besucht[j] && dists[j] < minDist) {
                    minDist = dists[j];
                    current = j;
                }
            }

            besucht[current] = true;

            for (int j = 0; j < super.knoten.length; ++j) {
                if (adjazenzMatrix[current][j] > 0 && dists[j] > dists[current] + adjazenzMatrix[current][j]) {
                    privs[j] = current;
                    dists[j] = dists[current] + adjazenzMatrix[current][j];
                    current = j;
                }
            }
        }

        System.out.println("Deikstra-Ergebnis: " + this.toString());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int current = zielIndex;
        while(current != startIndex) {
            builder.append(current).append("/");
            current = privs[current];
        }
        builder.append(startIndex);
        return builder.toString();
    }

    private int startIndex, zielIndex;

    private String currentWay;
    private int currentLength;
    private boolean[] besucht;
    private int[] privs;
    private int[] dists;
}
