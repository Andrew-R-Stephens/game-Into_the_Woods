package props.prototypes.level;

import models.environments.game.GameModel;
import props.prototypes.level.prop.ALevelProp;

import java.util.ArrayList;

public abstract class ALevel {

    protected GameModel gameModel;

    public ALevel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public abstract void addProp(ALevelProp prop);

    public abstract ArrayList<ALevelProp> getLevelProps();
}
