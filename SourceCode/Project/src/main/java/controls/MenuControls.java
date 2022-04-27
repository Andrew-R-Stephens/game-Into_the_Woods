package controls;

import models.prototypes.controls.AControls;

/**
 * <p>MenuControls is dedicated to the Controls of the AMenuEnvironments, such as MainMenuEnvironment and the
 * PauseMenuEnvironment.</p>
 * @author Andrew Stephens
 */
public class MenuControls extends AControls {

    /**
     * <p>Accepts an action and checks if the current controlled state of the action is set to enabled.</p>
     * @param type The Action requested.
     * @return The state of the Action.
     */
    public boolean isAction(Actions type) {
        return actions[type.ordinal()];
    }

}
