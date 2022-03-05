package props.levelactors;

import data.PreferenceData;
import proptypes.actors.levelactors.animated.ALevelProp;
import proptypes.types.actor.AActor;

import java.awt.*;

public class TestLevelProp extends ALevelProp {

    Color c = Color.GREEN;

    public TestLevelProp(float x, float y, float w, float h, float vx, float vy, boolean hasGravity, float mass) {
        super(x, y, w, h, vx, vy, hasGravity, mass);
    }

    @Override
    public boolean hasCollision(AActor a) {
        if(super.hasCollision(a)) {
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

        double sW = PreferenceData.scaledW, sH = PreferenceData.scaledH;

        g.setColor(c);
        g.drawRect((int)(x*sW), (int)(y*sH), (int)(w*sW), (int)(h*sH));
        g.drawString("TLP", (int)(x*sW) + 3, (int)(y*sH) + 12);
    }
}