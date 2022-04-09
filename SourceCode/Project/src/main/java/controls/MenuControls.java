package controls;

import models.prototypes.controls.AControlsModel;

import java.util.Arrays;

public class MenuControls extends AControlsModel {

    public boolean isAction(Actions type) {
        return actions[type.ordinal()];
    }

    public void resetInput() {
        Arrays.fill(directionals, false);
        Arrays.fill(actions, false);
    }

}
