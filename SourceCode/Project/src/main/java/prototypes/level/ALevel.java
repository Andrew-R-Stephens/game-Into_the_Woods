package prototypes.level;

import models.environments.game.GameModel;
import prototypes.level.prop.ALevelProp;

import java.util.ArrayList;

/**
 * The type A level.
 */
public abstract class ALevel {

    /**
     * The Game model.
     */
    protected GameModel gameModel;

    /**
     * Instantiates a new A level.
     *
     * @param gameModel the game model
     */
    public ALevel(GameModel gameModel) {
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
}
