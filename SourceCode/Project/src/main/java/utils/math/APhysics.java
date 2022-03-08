package utils.math;

import proptypes.types.actor.AActor;
import viewmodels.game.LevelModel;
public abstract class APhysics {

    protected boolean hasGravity = true;

    protected float accelerationRate = .2f, mass = 1;
    protected float vX, vY;
    protected float x, y, w, h;

    protected float bufferVert = 5, bufferHoriz = 5;

    protected boolean isFloorCollision, isWallCollisionLeft, isWallCollisionRight;

    protected boolean isUserControlled;

    protected APhysics(
            float x, float y,
            float w, float h,
            float vX, float vY,
            boolean hasGravity, float mass) {

        setPosition(x, y);
        setSize(w, h);
        setVelocity(vX, vY);
        setMass(mass);
        setAcceleration();
        hasGravity(hasGravity);

    }

    protected void update(double delta) {
        isFloorCollision = false;
        isWallCollisionLeft = false;
        isWallCollisionRight = false;

        calculateGravity(delta);

        updateVelocity(delta);
    }

    private void calculateGravity(double delta) {
        if(hasGravity) {
            vY += (LevelModel.GRAVITY / delta) * mass;
        }
    }

    private void updateVelocity(double delta) {

        accelerationRate /= delta;

        vY += vY * accelerationRate;
        vX += vY * accelerationRate;

        if(vY > 100f) {
            vY = 100f;
        } else if(vY < -100f) {
            vY = -100f;
        }
        if(vX > 100f) {
            vX = 100f;
        } else if(vX < -100f) {
            vX = -100f;
        }

    }

    public boolean hasCollision(AActor a) {

        boolean isFloorBounded = ((a.bottomBufferOuter()) >= top()) && (a.bottomBufferInner() <= bottom());

        boolean isWallBoundedLeft =
                !isFloorBounded && ((a.leftBufferOuter() >= right()) && (a.leftBufferInner() <= left()));

        boolean isWallBoundedRight =
                !isFloorBounded && ((a.rightBufferInner() >= left()) && (a.rightBufferOuter() <= right()));

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

        if ((hitRight || hitLeft) && isFloorBounded) {
            a.isFloorCollision = true;
        }

        if (hitTop || hitBottom) {

            if(isWallBoundedLeft) {
                a.isWallCollisionLeft = true;
            }
            if(isWallBoundedRight) {
                a.isWallCollisionRight = true;
            }
        }

        if((hitBottom || hitTop) && (hitLeft || hitRight)) {

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

            if(distX > distY) {
                if(hitTop) {
                    a.y = top() - a.h;

                    a.isFloorCollision = true;

                    if(!a.isUserControlled) {
                        a.vX *= .9f;
                    }
                }
                else {
                    a.y = bottom();
                }
                a.vY = 0;

            } else if (distX < distY){
                if(hitRight) {
                    a.x = right();
                    a.isWallCollisionLeft = true;
                }
                else {
                    a.x = left() - a.w;
                    a.isWallCollisionRight = true;
                }

                a.vX = 0;

            }

            return true;
        }

        return false;

    }

    private void setAcceleration() {
        accelerationRate = .2f  * mass;
    }

    private void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    private void setSize(float w, float h) {
        this.w = w;
        this.h = h;
    }

    private void setMass(float mass) {
        this.mass = mass;
    }

    protected void hasGravity(boolean hasGravity) {
        this.hasGravity = hasGravity;
    }

    protected void setVelocity(float velocityX, float velocityY) {
        this.vX = velocityX;
        this.vY = velocityY;
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

    protected float leftBufferOuter() { return left() - bufferHoriz; }

    protected float rightBufferInner() {
        return right() + bufferHoriz;
    }

    protected float rightBufferOuter() { return right() - bufferHoriz; }

    protected float bottomBufferInner() {
        return bottom() - bufferVert;
    }

    protected float bottomBufferOuter() {
        return bottom() + bufferVert;
    }

}
