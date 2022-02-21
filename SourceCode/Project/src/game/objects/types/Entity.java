package game.objects.types;

import game.objects.GameObject;

import java.awt.*;

public abstract class Entity extends GameObject implements IDrawable {

    protected double vx, vy;
    protected double MAX_VEL = 1;

    protected boolean gravityAllowed = true;
    protected double gravity = 9.8;

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

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval((int) (x - (w/2)), (int) (y - (h/2)), (int) w, (int) h);
        System.out.println("Drawing");
    }
}
