package controls;

import models.prototypes.controls.AControlsModel;

public class GameControls extends AControlsModel {

    public enum Abilities {
        JUMP,
        DASH
    }


    boolean[] abilities = new boolean[Abilities.values().length];


    public void setAbility(Abilities type, boolean state) {
        abilities[type.ordinal()] = state;
    }

    public boolean[] getAbilities() {
        return abilities;
    }

}
