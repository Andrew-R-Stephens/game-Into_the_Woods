package prototypes.controls;

/**
 * The type A controls model.
 */
public abstract class AControlsModel {

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
     * @param mouseController the mouse controller
     * @param keyController   the key controller
     */
    public void init(AMouseController mouseController, AKeyController keyController) {
        this.mouseController = mouseController;
        this.keyController = keyController;
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

}
