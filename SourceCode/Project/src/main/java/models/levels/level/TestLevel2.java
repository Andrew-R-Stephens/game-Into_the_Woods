package models.levels.level;

import models.actors.gameactors.props.platforms.Platform;
import models.actors.gameactors.props.triggers.collectibles.key.DoorKey;
import models.actors.gameactors.props.triggers.interactibles.Door;
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
        addProp(new Platform(0, 0, 100, 1080, 0, 0, false));
        addProp(new Platform(0, 0, 100, 1080, 0, 0, false));
        // Climbing Walls
        addProp(new Platform(100, -150, 50, 200, 0, 0, false));
        addProp(new Platform(200, 0, 50, 200, 0, 0, false));
        addProp(new Platform(300, 220, 50, 200, 0, 0, false));
        addProp(new Platform(400, 360, 50, 200, 0, 0, false));
        addProp(new Platform(500, 500, 50, 200, 0, 0, false));

        addProp(new Platform(9000, 0, 100, 1080, 0, 0, false));
        // Floor
        addProp(new Platform(0, 980, 10000, 100, 0, 0, false));

        // Other ProPlatformProp
        addProp(new Platform(1800, 100, 500, 100, 0, 0, false));
        addProp(new Platform(70, 800, 500, 100, 0, 0, false));
        addProp(new Platform(500, 700, 500, 100, 0, 0, false));
        addProp(new Platform(1100, 600, 500, 100, 0, 0, false));

        addProp(new Platform(1800, 600, 500, 50, 0, 0, false));
        addProp(new Platform(2000, 650, 220, 100, 0, 0, false));
        addProp(new Platform(3500, 750, 500, 100, 0, 0, false));
        addProp(new Platform(4000, 780, 200, 100, 0, 0, false));

        addProp(new Platform(6800, 400, 500, 50, 0, 0, false));
        addProp(new Platform(2500, 720, 220, 100, 0, 0, false));
        addProp(new Platform(3000, 700, 500, 50, 0, 0, false));
        addProp(new Platform(3200, 650, 100, 100, 0, 0, false));

        // Keys
        addProp(new DoorKey(gameEnvironment, 1600, 930, 100, 50, 0, 0));
        addProp(new DoorKey(gameEnvironment, 2500, 930, 100, 50, 0, 0));
        addProp(new DoorKey(gameEnvironment, 500, 930, 100, 50, 0, 0));

        // Door
        door = new Door(gameEnvironment, 2000, 830, 50, 100,
                0, 0, 1, false, false);
        addProp(door);

        // Door Listener
        addProp(new APropTrigger(gameEnvironment, 1800, 700, 450, 300,
                0, 0, 1,false, false) {
            @Override
            public void doAction() {
                //reactProp.onReact();
                door.onReceive();
            }

        });

        super.build();

    }

}
