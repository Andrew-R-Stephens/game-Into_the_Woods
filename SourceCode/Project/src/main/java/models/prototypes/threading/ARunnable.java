package models.prototypes.threading;

import models.utils.config.Config;

/**
 * <p></p>
 */
public abstract class ARunnable implements Runnable {

    protected boolean isRunning;

    protected int lastUpdates = Config.GAME_UPDATE_RATE;
    protected int updates = 0;

    /**
     * <p></p>
     * @param isPaused -
     */
    public void setPaused(boolean isPaused) {
        isRunning = !isPaused;
    }
}
