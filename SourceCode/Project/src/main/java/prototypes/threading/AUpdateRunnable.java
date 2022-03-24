package prototypes.threading;

/**
 * The type A update runnable.
 */
public abstract class AUpdateRunnable extends ARunnable {

    /**
     * The constant lastUpdates.
     */
    public static int lastUpdates = Integer.MAX_VALUE;
    /**
     * The Updates.
     */
    protected int updates = 0;

}
