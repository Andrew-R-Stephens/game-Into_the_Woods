package props.objects.gameactors;

import prototypes.actor.pawn.APawn;

import java.awt.*;

/**
 * TODO: Add description
 */
public class TestActor extends APawn {

    /**
     * Instantiates a new Test actor.
     *
     * @param x          the x
     * @param y          the y
     * @param w          the w
     * @param h          the h
     * @param vx         the vx
     * @param vy         the vy
     * @param hasGravity the has gravity
     */
    public TestActor(float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

}
