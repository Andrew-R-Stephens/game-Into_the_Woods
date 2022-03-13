package props.prototypes.level;

import props.prototypes.level.prop.ALevelProp;

import java.util.ArrayList;

public abstract class ALevel {

    public abstract void addProp(ALevelProp prop);

    public abstract ArrayList<ALevelProp> getLevelProps();
}
