package models.prototypes.threading;

import models.utils.config.Config;

public abstract class ARunnable implements Runnable {

    protected boolean isRunning;

    protected int lastUpdates = Config.GAME_UPDATE_RATE;
    protected int updates = 0;

    public void setPaused(boolean isPaused) {
        isRunning = !isPaused;
    }
}
