package controls;

import models.prototypes.controls.AControlsModel;

/**
 * TODO: Add description
 */
public class GameControls extends AControlsModel {

    /**
     * The enum Abilities.
     */
    public enum Abilities {
        JUMP,
        DASH
    }


    /**
     * The Abilities.
     */
    boolean[] abilities = new boolean[Abilities.values().length];


    /**
     * Sets ability.
     *
     * @param type  the type
     * @param state the state
     */
    public void setAbility(Abilities type, boolean state) {
        abilities[type.ordinal()] = state;
    }

    /**
     * Get abilities boolean [ ].
     *
     * @return the boolean [ ]
     */
    public boolean[] getAbilities() {
        return abilities;
    }

}
