package prototypes.threading;

public abstract class ARunnable implements Runnable {

    protected boolean isRunning;

    protected int lastUpdates = Integer.MAX_VALUE;
    protected int updates = 0;

    public void setPaused(boolean isPaused) {
        isRunning = !isPaused;
    }
}
