package models.utils.physics;

import models.prototypes.actor.AActor;
import models.utils.config.Config;
import models.utils.updates.IUpdatable;

/**
 * <p></p>
 */
public abstract class APhysics implements IUpdatable {

    public static final float GRAVITY = 9.8f / (float) Config.GAME_UPDATE_RATE;
    protected final float bufferVert = 5, bufferHoriz = 5;
    protected final float MAX_VEL_X = 9.8f;
    protected final float MAX_VEL_Y = 9.8f;
    protected final float friction = .5f;

    protected float ox, oy, ow, oh;
    protected float x, y, w, h;
    protected float vX, vY;

    protected boolean isFloorCollision,  isWallCollisionLeft, isWallCollisionRight;
    protected boolean hasGravity = true;
    protected boolean isUserControlled;
    protected boolean isMoving;

    /**
     * <p></p>
     * @param x -
     * @param y -
     * @param w -
     * @param h -
     * @param vX -
     * @param vY -
     * @param hasGravity -
     */
    protected APhysics(
            float x, float y,
            float w, float h,
            float vX, float vY,
            boolean hasGravity) {

        setOriginalPosition(x, y);
        setOriginalSize(w, h);
        setPosition(x, y);
        setSize(w, h);

        setVelocity(vX, vY);
        setGravity(hasGravity);
    }

    /**
     * <p></p>
     * @param delta -
     */
    public void update(float delta) {
        resetCollisions();

        updateVelocity(delta);
    }

    /**
     * <p></p>
     */
    private void resetCollisions() {
        isFloorCollision = false;
        isWallCollisionLeft = false;
        isWallCollisionRight = false;
    }

    /**
     * <p></p>
     * @param delta -
     */
    private void calculateGravity(float delta) {
        if (hasGravity && !isFloorCollision) {
            vY += (GRAVITY / delta);
        }
    }

    /**
     * <p></p>
     * @param delta -
     */
    private void updateVelocity(float delta) {
        calculateGravity(delta);
        limitVelocity(delta);
    }

    /**
     * <p></p>
     * @param delta -
     */
    public void limitVelocity(float delta) {

        float acc = 1 - (friction / Config.GAME_UPDATE_RATE / delta);

        //vY *= acc;
        vX *= acc;

        if(!(isMoving = Math.abs(vX) > .1f)) {
            vX = 0;
        }

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

    public boolean hasCollision(AActor a, float delta) {
        return hasCollision(a, delta, true);
    }

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
                    a.setY(top() - a.h);

                    a.isFloorCollision = true;

                    if (!a.isUserControlled) {
                        a.vX *= .9f;
                    }
                } else {
                    a.setY(bottom());
                }

                a.vY = 0;

            } else if (distX < distY) {
                if (hitRight) {
                    a.setX(right());
                    a.isWallCollisionLeft = true;
                } else {
                    a.setX(left() - a.w);
                    a.isWallCollisionRight = true;
                }

                a.vX = 0;

            }

            return true;
        }

        return false;

    }

    private void setOriginalSize(float w, float h) {
        this.ow = w;
        this.oh = h;
    }

    private void setOriginalPosition(float x, float y) {
        this.ox = x;
        this.oy = y;
    }


    private void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }

    public void setX(float x) {
        this.x = this.ox = x;
    }

    public void setY(float y) {
        this.y = this.oy = y;
    }

    public float[] getLocation() {
        return new float[]{x, y};
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getW() {
        return w;
    }

    public float getH() {
        return h;
    }

    public boolean isFalling() {
        return vY >= 0;
    }

    private void setSize(float w, float h) {
        this.w = w;
        this.h = h;
    }

    protected void setGravity(boolean hasGravity) {
        this.hasGravity = hasGravity;
    }

    public void setVelocity(float velocityX, float velocityY) {
        this.vX = velocityX;
        this.vY = velocityY;
    }

    public void setVX(int vX) {
        this.vX = vX;
    }

    public void setVY(int vY) {
        this.vY = vY;
    }

    protected float top() {
        return y;
    }

    protected float bottom() {
        return top() + h;
    }

    protected float left() {
        return x;
    }

    protected float right() {
        return left() + w;
    }

    protected float leftBufferInner() {
        return left() + bufferHoriz;
    }

    protected float leftBufferOuter() {
        return left() - bufferHoriz;
    }

    protected float rightBufferInner() {
        return right() + bufferHoriz;
    }

    protected float rightBufferOuter() {
        return right() - bufferHoriz;
    }

    protected float bottomBufferInner() {
        return bottom() - bufferVert;
    }

    protected float bottomBufferOuter() {
        return bottom() + bufferVert;
    }

}