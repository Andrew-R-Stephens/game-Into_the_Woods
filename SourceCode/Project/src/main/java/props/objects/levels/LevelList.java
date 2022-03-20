package props.objects.levels;

import models.data.PreferenceData;
import models.environments.game.GameModel;
import props.prototypes.actor.AActor;
import props.prototypes.level.ALevel;
import props.prototypes.level.prop.ALevelProp;

import java.awt.*;
import java.util.ArrayList;

/**
 * TODO: Add description
 */
public class LevelList {

    public static final float GRAVITY = 9.8f / (float)PreferenceData.GAME_UPDATE_RATE;

    private final ArrayList<ALevel> levels = new ArrayList<>();
    private int currentLevel = 0;

    public void init(GameModel gameModel) {
        addLevel(new TestLevel1(gameModel));
        addLevel(new TestLevel2(gameModel));
        addLevel(new TestLevel3(gameModel));

        System.out.println(setCurrentLevel(0));
    }

    public void addLevel(ALevel level){
        levels.add(level);
    }

    public boolean setCurrentLevel(int chosenLevel) {
        if(chosenLevel >= levels.size()) {
            return false;
        }

        this.currentLevel = chosenLevel;

        return true;
    }

    public ArrayList<ALevelProp> getLevelProps() {
        return levels.get(currentLevel).getLevelProps();
    }

    public void draw(Graphics g) {

        for (AActor levelProps : getLevelProps()) {
            if (levelProps instanceof ALevelProp p) {
                p.draw(g);
            }
        }

    }

}
