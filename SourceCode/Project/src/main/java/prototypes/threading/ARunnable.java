package prototypes.threading;

public abstract class ARunnable implements Runnable {

    /**
     * The Is running.
     */
    protected boolean isRunning;

    public void setPaused(boolean isPaused) {
        isRunning = !isPaused;
    }

}
