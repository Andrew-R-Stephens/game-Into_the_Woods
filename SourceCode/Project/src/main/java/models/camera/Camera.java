package models.camera;

import models.utils.config.ConfigData;

public class Camera {

    public static final float DEFAULT_ZOOM_LEVEL = 1f;
    public static float zoomLevel = DEFAULT_ZOOM_LEVEL;
    public static float zoomTarget = DEFAULT_ZOOM_LEVEL;

    public static float targX = 0, targY = 0;
    public static float camX = 0, camY = 0;

    public static float acceleration_pan = .05f;
    public static float acceleration_zoom = .001f;

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

        zoomTo();
    }

    public static void zoomTo() {
        float t = zoomLevel/zoomTarget;

        zoomLevel += acceleration_zoom * zoomTarget * (t * t * (3.0f - 2.0f * t));

        ConfigData.calcResolutionScale();

        zoomTarget = DEFAULT_ZOOM_LEVEL;
    }

    public static void reset() {
        targX = 0;
        targY = 0;
        camX = 0;
        camY = 0;
    }
}