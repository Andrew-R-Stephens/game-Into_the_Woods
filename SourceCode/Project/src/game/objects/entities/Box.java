package game.objects.entities;

import data.PreferenceData;
import game.objects.types.Entity;

public class Box extends Entity {

    public Box(double x, double y, double w, double h, double vx, double vy, double MAX_VEL) {
        super(x, y, w, h, vx, vy, MAX_VEL);
    }

    @Override
    public void update() {

    }

    @Override
    public void update(double delta) {

        if(gravityAllowed){
            vy += (gravity/ PreferenceData.frameRate);
        }

        if(Math.abs(vy) > MAX_VEL) {
            vy = MAX_VEL;
        }

        y += vy;
    }

}
