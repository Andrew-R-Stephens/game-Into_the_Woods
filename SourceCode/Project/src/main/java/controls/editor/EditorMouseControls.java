package controls.editor;

import models.prototypes.controls.AControls;
import models.prototypes.controls.AMouseController;

import java.awt.event.MouseEvent;

/**
 * Dedicated GameEnvironment Controls class that extends the AMouseController.
 * @author Andrew Stephens
 */
public class EditorMouseControls extends AMouseController {

    /**
     * <p>Creates the GameMouseControls and references the parent AControls</p>
     * @param controlsViewModel - the AControls parent model
     */
    public EditorMouseControls(AControls controlsViewModel) {
        super(controlsViewModel);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setPos(e.getX(), e.getY());
    }
}
