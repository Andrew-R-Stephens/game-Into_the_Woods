package props.objects.levels.level1;

import models.environments.game.GameEnvironment;
import props.objects.levelprops.boundaries.PlatformProp;
import props.objects.levelprops.gametriggers.collectibles.key.LevelKey;
import props.objects.levelprops.gametriggers.interactibles.SpikesTrigger;
import props.objects.levelprops.gametriggers.interactibles.SpringTrigger;
import props.objects.levelprops.gametriggers.interactibles.TestTrigger;
import prototypes.level.ALevel;
import utils.files.Resources;


/**
 * The type Test level 1.
 */
public class TestLevel1 extends ALevel {

    /**
     * Instantiates a new Test level 1.
     *
     * @param gameModel the game model
     */
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

        // Generic Triggers
        addProp(new TestTrigger(gameModel, 700, 880, 100, 100, 0, 0, false, false));
        addProp(new TestTrigger(gameModel, 300, 580, 100, 100, 0, 0, false, false));
        addProp(new TestTrigger(gameModel, 1000, 280, 100, 100, 0, 0, false, false));
        addProp(new TestTrigger(gameModel, 100, 680, 100, 100, 0, 0, false, false));

        // Springs
        addProp(new SpringTrigger(gameModel, 1000, 280, 100, 20, 0, 0, false, false));
        addProp(new SpringTrigger(gameModel, 1200, 280, 100, 20, 0, 0, false, false));
        addProp(new SpringTrigger(gameModel, 1400, 280, 100, 20, 0, 0, false, false));
        addProp(new SpringTrigger(gameModel, 300, 580, 100, 20, 0, 0, false, false));
        addProp(new SpringTrigger(gameModel, 1000, 280, 100, 20, 0, 0, false, false));
        addProp(new SpringTrigger(gameModel, 100, 680, 100, 20, 0, 0, false, false));

        // Spikes
        addProp(new SpikesTrigger(gameModel, 1000, 930, 100, 50, 0, 0, -1));
        addProp(new SpikesTrigger(gameModel, 1200, 960, 100, 20, 0, 0, -1));
        addProp(new SpikesTrigger(gameModel, 1400, 905, 100, 75, 0, 0, -1));

        // Keys
        addProp(new LevelKey(gameModel, 1600, 750, 100, 50, 0, 0));
        addProp(new LevelKey(gameModel, 2500, 450, 100, 50, 0, 0));
        addProp(new LevelKey(gameModel, 500, 300, 100, 50, 0, 0));
    }


}
