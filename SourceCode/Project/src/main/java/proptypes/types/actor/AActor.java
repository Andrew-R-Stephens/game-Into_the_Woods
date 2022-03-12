package proptypes.types.actor;

import data.PreferenceData;
import utils.drawables.IDrawable;
import utils.physics.APhysics;
import viewmodels.game.Camera;

import java.awt.*;


/**
 * TODO: Add description
 */
public abstract class AActor extends APhysics implements IDrawable {

    protected Color c = Color.GREEN;

    protected AActor(float x, float y,
                     float w, float h,
                     float vx, float vy,
                     boolean hasGravity) {

        super(x, y, w, h, vx, vy, hasGravity);

    }

    @Override
    protected void update(float delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics g) {

        double offsetX = ((x * PreferenceData.scaledW) + (Camera.x));
        double offsetY = ((y * PreferenceData.scaledH) + (Camera.y));

        double scaledW = w * PreferenceData.scaledW;
        double scaledH = h * PreferenceData.scaledH;

        g.drawRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
    }
}
