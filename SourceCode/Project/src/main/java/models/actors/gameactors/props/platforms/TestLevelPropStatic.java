package models.actors.gameactors.props.platforms;

/**
 * TODO: Add description
 */
public class TestLevelPropStatic extends TestLevelProp {

    /**
     * Instantiates a new Test level prop static.
     *
     * @param x          the x
     * @param y          the y
     * @param w          the w
     * @param h          the h
     * @param vx         the vx
     * @param vy         the vy
     * @param hasGravity the has gravity
     */
    public TestLevelPropStatic(float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(x, y, w, h, vx, vy, false);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}