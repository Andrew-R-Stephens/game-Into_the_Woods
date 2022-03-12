package viewmodels.states.game;

import props.objects.levels.TestLevel;
import props.objects.levels.TestLevel2;
import props.prototypes.actors.levelactors.animated.ALevelProp;
import props.prototypes.level.ALevel;
import props.prototypes.types.actor.AActor;
import viewmodels.data.PreferenceData;

import java.awt.*;
import java.util.ArrayList;

/**
 * TODO: Add description
 */
public class LevelModel {

    public static final float GRAVITY = 9.8f / (float)PreferenceData.GAME_UPDATE_RATE;

    private final ArrayList<ALevel> levels = new ArrayList<>();
    private int currentLevel = 0;

    public LevelModel() {
        addLevel(new TestLevel());
        addLevel(new TestLevel2());

        System.out.println(setCurrentLevel(1));
        System.out.println(setCurrentLevel(0));
        System.out.println(setCurrentLevel(2));
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

    public void render(Graphics g) {

        for (AActor levelProps : getLevelProps()) {
            if (levelProps instanceof ALevelProp p) {
                p.draw(g);
            }
        }

    }

}
