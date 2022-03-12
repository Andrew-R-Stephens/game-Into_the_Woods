package props.objects.gameactors;

import props.prototypes.types.actor.AActor;
import props.prototypes.types.actor.pawn.character.ACharacter;
import viewmodels.camera.Camera;
import viewmodels.controls.ControlsModel;
import viewmodels.data.PreferenceData;

import java.awt.*;

/**
 * TODO: Add description
 */
public class TestCharacter extends ACharacter {

    protected Color c = Color.BLUE;

    public TestCharacter(ControlsModel cModel, float x, float y, float w, float h, float vx, float vy,
                         boolean hasGravity) {
        super(cModel, x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        if (super.hasCollision(a, delta)) {
            c = Color.RED;
            return true;
        }
        c = Color.BLUE;
        return false;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        float tx =
                (float)(((PreferenceData.window_width_actual * .5) - (w * PreferenceData.scaledW)) - (x * PreferenceData.scaledW));
        float ty =
                (float)(((PreferenceData.window_height_actual * .5) - (h * PreferenceData.scaledH)) - (y * PreferenceData.scaledH));

        Camera.moveTo(tx, ty);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        // Scaled size
        double scaleW = w * PreferenceData.scaledW;
        double scaleH = h * PreferenceData.scaledH;

        //Half window width
        double centerX = (x * PreferenceData.scaledW) + (Camera.x) + scaleW;
        double centerY = (y * PreferenceData.scaledH) + (Camera.y) + scaleH;

        centerX -= scaleW;
        centerY -= scaleH;

        g.setColor(c);
        g.drawRect((int) (centerX), (int) (centerY), (int) (scaleW), (int) (scaleH));
        g.drawString("TC", (int) (centerX) + 3, (int) (centerY) + 12);

    }

    public String toString() {
        return "TC:  VX= " + (int)vX + ", VY= " + (int)vY;
    }

}
