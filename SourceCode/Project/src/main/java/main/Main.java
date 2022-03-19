package main;

import graphics.ui.game.GameCanvas;
import graphics.ui.menu.MenuCanvas;
import models.controls.game.GameControlsModel;
import models.controls.game.GameKeyControls;
import models.controls.game.GameMouseControls;
import models.controls.menu.MenuControlsModel;
import models.controls.menu.MenuKeyControls;
import models.controls.menu.MenuMouseControls;
import models.data.PreferenceData;
import models.environments.game.GameModel;
import models.environments.menus.mainmenu.MainMenuModel;
import props.objects.levels.LevelList;
import utils.files.PreferencesXMLParser;

/**
 * The type main.Main.
 */
public class Main {

    private static PreferenceData preferences;

    private static GameControlsModel gameControlsModel;
    private static MenuControlsModel menuControlsModel;

    private static MainMenuModel mainMenuModel;
    private static GameModel gameModel;

    private static LevelList levelsModel;

    private static MenuCanvas menuCanvas;
    private static GameCanvas gameCanvas;

    private static MainWindow window;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        loadAssets();

        // Create Objects
        create();

        // Initialize Objects
        init();

    }

    public static void loadAssets() {
        
    }

    public static void create() {

        // Create Preferences
        preferences = new PreferenceData();

        // Create Models
        gameControlsModel = new GameControlsModel();
        menuControlsModel = new MenuControlsModel();

        // Create Menu Models
        mainMenuModel = new MainMenuModel();
        //menusListModel = new MenusListModel();

        // Create Game Models
        levelsModel = new LevelList();
        gameModel = new GameModel();

        // Create State Canvases
        menuCanvas = new MenuCanvas();
        gameCanvas = new GameCanvas();

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

        // Initialize Models
        gameControlsModel.init(new GameMouseControls(gameControlsModel), new GameKeyControls(gameControlsModel));
        menuControlsModel.init(new MenuMouseControls(menuControlsModel), new MenuKeyControls(menuControlsModel));

        mainMenuModel.init(menuControlsModel);
        //pauseMenuModel.init(menuControlsModel);

        gameModel.init(gameControlsModel, levelsModel);
        //menusListModel.init(mainMenuModel);

        // Initialize Canvases
        menuCanvas.init(mainMenuModel);
        gameCanvas.init(gameModel);

        // Initialize Window
        window.init(preferences);
        window.addEnvironmentWithCanvas(mainMenuModel, menuCanvas);
        window.addEnvironmentWithCanvas(gameModel, gameCanvas);
        window.initEnvironmentAndCanvas(0);

        // Confirm and Apply scaling
        preferences.post();

    }

}
