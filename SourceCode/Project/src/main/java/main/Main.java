package main;

import views.swing.canvas.game.GameCanvas;
import views.swing.canvas.menu.MenuCanvas;
import views.swing.window.MainWindow;
import controls.GameControls;
import controls.MenuControls;
import controls.game.GameKeyControls;
import controls.game.GameMouseControls;
import controls.menu.MenuKeyControls;
import controls.menu.MenuMouseControls;
import models.environments.EnvironmentsHandler;
import models.environments.game.GameEnvironment;
import models.environments.game.hud.HUDModel;
import models.environments.game.hud.components.MapOverlay;
import models.environments.game.hud.components.PlayerStatsOverlay;
import models.environments.game.hud.components.TimeKeeperOverlay;
import models.environments.game.playerinventory.PlayerInventory;
import models.environments.menus.mainmenu.MainMenuEnvironment;
import models.environments.menus.pausemenumodel.PauseMenuEnvironment;
import models.runnables.RenderRunnable;
import models.runnables.UpdateRunnable;
import models.levels.LevelsList;
import models.utils.config.ConfigData;
import models.utils.files.PreferencesParser;
import models.utils.resources.Resources;

public class Main {

    private static ConfigData preferences;

    private static EnvironmentsHandler environmentsHandler;

    private static MapOverlay mapOverlay;
    private static PlayerStatsOverlay playerStatsOverlay;
    private static TimeKeeperOverlay timeKeeperOverlay;

    private static HUDModel hudModel;
    private static PlayerInventory inventory;

    private static GameControls gameControlsModel;
    private static MenuControls menuControlsModel;

    private static MainMenuEnvironment mainMenuEnvironment;
    private static GameEnvironment gameEnvironment;

    private static PauseMenuEnvironment pauseMenuModel;

    private static UpdateRunnable gameUpdateRunnable;
    private static RenderRunnable gameRenderRunnable;

    private static UpdateRunnable menuUpdateRunnable;
    private static RenderRunnable menuRenderRunnable;

    private static LevelsList levelsListModel;

    private static MenuCanvas mainMenuCanvas;
    private static GameCanvas gameCanvas;

    private static MainWindow window;

    /**
     * main - The starting point of the program.
     *
     * First loads all assets from file into a resource pool for later use and ease of access.
     * Then Creates all permanent static variables for protection against null pointer exceptions.
     * Then initializes all variables with their necessary reference objects. Assists in MVC cross-object communication.
     */
    public static void main(String[] args) {

        // Load in Images, Files, etc into a resource pool.
        loadAssets();

        // Create all permanent Objects
        create();

        // Initialize all permanent Objects
        init();

    }

    /**
     * loadAssets - loads all assets from file into a resource pool for later use and ease of access.
     */
    public static void loadAssets() {
        Resources resources = new Resources();

        resources.init();
    }

    /**
     * create - Creates all permanent static variables for protection against null pointer exceptions.
     */
    public static void create() {

        // Create Preferences
        preferences = new ConfigData();

        // Create Environment Handler
        environmentsHandler = new EnvironmentsHandler();

        // Create Control Models
        gameControlsModel = new GameControls();
        menuControlsModel = new MenuControls();

        // Create HUD Model Components
        mapOverlay = new MapOverlay();
        playerStatsOverlay = new PlayerStatsOverlay();
        timeKeeperOverlay = new TimeKeeperOverlay();

        // Create Game Environment Models
        hudModel = new HUDModel();
        inventory = new PlayerInventory();

        // Create Menu Environment
        mainMenuEnvironment = new MainMenuEnvironment();
        // Create Game Environment
        gameEnvironment = new GameEnvironment();

        // Create Pause Menu for Game Environment
        pauseMenuModel = new PauseMenuEnvironment();

        // Create Levels List Model
        levelsListModel = new LevelsList();

        // Create State Canvases
        mainMenuCanvas = new MenuCanvas();
        gameCanvas = new GameCanvas();

        gameUpdateRunnable = new UpdateRunnable();
        gameRenderRunnable = new RenderRunnable();
        menuUpdateRunnable = new UpdateRunnable();
        menuRenderRunnable = new RenderRunnable();

        // Create Window
        window = new MainWindow();

    }

    /**
     * init - initializes all variables with their necessary reference objects.
     * Assists in MVC cross-object referencing.
     */
    public static void init() {

        // Initialize Preferences
        PreferencesParser preferencesParser =
                new PreferencesParser(preferences, "files/", "Preferences", ".xml");
        preferencesParser.read();
        System.out.println(preferences.toString());

        // Initialize Window with Preference Data
        window.init(preferences, environmentsHandler);

        // Initialize Control Models
        gameControlsModel.init(new GameMouseControls(gameControlsModel), new GameKeyControls(gameControlsModel));
        menuControlsModel.init(new MenuMouseControls(menuControlsModel), new MenuKeyControls(menuControlsModel));

        // Initialize Levels
        int defaultLevel = 0;
        levelsListModel.init(gameEnvironment, defaultLevel);

        // Initialize AEnvironment Model Container
        environmentsHandler.init(window);

        // Initialize Pause Menu
        pauseMenuModel.init(environmentsHandler, menuControlsModel);
        pauseMenuModel.setGameEnvironment(gameEnvironment);

        hudModel.init(gameEnvironment, inventory, mapOverlay, playerStatsOverlay, timeKeeperOverlay);

        // Initialize AEnvironment Models
        mainMenuEnvironment.init(environmentsHandler, menuControlsModel);
        gameEnvironment.init(environmentsHandler,
                pauseMenuModel, gameControlsModel,
                levelsListModel, hudModel, inventory);

        // Initialize Canvases
        mainMenuCanvas.init(mainMenuEnvironment);
        gameCanvas.init(gameEnvironment);

        gameUpdateRunnable.init(gameEnvironment);
        gameRenderRunnable.init(gameCanvas);
        menuUpdateRunnable.init(mainMenuEnvironment);
        menuRenderRunnable.init(mainMenuCanvas);

        // Initialize Environments Model with both Main Menu, Pause Game and Game AEnvironments
        environmentsHandler.addEnvironmentPair(
                mainMenuEnvironment, mainMenuCanvas, menuUpdateRunnable, menuRenderRunnable);
        environmentsHandler.addEnvironmentPair(gameEnvironment, gameCanvas, gameUpdateRunnable, gameRenderRunnable);
        environmentsHandler.addEnvironmentPair(pauseMenuModel, gameCanvas, gameUpdateRunnable, gameRenderRunnable);
        environmentsHandler.setCurrentEnvironmentType(EnvironmentsHandler.EnvironmentType.MAIN_MENU);
        environmentsHandler.getCurrentEnvironment().onResume();

        // Initialize Window's Environment
        environmentsHandler.applyEnvironment();

        // Confirm and Apply scaling
        preferences.post();

    }

}
