package props.objects.levelprops;

import props.prototypes.actor.AActor;
import props.prototypes.level.prop.ALevelProp;

import java.awt.*;

/**
 * TODO: Add description
 */
public class TestLevelProp extends ALevelProp {

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