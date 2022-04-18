package models.levels.level;

import models.actors.gameactors.props.platforms.Platform;
import models.actors.gameactors.props.triggers.collectibles.key.DoorKey;
import models.actors.gameactors.props.triggers.interactibles.Door;
import models.actors.gameactors.props.triggers.interactibles.Spikes;
import models.actors.gameactors.props.triggers.interactibles.Spring;
import models.camera.Camera;
import models.environments.game.GameEnvironment;
import models.prototypes.level.ALevel;
import models.prototypes.level.prop.reactor.AReactProp;
import models.prototypes.level.prop.trigger.prop.APropTrigger;
import models.utils.resources.Resources;

import java.awt.*;

/**
 * This is TestLevel1 class
 */
public class TestLevel1 extends ALevel {

    public TestLevel1(GameEnvironment gameModel) {
        super(gameModel);

        setStartOrigin(300, 900);

        build();

        setBackgroundImage(getResources().getImage("menubackground"));
    }

    @Override
    public void build() {

        // Walls
        addProp(new Platform(getResources(), -500, 0, 500, 1080, 0, 0, false));
        addProp(new Platform(getResources(),9000, 0, 100, 1080, 0, 0, false));

        // Floor
        addProp(new Platform(getResources(),0, 980, 10000, 100, 0, 0, false));

        // Climbing Walls
        addProp(new Platform(getResources(),320, -150, 50, 200, 0, 0, false));
        addProp(new Platform(getResources(),500, 0, 50, 200, 0, 0, false));
        addProp(new Platform(getResources(),320, 220, 50, 200, 0, 0, false));
        addProp(new Platform(getResources(),500, 360, 50, 200, 0, 0, false));
        addProp(new Platform(getResources(),320, 500, 50, 200, 0, 0, false));

        // Other Platforms
        addProp(new Platform(getResources(),1800, 100, 500, 100, 0, 0, false));
        addProp(new Platform(getResources(),70, 800, 500, 100, 0, 0, false));
        addProp(new Platform(getResources(),500, 700, 500, 100, 0, 0, false));
        addProp(new Platform(getResources(),1100, 600, 500, 100, 0, 0, false));

        addProp(new Platform(getResources(),1800, 600, 500, 50, 0, 0, false));
        addProp(new Platform(getResources(),2000, 650, 220, 100, 0, 0, false));
        addProp(new Platform(getResources(),3500, 750, 500, 100, 0, 0, false));
        addProp(new Platform(getResources(),4000, 780, 200, 100, 0, 0, false));

        addProp(new Platform(getResources(),6800, 400, 500, 50, 0, 0, false));
        addProp(new Platform(getResources(),2500, 720, 220, 100, 0, 0, false));
        addProp(new Platform(getResources(),3000, 700, 500, 50, 0, 0, false));
        addProp(new Platform(getResources(),3200, 650, 100, 100, 0, 0, false));

        // REACTION PROP
        AReactProp reactProp = new AReactProp(getResources(), gameEnvironment, 75, 500, 100, 100,
                0, 0, 1,false, false) {
            @Override
            public void onReact() {
                color = new Color(255, 0, 50, 50);
            }
        };
        addProp(reactProp);

        // ZOOM TRIGGER
        addProp(new APropTrigger(getResources(), gameEnvironment, 0, -300, 800, 1080,
                0, 0, -1,false, false) {
            @Override
            public void doAction() {
                Camera.zoomTarget = .75f;
            }
        });

        // Door
        door = new Door(getResources(), gameEnvironment, 2000, 0, 50, 100,
                        0, 0, 1, false, false);
        addProp(door);

        // Door Open Animation Trigger
        addProp(new APropTrigger(getResources(), gameEnvironment, 1800, -150, 450, 300,
                0, 0, 1,false, false) {
            @Override
            public void doAction() {
                //reactProp.onReact();
                door.onReceive();
                Camera.zoomTarget = 2f;
            }

        });

        // Springs
        addProp(new Spring(getResources(), gameEnvironment, 1000, 280, 100, 20, 0, 0, -1,false, false));
        addProp(new Spring(getResources(), gameEnvironment, 1200, 280, 100, 20, 0, 0, -1,false, false));
        addProp(new Spring(getResources(), gameEnvironment, 1400, 280, 100, 20, 0, 0, -1,false, false));
        addProp(new Spring(getResources(), gameEnvironment, 300, 580, 100, 20, 0, 0, -1,false, false));
        addProp(new Spring(getResources(), gameEnvironment, 1000, 180, 100, 20, 0, 0, -1,false, false));
        addProp(new Spring(getResources(), gameEnvironment, 100, 680, 100, 20, 0, 0, -1,false, false));

        // Spikes
        addProp(new Spikes(getResources(), gameEnvironment, 1000, 930, 100, 50, 0, 0, -1));
        addProp(new Spikes(getResources(), gameEnvironment, 1200, 960, 100, 20, 0, 0, -1));
        addProp(new Spikes(getResources(), gameEnvironment, 1400, 905, 100, 75, 0, 0, -1));

        // Keys
        addProp(new DoorKey(getResources(), gameEnvironment, 1600, 750, 100, 50, 0, 0));
        addProp(new DoorKey(getResources(), gameEnvironment, 2500, 450, 100, 50, 0, 0));
        addProp(new DoorKey(getResources(), gameEnvironment, 500, 300, 100, 50, 0, 0));

        super.build();

    }


}
