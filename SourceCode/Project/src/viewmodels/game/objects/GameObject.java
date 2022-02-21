package viewmodels.game.objects;

public abstract class GameObject {

    protected double x, y;
    protected double w, h;

    public GameObject(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
    }

    protected abstract void update();

}
