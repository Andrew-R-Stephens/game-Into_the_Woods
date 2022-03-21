package props.objects.levels;

import models.environments.game.GameModel;
import props.objects.gametriggers.TestTrigger;
import props.objects.levelprops.TestLevelPropStatic;
import prototypes.level.ALevel;
import prototypes.level.prop.ALevelProp;

import java.util.ArrayList;


/**
 * The type Test level 1.
 */
public class TestLevel1 extends ALevel {

    private final ArrayList<ALevelProp> levelProps = new ArrayList<>();

    /**
     * Instantiates a new Test level 1.
     *
     * @param gameModel the game model
     */
    public TestLevel1(GameModel gameModel) {
        super(gameModel);

        // Walls
        addProp(new TestLevelPropStatic(-500, 0, 500, 1080, 0, 0, false));
        addProp(new TestLevelPropStatic(9000, 0, 100, 1080, 0, 0, false));

        // Floor
        addProp(new TestLevelPropStatic(0, 980, 10000, 100, 0, 0, false));

        // Climbing Walls
        addProp(new TestLevelPropStatic(320, -150, 50, 200, 0, 0, false));
        addProp(new TestLevelPropStatic(500, 0, 50, 200, 0, 0, false));
        addProp(new TestLevelPropStatic(320, 220, 50, 200, 0, 0, false));
        addProp(new TestLevelPropStatic(500, 360, 50, 200, 0, 0, false));
        addProp(new TestLevelPropStatic(320, 500, 50, 200, 0, 0, false));

        // Other Props
        addProp(new TestLevelPropStatic(1800, 100, 500, 100, 0, 0, false));
        addProp(new TestLevelPropStatic(70, 800, 500, 100, 0, 0, false));
        addProp(new TestLevelPropStatic(500, 700, 500, 100, 0, 0, false));
        addProp(new TestLevelPropStatic(1100, 600, 500, 100, 0, 0, false));

        addProp(new TestLevelPropStatic(1800, 600, 500, 50, 0, 0, false));
        addProp(new TestLevelPropStatic(2000, 650, 220, 100, 0, 0, false));
        addProp(new TestLevelPropStatic(3500, 750, 500, 100, 0, 0, false));
        addProp(new TestLevelPropStatic(4000, 780, 200, 100, 0, 0, false));

        addProp(new TestLevelPropStatic(6800, 400, 500, 50, 0, 0, false));
        addProp(new TestLevelPropStatic(2500, 720, 220, 100, 0, 0, false));
        addProp(new TestLevelPropStatic(3000, 700, 500, 50, 0, 0, false));
        addProp(new TestLevelPropStatic(3200, 650, 100, 100, 0, 0, false));

        addProp(new TestTrigger(gameModel, 700, 880, 100, 100, 0, 0, false, false));
        addProp(new TestTrigger(gameModel, 300, 580, 100, 100, 0, 0, false, false));
        addProp(new TestTrigger(gameModel, 1000, 280, 100, 100, 0, 0, false, false));
        addProp(new TestTrigger(gameModel, 100, 680, 100, 100, 0, 0, false, false));
    }


    public void addProp(ALevelProp prop) {
        levelProps.add(prop);
    }

    public ArrayList<ALevelProp> getLevelProps() {
        return levelProps;
    }
}
