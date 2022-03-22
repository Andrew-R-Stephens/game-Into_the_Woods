package models.environments;

import main.MainWindow;
import prototypes.threading.ARenderRunnable;
import prototypes.threading.AUpdateRunnable;
import prototypes.window.ACanvas;
import prototypes.window.environments.AEnvironment;
import utils.files.R;

import java.util.ArrayList;

/**
 * The type Environments model.
 */
public class EnvironmentsModel {

    private R imageBank;

    private MainWindow parentComponent;

    /**
     * The enum Environment type.
     */
    public enum EnvironmentType {
        /**
         * Main menu environment type.
         */
        MAIN_MENU,
        /**
         * Game environment type.
         */
        GAME }
    private EnvironmentType currentEnvironment = EnvironmentType.MAIN_MENU;

    private final ArrayList<AEnvironment> environments = new ArrayList<>();
    private final ArrayList<ACanvas> canvases = new ArrayList<>();
    private final ArrayList<AUpdateRunnable> updateRunnables = new ArrayList<>();
    private final ArrayList<ARenderRunnable> renderRunnables = new ArrayList<>();

    /**
     * Init.
     *
     * @param parentComponent the parent component
     */
    public void init(MainWindow parentComponent) {
        this.parentComponent = parentComponent;
    }

    /**
     * Add pair.
     *
     * @param model     the model
     * @param canvas    the canvas
     * @param uRunnable the u runnable
     * @param rRunnable the r runnable
     */
    public void addPair(AEnvironment model, ACanvas canvas, AUpdateRunnable uRunnable, ARenderRunnable rRunnable) {
        environments.add(model);
        canvases.add(canvas);
        updateRunnables.add(uRunnable);
        renderRunnables.add(rRunnable);
    }

    /**
     * Sets current environment.
     *
     * @param environmentType the environment type
     */
    public void setCurrentEnvironment(EnvironmentType environmentType) {
        this.currentEnvironment = environmentType;
    }

    /**
     * Gets current environment.
     *
     * @return the current environment
     */
    public AEnvironment getCurrentEnvironment() {
        return environments.get(currentEnvironment.ordinal());
    }

    /**
     * Gets current canvas.
     *
     * @return the current canvas
     */
    public ACanvas getCurrentCanvas() {
        return canvases.get(currentEnvironment.ordinal());
    }

    /**
     * Gets current update runnable.
     *
     * @return the current update runnable
     */
    public Runnable getCurrentUpdateRunnable() {
        return updateRunnables.get(currentEnvironment.ordinal());
    }

    /**
     * Gets current render runnable.
     *
     * @return the current render runnable
     */
    public Runnable getCurrentRenderRunnable() {
        return renderRunnables.get(currentEnvironment.ordinal());
    }

    /**
     * Apply environment.
     */
    public void applyEnvironment() {
        parentComponent.applyEnvironmentAndCanvas();
    }

}
