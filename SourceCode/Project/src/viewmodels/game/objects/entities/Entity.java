package viewmodels.game.objects.entities;

import viewmodels.game.objects.GameObject;

public abstract class Entity extends GameObject {

    protected double vx, vy;
    protected double MAX_VEL;

    public Entity(double x, double y, double w, double h, double vx, double vy, double MAX_VEL) {
        super(x, y, w, h);

        this.vx = vx;
        this.vy = vy;
        this.MAX_VEL = MAX_VEL;
    }

    public Entity(double x, double y, double w, double h, double vx, double vy) {
        super(x, y, w, h);

        this.vx = vx;
        this.vy = vy;
    }

    protected void setVX(double vx) {
        this.vx = vx;
    }

    protected void setVY(double vy) {
        this.vy = vy;
    }

    protected double getVX() {
        return vx;
    }

    protected double getVY() {
        return vy;
    }

    public abstract void update();
}
