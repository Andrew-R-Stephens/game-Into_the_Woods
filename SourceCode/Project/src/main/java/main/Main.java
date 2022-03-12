package main;

import data.PreferenceData;
import graphics.ui.game.GameCanvas;
import graphics.ui.game.GameWindow;
import utils.files.PreferencesXMLParser;
import viewmodels.controls.ControlsModel;
import viewmodels.game.GameModel;
import viewmodels.game.LevelModel;
import viewmodels.game.levels.TestLevel2;

/**
 * The type main.Main.
 */
public class Main {

    private static PreferenceData preferences;

    private static ControlsModel controlsViewModel;

    private static LevelModel levelModel;
    private static GameModel gameViewModel;

    private static GameCanvas gameCanvas;
    private static GameWindow gameWindow;

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

        controlsViewModel = new ControlsModel();
        gameViewModel = new GameModel();

        levelModel = new LevelModel();

        preferences = new PreferenceData();

        gameCanvas = new GameCanvas();
        gameWindow = new GameWindow();

    }

    /**
     * Init game objects.
     */
    public static void init() {

        levelModel.setLevel(new TestLevel2());

        gameViewModel.init(controlsViewModel, levelModel);

        controlsViewModel.init();

        PreferencesXMLParser preferencesXMLParser =
                new PreferencesXMLParser(preferences, "files/", "Preferences", ".xml");
        preferencesXMLParser.read();

        gameCanvas.init(gameViewModel);
        gameWindow.init(preferences, gameCanvas, controlsViewModel);

        preferences.post();

    }

}
