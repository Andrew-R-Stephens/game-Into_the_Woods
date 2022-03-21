package prototypes.threading;

/**
 * The type A render runnable.
 */
public abstract class ARenderRunnable implements Runnable {

    /**
     * The constant lastFrames.
     */
    public static int lastFrames = Integer.MAX_VALUE;
    /**
     * The Frames.
     */
    protected int frames = 0;
    /**
     * The Is running.
     */
    protected boolean isRunning;

}
