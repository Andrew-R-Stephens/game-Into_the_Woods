package models.prototypes.window.environments;

import javazoom.jl.player.Player;
import models.environments.EnvironmentsHandler;
import models.prototypes.controls.AKeyController;
import models.prototypes.controls.AMouseController;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

/**
 * The type A environment.
 */
public abstract class AEnvironment implements IUpdatable, IDrawable {

    protected Player audioPlayer;

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

    public abstract void startBackgroundAudio();

    protected void stopBackgroundAudio() {
        if(audioPlayer != null) {
            audioPlayer.close();
        }
    }

    public void onResume() {
        startBackgroundAudio();
    }

    public void onExit() {
        stopBackgroundAudio();
    }

    public abstract void reset();
}
