package models.actors.gameactors.props.platforms;

import models.utils.updates.IUpdatable;

public class TestLevelPropStatic extends TestLevelProp implements IUpdatable {

    public TestLevelPropStatic(float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(x, y, w, h, vx, vy, false);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}