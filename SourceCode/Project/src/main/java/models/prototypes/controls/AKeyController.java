package models.prototypes.controls;

import java.awt.event.KeyListener;

/**
 * <p></p>
 */
public abstract class AKeyController implements KeyListener {

    protected AControls controlsModel;

    /**
     * <p></p>
     * @param controlsModel -
     */
    public AKeyController(AControls controlsModel) {
        this.controlsModel = controlsModel;
    }

    /**
     * <p></p>
     * @return
     */
    public AControls getControlsModel() {
        return controlsModel;
    }

}
