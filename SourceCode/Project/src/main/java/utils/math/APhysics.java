package utils.math;

public abstract class APhysics {

    protected boolean hasGravity = true;
    protected final float GRAVITY = 9.8f;
    protected final float FLUID_DENSITY = 1.225f;
    protected double terminalVelocity;

    protected float
            x, y,
            w, h;
    protected float vX, vY;
    protected float
            MIN_VELY, MAX_VELY,
            MIN_VELX, MAX_VELX;

    protected float mass = 1;

    protected APhysics(
            float x, float y,
            float w, float h,
            float vX, float vY,
            float MIN_VELX, float MIN_VELY,
            float MAX_VELX, float MAX_VELY,
            boolean hasGravity, float mass) {

        setPosition(x, y);
        setSize(w, h);
        setVelocity(vX, vY);
        setMass(mass);

        setTerminalVelocity();

        hasGravity(hasGravity);
    }

    private void setTerminalVelocity() {
        terminalVelocity = (2*mass*GRAVITY)/(1.225*w*h*.47);
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

    protected void update(double delta) {
        calculateGravity(delta);

        updateVelocity(delta);

        constrainVelocity(delta);
    }

    private void updateVelocity(double delta) {



    }

    protected void reverseVelocity(boolean revX, boolean revY) {
        if(revX) {
            vX = -vX;
        }
        if(revY) {
            vY = -vY;
        }
    }

    private void calculateGravity(double delta) {
        if(hasGravity) {
            vY += (GRAVITY / delta) * mass;
        }
    }

    private void constrainVelocity(double delta) {

        double deccelerationRate = .2/delta/mass; //((mass - (.5 * FLUID_DENSITY * (vY*vY) * .47 * (w*h))))/ mass/delta;

        vY += deccelerationRate;
        vX += deccelerationRate;

    }


}
