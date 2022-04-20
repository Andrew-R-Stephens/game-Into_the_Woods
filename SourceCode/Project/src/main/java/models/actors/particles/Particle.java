package models.actors.particles;

import models.prototypes.actor.pawn.APawn;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;

/**
 * <p></p>
 */
public class Particle extends APawn implements IDrawable, IUpdatable {

    /**
     * <p></p>
     * @param resources -
     * @param x -
     * @param y -
     * @param w -
     * @param h -
     * @param vx -
     * @param vy -
     * @param hasGravity -
     */
    public Particle(Resources resources, float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(resources, x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
    }

}
