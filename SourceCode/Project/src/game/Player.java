package game;

import viewmodels.game.objects.entities.Entity;

public class Player extends Entity {

    public Player(double x, double y, double w, double h, double vx, double vy, double MAX_VEL) {
        super(x, y, w, h, vx, vy, MAX_VEL);
    }

    @Override
    public void update() {

    }

}
