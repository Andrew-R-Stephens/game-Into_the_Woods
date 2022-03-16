package models.states.game;

import models.data.PreferenceData;
import props.objects.levels.TestLevel;
import props.objects.levels.TestLevel2;
import props.objects.levels.TestLevel3;
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

    public LevelList() {
        addLevel(new TestLevel());
        addLevel(new TestLevel2());
        addLevel(new TestLevel3());

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
