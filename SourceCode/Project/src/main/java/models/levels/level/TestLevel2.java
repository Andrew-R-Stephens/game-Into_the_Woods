package models.levels.level;

import models.actors.platforms.Platform;
import models.actors.triggers.collectibles.key.DoorKey;
import models.actors.triggers.interactibles.Door;
import models.environments.game.GameEnvironment;
import models.prototypes.level.ALevel;
import models.prototypes.level.prop.trigger.prop.APropTrigger;

/**
 * The second proof-of-concept test level
 */
public class TestLevel2 extends ALevel {

    /**
     * Creates the second test Level.
     * @param gameModel
     */
    public TestLevel2(GameEnvironment gameModel) {
        super(gameModel);

        setStartOrigin(200, 50);

        build();

        addBackgroundLayer(getResources().getImage("caves_1"));
        addBackgroundLayer(getResources().getImage("caves_2"));
        addBackgroundLayer(getResources().getImage("caves_3"));
    }

    @Override
    public void build() {
        // Wall
//        addProp(new Platform(getResources(),"platform_level2",0, 0, 100, 1080, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level2",0, 0, 100, 1080, 0, 0, false));
//        // Climbing Walls
//        addProp(new Platform(getResources(),"platform_level2",100, -150, 50, 200, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level2",200, 0, 50, 200, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level2",300, 220, 50, 200, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level2",400, 360, 50, 200, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level2",500, 500, 50, 200, 0, 0, false));
//
//        addProp(new Platform(getResources(),"platform_level2",9000, 0, 100, 1080, 0, 0, false));
//        // Floor
//        addProp(new Platform(getResources(),"platform_level2",0, 980, 10000, 100, 0, 0, false));
//
//        // Other Props
//        addProp(new Platform(getResources(),"platform_level2",1800, 100, 500, 100, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level2",70, 800, 500, 100, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level2",500, 700, 500, 100, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level2",1100, 600, 500, 100, 0, 0, false));
//
//        addProp(new Platform(getResources(),"platform_level2",1800, 600, 500, 50, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level2",2000, 650, 220, 100, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level2",3500, 750, 500, 100, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level2",4000, 780, 200, 100, 0, 0, false));
//
//        addProp(new Platform(getResources(),"platform_level2",6800, 400, 500, 50, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level2",2500, 720, 220, 100, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level2",3000, 700, 500, 50, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level2",3200, 650, 100, 100, 0, 0, false));
//
//        // Keys
//        addProp(new DoorKey(getResources(), environment, 1600, 930, 100, 50, 0, 0));
//        addProp(new DoorKey(getResources(), environment, 2500, 930, 100, 50, 0, 0));
//        addProp(new DoorKey(getResources(), environment, 500, 930, 100, 50, 0, 0));

        // Door
        door = new Door(getResources(), environment, 2000, 830, 50, 100,
                0, 0, 1, false, false);
        addProp(door);

        // Door Listener
        addProp(new APropTrigger(getResources(), environment, 1800, 700, 450, 300,
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
