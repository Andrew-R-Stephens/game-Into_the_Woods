package prototypes.window.environments;

import models.environments.EnvironmentsHandler;
import prototypes.controls.AKeyController;
import prototypes.controls.AMouseController;
import utils.drawables.IDrawable;
import utils.updates.IUpdatable;

/**
 * The type A environment.
 */
public abstract class AEnvironment implements IUpdatable, IDrawable {

    /**
     * The Parent environments model.
     */
    public EnvironmentsHandler parentEnvironmentsModel;

    /**
     * The Key controller.
     */
    protected AKeyController keyController;
    /**
     * The Mouse controller.
     */
    protected AMouseController mouseController;

    /**
     * Init.
     *
     * @param parentEnvironmentsModel the parent environments model
     * @param keyController           the key controller
     * @param mouseController         the mouse controller
     */
    protected void init(EnvironmentsHandler parentEnvironmentsModel, AKeyController keyController, AMouseController mouseController) {
        this.parentEnvironmentsModel = parentEnvironmentsModel;
        this.keyController = keyController;
        this.mouseController = mouseController;
    }

    /**
     * Gets key controller.
     *
     * @return the key controller
     */
    public AKeyController getKeyController() {
        return keyController;
    }

    /**
     * Gets mouse controller.
     *
     * @return the mouse controller
     */
    public AMouseController getMouseController() {
        return mouseController;
    }

    public abstract void reset();
}
