package props.prototypes.actor.pawn;

import props.prototypes.actor.AActor;
import utils.drawables.IDrawable;

import java.awt.*;

/**
 * TODO: Add description
 */
public abstract class APawn extends AActor implements IDrawable {

    protected APawn(float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    protected void update(float delta) {
        super.update(delta);

        x += vX;
        y += vY;

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

}
