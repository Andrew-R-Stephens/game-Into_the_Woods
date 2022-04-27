package controls.game;

import models.prototypes.controls.AControls;
import models.prototypes.controls.AMouseController;

/**
 * Dedicated GameEnvironment Controls class that extends the AMouseController.
 * @author Andrew Stephens
 */
public class GameMouseControls extends AMouseController {

    /**
     * <p>Creates the GameMouseControls and references the parent AControls</p>
     * @param controlsViewModel - the AControls parent model
     */
    public GameMouseControls(AControls controlsViewModel) {
        super(controlsViewModel);
    }

}
