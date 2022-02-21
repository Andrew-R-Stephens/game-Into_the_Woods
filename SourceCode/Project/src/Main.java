import files.PreferenceData;
import graphics.ui.GameCanvas;
import graphics.ui.GameWindow;
import utils.PreferencesXMLParser;
import viewmodels.game.GameViewModel;

/**
 * The type Main.
 */
public class Main {

    private static PreferenceData preferences;

    private static GameViewModel gameViewModel;

    private static GameCanvas gameCanvas = new GameCanvas();
    private static GameWindow gameWindow = new GameWindow();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        create();

        // Initialize Game Models
        initViewModels();

        // Initialize Preferences
        initPreferenceFiles();

        // Initialize UI
        initWindow();

    }

    public static void create() {

        gameViewModel = new GameViewModel();
        preferences = new PreferenceData();

        gameCanvas = new GameCanvas();
        gameWindow = new GameWindow();
    }

    /**
     * Init game objects.
     */
    public static void initViewModels() {
        gameViewModel.init(preferences);
    }

    /**
     * Init preference files.
     */
    public static void initPreferenceFiles() {
        PreferencesXMLParser preferencesXMLParser = new PreferencesXMLParser(preferences, "Preferences.xml");
        preferencesXMLParser.read();

        preferences.post();
    }



    public static void initWindow() {
        gameCanvas.init(gameViewModel);
        gameWindow.init(preferences, gameCanvas, gameViewModel);
    }



}
