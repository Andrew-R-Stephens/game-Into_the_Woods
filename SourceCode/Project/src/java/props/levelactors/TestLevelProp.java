package props.levelactors;

import data.PreferenceData;
import proptypes.actors.levelactors.animated.ALevelProp;

import java.awt.*;

public class TestLevelProp extends ALevelProp {

    public TestLevelProp(float x, float y, float w, float h, float vx, float vy, boolean hasGravity, float mass) {
        super(x, y, w, h, vx, vy, hasGravity, mass);
    }

    @Override
    public void update(double delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        double sW = PreferenceData.scaledW, sH = PreferenceData.scaledH;

        g.setColor(Color.GREEN);
        g.drawRect((int)(x*sW), (int)(y*sH), (int)(w*sW), (int)(h*sH));
        g.setColor(Color.PINK);
        g.drawRect((int)(x), (int)(y), (int)(w), (int)(h));
    }
}