package props.prototypes.window.environments;

import main.EnvironmentsModel;
import props.prototypes.controls.AKeyController;
import props.prototypes.controls.AMouseController;
import utils.drawables.IDrawable;
import utils.updates.IUpdatable;

public abstract class AEnvironment implements IUpdatable, IDrawable {

    public EnvironmentsModel parentEnvironmentsModel;

    protected AKeyController keyController;
    protected AMouseController mouseController;

    protected void init(EnvironmentsModel parentEnvironmentsModel, AKeyController keyController, AMouseController mouseController) {
        this.parentEnvironmentsModel = parentEnvironmentsModel;
        this.keyController = keyController;
        this.mouseController = mouseController;
    }

    public AKeyController getKeyController() {
        return keyController;
    }

    public AMouseController getMouseController() {
        return mouseController;
    }
}
