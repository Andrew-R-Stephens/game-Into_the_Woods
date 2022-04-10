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

    private GameEnvironment gameEnvironment = null;

    private final ArrayList<ALevel> levels = new ArrayList<>();
    private int currentLevel = 0;

    public void init(GameEnvironment gameEnvironment, int currentLevel) {
        this.gameEnvironment = gameEnvironment;

        addLevel(new TestLevel1(gameEnvironment));
        addLevel(new TestLevel2(gameEnvironment));
        addLevel(new TestLevel3(gameEnvironment));

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

    public boolean navigateNextLevel() {
        gameEnvironment.reset();

        int tempCurrLevel = currentLevel;
        tempCurrLevel++;

        if(tempCurrLevel >= levels.size()) {
            return false;
        }

        currentLevel = tempCurrLevel;
        return true;
    }

    @Override
    public void draw(Graphics g) {
        getCurrentLevel().draw(g);
    }

    public void reset() {
        getCurrentLevel().reset();
    }
}
