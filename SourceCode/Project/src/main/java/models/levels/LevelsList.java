package models.levels;

import models.environments.game.GameEnvironment;
import models.levels.level.TestLevel1;
import models.levels.level.TestLevel2;
import models.levels.level.TestLevel3;
import models.prototypes.level.ALevel;
import models.utils.config.ConfigData;

import java.awt.*;
import java.util.ArrayList;

/**
 * TODO: Add description
 */
public class LevelsList {

    /**
     * The constant GRAVITY.
     */
    public static final float GRAVITY = 9.8f / (float) ConfigData.GAME_UPDATE_RATE;

    private final ArrayList<ALevel> levels = new ArrayList<>();
    private int currentLevel = 0;

    /**
     * Init.
     *
     * @param gameModel    the game model
     * @param currentLevel the current level
     */
    public void init(GameEnvironment gameModel, int currentLevel) {
        addLevel(new TestLevel1(gameModel));
        addLevel(new TestLevel2(gameModel));
        addLevel(new TestLevel3(gameModel));

        setCurrentLevel(currentLevel);
    }

    /**
     * Add level.
     *
     * @param level the level
     */
    public void addLevel(ALevel level){
        levels.add(level);
    }

    /**
     * Sets current level.
     *
     * @param chosenLevel the chosen level
     * @return the current level
     */
    public boolean setCurrentLevel(int chosenLevel) {
        if(chosenLevel >= levels.size()) {
            return false;
        }

        this.currentLevel = chosenLevel;

        return true;
    }

    public ALevel getCurrentLevel() {
        return levels.get(currentLevel);
    }


    /**
     * Draw.
     *
     * @param g the g
     */
    public void draw(Graphics g) {
        getCurrentLevel().draw(g);
    }

    public void reset() {
        levels.get(currentLevel).reset();
    }
}
