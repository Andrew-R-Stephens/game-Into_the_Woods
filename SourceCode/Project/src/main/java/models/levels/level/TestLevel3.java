package models.levels.level;

import models.actors.platforms.Platform;
import models.actors.triggers.collectibles.key.DoorKey;
import models.actors.triggers.interactibles.Door;
import models.environments.game.GameEnvironment;
import models.prototypes.level.ALevel;
import models.prototypes.level.LevelData;
import models.prototypes.level.prop.trigger.prop.APropTrigger;
import models.prototypes.level.LevelData.LevelModel;

/**
 * The third proof-of-concept test level
 */
public class TestLevel3 extends ALevel {

    /**
     * Creates the third test Level.
     * @param gameModel
     */
    public TestLevel3(GameEnvironment gameModel, LevelModel levelModel) {
        super(gameModel);

        setStartOrigin(200, 50);

        build();
    }

    @Override
    public void build() {

        // Floor
        addProp(new Platform(getResources(),"platform_level1", 0, 980, 10000, 100, 0, 0, false));

        for(int i = 0; i < 10; i++) {
            addProp(new Platform(getResources(),"platform_level1",i*20, 500+(-5*i*.8f), 20, 20, 0, 0, false));
        }

        // Keys
        addProp(new DoorKey(getResources(), gameEnvironment, 1600, 930, 100, 50, 0, 0));
        addProp(new DoorKey(getResources(), gameEnvironment, 2500, 930, 100, 50, 0, 0));
        addProp(new DoorKey(getResources(), gameEnvironment, 500, 930, 100, 50, 0, 0));

        // Door
        door = new Door(getResources(), gameEnvironment, 2000, 830, 50, 100,
                0, 0, 1, false, false);
        addProp(door);

        // Door Listener
        addProp(new APropTrigger(getResources(), gameEnvironment, 1800, 700, 450, 300,
                0, 0, 1,false, false) {
            @Override
            public void doAction() {
                //reactProp.onReact();
                door.onReceive();
            }

        });

        super.build();
    }

    @Override
    public void reset() {
        super.reset();
    }
}
