package proptypes.actors.levelactors.animated;

import proptypes.types.actor.AActor;

import java.awt.*;

public class ALevelProp extends AActor {

    protected ALevelProp(float x, float y, float w, float h, float vx, float vy, boolean hasGravity, float mass) {
        super(x, y, w, h, vx, vy, hasGravity, mass);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

}
