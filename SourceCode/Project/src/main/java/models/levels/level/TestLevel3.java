package models.levels.level;

import models.prototypes.environments.AEnvironment;
import models.prototypes.level.ALevel;
import models.prototypes.level.LevelModelRW.LevelModel;

/**
 * The third proof-of-concept test level
 */
public class TestLevel3 extends ALevel {

    /**
     * Creates the third test Level.
     * @param environment
     */
    public TestLevel3(AEnvironment environment, LevelModel levelModel) {
        super(environment);

        setStartOrigin(200, 50);

        build();
    }

    @Override
    public void build() {

        // Floor
//        addProp(new Platform(getResources(),"platform_level1", 0, 980, 10000, 100, 0, 0, false));
//
//        for(int i = 0; i < 10; i++) {
//            addProp(new Platform(getResources(),"platform_level1",i*20, 500+(-5*i*.8f), 20, 20, 0, 0, false));
//        }
//
//        // Keys
//        addProp(new DoorKey(getResources(), environment, 1600, 930, 100, 50, 0, 0));
//        addProp(new DoorKey(getResources(), environment, 2500, 930, 100, 50, 0, 0));
//        addProp(new DoorKey(getResources(), environment, 500, 930, 100, 50, 0, 0));
//
//        // Door
//        door = new Door(getResources(), environment, 2000, 830, 50, 100,
//                0, 0, 1, false, false);
//        addProp(door);
//
//        // Door Listener
/*
        addProp(new APropTrigger(getResources(), environment, 1800, 700, 450, 300,
                0, 0, 1,false, false) {
            @Override
            public void doAction() {
                //reactProp.onReact();
                door.onReceive();
            }

        });
*/

        super.build();
    }

    @Override
    public void reset() {
        super.reset();
    }
}
