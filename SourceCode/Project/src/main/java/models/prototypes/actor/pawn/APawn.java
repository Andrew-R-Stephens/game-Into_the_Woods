package models.prototypes.actor.pawn;

import models.prototypes.actor.AActor;
import models.utils.drawables.IDrawable;

import java.awt.*;

/**
 * The type A pawn.
 */
public abstract class APawn extends AActor implements IDrawable {

    /**
     * Instantiates a new A pawn.
     *
     * @param x          the x
     * @param y          the y
     * @param w          the w
     * @param h          the h
     * @param vx         the vx
     * @param vy         the vy
     * @param hasGravity the has gravity
     */
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
