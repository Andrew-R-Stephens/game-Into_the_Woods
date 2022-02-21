package viewmodels.game.objects.entities;

import viewmodels.game.objects.GameObject;
import graphics.ui.GameCanvas;

public class Entity extends GameObject {

    protected GameCanvas gameCanvas;

    public Entity(GameCanvas gameCanvas) {
        this.gameCanvas = gameCanvas;
    }

    public void update() {
        System.out.println("Updating Object");
    }

    public void draw() {
        System.out.println("Drawing Object");
    }

}
