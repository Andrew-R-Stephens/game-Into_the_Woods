package proptypes.level;

import proptypes.actors.levelactors.animated.ALevelProp;

import java.util.ArrayList;

public abstract class ALevel {

    public abstract void addProp(ALevelProp prop);

    public abstract ArrayList<ALevelProp> getLevelProps();
}
