package game.objects;

public abstract class GameObject {

    protected double x, y;
    protected double w, h;

    public GameObject(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    protected abstract void update();

}
