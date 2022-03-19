package props.prototypes.level.prop;

import models.camera.Camera;
import models.data.PreferenceData;
import props.prototypes.actor.AActor;

import java.awt.*;

/**
 * TODO: Add description
 */
public abstract class ALevelProp extends AActor {

    protected ALevelProp(float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);

        double offsetX = ((x * PreferenceData.scaledW) + (Camera.x));
        double offsetY = ((y * PreferenceData.scaledH) + (Camera.y));

        double scaledW = w * PreferenceData.scaledW;
        double scaledH = h * PreferenceData.scaledH;

        g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
    }

}
