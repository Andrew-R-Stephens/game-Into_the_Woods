package models.levels.level;

import models.actors.gameactors.props.platforms.PlatformProp;
import models.actors.gameactors.props.triggers.collectibles.key.LevelKey;
import models.actors.gameactors.props.triggers.interactibles.DoorTrigger;
import models.environments.game.GameEnvironment;
import models.prototypes.level.ALevel;
import models.prototypes.level.prop.trigger.prop.APropTrigger;


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

        // Keys
        addProp(new LevelKey(gameEnvironment, 1600, 930, 100, 50, 0, 0));
        addProp(new LevelKey(gameEnvironment, 2500, 930, 100, 50, 0, 0));
        addProp(new LevelKey(gameEnvironment, 500, 930, 100, 50, 0, 0));

        // Door
        door = new DoorTrigger(gameEnvironment, 2000, 830, 50, 100,
                0, 0, 1, false, false);
        addProp(door);

        // Door Listener
        addProp(new APropTrigger(gameEnvironment, 1800, 700, 450, 300,
                0, 0, 1,false, false) {
            @Override
            public void doAction() {
                //reactProp.onReact();
                door.onReact();
            }

        });

        super.build();

    }

}
