package models.environments;

import models.environments.game.GameEnvironment;
import models.environments.menus.mainmenu.MainMenuEnvironment;
import models.prototypes.environments.AEnvironment;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.prototypes.threading.ARunnable;
import models.prototypes.views.ACanvas;
import models.utils.config.Config;
import models.utils.config.SaveData;
import views.window.MainWindow;

import java.util.ArrayList;

/**
 * <p>Contains references to all AEnvironments, and holds references each of their ACanvases and ARunnables.</p>
 * <br>
 * <p>Works to handle navigation between AEnvironments as needed. Controls threading and AWindow build procedures.</p>
 * @author Andrew Stephens
 */
public class EnvironmentsHandler {

    /**<p>The main window frame.</p>*/
    private MainWindow parentWindow;

    /**<p>The save data with previously saved information and current information.</p>*/
    private SaveData saveData;

    /**<p>The current environment that the user is in</p>*/
    private EnvironmentType currentEnvironment = EnvironmentType.MAIN_MENU;

    /**<p>The list of all environments available</p>*/
    private final ArrayList<AEnvironment> environments = new ArrayList<>();
    /**<p>The list of all canvases available</p>*/
    private final ArrayList<ACanvas> canvases = new ArrayList<>();
    /**<p>The list of all update runnables available</p>*/
    private final ArrayList<ARunnable> updateRunnables = new ArrayList<>();
    /**<p>The list of all render runnables available</p>*/
    private final ArrayList<ARunnable> renderRunnables = new ArrayList<>();

    /**<p>The Updates thread that all environments' update runnables will use.</p>*/
    private Thread updatesThread;
    /**<p>The Render thread that all environments' render runnables will use.</p>*/
    private Thread rendersThread;

    /**
     * <p>Initializes the EnvironmentsHandler.</p>
     * @param parentDisplayWindow the window that is shown to the user
     */
    public void init(MainWindow parentDisplayWindow, SaveData saveData) {
        this.parentWindow = parentDisplayWindow;
        this.saveData = saveData;
    }

    /**
     * <p>Adds a new AEnvironment subtype to the list of AEnvironments.</p>
     * @param model The AEnvironment
     * @param canvas The ACanvas for the AEnvironment
     * @param uRunnable The update ARunnable for the AEnvironment
     * @param rRunnable The render ARunnable for the AEnvironment
     */
    public void addEnvironmentPair(AEnvironment model, ACanvas canvas, ARunnable uRunnable, ARunnable rRunnable) {
        environments.add(model);
        canvases.add(canvas);
        updateRunnables.add(uRunnable);
        renderRunnables.add(rRunnable);
    }

    /**
     * <p>Sets the current EnvironmentType.</p>
     * @param environmentType the chosen EnvironmentType.
     */
    public void setCurrentEnvironmentType(EnvironmentType environmentType) {
        this.currentEnvironment = environmentType;
    }

    /**
     * <p>Handles the procedure of switching from one AEnvironment to another. AEnvironments do no all need to be reset
     * due to some AEnvironments existing at certain points. Therefore, a reset condition is passed.</p>
     * @param environmentType the desired environment to switch to.
     * @param resetEnvironment Whether or not the environment should be reset.
     * @return this EnvironmentsHandler, used for chaining
     */
    public EnvironmentsHandler swapToEnvironment(EnvironmentType environmentType, boolean resetEnvironment) {
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
     * @return the active AEnvironment.
     */
    public AEnvironment getCurrentEnvironment() {
        return environments.get(currentEnvironment.ordinal());
    }

    /**
     * @return the GameEnvironment.
     */
    public GameEnvironment getGameEnvironment() {
        return (GameEnvironment) environments.get(EnvironmentType.GAME.ordinal());
    }

    /**
     * @return the MainMenuEnvironment.
     */
    public MainMenuEnvironment getMenuEnvironment() {
        return (MainMenuEnvironment) environments.get(EnvironmentType.MAIN_MENU.ordinal());
    }

    /**
     * @return the active AEnvironment's canvas.
     */
    public ACanvas getCurrentCanvas() {
        return canvases.get(currentEnvironment.ordinal());
    }

    /**
     * @return the active AEnvironment's updates-based ARunnable
     */
    public Runnable getCurrentUpdateRunnable() {
        return updateRunnables.get(currentEnvironment.ordinal());
    }

    /**
     * <p>Returns the ARunnable of the current AEnvironment that deals with renders.</p>
     * @return the active AEnvironment's render-based ARunnable
     */
    public Runnable getCurrentRenderRunnable() {
        return renderRunnables.get(currentEnvironment.ordinal());
    }

    /**
     * <p>Calls the applyEnvironment method with the reset default set as true.</p>
     */
    public void applyEnvironment() {
        applyEnvironment(true);
    }

    /**
     * <p>Applies the current AEnvironment to the Window. Restarts the current AEnvironment's Threads if necessary.</p>
     * @param resetThreads whether or not the threads should be rebuilt
     */
    public void applyEnvironment(boolean resetThreads) {
        if(environments.get(currentEnvironment.ordinal()) instanceof AMenuEnvironment) {
            parentWindow.buildCursor(true);
        } else {
            parentWindow.buildCursor(false);
        }

        parentWindow.build();

        if(resetThreads) {
            initThreads();
        }
    }

    /**
     * <p>Pauses the current AEnvironment's ARunnables.</p>
     */
    public void pauseThreads() {
        updateRunnables.get(currentEnvironment.ordinal()).setPaused(true);
        renderRunnables.get(currentEnvironment.ordinal()).setPaused(true);
    }

    /**
     * <p>Destroys active threads and creates new Threads using the current AEnvironment's ARunnables.</p>
     */
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

    public SaveData getSaveData() {
        return saveData;
    }

    /**
     * <p>Rebuilds the window with the appropriate window dimensions and WindowType.</p>
     * <p>Used primarily through the Options menus.</p>
     */
    public void rebuildWindow() {
        parentWindow.dispose();
        parentWindow = new MainWindow();
        parentWindow.setResources(getCurrentEnvironment().getResources());
        parentWindow.init(this);
        applyEnvironment(false);
        Config.calcResolutionScale();
    }

    /**
     * <p>The EnvironmentType is responsible for simply giving enumeration to the three types of environments, which
     * allows a more verbose representation of desired requests when switching environments.</p>
     */
    public enum EnvironmentType {
        MAIN_MENU,
        GAME,
        GAME_PAUSE_MENU
    }
}
