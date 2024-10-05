package models.levels.level;

import models.actors.platforms.Platform;
import models.actors.triggers.collectibles.key.DoorKey;
import models.actors.triggers.interactibles.Door;
import models.actors.triggers.interactibles.Spikes;
import models.actors.triggers.interactibles.Spring;
import models.camera.Camera;
import models.environments.game.GameEnvironment;
import models.prototypes.level.ALevel;
import models.prototypes.level.prop.reactor.AReactProp;
import models.prototypes.level.prop.trigger.prop.APropTrigger;

import java.awt.*;

/**
 * This is TestLevel1 class for proof of concept
 */
public class TestLevel1 extends ALevel {


    /**
     * Creates the first test Level.
     * @param gameModel
     */
    public TestLevel1(GameEnvironment gameModel) {
        super(gameModel);

        setStartOrigin(300, 900);

        build();

        addBackgroundLayer(getResources().getImage("forest_1"));
        addBackgroundLayer(getResources().getImage("forest_2"));
        addBackgroundLayer(getResources().getImage("forest_3"));
    }

    @Override
    public void build() {

        // Walls
//        addProp(new Platform(getResources(),"platform_level1", -500, 0, 500, 1080, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level1",9000, 0, 100, 1080, 0, 0, false));
//
//        // Floor
//        addProp(new Platform(getResources(),"platform_level1",-1, 980, 10001, 100, 0, 0, false));
//
//        // Climbing Walls
//        addProp(new Platform(getResources(),"platform_level1",320, -150, 50, 200, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level1",500, 0, 50, 200, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level1",320, 220, 50, 200, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level1",500, 360, 50, 200, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level1",320, 500, 50, 200, 0, 0, false));
//
//        // Other Platforms
//        addProp(new Platform(getResources(),"platform_level1",1800, 100, 500, 100, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level1",70, 800, 500, 100, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level1",500, 700, 500, 100, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level1",1100, 600, 500, 100, 0, 0, false));
//
//        addProp(new Platform(getResources(),"platform_level1",1800, 600, 500, 50, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level1",2000, 650, 220, 100, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level1",3500, 750, 500, 100, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level1",4000, 780, 200, 100, 0, 0, false));
//
//        addProp(new Platform(getResources(),"platform_level1",6800, 400, 500, 50, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level1",2500, 720, 220, 100, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level1",3000, 700, 500, 50, 0, 0, false));
//        addProp(new Platform(getResources(),"platform_level1",3200, 650, 100, 100, 0, 0, false));

        // REACTION PROP
        AReactProp reactProp = new AReactProp(getResources(), environment, 75, 500, 100, 100,
                0, 0, 1,false, false) {
            @Override
            public void onReact() {
                color = new Color(255, 0, 50, 50);
            }
        };
        addProp(reactProp);

        // ZOOM TRIGGER
        addProp(new APropTrigger(getResources(), environment, 0, -300, 800, 1080,
                0, 0, -1,false, false) {
            @Override
            public void doAction() {
                Camera.zoomTarget = .75f;
            }
        });

        // Door
        door = new Door(getResources(), environment, 2000, 0, 50, 100,
                        0, 0, 1, false, false);
        addProp(door);

        // Door Open Animation Trigger
        addProp(new APropTrigger(getResources(), environment, 1800, -150, 450, 300,
                0, 0, 1,false, false) {
            @Override
            public void doAction() {
                //reactProp.onReact();
                door.onReceive();
                Camera.zoomTarget = 2f;
            }

        });

        // Spikes
        addProp(new Spikes(getResources(), environment, 1000, 930, 100, 50, 0, 0, -1));
        addProp(new Spikes(getResources(), environment, 1200, 960, 100, 20, 0, 0, -1));
        addProp(new Spikes(getResources(), environment, 1400, 905, 100, 75, 0, 0, -1));

        // Keys
        addProp(new DoorKey(getResources(), environment, 1600, 750, 100, 50, 0, 0));
        addProp(new DoorKey(getResources(), environment, 2500, 450, 100, 50, 0, 0));
        addProp(new DoorKey(getResources(), environment, 500, 300, 100, 50, 0, 0));

        super.build();

    }


}
