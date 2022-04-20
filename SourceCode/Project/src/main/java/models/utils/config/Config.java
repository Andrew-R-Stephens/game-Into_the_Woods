package models.utils.config;

import models.camera.Camera;

import java.awt.*;

/**
 * <p></p>
 */
public class Config {

    private static DisplayInfo displayInfo;

    private static WindowType window_type = WindowType.WINDOWED;

    public static int DEFAULT_WINDOW_WIDTH = 1920, DEFAULT_WINDOW_HEIGHT = 1080;
    public static int window_width_selected = DEFAULT_WINDOW_WIDTH, window_height_selected = DEFAULT_WINDOW_HEIGHT;
    public static int window_width_actual = DEFAULT_WINDOW_WIDTH, window_height_actual = DEFAULT_WINDOW_HEIGHT;

    public static float scaledW = 1f, scaledH = 1f;
    public static float scaledW_zoom = 1f, scaledH_zoom = 1f;

    public static short GAME_UPDATE_RATE = 60;
    public static short FRAME_RATE_DEFAULT = 60;
    public static short frameRate = 60;

    /**
     * <p></p>
     */
    public static void calcResolutionScale() {
        scaledW = (float)window_width_actual / (float) DEFAULT_WINDOW_WIDTH;
        scaledH = (float)window_height_actual / (float) DEFAULT_WINDOW_HEIGHT;

        scaledW_zoom = scaledW * Camera.zoomLevel;
        scaledH_zoom = scaledH * Camera.zoomLevel;
    }

    /**
     * <p></p>
     * @param width -
     */
    public void setWindowWidthSelected(int width) {
        window_width_selected = width;
    }

    /**
     * <p></p>
     * @return
     */
    public static int getWindowWidthSelected() {
        return window_width_selected;
    }

    /**
     * <p></p>
     * @param height -
     */
    public void setWindowHeightSelected(int height) {
        window_height_selected = height;
    }

    /**
     * <p></p>
     * @return
     */
    public static int getWindowHeightSelected() {
        return window_height_selected;
    }

    /**
     * <p></p>
     * @param width -
     */
    public static void setWindowWidthActual(int width) {
        window_width_actual = width;
    }

    /**
     * <p></p>
     * @param height -
     */
    public static void setWindowHeightActual(int height) {
        window_height_actual = height;
    }

    /**
     * <p></p>
     * @param width -
     */
    public void setWindowWidthDefault(int width) {
        Config.DEFAULT_WINDOW_WIDTH = width;
    }

    /**
     * <p></p>
     * @param height -
     */
    public void setWindowHeightDefault(int height) {
        Config.DEFAULT_WINDOW_HEIGHT = height;
    }

    /**
     * <p></p>
     * @param type -
     */
    public static void setWindowType(int type) {
        window_type = WindowType.values()[type];
    }

    /**
     * <p></p>
     * @param type -
     */
    public static void setWindowType(WindowType type) {
        window_type = type;
    }

    /**
     * <p></p>
     * @return
     */
    public static WindowType getWindowType() {
        return window_type;
    }

    /**
     * <p></p>
     * @param updateRate -
     */
    public void setGameUpdateRate(short updateRate) {
        GAME_UPDATE_RATE = updateRate;
    }

    /**
     * <p></p>
     * @param frameRate -
     */
    public void setFrameRate(short frameRate) {
        Config.frameRate = frameRate;
    }

    /**
     * <p></p>
     * @return
     */
    public short getFrameRate() {
        return frameRate;
    }

    public void setFrameRateDefault(short framerateDefault) {
        FRAME_RATE_DEFAULT = framerateDefault;
    }

    /**
     * <p></p>
     */
    public void post() {
        calcResolutionScale();
    }

    /**
     * <p></p>
     * @return
     */
    public String toString() {
        return window_width_selected + " " + window_height_selected + " " + window_type.name;
    }

    /**
     * <p></p>
     */
    public enum WindowType {
        WINDOWED                (0, "Windowed"),
        WINDOWED_BORDERLESS     (1, "Windowed Borderless"),
        WINDOWED_FULLSCREEN     (2, "Fullscreen Windowed"),
        FULLSCREEN_EXCLUSIVE    (3, "Fullscreen Exclusive");

        private final int type;
        private final String name;

        /**
         * <p></p>
         * @param type
         * @param name
         */
        WindowType(int type, String name){
            this.type = type;
            this.name = name;
        }

        /**
         * <p></p>
         * @return
         */
        public String getName() {
            return name;
        }

    }

}
