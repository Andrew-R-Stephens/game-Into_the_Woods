package models.camera;

import models.utils.config.Config;

/**
 * <p></p>
 */
public class Camera {

    public static final float DEFAULT_ZOOM_LEVEL = 1f;
    public static float zoomLevel = DEFAULT_ZOOM_LEVEL;
    public static float zoomTarget = DEFAULT_ZOOM_LEVEL;

    public static float targX = 0, targY = 0;
    public static float camX = 0, camY = 0;
    public static float mapX = 0, mapY = 0;

    public static float DEFAULT_ZOOM_ACCELERATION = .001f;
    public static float DEFAULT_PAN_ACCELERATION = .05f;
    public static float acceleration_pan = DEFAULT_PAN_ACCELERATION;
    public static float acceleration_zoom = DEFAULT_ZOOM_ACCELERATION;

    /**
     * <p></p>
     */
    public Camera() {
        reset();
    }

    /**
     * <p></p>
     * @param x2 -
     * @param y2 -
     */
    public static void moveTo(float x2, float y2) {
        mapX = x2;
        mapY = y2;

        targX = x2 * zoomLevel;
        targY = y2 * zoomLevel;

        // Casting these to int rounds the translation, which helps mitigate render misalignment issues
        camX += (((x2 * zoomLevel) - (camX * zoomLevel)) * acceleration_pan);
        camY += (((y2 * zoomLevel) - (camY * zoomLevel)) * acceleration_pan);

        zoomTo();
    }

    /**
     * <p></p>
     */
    public static void zoomTo() {
        float t = zoomLevel/zoomTarget;

        zoomLevel += acceleration_zoom * zoomTarget * (t * t * (3.0f - 2.0f * t));

        Config.calcResolutionScale();

        zoomTarget = DEFAULT_ZOOM_LEVEL;
        acceleration_zoom = DEFAULT_ZOOM_ACCELERATION;
        acceleration_pan = DEFAULT_PAN_ACCELERATION;
    }

    /**
     * <p></p>
     */
    public static void reset() {
        targX = Config.window_width_actual * .5f;
        targY = Config.window_height_actual * .5f;

        camX = Config.window_width_actual * .5f;
        camY = Config.window_height_actual * .5f;
    }
}