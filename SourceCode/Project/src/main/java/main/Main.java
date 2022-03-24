package main;

import graphics.canvas.game.GameCanvas;
import graphics.canvas.menu.MenuCanvas;
import graphics.window.MainWindow;
import models.controls.GameControlsModel;
import models.controls.MenuControlsModel;
import models.controls.game.GameKeyControls;
import models.controls.game.GameMouseControls;
import models.controls.menu.MenuKeyControls;
import models.controls.menu.MenuMouseControls;
import utils.config.PreferenceData;
import models.environments.EnvironmentsHandler;
import models.environments.game.GameEnvironment;
import models.environments.menu.mainmenu.MainMenuEnvironment;
import props.objects.levels.LevelList;
import models.runnables.game.GameRenderRunnable;
import models.runnables.game.GameUpdateRunnable;
import models.runnables.menu.MenuRenderRunnable;
import models.runnables.menu.MenuUpdateRunnable;
import utils.files.PreferencesXMLParser;
import utils.files.Resources;

/**
 * The type main.Main.
 */
public class Main {

    private static PreferenceData preferences;

    private static EnvironmentsHandler environmentsModel;

    private static GameControlsModel gameControlsModel;
    private static MenuControlsModel menuControlsModel;

    private static MainMenuEnvironment mainMenuModel;
    private static GameEnvironment gameModel;

    private static GameUpdateRunnable gameUpdateRunnable;
    private static GameRenderRunnable gameRenderRunnable;

    private static MenuUpdateRunnable menuUpdateRunnable;
    private static MenuRenderRunnable menuRenderRunnable;

    private static LevelList levelsListModel;

    private static MenuCanvas mainMenuCanvas;
    private static GameCanvas gameCanvas;

    private static MainWindow window;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        // Load Images, Files, etc
        loadAssets();

        // Create Objects
        create();

        // Initialize Objects
        init();

    }

    /**
     * Load assets.
     */
    public static void loadAssets() {
        Resources resources = new Resources();

        resources.init();
    }

    /**
     * Create.
     */
    public static void create() {

        // Create Preferences
        preferences = new PreferenceData();

        // Create Environment Handler
        environmentsModel = new EnvironmentsHandler();

        // Create Control Models
        gameControlsModel = new GameControlsModel();
        menuControlsModel = new MenuControlsModel();

        // Create Menu Environment
        mainMenuModel = new MainMenuEnvironment();

        // Create Game Environment
        gameModel = new GameEnvironment();

        // Create Levels List Model
        levelsListModel = new LevelList();

        // Create State Canvases
        mainMenuCanvas = new MenuCanvas();
        gameCanvas = new GameCanvas();

        gameUpdateRunnable = new GameUpdateRunnable();
        gameRenderRunnable = new GameRenderRunnable();
        menuUpdateRunnable = new MenuUpdateRunnable();
        menuRenderRunnable = new MenuRenderRunnable();

        // Create Window
        window = new MainWindow();

    }

    /**
     * Init game objects.
     */
    public static void init() {

        // Initialize Preferences
        PreferencesXMLParser preferencesParser =
                new PreferencesXMLParser(preferences, "files/", "Preferences", ".xml");
        preferencesParser.read();
        System.out.println(preferences.toString());

        // Initialize Control Models
        gameControlsModel.init(new GameMouseControls(gameControlsModel), new GameKeyControls(gameControlsModel));
        menuControlsModel.init(new MenuMouseControls(menuControlsModel), new MenuKeyControls(menuControlsModel));

        // Initialize Levels
        int defaultLevel = 0;
        levelsListModel.init(gameModel, defaultLevel);

        // Initialize AEnvironment Model Container
        environmentsModel.init(window);

        // Initialize AEnvironment Models
        mainMenuModel.init(environmentsModel, menuControlsModel);
        gameModel.init(environmentsModel, gameControlsModel, levelsListModel);

        // Initialize Canvases
        mainMenuCanvas.init(mainMenuModel);
        gameCanvas.init(gameModel);

        gameUpdateRunnable.init(gameModel);
        gameRenderRunnable.init(gameCanvas);
        menuUpdateRunnable.init(mainMenuModel);
        menuRenderRunnable.init(mainMenuCanvas);

        // Initialize Environments Model with Main Menu and Game AEnvironments
        environmentsModel.addPair(mainMenuModel, mainMenuCanvas, menuUpdateRunnable, menuRenderRunnable);
        environmentsModel.addPair(gameModel, gameCanvas, gameUpdateRunnable, gameRenderRunnable);

        // Initialize Window with Preference Data
        window.init(preferences);

        mainMenuModel.init();

        // Initialize Window's Environment
        window.initEnvironmentsModel(environmentsModel);
        window.applyEnvironmentAndCanvas(EnvironmentsHandler.EnvironmentType.MAIN_MENU);

        // Confirm and Apply scaling
        preferences.post();

    }

}
