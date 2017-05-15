/**
 * Created by js on 15.05.17.
 */
public class DijkstraKnoten extends Knoten {
    public DijkstraKnoten(String name, int x, int y) {
        super(name, x, y);
    }

    public DijkstraKnoten(String name, float x, float y) {
        super(name, x, y);
    }

    public Knoten priv = null;
    public int dist = Integer.MAX_VALUE;
}
