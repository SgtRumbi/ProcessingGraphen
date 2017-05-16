/**
 * Created by js on 15.05.17.
 */
public final class BruteForceWaySearch extends GraphOperation<Knoten> {
    public BruteForceWaySearch(Knoten[] knoten, int[][] adjazenzMatrix) {
        super.cacheGraph(knoten, adjazenzMatrix);
    }

    public void start(int startIndex, int zielIndex) {
        besucht = new boolean[this.knoten.length];
        currentWay = "";
        currentLength = Integer.MAX_VALUE;

        find(startIndex, zielIndex, knoten[startIndex].name, 0);
        System.out.printf("\nBeste(r) Weg(e): %s\n", currentWay);
    }

    private void find(int aktKnotenIndex, int zielKnotenIndex, String pfad, int len) {
        besucht[aktKnotenIndex] = true;

        if(aktKnotenIndex == zielKnotenIndex) {
            if(len == currentLength) {
                currentWay += "\n" + pfad + ": " + currentLength;
                System.out.println("Gleich guten Weg gefunden...");
            } else if(len < currentLength) {
                System.out.println("Besseren Weg als " + currentWay + " gefunden: " + pfad);
                currentLength = len;
                currentWay = pfad;
            }
        } else {
            for(int i = 0; i < knoten.length; ++i) {
                if(connectionExists(aktKnotenIndex, i) && !besucht[i]) {
                    String newPath = pfad + "/" + knoten[i].name;
                    int newLen = len + lenFromTo(aktKnotenIndex, i);
                    find(i, zielKnotenIndex, newPath, newLen);
                }
            }
        }

        besucht[aktKnotenIndex] = false;
    }

    private String currentWay;
    private int currentLength;
    private boolean[] besucht;
}
