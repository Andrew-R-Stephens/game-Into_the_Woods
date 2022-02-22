package objects.types.pawn.actor;

import utils.IDrawable;
import objects.types.pawn.APawn;

import java.awt.*;

public abstract class Actor extends APawn implements IDrawable {

    protected Actor(float x, float y, float w, float h, float vx, float vy, float MIN_VELX, float MIN_VELY, float MAX_VELX, float MAX_VELY, boolean hasGravity, float mass) {
        super(x, y, w, h, vx, vy, MIN_VELX, MIN_VELY, MAX_VELX, MAX_VELY, hasGravity, mass);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval((int) (x - (w/2)), (int) (y - (h/2)), (int) w, (int) h);
    }
}
