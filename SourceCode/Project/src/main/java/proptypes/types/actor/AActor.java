package proptypes.types.actor;

import data.PreferenceData;
import utils.IDrawable;
import utils.math.APhysics;
import viewmodels.game.WorldModel;

import java.awt.*;

/**
 *
 */
public abstract class AActor extends APhysics implements IDrawable {

    protected Color c = Color.GREEN;

    protected AActor(float x, float y,
                     float w, float h,
                     float vx, float vy,
                     boolean hasGravity,
                     float mass) {

        super(x, y, w, h, vx, vy, hasGravity, mass);

    }

    @Override
    protected void update(double delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics g) {

        double offsetX = ((x*PreferenceData.scaledW) + (WorldModel.offX));
        double offsetY = ((y*PreferenceData.scaledH) + (WorldModel.offY));

        double scaledW = w * PreferenceData.scaledW;
        double scaledH = h * PreferenceData.scaledH;

        g.drawRect((int)((offsetX)), (int)(offsetY), (int)(scaledW), (int)(scaledH));
    }
}
