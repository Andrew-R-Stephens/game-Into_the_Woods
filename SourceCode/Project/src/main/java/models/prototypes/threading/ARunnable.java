package models.prototypes.threading;

import models.utils.config.Config;

/**
 * <p>The superclass of Render and Update Runnables. This class contains the variables that both subclasses share.</p>
 * @author Andrew Stephens
 */
public abstract class ARunnable implements Runnable {

    /**<p>Is the runnable is looping.</p>*/
    protected boolean isRunning;

    /**<p>The last number of ticks in the last 1 second cycle.</p>*/
    protected int lastUpdates = Config.GAME_UPDATE_RATE;
    /**<p>The current count of ticks contained in this current 1 second cycle.</p>*/
    protected int updates = 0;

    /**
     * <p>Sets the loop to be paused or not.</p>
     * @param isPaused If the loop should be paused.
     */
    public void setPaused(boolean isPaused) {
        isRunning = !isPaused;
    }
}
