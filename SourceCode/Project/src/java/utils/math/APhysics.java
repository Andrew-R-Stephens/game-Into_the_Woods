package utils.math;

import data.PreferenceData;

public abstract class APhysics {

    protected boolean hasGravity = true;
    protected final float GRAVITY = 9.8f;
    protected final float FLUID_DENSITY = 1.225f;
    protected double terminalVelocity;
    double deccelerationRate = .2;

    protected float
            x, y,
            w, h;
    protected float vX, vY;

    protected float mass = 1;

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
        calculateGravity(delta);

        updateVelocity(delta);
    }

    private void updateVelocity(double delta) {

        deccelerationRate /= delta;

        vY += deccelerationRate;
        vX += deccelerationRate;

    }

    private void calculateGravity(double delta) {
        if(hasGravity) {
            vY += (GRAVITY / delta) * mass;
        }
    }

    public boolean isInBounds() {
        if(x + w > 0 && x < PreferenceData.window_width) {
            if(y + h > 0 && y < PreferenceData.window_height) {
                return true;
            }
        }
        return false;
    }

    public void doCollision(float[] collisions) {
        vX *= collisions[0];
        vY *= -1;
        x = collisions[2];
        y = collisions[3];
    }
}
