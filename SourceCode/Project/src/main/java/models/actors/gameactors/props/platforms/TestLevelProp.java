package models.actors.gameactors.props.platforms;

import models.prototypes.actor.AActor;
import models.prototypes.level.prop.ALevelProp;

import java.awt.*;

/**
 * TODO: Add description
 */
public class TestLevelProp extends ALevelProp {

    /**
     * Instantiates a new Test level prop.
     *
     * @param x          the x
     * @param y          the y
     * @param w          the w
     * @param h          the h
     * @param vx         the vx
     * @param vy         the vy
     * @param hasGravity the has gravity
     */
    public TestLevelProp(float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        if (super.hasCollision(a, delta)) {
            c = Color.RED;
            return true;
        }
        c = Color.BLUE;
        return false;
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