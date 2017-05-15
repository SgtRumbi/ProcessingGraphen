import java.util.*;

public class DFS extends Search implements AnimOperation {
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
            int knotenIndex = stack.pop();
            
            besucht[knotenIndex] = true;
            System.out.println(knoten[knotenIndex].name);
            knoten[knotenIndex].colour = "ff00efef";
            
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

    private Stack<Integer> stack = new Stack<Integer>();
    private boolean[] besucht;
}
