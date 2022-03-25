package props.objects.levels;

import models.environments.game.GameEnvironment;
import props.objects.levelprops.TestLevelPropStatic;
import prototypes.level.ALevel;
import prototypes.level.prop.ALevelProp;

import java.util.ArrayList;


/**
 * The type Test level 3.
 */
public class TestLevel3 extends ALevel {

    private final ArrayList<ALevelProp> levelProps = new ArrayList<>();

    /**
     * Instantiates a new Test level 3.
     *
     * @param gameModel the game model
     */
    public TestLevel3(GameEnvironment gameModel) {
        super(gameModel);

        setStartOrigin(200, 50);

        for(int i = 0; i < 1000; i++) {
            addProp(new TestLevelPropStatic(i*20, 500+(-5*i*.8f), 20, 20, 0, 0, false));
        }

        // Floor
        addProp(new TestLevelPropStatic(0, 980, 10000, 100, 0, 0, false));

    }


    public void addProp(ALevelProp prop) {
        levelProps.add(prop);
    }

    public ArrayList<ALevelProp> getLevelProps() {
        return levelProps;
    }

}
