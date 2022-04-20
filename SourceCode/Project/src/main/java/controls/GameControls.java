package controls;

import models.prototypes.controls.AControls;

import java.util.Arrays;

/**
 * <p>GameControls is dedicated to the Controls of the GameEnvironment.</p>
 */
public class GameControls extends AControls {

    boolean[] abilities = new boolean[Abilities.values().length];

    /**
     * <p>Controls the state of a specific ability.</p>
     * @param type The Ability specified.
     * @param state The requested state of the ability.
     */
    public void setAbility(Abilities type, boolean state) {
        abilities[type.ordinal()] = state;
    }

    /**
     * @return The current state of all Abilities.
     */
    public boolean[] getAbilities() {
        return abilities;
    }

    /**
     * <p>Sets states of all controls to disabled.</p>
     */
    public void reset() {
        super.reset();
        Arrays.fill(abilities, false);
    }

    /**
     * The enumeration of all Abilities. Abilities are the player's extra movement capabilities aside from
     * directional movement.
     */
    public enum Abilities {
        JUMP,
        DASH
    }
}
