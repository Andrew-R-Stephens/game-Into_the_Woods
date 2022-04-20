package models.prototypes.environments;

import javazoom.jl.player.advanced.AdvancedPlayer;
import models.environments.EnvironmentsHandler;
import models.prototypes.controls.AControls;
import models.prototypes.controls.AKeyController;
import models.prototypes.controls.AMouseController;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

/**
 * <p></p>
 */
public abstract class AEnvironment implements IUpdatable, IDrawable {

    private EnvironmentsHandler parentEnvironmentsHandler;

    private Resources resources;

    protected AdvancedPlayer audioPlayer;

    protected AKeyController keyController;
    protected AMouseController mouseController;

    /**
     * <p></p>
     * @param parentEnvironmentsModel -
     * @param gameControls -
     */
    protected void init(EnvironmentsHandler parentEnvironmentsModel, AControls gameControls) {
        this.parentEnvironmentsHandler = parentEnvironmentsModel;
        this.keyController = gameControls.getKeyController();
        this.mouseController = gameControls.getMouseController();
    }

    /**
     * <p></p>
     * @return
     */
    public AKeyController getKeyController() {
        return keyController;
    }

    /**
     * <p></p>
     * @return
     */
    public AMouseController getMouseController() {
        return mouseController;
    }

    /**
     * <p></p>
     */
    public abstract void startBackgroundAudio();

    /**
     * <p></p>
     */
    protected void stopBackgroundAudio() {
        if(audioPlayer != null) {
            audioPlayer.close();
        }
    }

    /**
     * <p></p>
     */
    public abstract void reset();

    /**
     * <p></p>
     * @return
     */
    public EnvironmentsHandler getParentEnvironmentsHandler() {
        return parentEnvironmentsHandler;
    }

    /**
     * <p></p>
     * @param resources -
     */
    public void setResources(Resources resources) {
        this.resources = resources;
    }

    /**
     * <p></p>
     * @return
     */
    public Resources getResources() {
        return resources;
    }

    /**
     * <p></p>
     */
    public void onResume() {
        Thread audioInitThread = new Thread(this::startBackgroundAudio);
        audioInitThread.start();
    }

    /**
     * <p></p>
     */
    public void onExit() {
        stopBackgroundAudio();
    }

}
