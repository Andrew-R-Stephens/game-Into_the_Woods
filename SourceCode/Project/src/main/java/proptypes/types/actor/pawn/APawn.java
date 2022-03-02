package proptypes.types.actor.pawn;

import data.PreferenceData;
import utils.IDrawable;
import proptypes.types.actor.AActor;

import java.awt.*;

public abstract class APawn extends AActor implements IDrawable {

    protected APawn(float x, float y, float w, float h, float vx, float vy, float MIN_VELX, float MIN_VELY, float MAX_VELX, float MAX_VELY, boolean hasGravity, float mass) {
        super(x, y, w, h, vx, vy, MIN_VELX, MIN_VELY, MAX_VELX, MAX_VELY, hasGravity, mass);
    }

    @Override
    protected void update(double delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics g) {
        double sW = PreferenceData.scaledW, sH = PreferenceData.scaledH;

        g.setColor(Color.BLACK);
        g.fillOval((int) ((x) - (w*sW/2)), (int) ((y) - (h*sH/2)), (int) (w*sW), (int) (h*sH));
    }
}
