import java.util.LinkedList;
import java.util.Queue;

public class BFS extends Search implements AnimOperation {
    @Override
    public void cacheGraph(Knoten[] knoten, int[][] matrix) {
        super.knoten = knoten;
        super.adjazenzMatrix = matrix;
        besucht = new boolean[knoten.length];
    }

    @Override
    public void start(int entryPoint) {
        stack.add(entryPoint);
        super.searchStarted();
    }

    @Override
    public void step() {
        if(super.isStarted()) {
            int knotenIndex = stack.poll();

            besucht[knotenIndex] = true;
            System.out.println(knoten[knotenIndex].name);
            knoten[knotenIndex].colour = "ffef00ef";

            for(int i = 0; i < besucht.length; ++i) {
                if(super.adjazenzMatrix[i][knotenIndex] > 0 && !besucht[i]) {
                    stack.add(i);
                }
            }
        }

        if(stack.isEmpty()) {
            super.searchFinished();
        }
    }

    private Queue<Integer> stack = new LinkedList<>();
    private boolean[] besucht;
}
