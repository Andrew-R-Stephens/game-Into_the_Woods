package props.prototypes.window.environments.game;

import utils.drawables.IDrawable;
import utils.updates.IUpdatable;

import java.awt.*;

public class AOverlayComponent implements IUpdatable, IDrawable {

    protected int x, y, w, h;

    protected AOverlayComponent(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void update(float delta) {

    }
}
