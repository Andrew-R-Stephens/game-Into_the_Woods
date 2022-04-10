package models.levels.level;

import models.actors.gameactors.ParticleActor;
import models.actors.gameactors.props.platforms.PlatformProp;
import models.actors.gameactors.props.triggers.collectibles.key.LevelKey;
import models.actors.gameactors.props.triggers.interactibles.DoorTrigger;
import models.actors.gameactors.props.triggers.interactibles.SpikesTrigger;
import models.actors.gameactors.props.triggers.interactibles.SpringTrigger;
import models.environments.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.actor.pawn.character.ACharacter;
import models.prototypes.level.ALevel;
import models.prototypes.level.prop.reactor.AReactProp;
import models.prototypes.level.prop.trigger.prop.APropTrigger;
import models.utils.resources.Resources;

import java.awt.*;
import java.util.Random;


public class TestLevel1 extends ALevel {

    public TestLevel1(GameEnvironment gameModel) {
        super(gameModel);

        setStartOrigin(300, 900);

        build();

        setBackgroundImage(Resources.getImage("menubackground"));
    }

    @Override
    public void build() {

        // Walls
        addProp(new PlatformProp(-500, 0, 500, 1080, 0, 0, false));
        addProp(new PlatformProp(9000, 0, 100, 1080, 0, 0, false));

        // Floor
        addProp(new PlatformProp(0, 980, 10000, 100, 0, 0, false));

        // Climbing Walls
        addProp(new PlatformProp(320, -150, 50, 200, 0, 0, false));
        addProp(new PlatformProp(500, 0, 50, 200, 0, 0, false));
        addProp(new PlatformProp(320, 220, 50, 200, 0, 0, false));
        addProp(new PlatformProp(500, 360, 50, 200, 0, 0, false));
        addProp(new PlatformProp(320, 500, 50, 200, 0, 0, false));

        // Other Platforms
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

        // REACTION PROP
        AReactProp reactProp = new AReactProp(gameEnvironment, 75, 500, 100, 100,
                0, 0, 1,false, false) {
            @Override
            public void onReact() {
                color = new Color(255, 0, 50, 50);
            }
        };
        addProp(reactProp);

        // TRIGGERS
        addProp(new APropTrigger(gameEnvironment, 700, 880, 100, 100,
                0, 0, 1,false, false) {
            @Override
            public void doAction() {
                gameEnvironment.queueActor(new ParticleActor(this.x, this.y, 10, 10,
                        new Random().nextFloat(-10, 10), new Random().nextFloat(-10, 10),
                        true));
            }

            @Override
            public boolean hasCollision(AActor a, float delta) {
                if(!(a instanceof ACharacter)) {
                    return false;
                }

                return super.hasCollision(a, delta);
            }
        });

        // Door
        door = new DoorTrigger(gameEnvironment, 2000, 0, 50, 100,
                        0, 0, 1, false, false);
        addProp(door);


        addProp(new APropTrigger(gameEnvironment, 1800, -150, 450, 300,
                0, 0, 1,false, false) {
            @Override
            public void doAction() {
                //reactProp.onReact();
                door.onReact();
            }

        });

        // Springs
        addProp(new SpringTrigger(gameEnvironment, 1000, 280, 100, 20, 0, 0, -1,false, false));
        addProp(new SpringTrigger(gameEnvironment, 1200, 280, 100, 20, 0, 0, -1,false, false));
        addProp(new SpringTrigger(gameEnvironment, 1400, 280, 100, 20, 0, 0, -1,false, false));
        addProp(new SpringTrigger(gameEnvironment, 300, 580, 100, 20, 0, 0, -1,false, false));
        addProp(new SpringTrigger(gameEnvironment, 1000, 180, 100, 20, 0, 0, -1,false, false));
        addProp(new SpringTrigger(gameEnvironment, 100, 680, 100, 20, 0, 0, -1,false, false));

        // Spikes
        addProp(new SpikesTrigger(gameEnvironment, 1000, 930, 100, 50, 0, 0, -1));
        addProp(new SpikesTrigger(gameEnvironment, 1200, 960, 100, 20, 0, 0, -1));
        addProp(new SpikesTrigger(gameEnvironment, 1400, 905, 100, 75, 0, 0, -1));

        // Keys
        addProp(new LevelKey(gameEnvironment, 1600, 750, 100, 50, 0, 0));
        addProp(new LevelKey(gameEnvironment, 2500, 450, 100, 50, 0, 0));
        addProp(new LevelKey(gameEnvironment, 500, 300, 100, 50, 0, 0));

        super.build();

    }


}
