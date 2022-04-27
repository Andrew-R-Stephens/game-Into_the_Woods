package controls.menu;

import models.prototypes.controls.AControls;
import models.prototypes.controls.AMouseController;

import java.awt.event.MouseEvent;

/**
 * Dedicated AMenuEnvironment Controls class that extends the AMouseController.
 * @author Andrew Stephens
 */
public class MenuMouseControls extends AMouseController {

    /**
     * <p>Creates the MenuMouseControls and references the parent AControls</p>
     * @param controlsViewModel - the AControls parent model
     */
    public MenuMouseControls(AControls controlsViewModel) {
        super(controlsViewModel);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setPos(e.getX(), e.getY());
    }

}
