package props.objects.levels;

import models.environments.game.GameModel;
import props.objects.levelprops.TestLevelPropStatic;
import props.prototypes.level.ALevel;
import props.prototypes.level.prop.ALevelProp;

import java.util.ArrayList;


public class TestLevel3 extends ALevel {

    private final ArrayList<ALevelProp> levelProps = new ArrayList<>();

    public TestLevel3(GameModel gameModel) {
        super(gameModel);

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
