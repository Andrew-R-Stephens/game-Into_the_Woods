package props.prototypes.level;

import props.prototypes.actors.levelactors.animated.ALevelProp;

import java.util.ArrayList;

public abstract class ALevel {

    public abstract void addProp(ALevelProp prop);

    public abstract ArrayList<ALevelProp> getLevelProps();
}
