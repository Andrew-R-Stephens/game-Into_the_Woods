package props.objects.levels;

import models.environments.game.GameEnvironment;
import props.objects.levels.level1.TestLevel1;
import props.objects.levels.level1.TestLevel2;
import props.objects.levels.level1.TestLevel3;
import prototypes.level.ALevel;
import utils.config.ConfigData;

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