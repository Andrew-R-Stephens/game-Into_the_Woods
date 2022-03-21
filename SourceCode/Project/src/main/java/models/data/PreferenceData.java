package models.data;

/**
 * Preference Data stores the fundamental information of user-defined preferences.
 */
public class PreferenceData {

    /**
     * The enum Window type.
     */
    public enum WindowType {
        /**
         * Windowed window type.
         */
        WINDOWED                (0, "Windowed"),
        /**
         * The Windowed borderless.
         */
        WINDOWED_BORDERLESS     (1, "Windowed Borderless"),
        /**
         * The Windowed fullscreen.
         */
        WINDOWED_FULLSCREEN     (2, "Windowed Fullscreen"),
        /**
         * Fullscreen exclusive window type.
         */
        FULLSCREEN_EXCLUSIVE    (3, "Fullscreen");

        private int type;
        private String name;

        WindowType(int type, String name){
            this.type = type;
            this.name = name;
        }
    }

    private static WindowType window_type = WindowType.WINDOWED;

    /**
     * The constant DEFAULT_WINDOW_WIDTH.
     */
    public static int DEFAULT_WINDOW_WIDTH = 1920, /**
     * The Default window height.
     */
    DEFAULT_WINDOW_HEIGHT = 1080;
    /**
     * The constant window_width_selected.
     */
    public static int window_width_selected = 0, /**
     * The Window height selected.
     */
    window_height_selected = 0;
    /**
     * The constant window_width_actual.
     */
    public static int window_width_actual = 0, /**
     * The Window height actual.
     */
    window_height_actual = 0;

    /**
     * The constant scaledW.
     */
    public static float scaledW = 1f, /**
     * The Scaled h.
     */
    scaledH = 1f;


    /**
     * The constant GAME_UPDATE_RATE.
     */
    public static short GAME_UPDATE_RATE = 60;
    /**
     * The constant FRAME_RATE_DEFAULT.
     */
    public static short FRAME_RATE_DEFAULT = 60;
    /**
     * The constant frameRate.
     */
    public static short frameRate = 60;


    /**
     * Calc resolution scale.
     */
    public void calcResolutionScale() {
        scaledW = (float)window_width_actual / (float) DEFAULT_WINDOW_WIDTH;
        scaledH = (float)window_height_actual / (float) DEFAULT_WINDOW_HEIGHT;
    }

    /**
     * Sets window width selected.
     *
     * @param width the width
     */
    public void setWindowWidthSelected(int width) {
        window_width_selected = width;
    }

    /**
     * Gets window width selected.
     *
     * @return the window width selected
     */
    public int getWindowWidthSelected() {
        return window_width_selected;
    }

    /**
     * Sets window height selected.
     *
     * @param height the height
     */
    public void setWindowHeightSelected(int height) {
        window_height_selected = height;
    }

    /**
     * Gets window height selected.
     *
     * @return the window height selected
     */
    public int getWindowHeightSelected() {
        return window_height_selected;
    }

    /**
     * Sets window width actual.
     *
     * @param width the width
     */
    public void setWindowWidthActual(int width) {
        window_width_actual = width;
    }

    /**
     * Sets window height actual.
     *
     * @param height the height
     */
    public void setWindowHeightActual(int height) {
        window_height_actual = height;
    }

    /**
     * Sets window width default.
     *
     * @param width the width
     */
    public void setWindowWidthDefault(int width) {
        PreferenceData.DEFAULT_WINDOW_WIDTH = width;
    }

    /**
     * Sets window height default.
     *
     * @param height the height
     */
    public void setWindowHeightDefault(int height) {
        PreferenceData.DEFAULT_WINDOW_HEIGHT = height;
    }

    /**
     * Sets window type.
     *
     * @param type the type
     */
    public void setWindowType(int type) {
        window_type = WindowType.values()[type];
    }

    /**
     * Gets window type.
     *
     * @return the window type
     */
    public WindowType getWindowType() {
        return window_type;
    }

    /**
     * Sets game update rate.
     *
     * @param updateRate the update rate
     */
    public void setGameUpdateRate(short updateRate) {
        GAME_UPDATE_RATE = updateRate;
    }

    /**
     * Sets frame rate.
     *
     * @param frameRate the frame rate
     */
    public void setFrameRate(short frameRate) {
        PreferenceData.frameRate = frameRate;
    }

    /**
     * Gets frame rate.
     *
     * @return the frame rate
     */
    public short getFrameRate() {
        return frameRate;
    }

    /**
     * Sets frame rate default.
     *
     * @param framerateDefault the framerate default
     */
    public void setFrameRateDefault(short framerateDefault) {
        FRAME_RATE_DEFAULT = framerateDefault;
    }

    /**
     * Post.
     */
    public void post() {
        calcResolutionScale();
    }

    public String toString() {
        return window_width_selected + " " + window_height_selected + " " + window_type.name;
    }

}
