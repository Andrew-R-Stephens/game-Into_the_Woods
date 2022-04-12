package models.prototypes.controls;

import java.awt.event.KeyListener;

public abstract class AKeyController implements KeyListener {

    protected AControls controlsModel;

    public AKeyController(AControls controlsModel) {
        this.controlsModel = controlsModel;
    }

    public AControls getControlsModel() {
        return controlsModel;
    }

}
