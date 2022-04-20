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
 * <p>The AEnvironment is a superclass of all Environment-based classes. It contains a reference to the
 * EnvironmentsHandler which is used for multiple types of manipulations such as AEnvironment navigation.</p>
 * <p>It contains references to the AKeyController and AMouseController for this particular Environment subtype.</p>
 * <p>There is a reference to the prebuilt Resources, allowing quick access to pre-allocated resources.</p>
 * <p>The AdvancedPlayer is used to control audio streams which remain particular to this Environment.</p>
 */
public abstract class AEnvironment implements IUpdatable, IDrawable {

    protected AdvancedPlayer audioPlayer;
    private EnvironmentsHandler parentEnvironmentsHandler;
    private AKeyController keyController;
    private AMouseController mouseController;
    private Resources resources;

    /**
     * <p>Initializes with the EnvironmentHandler and the controller used for this particular Environment subtype.</p>
     * @param parentEnvironmentsHandler The EnvironmentsHandler that contains this Environment.
     * @param controls The AControls subtype that this Environment subtype uses.
     */
    protected void init(EnvironmentsHandler parentEnvironmentsHandler, AControls controls) {
        this.parentEnvironmentsHandler = parentEnvironmentsHandler;
        this.keyController = controls.getKeyController();
        this.mouseController = controls.getMouseController();
    }

    /**
     * <p>Retrieves the Environment's AKeyController subtype.</p>
     * @return The Key Controller subtype.
     */
    public AKeyController getKeyController() {
        return keyController;
    }

    /**
     * <p>Retrieves the Environment's AMouseController subtype.</p>
     * @return The Mouse Controller subtype
     */
    public AMouseController getMouseController() {
        return mouseController;
    }

    /**
     * <p>Should be defined in subclasses to specify what it means to enable the current audio stream. Called
     * primarily during onResume executions. Some subclasses might not need this to execute, so the body may be
     * undefined there.</p>
     */
    public abstract void startBackgroundAudio();

    /**
     * <p>Called primarily during onExit executions. Some subclasses might not need this to execute, so the body may be
     * overridden as undefined there.</p>
     */
    protected void stopBackgroundAudio() {
        if(audioPlayer != null) {
            audioPlayer.close();
        }
    }

    /**
     * <p>Defined by subclasses. Used to completely default all fields.</p>
     */
    public abstract void reset();

    /**
     * <p>Retrieves the EnvironmentHandler that contains this object.</p>
     * @return The EnvironmentsHandler parent container.
     */
    public EnvironmentsHandler getParentEnvironmentsHandler() {
        return parentEnvironmentsHandler;
    }

    /**
     * <p>Retrieves the Resources pool that this class has access to.</p>
     * @return The Resources pool.
     */
    public Resources getResources() {
        return resources;
    }

    /**
     * <p>Sets the Resources locally. Used by any classes contained within or deriving this class.</p>
     * @param resources The Resources. Resources cannot be null.
     */
    public void setResources(Resources resources) {
        this.resources = resources;
    }

    /**
     * <p>Executes when the process deems that the Environment should continue to execute.</p>
     * <p>Deals with starting the audiostream.</p>
     */
    public void onResume() {
        Thread audioInitThread = new Thread(this::startBackgroundAudio);
        audioInitThread.start();
    }

    /**
     * <p>Executes when the process deems that this Environment should cease.</p>
     * <p>Deals with terminating the audiostream.</p>
     */
    public void onExit() {
        stopBackgroundAudio();
    }

}
