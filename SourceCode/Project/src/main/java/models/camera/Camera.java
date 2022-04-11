package models.camera;

import models.utils.config.ConfigData;

public class Camera {

    public static float zoomLevel = 1.0f;
    public static float targX = 0, targY = 0;
    public static float camX = 0, camY = 0;

    public static double acceleration = .05;

    public Camera() {
        targX = ConfigData.window_width_actual * .5f;
        targY = ConfigData.window_height_actual * .5f;

        camX = ConfigData.window_width_actual * .5f;
        camY = ConfigData.window_height_actual * .5f;
    }

    public static void moveTo(float x2, float y2) {
        targX = x2;
        targY = y2;

        // Casting these to int rounds the translation, which helps mitigate render misalignment issues
        camX += ((x2 - camX) * acceleration);
        camY += ((y2 - camY) * acceleration);
    }

    public static void reset() {
        targX = 0;
        targY = 0;
        camX = 0;
        camY = 0;
    }
}