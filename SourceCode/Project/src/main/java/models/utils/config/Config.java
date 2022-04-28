package models.utils.config;

import main.Main;
import models.camera.Camera;

import java.io.File;
import java.net.URISyntaxException;

/**
 * <p>The Configuration class deals with essential system data.</p>
 * <p>Heavily used for scaling of all rendered elements, managing render and update rates, and determining window
 * types.</p>
 * @author Andrew Stephens
 */
public class Config {

    /**<p>The name of the Operating System.</p>*/
    private static String opSysName;
    /**<p>The system path of the jar.</p>*/
    public static String jarPath;

    /**<p>The DisplayInfo of the system</p>*/
    private final static DisplayInfo displayInfo = new DisplayInfo();

    /**<p>The type of frame for this window.</p>*/
    private static WindowType window_type = WindowType.WINDOWED;

    /**<p>The default window dimension that all render scales compare to.</p>*/
    public static int DEFAULT_WINDOW_WIDTH = 1920, DEFAULT_WINDOW_HEIGHT = 1080;
    /**<p>The user requested window width.</p>*/
    public static int window_width_selected = DEFAULT_WINDOW_WIDTH, window_height_selected = DEFAULT_WINDOW_HEIGHT;
    /**<p>The actual window width. Is restricted by the screen dimensions.</p>*/
    public static int window_width_actual = DEFAULT_WINDOW_WIDTH, window_height_actual = DEFAULT_WINDOW_HEIGHT;

    /**<p>The scaled dimensions of the window against the DEFAULT_WINDOW dimensions</p>*/
    public static float scaledW = 1f, scaledH = 1f;
    /**<p>The scaled dimensions, with camera zoom considered, compared against the DEFAULT_WINDOW dimensions.</p>*/
    public static float scaledW_zoom = 1f, scaledH_zoom = 1f;

    /**<p>The default tick rate of the environments.</p>*/
    public static short GAME_UPDATE_RATE = 60;
    /**<p>The default target framerate for the renders.</p>*/
    public static short FRAME_RATE_DEFAULT = 60;
    /**<p>The actual framerate for the renders.</p>*/
    public static short frameRate = 60;

    /**
     * <p>Looks at the system configurations for operating system type and finds the JAR path.</p>
     */
    public static void registerSystemInfo() {
        opSysName = "Operating System: " + System.getProperty("os.name");

        try {
            jarPath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Called after the system obtains the current Window. Records the scale factor of the current window size
     * against the default window size. Allows for accurate scaled renders of the environment's elements.</p>
     */
    public static void calcResolutionScale() {
        scaledW = (float)window_width_actual / (float) DEFAULT_WINDOW_WIDTH;
        scaledH = (float)window_height_actual / (float) DEFAULT_WINDOW_HEIGHT;

        scaledW_zoom = scaledW * Camera.zoomLevel;
        scaledH_zoom = scaledH * Camera.zoomLevel;
    }

    public static DisplayInfo getDisplayData() {
        return displayInfo;
    }

    /**
     * <p>The desired dimensions of the frame size. Used against the default dimensions to obtain proper scaling.</p>
     * <p>This may become the actual dimension, unless there are conflicts.</p>
     * @param width The desired window width.
     */
    public void setWindowWidthSelected(int width) {
        window_width_selected = width;
    }

    /**
     * <p>The desired dimensions of the frame size. Used against the default dimensions to obtain proper scaling.</p>
     * <p>This may become the actual dimension, unless there are conflicts.</p>
     * @return The desired window width.
     */
    public static int getWindowWidthSelected() {
        return window_width_selected;
    }

    /**
     * <p>The desired dimensions of the frame size. Used against the default dimensions to obtain proper scaling.</p>
     * <p>This may become the actual dimension, unless there are conflicts.</p>
     * @param height The desired window height.
     */
    public void setWindowHeightSelected(int height) {
        window_height_selected = height;
    }

    /**
     * <p>The desired dimensions of the frame size. Used against the default dimensions to obtain proper scaling.</p>
     * <p>This may become the actual dimension, unless there are conflicts.</p>
     * @return The desired window height.
     */
    public static int getWindowHeightSelected() {
        return window_height_selected;
    }

    /**
     * <p>The actual dimensions of the frame size. Used against the default dimensions to obtain proper scaling.</p>
     * @param width The actual window width.
     */
    public static void setWindowWidthActual(int width) {
        window_width_actual = width;
    }

    /**
     * <p>The actual dimensions of the frame size. Used against the default dimensions to obtain proper scaling.</p>
     * @param height The actual window height.
     */
    public static void setWindowHeightActual(int height) {
        window_height_actual = height;
    }

    /**
     * <p>The default dimensions standardize the scale factor between default and user-configured frame size.</p>
     * @param width The default window width.
     */
    public void setWindowWidthDefault(int width) {
        Config.DEFAULT_WINDOW_WIDTH = width;
    }

    /**
     * <p>The default dimensions standardize the scale factor between default and user-configured frame size.</p>
     * @param height The default window height.
     */
    public void setWindowHeightDefault(int height) {
        Config.DEFAULT_WINDOW_HEIGHT = height;
    }

    /**
     * <p>Accepts the desired WindowType ordinal. This will be used by the AWindow for designing the style of frame
     * requested.</p>
     * @param type The WindowType ordinal value, set by the config.
     */
    public static void setWindowType(int type) {
        window_type = WindowType.values()[type];
    }

    /**
     * <p>Accepts the desired WindowType. This will be used by the AWindow for designing the style of frame requested.</p>
     * @param type The WindowType value set by the config or by the user.
     */
    public static void setWindowType(WindowType type) {
        window_type = type;
    }

    /**
     * <p>This will be used by the AWindow uses WindowType for designing the style of frame requested.</p>
     * @return - the WindowType set by the config or by the user.
     */
    public static WindowType getWindowType() {
        return window_type;
    }

    /**
     * <p>Used to standardize variations in tickrate, where the ratio gets shared among all updatable entities to
     * normalize movement.</p>
     * @param updateRate The target update rate for all environments.
     */
    public void setGameUpdateRate(short updateRate) {
        GAME_UPDATE_RATE = updateRate;
    }

    /**
     * <p>This is the framerate that's controlled by the config and has the option to be changed by the user.</p>
     * @param frameRate The target frame rate for all canvases.
     */
    public void setFrameRate(short frameRate) {
        Config.frameRate = frameRate;
    }

    /**
     * <p>The standardized rate of draw calls set by the config file. This will be the target framerate unless there's a
     * higher limit set by the default display or if the user changes their preferences.</p>
     * @param framerateDefault The default frame rate target.
     */
    public void setFrameRateDefault(short framerateDefault) {
        FRAME_RATE_DEFAULT = framerateDefault;
    }

    /**
     * <p>The wrapper method used to calibrate render scaling during first-time setup.</p>
     */
    public void post() {
        calcResolutionScale();
    }

    /**
     * <p>The Window Types available to the user. The definitions of these are defined in the AWindow class.</p>
     */
    public enum WindowType {
        WINDOWED                (0, "Windowed"),
        WINDOWED_BORDERLESS     (1, "Windowed Borderless"),
        WINDOWED_FULLSCREEN     (2, "Fullscreen Windowed"),
        FULLSCREEN_EXCLUSIVE    (3, "Fullscreen Exclusive");

        private final int type;
        private final String name;

        /**
         * <p>The WindowType constructor.</p>
         * @param type The ordinal of this WindowType, set during the build first time setup.
         * @param name The displayable name of this WindowType.
         */
        WindowType(int type, String name){
            this.type = type;
            this.name = name;
        }

        /**
         * <p>Gets the name of the WindowType</p>
         * @return the WindowType name which is automatically defined by the enum constructor
         */
        public String getName() {
            return name;
        }

    }

}
