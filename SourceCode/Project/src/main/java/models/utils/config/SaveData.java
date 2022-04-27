package models.utils.config;

import models.utils.files.SaveFileRW;

/**
 * <p>The data obtained from the the users previously written save file. Save files are simple, as they are only
 * manipulated to store and retrieve the user's last completed level. The Save Data will automatically update if a
 * level is completed.</p>
 * <p>Progress is reset if the user chooses to start a New Game.</p>
 * <p>Progress is written to file either at the next available time whenever the user's progress is updated or when
 * the user closes the program. Non-graceful termination may lead to corrupted save file or lost progress from the
 * previous execution.</p>
 * @author Andrew Stephens
 */
public class SaveData {

    private SaveFileRW saveFileRW;

    private int lastCompletedLevel = -1;

    /**
     * <p>Initializes the progress to default values.</p>
     */
    public void init(SaveFileRW saveFileRW) {
        this.saveFileRW = saveFileRW;
        this.saveFileRW.init(this, "savedata.json");

        saveFileRW.deserialize();
    }

    /**
     * Sets the user's level progress.
     *
     * @param lastCompletedLevel The last level completed. Must be >= 0.
     */
    public SaveData setLevelProgress(int lastCompletedLevel) {
        System.out.println("Setting " + lastCompletedLevel);
        this.lastCompletedLevel = lastCompletedLevel;
        return this;
    }

    /**
     * Gets the user's last completed level.
     *
     * @return The last completed level. Must be >= -1.
     */
    public int getLevelProgress() {
        return lastCompletedLevel;
    }

    public void save() {
        saveFileRW.savetoFile();
    }
}
