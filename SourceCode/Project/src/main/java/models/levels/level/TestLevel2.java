package models.levels.level;

import models.actors.gameactors.props.platforms.PlatformProp;
import models.actors.gameactors.props.triggers.interactibles.DoorTrigger;
import models.environments.game.GameEnvironment;
import models.prototypes.level.ALevel;


public class TestLevel2 extends ALevel {

    public TestLevel2(GameEnvironment gameModel) {
        super(gameModel);

        setStartOrigin(200, 50);

        build();

    }

    @Override
    public void build() {
        // Wall
        addProp(new PlatformProp(0, 0, 100, 1080, 0, 0, false));
        addProp(new PlatformProp(0, 0, 100, 1080, 0, 0, false));
        // Climbing Walls
        addProp(new PlatformProp(100, -150, 50, 200, 0, 0, false));
        addProp(new PlatformProp(200, 0, 50, 200, 0, 0, false));
        addProp(new PlatformProp(300, 220, 50, 200, 0, 0, false));
        addProp(new PlatformProp(400, 360, 50, 200, 0, 0, false));
        addProp(new PlatformProp(500, 500, 50, 200, 0, 0, false));

        addProp(new PlatformProp(9000, 0, 100, 1080, 0, 0, false));
        // Floor
        addProp(new PlatformProp(0, 980, 10000, 100, 0, 0, false));

        // Other ProPlatformProp
        addProp(new PlatformProp(1800, 100, 500, 100, 0, 0, false));
        addProp(new PlatformProp(70, 800, 500, 100, 0, 0, false));
        addProp(new PlatformProp(500, 700, 500, 100, 0, 0, false));
        addProp(new PlatformProp(1100, 600, 500, 100, 0, 0, false));

        addProp(new PlatformProp(1800, 600, 500, 50, 0, 0, false));
        addProp(new PlatformProp(2000, 650, 220, 100, 0, 0, false));
        addProp(new PlatformProp(3500, 750, 500, 100, 0, 0, false));
        addProp(new PlatformProp(4000, 780, 200, 100, 0, 0, false));

        addProp(new PlatformProp(6800, 400, 500, 50, 0, 0, false));
        addProp(new PlatformProp(2500, 720, 220, 100, 0, 0, false));
        addProp(new PlatformProp(3000, 700, 500, 50, 0, 0, false));
        addProp(new PlatformProp(3200, 650, 100, 100, 0, 0, false));

        addProp(new DoorTrigger(gameEnvironment, 900, 850, 100, 100,
                0, 0, 1, false, false));
    }

    @Override
    public void reset() {

    }

}
