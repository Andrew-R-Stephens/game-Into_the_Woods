package models.actors.particles;

import models.actors.platforms.Platform;
import models.camera.Camera;
import models.prototypes.actor.AActor;
import models.prototypes.actor.pawn.APawn;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;

/**
 * <p>A Particle is a Pawn that accepts an initial velocity and position. It follows its vector and does nothing
 * else.</p>
 * @author Andrew Stephens
 */
public class Particle extends APawn implements IDrawable, IUpdatable {

    /**
     * <p>Called from the subtypes, this method initializes the object.</p>
     * @param resources The resources of the parent Environment
     * @param x The horizontal position, relative to the default dimensions.
     * @param y The y position, relative to the default dimensions.
     * @param w The width, relative to the default dimensions.
     * @param h The height, relative to the default dimensions.
     * @param vx The horizontal velocity.
     * @param vy The vertical velocity.
     * @param hasGravity If the object should be effected by gravity.
     */
    public Particle(Resources resources, float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(resources, x, y, w, h, vx, vy, hasGravity);
    }

    public void doAction() {

    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        if(!(a instanceof Platform)) {
            return false;
        }
        boolean hasCollision = super.hasCollision(a, delta);

        if(hasCollision) {
            doAction();
        }

        return hasCollision;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(100, 10, 10, 150));

        float offsetX = ((x * Config.scaledW_zoom) + (Camera.camX));
        float offsetY = ((y * Config.scaledH_zoom) + (Camera.camY));

        float scaledW = w * Config.scaledW_zoom;
        float scaledH = h * Config.scaledH_zoom;

        g.fillRect((int) (offsetX), (int) (offsetY), (int) (scaledW), (int) (scaledH));
    }
}
