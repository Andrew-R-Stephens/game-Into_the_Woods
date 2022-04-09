package models.levels.level;

import models.actors.gameactors.props.platforms.PlatformProp;
import models.actors.gameactors.props.triggers.interactibles.DoorTrigger;
import models.environments.game.GameEnvironment;
import models.prototypes.level.ALevel;


public class TestLevel3 extends ALevel {

    public TestLevel3(GameEnvironment gameModel) {
        super(gameModel);

        setStartOrigin(200, 50);

        build();

        // Floor
        addProp(new PlatformProp(0, 980, 10000, 100, 0, 0, false));

    }

    @Override
    public void build() {
        for(int i = 0; i < 1000; i++) {
            addProp(new PlatformProp(i*20, 500+(-5*i*.8f), 20, 20, 0, 0, false));
        }

        addProp(new DoorTrigger(gameEnvironment, 75, 850, 100, 100,
                0, 0, 1, false, false));
    }

    @Override
    public void reset() {

    }

}
