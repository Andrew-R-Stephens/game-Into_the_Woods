package models.environments;

import views.swing.window.MainWindow;
import models.environments.game.GameEnvironment;
import models.environments.menus.mainmenu.MainMenuEnvironment;
import models.prototypes.threading.ARunnable;
import models.prototypes.window.ACanvas;
import models.prototypes.window.environments.AEnvironment;

import java.util.ArrayList;

/**
 * The type Environments model.
 */
public class EnvironmentsHandler {

    private MainWindow parentDisplayWindow;

    /**
     * The enum Environment type.
     */
    public enum EnvironmentType {
        MAIN_MENU,
        GAME,
        GAME_PAUSE_MENU
    }
    private EnvironmentType currentEnvironment = EnvironmentType.MAIN_MENU;

    private final ArrayList<AEnvironment> environments = new ArrayList<>();
    private final ArrayList<ACanvas> canvases = new ArrayList<>();
    private final ArrayList<ARunnable> updateRunnables = new ArrayList<>();
    private final ArrayList<ARunnable> renderRunnables = new ArrayList<>();

    private Thread updatesThread;
    private Thread rendersThread;

    /**
     * Init.
     *
     * @param parentDisplayWindow the parent component
     */
    public void init(MainWindow parentDisplayWindow) {
        this.parentDisplayWindow = parentDisplayWindow;
    }

    /**
     * Add pair.
     *
     * @param model     the model
     * @param canvas    the canvas
     * @param uRunnable the u runnable
     * @param rRunnable the r runnable
     */
    public void addEnvironmentPair(AEnvironment model, ACanvas canvas, ARunnable uRunnable, ARunnable rRunnable) {
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
    public void setCurrentEnvironmentType(EnvironmentType environmentType) {
        this.currentEnvironment = environmentType;
    }

    public EnvironmentsHandler swapToEnvironmentType(EnvironmentType environmentType, boolean resetEnvironment) {
        if(resetEnvironment) {
            environments.get(currentEnvironment.ordinal()).onExit();
            environments.get(currentEnvironment.ordinal()).reset();
        }
        pauseThreads();

        EnvironmentType previousEnvironment = currentEnvironment;
        setCurrentEnvironmentType(environmentType);

        if(resetEnvironment &&
                environments.get(previousEnvironment.ordinal()) != environments.get(currentEnvironment.ordinal())) {
            environments.get(previousEnvironment.ordinal()).onExit();
            environments.get(currentEnvironment.ordinal()).onResume();
        }

        return this;
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
     * Gets game model.
     *
     * @return the game model
     */
    public GameEnvironment getGameEnvironment() {
        return (GameEnvironment) environments.get(EnvironmentType.GAME.ordinal());
    }

    public MainMenuEnvironment getMenuEnvironment() {
        return (MainMenuEnvironment) environments.get(EnvironmentType.MAIN_MENU.ordinal());
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
        parentDisplayWindow.build();

        initThreads();
    }

    public void pauseThreads() {
        updateRunnables.get(currentEnvironment.ordinal()).setPaused(true);
        renderRunnables.get(currentEnvironment.ordinal()).setPaused(true);
    }

    public void initThreads() {

        if(updatesThread != null) {
            updatesThread.interrupt();
            updatesThread = null;
        }
        if(rendersThread != null) {
            rendersThread.interrupt();
            rendersThread = null;
        }

        updatesThread = new Thread(getCurrentUpdateRunnable());
        rendersThread = new Thread(getCurrentRenderRunnable());

        updatesThread.start();
        rendersThread.start();
    }

}
