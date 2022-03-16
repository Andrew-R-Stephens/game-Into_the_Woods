package props.prototypes.threading;

public abstract class AUpdateRunnable implements Runnable {

    public static int lastUpdates = Integer.MAX_VALUE;
    protected int updates = 0;
    protected boolean isRunning;

}
