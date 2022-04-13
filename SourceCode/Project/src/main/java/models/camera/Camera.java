package models.camera;

import models.utils.config.ConfigData;

public class Camera {

    public static float zoomLevel = 1.5f;
    public static float targX = 0, targY = 0;
    public static float camX = 0, camY = 0;

    public static float acceleration_pan = .05f;
    public static float acceleration_zoom = .005f;

    public Camera() {
        targX = ConfigData.window_width_actual * .5f;
        targY = ConfigData.window_height_actual * .5f;

        camX = ConfigData.window_width_actual * .5f;
        camY = ConfigData.window_height_actual * .5f;
    }

    public static void moveTo(float x2, float y2) {
        targX = x2 * zoomLevel;
        targY = y2 * zoomLevel;

        // Casting these to int rounds the translation, which helps mitigate render misalignment issues
        camX += ((x2 - camX) * acceleration_pan);
        camY += ((y2 - camY) * acceleration_pan);
    }

    public static void zoom(float zoomTarget) {
        //zoomLevel += (float)(Math.cos(Math.PI * (zoomLevel)) - zoomTarget-zoomLevel) / 2;
        zoomLevel += ((zoomTarget - zoomLevel) * (acceleration_zoom));

        ConfigData.calcResolutionScale();
    }

    public static void reset() {
        targX = 0;
        targY = 0;
        camX = 0;
        camY = 0;
    }
}