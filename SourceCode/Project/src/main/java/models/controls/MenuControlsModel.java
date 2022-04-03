package models.controls;

import prototypes.controls.AControlsModel;

import java.util.Arrays;

/**
 * TODO: Add description
 */
public class MenuControlsModel extends AControlsModel {

    public boolean isAction(Actions type) {
        return actions[type.ordinal()];
    }

    public void resetInput() {
        Arrays.fill(directionals, false);
        Arrays.fill(actions, false);
    }

}
