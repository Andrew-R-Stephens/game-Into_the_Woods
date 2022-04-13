package models.utils.config;

import models.camera.Camera;

public class ConfigData {

    public enum WindowType {
        WINDOWED                (0, "Windowed"),
        WINDOWED_BORDERLESS     (1, "Windowed Borderless"),
        WINDOWED_FULLSCREEN     (2, "Windowed Fullscreen"),
        FULLSCREEN_EXCLUSIVE    (3, "Fullscreen");

        private final int type;
        private final String name;

        WindowType(int type, String name){
            this.type = type;
            this.name = name;
        }
    }

    private static WindowType window_type = WindowType.WINDOWED;

    public static int DEFAULT_WINDOW_WIDTH = 1920, DEFAULT_WINDOW_HEIGHT = 1080;
    public static int window_width_selected = 0, window_height_selected = 0;
    public static int window_width_actual = 0, window_height_actual = 0;

    public static float scaledW = 1f, scaledH = 1f;
    public static float scaledW_zoom = 1f, scaledH_zoom = 1f;

    public static short GAME_UPDATE_RATE = 60;
    public static short FRAME_RATE_DEFAULT = 60;
    public static short frameRate = 60;

    public static void calcResolutionScale() {
        scaledW = (float)window_width_actual / (float) DEFAULT_WINDOW_WIDTH;
        scaledH = (float)window_height_actual / (float) DEFAULT_WINDOW_HEIGHT;

        scaledW_zoom = scaledW * Camera.zoomLevel;
        scaledH_zoom = scaledH * Camera.zoomLevel;
    }

    public void setWindowWidthSelected(int width) {
        window_width_selected = width;
    }

    public int getWindowWidthSelected() {
        return window_width_selected;
    }

    public void setWindowHeightSelected(int height) {
        window_height_selected = height;
    }

    public int getWindowHeightSelected() {
        return window_height_selected;
    }

    public void setWindowWidthActual(int width) {
        window_width_actual = width;
    }

    public void setWindowHeightActual(int height) {
        window_height_actual = height;
    }

    public void setWindowWidthDefault(int width) {
        ConfigData.DEFAULT_WINDOW_WIDTH = width;
    }

    public void setWindowHeightDefault(int height) {
        ConfigData.DEFAULT_WINDOW_HEIGHT = height;
    }

    public void setWindowType(int type) {
        window_type = WindowType.values()[type];
    }

    public WindowType getWindowType() {
        return window_type;
    }

    public void setGameUpdateRate(short updateRate) {
        GAME_UPDATE_RATE = updateRate;
    }

    public void setFrameRate(short frameRate) {
        ConfigData.frameRate = frameRate;
    }

    public short getFrameRate() {
        return frameRate;
    }

    public void setFrameRateDefault(short framerateDefault) {
        FRAME_RATE_DEFAULT = framerateDefault;
    }

    public void post() {
        calcResolutionScale();
    }

    public String toString() {
        return window_width_selected + " " + window_height_selected + " " + window_type.name;
    }

}
