package controls;

import models.prototypes.controls.AControls;

public class MenuControls extends AControls {

    /**
     * <p></p>
     * @param type -
     * @return
     */
    public boolean isAction(Actions type) {
        return actions[type.ordinal()];
    }

}
