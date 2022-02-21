package game.objects.entities;

import files.PreferenceData;
import game.objects.types.Entity;


public class Player extends Entity {

    public Player(double x, double y, double w, double h, double vx, double vy, double MAX_VEL) {
        super(x, y, w, h, vx, vy, MAX_VEL);
    }

    public Player(double x, double y, double w, double h, double vx, double vy) {
        super(x, y, w, h, vx, vy);
    }

    @Override
    public void update() {

        if(gravityAllowed){
            vy += (gravity/PreferenceData.frameRate);
        }

        if(Math.abs(vy) > MAX_VEL) {
            vy = MAX_VEL;
        }

        y += vy;
    }

}
