package models.levels;

import models.environments.game.GameEnvironment;
import models.levels.level.TestLevel1;
import models.levels.level.TestLevel2;
import models.levels.level.TestLevel3;
import models.prototypes.level.ALevel;
import models.utils.config.ConfigData;
import models.utils.drawables.IDrawable;

import java.awt.*;
import java.util.ArrayList;

public class LevelsList implements IDrawable {

    public static final float GRAVITY = 9.8f / (float) ConfigData.GAME_UPDATE_RATE;

    private final ArrayList<ALevel> levels = new ArrayList<>();
    private int currentLevel = 0;

    public void init(GameEnvironment gameModel, int currentLevel) {
        addLevel(new TestLevel1(gameModel));
        addLevel(new TestLevel2(gameModel));
        addLevel(new TestLevel3(gameModel));

        setCurrentLevel(currentLevel);
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

    public ALevel getCurrentLevel() {
        return levels.get(currentLevel);
    }

    @Override
    public void draw(Graphics g) {
        getCurrentLevel().draw(g);
    }

    public void reset() {
        levels.get(currentLevel).reset();
    }
}
