package props.prototypes.window.environments;

import props.prototypes.controls.AKeyController;
import props.prototypes.controls.AMouseController;
import utils.drawables.IDrawable;
import utils.updates.IUpdatable;

public abstract class AEnvironment implements IUpdatable, IDrawable {

    protected AKeyController keyController;
    protected AMouseController mouseController;

    public AKeyController getKeyController() {
        return keyController;
    }

    public AMouseController getMouseController() {
        return mouseController;
    }
}
