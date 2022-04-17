package models.prototypes.environments;

import javazoom.jl.player.advanced.AdvancedPlayer;
import models.environments.EnvironmentsHandler;
import models.prototypes.controls.AKeyController;
import models.prototypes.controls.AMouseController;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

public abstract class AEnvironment implements IUpdatable, IDrawable {

    protected EnvironmentsHandler parentEnvironmentsHandler;

    private Resources resources;

    protected AdvancedPlayer audioPlayer;

    protected AKeyController keyController;
    protected AMouseController mouseController;

    protected void init(EnvironmentsHandler parentEnvironmentsModel, AKeyController keyController, AMouseController mouseController) {
        this.parentEnvironmentsHandler = parentEnvironmentsModel;
        this.keyController = keyController;
        this.mouseController = mouseController;
    }

    public AKeyController getKeyController() {
        return keyController;
    }

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
        Thread audioInitThread = new Thread(this::startBackgroundAudio);
        audioInitThread.start();
    }

    public void onExit() {
        stopBackgroundAudio();
    }

    public abstract void reset();

    public EnvironmentsHandler getParentEnvironmentsHandler() {
        return parentEnvironmentsHandler;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }
}
