package viewmodels.game;

import data.PreferenceData;
import props.levelactors.TestLevelPropStatic;
import proptypes.actors.levelactors.animated.ALevelProp;
import proptypes.level.ALevel;
import proptypes.types.actor.AActor;

import java.awt.*;
import java.util.ArrayList;

/**
 * TODO: Add description
 */
public class LevelModel {

    public static final float GRAVITY = 9.8f / (float)PreferenceData.GAME_UPDATE_RATE;

    private ALevel level;

    public LevelModel() {

    }

    public void setLevel(ALevel level){
        this.level = level;
    }

    public ArrayList<ALevelProp> getLevelProps() {
        return level.getLevelProps();
    }

    public void render(Graphics g) {
        for (AActor levelProps : getLevelProps()) {
            if (levelProps instanceof ALevelProp p) {
                p.draw(g);
            }
        }
    }

}
