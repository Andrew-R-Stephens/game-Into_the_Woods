package models.utils.config;

import models.prototypes.actor.pawn.character.ACharacter;
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

    /**<p>SaveFileRW parsing process.</p>*/
    private SaveFileRW saveFileRW;

    /**
     * The character type used for this run.
     */
    private ACharacter.CharacterType characterType = ACharacter.CharacterType.TEO;
    /**<p>The last level that the user has completed.</p>*/
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

    public ACharacter.CharacterType getCharacterType() {
        return characterType;
    }

    /**
     * <p>Defaults the saveData. The saves the saveData to file immediately.</p>
     */
    public void createNewGame() {
        this.lastCompletedLevel = -1;
        this.characterType = ACharacter.CharacterType.TEO;
    }

    /**
     * <p>Requests to record the SaveData to file.</p>
     */
    public void save() {
        System.out.println("The save file was saved " + (saveFileRW.savetoFile() ? "successfully" : "unsuccessfully"));
    }

    public void setAll(
            int levelProgress, int characterType,
            int windowWidth, int windowHeight, int windowType, short framerate) {
        this.lastCompletedLevel = levelProgress;
        this.characterType = ACharacter.CharacterType.values()[characterType];
        Config.window_width_selected = windowWidth;
        Config.window_height_selected = windowHeight;
        Config.setWindowType(windowType);
        Config.frameRate = framerate;
    }
}
