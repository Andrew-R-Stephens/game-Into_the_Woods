package main;

import graphics.ui.game.GameCanvas;
import graphics.ui.menu.MenuCanvas;
import models.controls.ControlsModel;
import models.data.PreferenceData;
import models.environments.game.GameModel;
import props.objects.levels.LevelList;
import models.environments.menus.MenuModel;
import utils.files.PreferencesXMLParser;

/**
 * The type main.Main.
 */
public class Main {

    private static PreferenceData preferences;

    private static ControlsModel controlsModel;

    private static MenuModel menuModel;
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

        // Create Objects
        create();

        // Initialize Objects
        init();

    }

    public static void create() {

        // Create Preferences
        preferences = new PreferenceData();

        // Create Models
        controlsModel = new ControlsModel();

        levelsModel = new LevelList(); // List of Levels

        menuModel = new MenuModel();
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
        PreferencesXMLParser preferencesParser =
                new PreferencesXMLParser(preferences, "files/", "Preferences", ".xml");
        preferencesParser.read();

        controlsModel.init();

        gameModel.init(controlsModel, levelsModel);

        gameCanvas.init(gameModel);
        menuCanvas.init(menuModel);

        window.init(preferences, controlsModel);
        window.addEnvironmentWithCanvas(gameModel, gameCanvas);
        window.addEnvironmentWithCanvas(menuModel, menuCanvas);
        window.initEnvironmentAndCanvas(0);

        preferences.post();

    }

}
