package models.levels.level;

import models.environments.game.GameEnvironment;
import models.actors.gameactors.props.platforms.TestLevelPropStatic;
import models.prototypes.level.ALevel;


public class TestLevel3 extends ALevel {

    public TestLevel3(GameEnvironment gameModel) {
        super(gameModel);

        setStartOrigin(200, 50);

        build();

        // Floor
        addProp(new TestLevelPropStatic(0, 980, 10000, 100, 0, 0, false));

    }

    @Override
    public void build() {
        for(int i = 0; i < 1000; i++) {
            addProp(new TestLevelPropStatic(i*20, 500+(-5*i*.8f), 20, 20, 0, 0, false));
        }
    }

    @Override
    public void reset() {

    }

}
