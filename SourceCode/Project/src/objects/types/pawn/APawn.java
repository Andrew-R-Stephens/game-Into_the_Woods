package objects.types.pawn;

public abstract class APawn {

    protected double x, y;
    protected double w, h;

    public APawn(double x, double y, double w, double h) {
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

    protected abstract void update(double delta);

    protected abstract void update();

}
