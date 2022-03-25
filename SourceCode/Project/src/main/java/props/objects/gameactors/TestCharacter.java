package props.objects.gameactors;

import models.camera.Camera;
import models.controls.GameControlsModel;
import prototypes.actor.AActor;
import prototypes.actor.pawn.character.ACharacter;
import utils.config.PreferenceData;

import java.awt.*;

/**
 * TODO: Add description
 */
public class TestCharacter extends ACharacter {

    /**
     * Instantiates a new Test character.
     *
     * @param cModel     the c model
     * @param x          the x
     * @param y          the y
     * @param w          the w
     * @param h          the h
     * @param vx         the vx
     * @param vy         the vy
     * @param hasGravity the has gravity
     */
    public TestCharacter(GameControlsModel cModel, float x, float y, float w, float h, float vx, float vy,
                         boolean hasGravity) {
        super(cModel, x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        return super.hasCollision(a, delta);
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
        //super.draw(g);

        // Scaled size
        double scaleW = w * PreferenceData.scaledW;
        double scaleH = h * PreferenceData.scaledH;

        //Half window width
        double centerX = (x * PreferenceData.scaledW) + (Camera.x) + scaleW;
        double centerY = (y * PreferenceData.scaledH) + (Camera.y) + scaleH;

        centerX -= scaleW;
        centerY -= scaleH;

        g.setColor(c);
        g.fillRect((int) (centerX), (int) (centerY), (int) (scaleW), (int) (scaleH));
        g.drawString("TC", (int) (centerX) + 3, (int) (centerY) + 12);

    }

    public String toString() {
        return "TC:  VX= " + (int)vX + ", VY= " + (int)vY;
    }

}
