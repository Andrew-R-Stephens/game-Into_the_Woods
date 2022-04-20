package models.utils.config;

/**
 * <p>The data obtained from the the users previously written save file. Save files are simple, as they are only
 * manipulated to store and retrieve the user's last completed level. The Save Data will automatically update if a
 * level is completed.</p>
 * <p>Progress is reset if the user chooses to start a New Game.</p>
 * <p>Progress is written to file either at the next available time whenever the user's progress is updated or when
 * the user closes the program. Non-graceful termination may lead to corrupted save file or lost progress from the
 * previous execution.</p>
 */
public class SaveData {

    private int lastCompletedLevel = -1;

    /**
     * <p>Initializes the progress to default values.</p>
     */
    public SaveData() {

    }

    /**
     * Sets the user's level progress.
     * @param lastCompletedLevel The last level completed. Must be >= 0.
     */
    public void setLevelProgress(int lastCompletedLevel) {
        this.lastCompletedLevel = lastCompletedLevel;
    }

    /**
     * Gets the user's last completed level.
     * @return The last completed level. Must be >= -1.
     */
    public int getLevelProgress() {
        return lastCompletedLevel;
    }

}
