package main;

import props.prototypes.threading.ARenderRunnable;
import props.prototypes.threading.AUpdateRunnable;
import props.prototypes.window.ACanvas;
import props.prototypes.window.environments.AEnvironment;

import java.util.ArrayList;

public class EnvironmentsModel {

    enum EnvironmentType { MAIN_MENU, GAME }
    private EnvironmentType currentEnvironment = EnvironmentType.MAIN_MENU;

    private final ArrayList<AEnvironment> environments = new ArrayList<>();
    private final ArrayList<ACanvas> canvases = new ArrayList<>();
    private final ArrayList<AUpdateRunnable> updateRunnables = new ArrayList<>();
    private final ArrayList<ARenderRunnable> renderRunnables = new ArrayList<>();

    public void addPair(AEnvironment model, ACanvas canvas, AUpdateRunnable uRunnable, ARenderRunnable rRunnable) {
        environments.add(model);
        canvases.add(canvas);
        updateRunnables.add(uRunnable);
        renderRunnables.add(rRunnable);
    }

    public void setCurrentEnvironment(EnvironmentType environmentType) {
        this.currentEnvironment = environmentType;
    }

    public AEnvironment getCurrentEnvironment() {
        return environments.get(currentEnvironment.ordinal());
    }

    public ACanvas getCurrentCanvas() {
        return canvases.get(currentEnvironment.ordinal());
    }

    public Runnable getCurrentUpdateRunnable() {
        return updateRunnables.get(currentEnvironment.ordinal());
    }

    public Runnable getCurrentRenderRunnable() {
        return renderRunnables.get(currentEnvironment.ordinal());
    }
}
