package utils.math;

import data.PreferenceData;
import proptypes.types.actor.AActor;

public abstract class APhysics {

    protected boolean hasGravity = true;
    protected final float GRAVITY = 9.8f;
    protected double deccelerationRate = .2;
    protected float mass = 1;

    protected boolean canPrimaryJump, canWallJump;
    protected double jumpBufferVert = 20, jumpBufferHoriz = 20;

    protected float
            x, y,
            w, h;
    protected float vX, vY;


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

    private void setAcceleration() {
        deccelerationRate = .2  * mass;
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

    protected float left() {
        return x;
    }

    protected float right() {
        return x+w;
    }

    protected float top() {
        return y;
    }

    protected float bottom() {
        return y+h;
    }

    protected void update(double delta) {
        canPrimaryJump(false);
        canWallJump(false);

        calculateGravity(delta);

        updateVelocity(delta);
    }

    private void calculateGravity(double delta) {
        if(hasGravity) {
            vY += (GRAVITY / delta) * mass;
        }
    }

    private void updateVelocity(double delta) {

        deccelerationRate /= delta;

        vY += vY * deccelerationRate;
        vX += vY * deccelerationRate;

        if(vY > 100) {
            vY = 100;
        } else if(vY < -100) {
            vY = -100;
        }
        if(vX > 100) {
            vX = 100;
        } else if(vX < -100) {
            vX = -100;
        }

    }

    public boolean isInFrameBounds() {
        if(x + w > 0 && x < PreferenceData.DEFAULT_WINDOW_WIDTH) {
            if(y + h > 0 && y < PreferenceData.DEFAULT_WINDOW_HEIGHT) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCollision(AActor a) {

        boolean hitBottom = (a.top() <= bottom()) && (a.top() >= top());
        boolean hitTop = (a.bottom() >= top()) && (a.bottom() <= bottom());
        boolean hitLeft = (a.right() >= left()) && (a.right() <= right());
        boolean hitRight = (a.left() <= right()) && (a.left() >= left());

        if((hitBottom || hitTop) && (hitLeft || hitRight)) {

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
                }
                else {
                    a.y = bottom();
                }

                a.vX *= .5;
                a.vY *= -.5;

            } else if (distX < distY){
                if(hitRight) {
                    a.x = right();
                }
                else {
                    a.x = left() - a.w;
                }

                a.vY *= .5;
                a.vX *= -.5;

            }

            a.canPrimaryJump(a.bottom() + jumpBufferVert > top());
            a.canWallJump(a.left() - jumpBufferHoriz < right() || a.right() + jumpBufferHoriz > left());

            return true;
        }

        return false;

    }

    public void canPrimaryJump(boolean canJump) {
        this.canPrimaryJump = canJump;
    }

    public void canWallJump(boolean canJump) {
        this.canWallJump = canJump;
    }

}
