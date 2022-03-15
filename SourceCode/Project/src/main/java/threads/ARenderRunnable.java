package threads;

public abstract class ARenderRunnable implements Runnable {

    public static int lastFrames = Integer.MAX_VALUE;
    protected int frames = 0;
    protected boolean isRunning;

}
