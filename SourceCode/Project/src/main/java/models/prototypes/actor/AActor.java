package models.prototypes.actor;

import models.camera.Camera;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.physics.APhysics;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;

/**
 * <p>The AActor is the most basic form of entity in the GameEnvironment. Contains a reference to the Resources.
 * Acts as a wrapper, but also contains some data that allows for the appropriate orientations of subtypes.</p>
 * @author Andrew Stephens
 */
public abstract class AActor extends APhysics implements IUpdatable {

    /**<p>The persistent Resources.</p>*/
    protected Resources resources;

    /**<p>The current Facing direction.</p>*/
    protected Facing facing;

    /**<p>The enums meant for setting the facing direction.</p>*/
    protected enum Facing { LEFT, RIGHT, UP, DOWN }

    /**<p>The current simple color of the actor's hitbox.</p>*/
    protected Color color = new Color(0, 0, 255, 50);

    private boolean canRender = false;

    /**
     * <p>Called from the subtypes, this method initializes the object with position and size relative to the
     * default dimensions.</p>
     * @param resources The resources of the parent Environment
     * @param x The horizontal position, relative to the default dimensions.
     * @param y The y position, relative to the default dimensions.
     * @param w The width, relative to the default dimensions.
     * @param h The height, relative to the default dimensions.
     * @param vx The horizontal velocity.
     * @param vy The vertical velocity.
     * @param hasGravity If the object should be effected by gravity.
     */
    protected AActor(
            Resources resources,
            float x, float y,
            float w, float h,
            float vx, float vy,
            boolean hasGravity) {

        super(x, y, w, h, vx, vy, hasGravity);

        this.resources = resources;
    }

    protected AActor(
            float x, float y,
            float w, float h,
            float vx, float vy,
            boolean hasGravity) {

        super(x, y, w, h, vx, vy, hasGravity);
    }

    public Resources getResources() {
        return resources;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.RED);

        float offsetX = ((x * Config.scaledW) + (Camera.targX));
        float offsetY = ((y * Config.scaledH) + (Camera.targY));

        float scaledW = w * Config.scaledW;
        float scaledH = h * Config.scaledH;

        g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
    }

    public void drawAsHUD(Graphics2D g) {  }

    public void setCanRender(boolean canRender) {
        this.canRender = canRender;
    }

    public boolean canRender() {
        return canRender;
    }
}
