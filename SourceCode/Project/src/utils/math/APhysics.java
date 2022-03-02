package utils.math;

public abstract class APhysics {

    protected boolean hasGravity = true;
    protected final float GRAVITY = 9.8f;
    protected final float FLUID_DENSITY = 1.225f;

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

        setVelocityConstraints(MIN_VELX, MIN_VELY, MAX_VELX, MAX_VELY);
        hasGravity(hasGravity);
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

    protected void setVelocityConstraints(float MIN_VELX, float MIN_VELY, float MAX_VELX, float MAX_VELY) {
        this.MIN_VELX = MIN_VELX;
        this.MIN_VELY = MIN_VELY;
        this.MAX_VELX = MAX_VELX;
        this.MAX_VELY = MAX_VELY;
        /*this.MIN_VELX = factorMass(MIN_VELX);
        this.MIN_VELY = factorMass(MIN_VELY);
        this.MAX_VELX = factorMass(MAX_VELX);
        this.MAX_VELY = factorMass(MAX_VELY);*/
    }

    private float factorMass(float baseVel) {
        float objectarea = w*h;
        float dragForce = 1;
        float flowSpeed = 1;
        float dragCoeff = (2*dragForce)/(FLUID_DENSITY*(flowSpeed*flowSpeed)* 1);
        return baseVel * (float)Math.sqrt((2*mass*GRAVITY) / (FLUID_DENSITY*(objectarea)*dragCoeff));
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
