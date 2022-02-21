package game.objects.entities;

import game.objects.types.Entity;

public class TestEntity extends Entity {

    public TestEntity(double x, double y, double w, double h, double vx, double vy, double MAX_VEL) {
        super(x, y, w, h, vx, vy, MAX_VEL);
    }

    public TestEntity(double x, double y, double w, double h, double vx, double vy) {
        super(x, y, w, h, vx, vy);
    }

    @Override
    public void update(double delta) {

        if(gravityAllowed){
            vy += (gravity / delta);
            //vy /= delta;
        }

        if(vy < 0) {
            vy += .2 / delta;

            if(vy < -MAX_VEL) {
                vy = -MAX_VEL;
            }
        } else if (vy > 0) {
            vy -= .2 / delta;

            if(vy > MAX_VEL) {
                vy = MAX_VEL;
            }
        }
        y += vy / delta;


        if(vx < 0) {
            vx += .2 / delta;

            if(vx < -MAX_VEL) {
                vx = -MAX_VEL;
            }
        } else if (vx > 0) {
            vx -= .2 / delta;

            if(vx > MAX_VEL) {
                vx = MAX_VEL;
            }
        }
        x += vx / delta;

    }

    @Override
    public void update() {
/*

        if(gravityAllowed){
            vy += (gravity);
        }

        if(Math.abs(vy) > MAX_VEL) {
            vy = MAX_VEL;
        }

        y += vy;
*/

    }

}
