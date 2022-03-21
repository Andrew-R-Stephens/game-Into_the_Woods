package props.objects.levels;

import models.data.PreferenceData;
import models.environments.game.GameModel;
import prototypes.actor.AActor;
import prototypes.level.ALevel;
import prototypes.level.prop.ALevelProp;

import java.awt.*;
import java.util.ArrayList;

/**
 * TODO: Add description
 */
public class LevelList {

    /**
     * The constant GRAVITY.
     */
    public static final float GRAVITY = 9.8f / (float)PreferenceData.GAME_UPDATE_RATE;

    private final ArrayList<ALevel> levels = new ArrayList<>();
    private int currentLevel = 0;

    /**
     * Init.
     *
     * @param gameModel    the game model
     * @param currentLevel the current level
     */
    public void init(GameModel gameModel, int currentLevel) {
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

    /**
     * Gets level props.
     *
     * @return the level props
     */
    public ArrayList<ALevelProp> getLevelProps() {
        return levels.get(currentLevel).getLevelProps();
    }

    /**
     * Draw.
     *
     * @param g the g
     */
    public void draw(Graphics g) {

        for (AActor levelProps : getLevelProps()) {
            if (levelProps instanceof ALevelProp p) {
                p.draw(g);
            }
        }

    }

}
