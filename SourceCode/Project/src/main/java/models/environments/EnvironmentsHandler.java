package models.environments;

import models.environments.game.GameEnvironment;
import models.environments.menus.mainmenu.MainMenuEnvironment;
import models.prototypes.threading.ARunnable;
import models.prototypes.window.ACanvas;
import models.prototypes.window.environments.AEnvironment;
import views.swing.window.MainWindow;

import java.util.ArrayList;

public class EnvironmentsHandler {

    private MainWindow parentDisplayWindow;

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

    public void init(MainWindow parentDisplayWindow) {
        this.parentDisplayWindow = parentDisplayWindow;
    }

    public void addEnvironmentPair(AEnvironment model, ACanvas canvas, ARunnable uRunnable, ARunnable rRunnable) {
        environments.add(model);
        canvases.add(canvas);
        updateRunnables.add(uRunnable);
        renderRunnables.add(rRunnable);
    }

    public void setCurrentEnvironmentType(EnvironmentType environmentType) {
        this.currentEnvironment = environmentType;
    }

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

    public AEnvironment getCurrentEnvironment() {
        return environments.get(currentEnvironment.ordinal());
    }

    public GameEnvironment getGameEnvironment() {
        return (GameEnvironment) environments.get(EnvironmentType.GAME.ordinal());
    }

    public MainMenuEnvironment getMenuEnvironment() {
        return (MainMenuEnvironment) environments.get(EnvironmentType.MAIN_MENU.ordinal());
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

    public void applyEnvironment() {
        applyEnvironment(true);
    }

    public void applyEnvironment(boolean resetThreads) {
        parentDisplayWindow.build();

        if(resetThreads) {
            initThreads();
        }
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
