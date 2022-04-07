package models.prototypes.threading;

import models.utils.config.ConfigData;

public abstract class ARunnable implements Runnable {

    protected boolean isRunning;

    protected int lastUpdates = ConfigData.GAME_UPDATE_RATE;
    protected int updates = 0;

    public void setPaused(boolean isPaused) {
        isRunning = !isPaused;
    }
}
