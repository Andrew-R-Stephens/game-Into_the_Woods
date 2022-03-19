package props.prototypes.controls;

public abstract class AControlsModel {

    protected AKeyController keyController;
    protected AMouseController mouseController;

    public void init(AMouseController mouseController, AKeyController keyController) {
        this.mouseController = mouseController;
        this.keyController = keyController;
    }

    public AKeyController getKeyController() {
        return keyController;
    }

    public AMouseController getMouseController() {
        return mouseController;
    }

}
