package utils.math;

public abstract class APhysics {

    protected boolean hasGravity = true;
    protected final float GRAVITY = .98f;

    protected float vX, vY;
    protected float
            MIN_VELY, MAX_VELY,
            MIN_VELX, MAX_VELX;

    protected float mass = 1;

    protected APhysics(float vX, float vY, float MIN_VELX, float MIN_VELY, float MAX_VELX, float MAX_VELY, boolean hasGravity, float mass) {
        setVelocity(vX, vY);
        setVelocityConstraints(MIN_VELX, MIN_VELY, MAX_VELX, MAX_VELY);
        hasGravity(hasGravity);
        setMass(mass);
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

    protected void setVelocityConstraints(float MIN_VELX, float MIN_VELY, float MAX_VELX, float MAX_VELY) {
        this.MIN_VELX = MIN_VELX;
        this.MIN_VELY = MIN_VELY;
        this.MAX_VELX = MAX_VELX;
        this.MAX_VELY = MAX_VELY;
    }

    protected void update(double delta) {
        calculateGravity(delta);
        constrainVelocity(delta);
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
            vY += GRAVITY / delta;
        }
    }

    private void constrainVelocity(double delta) {
        double deccelerationRate = .2/delta;

        if(vY > MAX_VELY) {
            vY = MAX_VELY;
            vY -= deccelerationRate;
        } else if (vY < MIN_VELY) {
            vY = MIN_VELY;
            vY += deccelerationRate;
        }

        if(vX > MAX_VELX) {
            vX = MAX_VELX;
            vX -= deccelerationRate;
        } else if (vX < MIN_VELX) {
            vX = MIN_VELX;
            vX += deccelerationRate;
        }


        //TODO: Needs to slow down over time with air resistance and friction

        //-----
        /*
        if(Math.abs(vX) < .00001f) {
            vX = 0;
        }
        if(Math.abs(vY) < .00001f) {
            vY = 0;
        }*/
    }


}
