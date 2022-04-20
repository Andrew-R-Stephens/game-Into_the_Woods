package controls;

import models.prototypes.controls.AControls;

import java.util.Arrays;

/**
 * <p></p>
 */
public class GameControls extends AControls {

    boolean[] abilities = new boolean[Abilities.values().length];

    /**
     * <p></p>
     * @param type -
     * @param state -
     */
    public void setAbility(Abilities type, boolean state) {
        abilities[type.ordinal()] = state;
    }

    /**
     * <p></p>
     * @return
     */
    public boolean[] getAbilities() {
        return abilities;
    }

    /**
     * <p></p>
     */
    public void reset() {
        super.reset();
        Arrays.fill(abilities, false);
    }

    public enum Abilities {
        JUMP,
        DASH
    }
}
