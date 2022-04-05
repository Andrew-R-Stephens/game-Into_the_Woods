package prototypes.actor;

import models.camera.Camera;
import utils.config.ConfigData;
import utils.drawables.IDrawable;
import utils.drawables.IHUDDrawable;
import utils.physics.APhysics;

import java.awt.*;


/**
 * TODO: Add description
 */
public abstract class AActor extends APhysics implements IDrawable {

    protected Facing facing;
    public enum Facing { LEFT, RIGHT, UP, DOWN }

    /**
     * The C.
     */
    protected Color c = Color.GREEN;

    /**
     * Instantiates a new A actor.
     *
     * @param x          the x
     * @param y          the y
     * @param w          the w
     * @param h          the h
     * @param vx         the vx
     * @param vy         the vy
     * @param hasGravity the has gravity
     */
    protected AActor(float x, float y,
                     float w, float h,
                     float vx, float vy,
                     boolean hasGravity) {

        super(x, y, w, h, vx, vy, hasGravity);

    }

    @Override
    protected void update(float delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.CYAN);

        double offsetX = ((x * ConfigData.scaledW) + (Camera.x));
        double offsetY = ((y * ConfigData.scaledH) + (Camera.y));

        double scaledW = w * ConfigData.scaledW;
        double scaledH = h * ConfigData.scaledH;

        g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
    }
}
