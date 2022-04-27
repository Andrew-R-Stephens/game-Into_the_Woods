package models.prototypes.threading;

import models.utils.config.Config;

/**
 * <p>The superclass of Render and Update Runnables. This class contains the variables that both subclasses share.</p>
 * @author Andrew Stephens
 */
public abstract class ARunnable implements Runnable {

    protected boolean isRunning;

    protected int lastUpdates = Config.GAME_UPDATE_RATE;
    protected int updates = 0;

    /**
     * <p>Sets the loop to be paused or not.</p>
     * @param isPaused If the loop should be paused.
     */
    public void setPaused(boolean isPaused) {
        isRunning = !isPaused;
    }
}
