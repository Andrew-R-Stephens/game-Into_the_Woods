package models.utils.physics;

import models.prototypes.actor.AActor;
import models.prototypes.level.prop.AProp;
import models.utils.config.Config;
import models.utils.updates.IUpdatable;
import models.textures.meshes.Tile;

/**
 * <p>The APhysics class contains data for any in-game object that should contain positional, dimensional, and vector
 * data.</p>
 * @author Andrew Stephens
 */
public abstract class APhysics implements IUpdatable {

    /**<p>The gravity that the object will be affected by.</p>*/
    public static final float GRAVITY = 9.8f / (float) Config.GAME_UPDATE_RATE;
    /**<p>The buffers of the hitbox.</p>*/
    protected final float bufferVert = 5, bufferHoriz = 5;
    /**<p>The maximum velocity that the object can move per second.</p>*/
    protected final float MAX_VEL_X = 9.8f, MAX_VEL_Y = 9.8f;
    /**<p>The multiplier that friction has on the object's motion.</p>*/
    protected final float friction = .5f;

    /**<p>The original dimension of the object.</p>*/
    protected float ox, oy, ow, oh;
    /**<p>The position of the object.</p>*/
    protected float x, y;
    /**<p>The width and height of the object.</p>*/
    protected float w, h;
    /**<p>The velocity of the object.</p>*/
    protected float vX, vY;

    /**<p>If the collision on a specific face is made.</p>*/
    protected boolean
            isFloorCollision, isCeilingCollision,
            isWallCollisionLeft, isWallCollisionRight;
    /**<p>If the object should be affected by gravity.</p>*/
    protected boolean hasGravity = true;
    /**<p>If the object is being directly controlled by the user.</p>*/
    protected boolean isUserControlled;
    /**<p>If this object is moving either horizontally or vertically.</p>*/
    protected boolean isMoving;

    /**
     * <p>Called from the subtypes, this method initializes the object with position and size relative to the
     * default dimensions.</p>
     * @param x The horizontal position, relative to the default dimensions.
     * @param y The y position, relative to the default dimensions.
     * @param w The width, relative to the default dimensions.
     * @param h The height, relative to the default dimensions.
     * @param vX The horizontal velocity.
     * @param vY The vertical velocity.
     * @param hasGravity If the object should be effected by gravity.
     */
    protected APhysics(
            float x, float y,
            float w, float h,
            float vX, float vY,
            boolean hasGravity) {

        float tX = roundCoordinate(x), tY = roundCoordinate(y);
        float tW = roundCoordinate(w), tH = roundCoordinate(h);
        float tVX = roundCoordinate(vX), tVY = roundCoordinate(vY);

        setOriginalPosition(tX, tY);
        setOriginalSize(tW, tH);
        setPosition(tX, tY);
        setSize(tW, tH);

        setVelocity(tVX, tVY);

        setGravity(hasGravity);
    }

    public static float roundCoordinate(float num) {
        float roundingFactor = Tile.W;
        float rounded = (float)Math.round(num / roundingFactor);
        return rounded * roundingFactor;
    }

    /**
     * <p>Updates the object by resetting collisions and then updating the velocity data.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    public void update(float delta) {
        resetCollisions();

        updateVelocity(delta);
    }

    /**
     * <p>Resets the state of collisions on the object.</p>
     */
    private void resetCollisions() {
        isFloorCollision = false;
        isWallCollisionLeft = false;
        isWallCollisionRight = false;
    }

    /**
     * <p>Calculates the y velocity under gravity for this object.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    private void calculateGravity(float delta) {
        if (hasGravity && !isFloorCollision) {
            vY += (GRAVITY / delta);
        }
    }

    /**
     * <p>Updates the velocity of this object. Then limits the velocity to max and min values.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    private void updateVelocity(float delta) {
        calculateGravity(delta);
        limitVelocity(delta);
    }

    /**
     * <p>Limits the velocity if the velocity exceeds the maximum velocity in any direction.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
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

    public boolean hasGravity() {
        return hasGravity;
    }

    /**
     * Checks if the object had collisions with the actor in question. Overloads the same method with a control that
     * dictates if the object should act in an elastic manner if there is a collision.
     * @param a The actor being compared.
     * @param delta The ratio of actual/target update rate for the game ticks.
     * @return if there has been a collision with the actor.
     */
    public boolean checkCollision(AActor a, float delta) {
        return checkCollision(a, delta, true);
    }
    /*
    public boolean hasCollision(AActor a, float delta) {
        return hasCollision(a, delta, true);
    }*/

    /**
     * Checks if the object had collisions with the actor in question.
     * @param a The other actor.
     * @param delta The ratio of actual/target update rate for the game ticks.
     * @param moveToBounds If the actor should act elastically under an affirmed collision state.
     * @return if there has been a collision with the actor.
     */
    public boolean checkCollision(AActor a, float delta, boolean moveToBounds) {

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
                return true; // If collision occurs, and movement should not occur, do not proceed.
            }

            // Determine the side that the object should rebound off of
            float distY = Math.min(Math.abs(a.top() - bottom()), Math.abs(a.bottom() - top()));
            float distX = Math.min(Math.abs(a.right() - left()), Math.abs(a.left() - right()));

            if (distX > distY) {

                if (hitTop) {
                    a.setY(AProp.roundCoordinate(top() - a.h));

                    a.isFloorCollision = true;

                    if (!a.isUserControlled) {
                        a.vX *= .9f;
                    }
                } else {
                    a.setY(AProp.roundCoordinate(bottom() + 1));
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

    /*
    public boolean hasCollision(AActor a, float delta, boolean moveToBounds) {

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
                    a.setY(AProp.roundCoordinate(top() - a.h));

                    a.isFloorCollision = true;

                    if (!a.isUserControlled) {
                        a.vX *= .9f;
                    }
                } else {
                    a.setY(AProp.roundCoordinate(bottom() + 1));
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
    */

    /**
     * Sets the original size of the object.
     * @param w Width
     * @param h Height
     */
    private void setOriginalSize(float w, float h) {
        this.ow = w;
        this.oh = h;
    }

    /**
     * Sets the origin x and y positions.
     * @param x Horizontal position
     * @param y Vertical position
     */
    private void setOriginalPosition(float x, float y) {
        this.ox = x;
        this.oy = y;
    }

    /**
     * Sets the temporary x and y positions
     * @param x Horizontal position
     * @param y Vertical position
     */
    private void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }

    /**
     * Gets the position.
     * @return The horizontal and vertical position.
     */
    public float[] getLocation() {
        return new float[]{x, y};
    }

    /**
     * Gets the horizontal position.
     * @return The horizontal position.
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the horizontal position.
     * @param x The horizontal position.
     */
    public void setX(float x) {
        this.x = this.ox = x;
    }

    /**
     * Gets the vertical position.
     * @return The vertical position.
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the vertical position.
     * @param y The vertical position.
     */
    public void setY(float y) {
        this.y = this.oy = y;
    }

    /**
     * Gets the width.
     * @return The width.
     */
    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    /**
     * Gets the height.
     * @return The height
     */
    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    /**
     * Returns if the object has a y velocity of >= 0
     * @return if the object's y velocity is >= 0
     */
    public boolean isFalling() {
        return vY >= 0;
    }

    /**
     * Sets the Width and Height
     * @param w The width
     * @param h The height
     */
    private void setSize(float w, float h) {
        this.w = w;
        this.h = h;
    }

    /**
     * Sets if the object should be affected by gravity.
     * @param hasGravity If the object has gravity.
     */
    public void setGravity(boolean hasGravity) {
        this.hasGravity = hasGravity;
        if(!hasGravity) {
            vY = 0;
        }
    }

    public float getVX() {
        return this.vX;
    }

    public float getVY() {
        return this.vY;
    }

    /**
     * Sets the horizontal and vertical velocity.
     * @param velocityX The horizontal velocity
     * @param velocityY The vertical velocity.
     */
    public void setVelocity(float velocityX, float velocityY) {
        this.vX = velocityX;
        this.vY = velocityY;
    }

    /**
     * Sets the horizontal velocity.
     * @param vX The horizontal velocity
     */
    public void setVX(float vX) {
        this.vX = vX;
    }

    /**
     * Sets the vertical velocity
     * @param vY The vertical velocity.
     */
    public void setVY(float vY) {
        this.vY = vY;
    }

    /**
     * Gets the y position.
     * @return
     */
    protected float top() {
        return y;
    }

    /**
     * Gets the y+height position.
     * @return
     */
    protected float bottom() {
        return top() + h;
    }

    /**
     * Gets the x position
     * @return
     */
    protected float left() {
        return x;
    }

    /**
     * Gets the x + width position.
     * @return
     */
    protected float right() {
        return left() + w;
    }

    /**
     * Gets the inner left boundary based on the horizontal boundary and the left side.
     * @return left inner padding
     */
    protected float leftBufferInner() {
        return left() + bufferHoriz;
    }

    /**
     * Gets the outer left boundary based on the horizontal boundary and the left side.
     * @return left outer padding
     */
    protected float leftBufferOuter() {
        return left() - bufferHoriz;
    }

    /**
     * Gets the inner right boundary based on the horizontal boundary and the right side.
     * @return right inner padding
     */
    protected float rightBufferInner() {
        return right() + bufferHoriz;
    }

    /**
     * Gets the outer right boundary based on the horizontal boundary and the right side.
     * @return right outer padding
     */
    protected float rightBufferOuter() {
        return right() - bufferHoriz;
    }

    /**
     * Gets the inner bottom boundary based on the vertical boundary and the y+height.
     * @return bottom inner padding
     */
    protected float bottomBufferInner() {
        return bottom() - bufferVert;
    }

    /**
     * Gets the outer bottom boundary based on the vertical boundary and the y+height.
     * @return bottom outer padding
     */
    protected float bottomBufferOuter() {
        return bottom() + bufferVert;
    }

}