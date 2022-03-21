package prototypes.threading;

/**
 * The type A update runnable.
 */
public abstract class AUpdateRunnable implements Runnable {

    /**
     * The constant lastUpdates.
     */
    public static int lastUpdates = Integer.MAX_VALUE;
    /**
     * The Updates.
     */
    protected int updates = 0;
    /**
     * The Is running.
     */
    protected boolean isRunning;

}
