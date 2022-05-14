package models.levels;

import models.environments.game.GameEnvironment;
import models.levels.level.Level1;
import models.levels.level.Level2;
import models.levels.level.TestLevel3;
import models.prototypes.level.ALevel;
import models.utils.drawables.IDrawable;

import java.awt.*;
import java.util.ArrayList;

/**
 * <p>The LevelsList contains the list of all Levels that exist for the game. Loads all levels at the same time.</p>
 * <p>Only the current level is updated or drawn.</p>
 * @author Andrew Stephens
 */
public class LevelsList implements IDrawable {

    public static final float WORLD_SCALE = .75f;

    /**<p>The parent Game Environment</p>*/
    private GameEnvironment gameEnvironment = null;

    /**<p>The list of all levels available</p>*/
    private final ArrayList<ALevel> levels = new ArrayList<>();
    /**<p>The index of the active level</p>*/
    private int currentLevel = 0;

    /**
     * <p>Initializes the Levels list with the parent GameEnvironment and the starting level.</p>
     * @param gameEnvironment The parent GameEnvironment
     * @param currentLevel The starting level
     */
    public void init(GameEnvironment gameEnvironment, int currentLevel) {
        this.gameEnvironment = gameEnvironment;

        addLevel(new Level1(gameEnvironment));
        addLevel(new Level2(gameEnvironment));
        addLevel(new TestLevel3(gameEnvironment));

        setCurrentLevel(currentLevel);
    }

    /**
     * Adds a new level to the level list
     * @param level The level to add
     */
    public void addLevel(ALevel level){
        levels.add(level);
    }

    /**
     * Sets the index of the current level. Retains the level number if the level does not exist.
     * @param chosenLevel The level chosen to be set to
     * @return If the level could be navigated to.
     */
    public boolean setCurrentLevel(int chosenLevel) {
        if(chosenLevel >= levels.size()) {
            return false;
        }

        this.currentLevel = chosenLevel;

        return true;
    }

    /**
     * Gets the current level.
     * @return The Level that is currently active.
     */
    public ALevel getCurrentLevel() {
        return levels.get(currentLevel);
    }

    /**
     * <p>Navigates to the next available level. This is called when the user passes the previous level.</p>
     * <p>Progress is saved to file via this method.</p>
     * @return If there is a following level present.
     */
    public boolean navigateNextLevel() {
        new Thread(() -> gameEnvironment.getParentEnvironmentsHandler()
                .getSaveData().setLevelProgress(currentLevel).save()).start();

        gameEnvironment.reset();

        int tempCurrLevel = currentLevel;
        tempCurrLevel++;

        if(tempCurrLevel >= levels.size()) {
            return false;
        }

        currentLevel = tempCurrLevel;
        return true;
    }

    /**
     * Resets the current level data.
     */
    public void reset() {
        getCurrentLevel().reset();
    }

    @Override
    public void draw(Graphics2D g2d) {
        getCurrentLevel().draw(g2d);
    }

}
