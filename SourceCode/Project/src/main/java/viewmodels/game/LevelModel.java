package viewmodels.game;

import proptypes.actors.levelactors.animated.ALevelProp;

import java.util.ArrayList;

public class LevelModel {

    public static final float GRAVITY = 9.8f;

    private final ArrayList<ALevelProp> levelProps = new ArrayList<>();

    public void addProp(ALevelProp prop) {
        levelProps.add(prop);
    }

    public ArrayList<ALevelProp> getLevelProps() {
        return levelProps;
    }
}
