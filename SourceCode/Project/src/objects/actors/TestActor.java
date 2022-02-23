package objects.actors;

import objects.types.pawn.actor.AActor;

public class TestActor extends AActor {

    public TestActor(float x, float y, float w, float h, float vx, float vy, float MIN_VELX, float MIN_VELY, float MAX_VELX, float MAX_VELY, boolean hasGravity, float mass) {
        super(x, y, w, h, vx, vy, MIN_VELX, MIN_VELY, MAX_VELX, MAX_VELY, hasGravity, mass);
    }

    @Override
    public void update(double delta) {

        if(hasGravity){
            vY += (GRAVITY / delta);
            //vy /= delta;
        }

        if(vY < 0) {
            vY += .2 / delta;

            if(vY < MIN_VELY) {
                vY = MIN_VELY;
            }
        } else if (vY > 0) {
            vY -= .2 / delta;

            if(vY > MAX_VELY) {
                vY = MAX_VELY;
            }
        }
        y += vY / delta;


        if(vX < 0) {
            vX += .2 / delta;
        } else if (vX > 0) {
            vX -= .2 / delta;
        }
        x += vX / delta;

        y += vY;
        x += vX;
    }


}
