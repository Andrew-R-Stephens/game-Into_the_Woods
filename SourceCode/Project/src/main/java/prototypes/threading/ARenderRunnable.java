package prototypes.threading;

/**
 * The type A render runnable.
 */
public abstract class ARenderRunnable extends ARunnable {

    /**
     * The constant lastFrames.
     */
    protected static int lastFrames = Integer.MAX_VALUE;
    /**
     * The Frames.
     */
    protected int frames = 0;

    public static int getLastFrames() {
        return lastFrames;
    }

}
