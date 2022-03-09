package props.levelactors;

import proptypes.actors.levelactors.animated.ALevelProp;
import proptypes.types.actor.AActor;

import java.awt.*;

/**
 * TODO: Add description
 */
public class TestLevelProp extends ALevelProp {

    public TestLevelProp(float x, float y, float w, float h, float vx, float vy, boolean hasGravity, float mass) {
        super(x, y, w, h, vx, vy, hasGravity, mass);
    }

    @Override
    public boolean hasCollision(AActor a) {
        if (super.hasCollision(a)) {
            c = Color.RED;
            return true;
        }
        c = Color.BLUE;
        return false;
    }

    @Override
    public void update(double delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}