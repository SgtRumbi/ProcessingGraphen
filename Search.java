/**
 * Created by js on 15.05.17.
 */
public abstract class Search extends GraphOperation<Knoten> {
    public abstract void start(int entryPoint);

    // Getter/Setter:

    protected void searchStarted() {
        isStarted = true;
    }

    protected void searchFinished() {
        isStarted = false;
    }

    public boolean isStarted() {
        return(isStarted);
    }
    private boolean isStarted = false;
}
