package models.camera;

import models.utils.config.Config;

/**
 * <p>Camera class contains static variables and methods which allow for the manipulation of viewport-based data.</p>
 * <p>Viewport data is used in all Environments where there is position-based renders.</p>
 * <p>In the Game state, the player position will control the camera offset.</p>
 * <p>In the Menu state, the mouse position will control the camera offset.</p>
 * <p>Camera zooms in or out to act to increase or decrease the scaling of in-game objects.</p>
 * @author Andrew Stephens
 */
public class Camera {

    /**<p>The default level of zoom. Where the target resolves to if reset.</p>*/
    public static final float DEFAULT_ZOOM_LEVEL = 1f;
    public static final float SCALE_MINIMAP = .5f;
    /**<p>The current zoom level.</p>*/
    public static float zoomLevel = DEFAULT_ZOOM_LEVEL;
    /**<p>The target zoom level</p>*/
    public static float zoomTarget = DEFAULT_ZOOM_LEVEL;

    /**<p>The target camera coordinates.</p>*/
    public static float targX = 0, targY = 0;
    /**<p>The current chase camera coordinates.</p>*/
    public static float camX = 0, camY = 0;
    /**<p>The current non-chase camera coordinates.</p>*/
    public static float mapX = 0, mapY = 0;

    /**<p>The default acceleration for the camera.</p>*/
    public static float DEFAULT_ZOOM_ACCELERATION = .001f, DEFAULT_PAN_ACCELERATION = .05f;
    /**<p>The current acceleration for the camera.</p>*/
    public static float acceleration_pan = DEFAULT_PAN_ACCELERATION, acceleration_zoom = DEFAULT_ZOOM_ACCELERATION;

    /**
     * <p>Initializes the Camera to the standard position and scale.</p>
     */
    public Camera() {
        reset();
    }

    /**
     * <p>This accepts a default-scale-relative location in the horizontal and vertical axis'. The following global
     * variables manipulate scale and position to reflect new translations.</p>
     * @param x2 The target position for the camera to move to
     * @param y2 The target position for the camera to move to
     */
    public static void moveTo(float x2, float y2) {
        mapX = x2;
        mapY = y2;

        targX = x2 * zoomLevel;
        targY = y2 * zoomLevel;

        // Casting these to int rounds the translation, which helps mitigate render misalignment issues
        camX += ((x2 * zoomLevel) - (camX * zoomLevel)) * acceleration_pan;
        camY += ((y2 * zoomLevel) - (camY * zoomLevel)) * acceleration_pan;
;
        zoomTo();
    }

    /**
     * <p>Acts to zoom the camera to the target zoom level.</p>
     */
    public static void zoomTo() {
        float t = zoomLevel/zoomTarget;

        zoomLevel += acceleration_zoom * zoomTarget * ((t * t) * (3.0f - 2.0f * t));

        Config.calcResolutionScale();

        zoomTarget = DEFAULT_ZOOM_LEVEL;
        acceleration_zoom = DEFAULT_ZOOM_ACCELERATION;
        acceleration_pan = DEFAULT_PAN_ACCELERATION;
    }

    /**
     * <p>Resets the positional data of the camera.</p>
     */
    public static void reset() {
        targX = Config.window_width_actual * .5f;
        targY = Config.window_height_actual * .5f;

        camX = Config.window_width_actual * .5f;
        camY = Config.window_height_actual * .5f;
    }

    public static int[] getRelativeMousePos(int[] mP) {
        int mx = (int)((mP[0] - Camera.camX) / Config.scaledW_zoom);
        int my = (int)((mP[1] - Camera.camY) / Config.scaledH_zoom);
        return new int[]{mx, my};
    }

    public static float[] getRelativeOffset(float x, float y) {
        float offsetX = ((x * Config.scaledW_zoom) + (Camera.camX));
        float offsetY = ((y * Config.scaledH_zoom) + (Camera.camY));
        return new float[]{offsetX, offsetY};
    }

    public static float[] getRelativeOffsetBy(float x, float y, float scale) {
        float offsetX = ((x * Config.scaledW_zoom * scale) + (Camera.camX * scale));
        float offsetY = ((y * Config.scaledH_zoom * scale) + (Camera.camY * scale));
        return new float[]{offsetX, offsetY};
    }

    public static float[] getRelativeScale(float w, float h) {
        float scaledW = w * Config.scaledW_zoom;
        float scaledH = h * Config.scaledH_zoom;
        return new float[]{scaledW, scaledH};
    }

    public static float[] getRelativeScaleBy(float w, float h, float scale) {
        float scaledW = w * Config.scaledW_zoom * scale;
        float scaledH = h * Config.scaledH_zoom * scale;
        return new float[]{scaledW, scaledH};
    }

    private static float[] absoluteToRelative(float x, float y) {
        int mx = (int)((x - Camera.camX) / Config.scaledW_zoom);
        int my = (int)((y - Camera.camY) / Config.scaledH_zoom);
        return new float[]{mx, my};
    }

    public static float[] getViewportRelative(float x, float y) {
        int mx = (int)((x - Camera.camX) / Config.scaledW_zoom);
        int my = (int)((y - Camera.camY) / Config.scaledH_zoom);
        return new float[]{mx, my};
    }

    public static float[] getMapRelative() {
        int mx = (int)((-mapX) / Config.scaledW_zoom);
        int my = (int)((-mapY) / Config.scaledH_zoom);
        return new float[]{mx, my};
    }

    public static float[] getCamRelative() {
        int mx = (int)((-Camera.camX) / Config.scaledW_zoom);
        int my = (int)((-Camera.camY) / Config.scaledH_zoom);
        return new float[]{mx, my};
    }

    public static float[] getTargRelative() {
        int mx = (int)((-targX) / Config.scaledW_zoom);
        int my = (int)((-targY) / Config.scaledH_zoom);
        return new float[]{mx, my};
    }
}