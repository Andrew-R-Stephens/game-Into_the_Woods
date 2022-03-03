package props.levelactors;

import proptypes.actors.levelactors.animated.ALevelProp;

public class TestLevelProp extends ALevelProp {

    public TestLevelProp(float x, float y, float w, float h, float vx, float vy, boolean hasGravity, float mass) {
        super(x, y, w, h, vx, vy, hasGravity, mass);
    }

    @Override
    public void update(double delta) {

    }
}