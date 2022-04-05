package props.objects.levels.level;

import models.environments.game.GameEnvironment;
import props.objects.levelprops.boundaries.TestLevelPropStatic;
import prototypes.level.ALevel;


/**
 * The type Test level 3.
 */
public class TestLevel3 extends ALevel {

    /**
     * Instantiates a new Test level 3.
     *
     * @param gameModel the game model
     */
    public TestLevel3(GameEnvironment gameModel) {
        super(gameModel);

        setStartOrigin(200, 50);

        build();

        // Floor
        addProp(new TestLevelPropStatic(0, 980, 10000, 100, 0, 0, false));

    }

    @Override
    public void build() {
        for(int i = 0; i < 1000; i++) {
            addProp(new TestLevelPropStatic(i*20, 500+(-5*i*.8f), 20, 20, 0, 0, false));
        }
    }

    @Override
    public void reset() {

    }

}
