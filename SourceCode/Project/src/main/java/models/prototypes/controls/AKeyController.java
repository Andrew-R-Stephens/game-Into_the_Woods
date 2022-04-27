package models.prototypes.controls;

import java.awt.event.KeyListener;

/**
 * <p>The AKeyController is an abstract class that Key controller based class inherit.</p>
 * @author Andrew Stephens
 */
public abstract class AKeyController implements KeyListener {

    protected AControls controlsModel;

    /**
     * <p>Creates a new AKeyController</p>
     * @param controlsModel The parent AControls model
     */
    public AKeyController(AControls controlsModel) {
        this.controlsModel = controlsModel;
    }

    /**
     * <p>Gets the parent ControlsModel.</p>
     * @return the parent ControlsModel
     */
    public AControls getControlsModel() {
        return controlsModel;
    }

}
