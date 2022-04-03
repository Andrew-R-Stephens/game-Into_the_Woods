package prototypes.controls;

import models.controls.GameControlsModel;

import java.awt.event.KeyListener;

/**
 * TODO: Add description
 */
public abstract class AKeyController implements KeyListener {

    protected AControlsModel controlsModel;

    public AKeyController(AControlsModel controlsModel) {
        this.controlsModel = controlsModel;
    }

    public AControlsModel getControlsModel() {
        return controlsModel;
    }

}
