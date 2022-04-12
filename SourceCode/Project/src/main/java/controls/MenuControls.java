package controls;

import models.prototypes.controls.AControls;

public class MenuControls extends AControls {

    public boolean isAction(Actions type) {
        return actions[type.ordinal()];
    }

}
