package utils.physics;

import props.objects.levels.LevelList;
import prototypes.actor.AActor;

/**
 * TODO: Add description
 */
public abstract class APhysics {

    /**
     * The Has gravity.
     */
    protected boolean hasGravity = true;

    /**
     * The Max vel x.
     */
    protected float MAX_VEL_X = 9.8f;
    /**
     * The Max vel y.
     */
    protected float MAX_VEL_Y = 9.8f;

    /**
     * The Friction.
     */
    protected float friction = .2f;
    /**
     * The V x.
     */
    protected float vX, /**
     * The V y.
     */
    vY;
    /**
     * The X.
     */
    protected float x, /**
     * The Y.
     */
    y, /**
     * The W.
     */
    w, /**
     * The H.
     */
    h;

    /**
     * The Buffer vert.
     */
    protected float bufferVert = 5, /**
     * The Buffer horiz.
     */
    bufferHoriz = 5;

    /**
     * The Is floor collision.
     */
    protected boolean isFloorCollision, /**
     * The Is wall collision left.
     */
    isWallCollisionLeft, /**
     * The Is wall collision right.
     */
    isWallCollisionRight;

    /**
     * The Is user controlled.
     */
    protected boolean isUserControlled;

    /**
     * Instantiates a new A physics.
     *
     * @param x          the x
     * @param y          the y
     * @param w          the w
     * @param h          the h
     * @param vX         the v x
     * @param vY         the v y
     * @param hasGravity the has gravity
     */
    protected APhysics(
            float x, float y,
            float w, float h,
            float vX, float vY,
            boolean hasGravity) {

        setPosition(x, y);
        setSize(w, h);
        setVelocity(vX, vY);
        setGravity(hasGravity);

    }

    /**
     * Update.
     *
     * @param delta the delta
     */
    protected void update(float delta) {
        resetCollisions();

        updateVelocity(delta);
    }

    private void resetCollisions() {
        isFloorCollision = false;
        isWallCollisionLeft = false;
        isWallCollisionRight = false;
    }

    private void calculateGravity(float delta) {
        if (hasGravity && !isFloorCollision) {
            vY += (LevelList.GRAVITY / delta);
        }
    }

    private void updateVelocity(float delta) {

        calculateGravity(delta);

        //float acc = friction / PreferenceData.GAME_UPDATE_RATE / delta;

        //vY *= 1-acc;
        //vX *= 1-acc;

        limitVelocity();

    }

    /**
     * Limit velocity.
     */
    public void limitVelocity() {
        if (vY > MAX_VEL_Y * 5f) {
            vY = MAX_VEL_Y * 100f;
        } else if (vY < -MAX_VEL_Y) {
            vY = -MAX_VEL_Y;
        }
        if (vX > MAX_VEL_X) {
            vX = MAX_VEL_X;
        } else if (vX < -MAX_VEL_X) {
            vX = -MAX_VEL_X;
        }
    }

    /**
     * Has collision boolean.
     *
     * @param a     the a
     * @param delta the delta
     * @return the boolean
     */
    public boolean hasCollision(AActor a, float delta) {
        return hasCollision(a, delta, true);
    }

    /**
     * Has collision boolean.
     *
     * @param a            the a
     * @param delta        the delta
     * @param moveToBounds the move to bounds
     * @return the boolean
     */
    public boolean hasCollision(AActor a, float delta, boolean moveToBounds) {

        boolean isFloorBounded = ((a.bottomBufferOuter()) >= top()) && (a.bottomBufferInner() <= bottom());

        /*
        boolean isWallBoundedLeft =
                !isFloorBounded && ((a.leftBufferOuter() >= right()) && (a.leftBufferInner() <= left()));

        boolean isWallBoundedRight =
                !isFloorBounded && ((a.rightBufferInner() <= left()) && (a.rightBufferOuter() >= right()));
        */

        // Determine the conditions of the object collision
        boolean hitBottom =
                ((a.top() <= bottom()) && (a.top() >= top())) ||
                        ((bottom() >= a.top()) && (bottom() <= a.bottom()));
        boolean hitTop =
                ((a.bottom() >= top()) && (a.bottom() <= bottom())) ||
                        ((top() <= a.bottom()) && (top() >= a.top()));
        boolean hitLeft =
                ((a.right() >= left()) && (a.right() <= right())) ||
                        ((left() <= a.right()) && (left() >= a.left()));
        boolean hitRight =
                ((a.left() <= right()) && (a.left() >= left())) ||
                        ((right() >= a.left()) && (right() <= a.right()));

        if ((hitBottom || hitTop) && (hitLeft || hitRight)) {

            if(!moveToBounds) {
                return true;
            }

            // Determine the side that the object should rebound off of
            float distX, distY;
            if (hitBottom) {
                distY = Math.abs(a.top() - bottom());
            } else {
                distY = Math.abs(a.bottom() - top());
            }
            if (hitLeft) {
                distX = Math.abs(a.right() - left());
            } else {
                distX = Math.abs(a.left() - right());
            }

            if (distX > distY) {
                if (hitTop) {
                    a.y = top() - a.h;

                    a.isFloorCollision = true;

                    if (!a.isUserControlled) {
                        a.vX *= .9f;
                    }
                } else {
                    a.y = bottom();
                }
                a.vY = 0;

            } else if (distX < distY) {
                if (hitRight) {
                    a.x = right();
                    a.isWallCollisionLeft = true;
                } else {
                    a.x = left() - a.w;
                    a.isWallCollisionRight = true;
                }

                a.vX = 0;

            }

            return true;
        }

        return false;

    }

    private void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    private void setSize(float w, float h) {
        this.w = w;
        this.h = h;
    }

    /**
     * Sets gravity.
     *
     * @param hasGravity the has gravity
     */
    protected void setGravity(boolean hasGravity) {
        this.hasGravity = hasGravity;
    }

    /**
     * Sets velocity.
     *
     * @param velocityX the velocity x
     * @param velocityY the velocity y
     */
    protected void setVelocity(float velocityX, float velocityY) {
        this.vX = velocityX;
        this.vY = velocityY;
    }

    /**
     * Top float.
     *
     * @return the float
     */
    protected float top() {
        return y;
    }

    /**
     * Bottom float.
     *
     * @return the float
     */
    protected float bottom() {
        return top() + h;
    }

    /**
     * Left float.
     *
     * @return the float
     */
    protected float left() {
        return x;
    }

    /**
     * Right float.
     *
     * @return the float
     */
    protected float right() {
        return left() + w;
    }

    /**
     * Left buffer inner float.
     *
     * @return the float
     */
    protected float leftBufferInner() {
        return left() + bufferHoriz;
    }

    /**
     * Left buffer outer float.
     *
     * @return the float
     */
    protected float leftBufferOuter() {
        return left() - bufferHoriz;
    }

    /**
     * Right buffer inner float.
     *
     * @return the float
     */
    protected float rightBufferInner() {
        return right() + bufferHoriz;
    }

    /**
     * Right buffer outer float.
     *
     * @return the float
     */
    protected float rightBufferOuter() {
        return right() - bufferHoriz;
    }

    /**
     * Bottom buffer inner float.
     *
     * @return the float
     */
    protected float bottomBufferInner() {
        return bottom() - bufferVert;
    }

    /**
     * Bottom buffer outer float.
     *
     * @return the float
     */
    protected float bottomBufferOuter() {
        return bottom() + bufferVert;
    }

}
