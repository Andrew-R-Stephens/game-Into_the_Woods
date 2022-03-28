package prototypes.level;

import models.environments.game.GameEnvironment;
import props.objects.levels.inventory.LevelCollectibles;
import prototypes.level.prop.ALevelProp;

import java.util.ArrayList;

/**
 * The type A level.
 */
public abstract class ALevel {

    /**
     * The Game model.
     */
    protected GameEnvironment gameModel;
    protected int[] startOrigin = new int[2];

    protected final ArrayList<ALevelProp> levelProps = new ArrayList<>();

    protected LevelCollectibles collectibles;

    /**
     * Instantiates a new A level.
     *
     * @param gameModel the game model
     */
    public ALevel(GameEnvironment gameModel) {
        this.gameModel = gameModel;
    }

    /**
     * Add prop.
     *
     * @param prop the prop
     */
    public abstract void addProp(ALevelProp prop);

    /**
     * Gets level props.
     *
     * @return the level props
     */
    public abstract ArrayList<ALevelProp> getLevelProps();

    public int[] getCharacterOrigin() {
        return startOrigin;
    }

    public void setStartOrigin(int x, int y) {
        startOrigin = new int[]{x, y};
    }

    public void reset() {
    }

}
