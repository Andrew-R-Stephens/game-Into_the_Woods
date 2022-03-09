package props.gameactors;

import data.PreferenceData;
import proptypes.types.actor.AActor;
import proptypes.types.actor.pawn.character.ACharacter;
import viewmodels.controls.ControlsModel;
import viewmodels.game.WorldModel;

import java.awt.*;

/**
 * TODO: Add description
 */
public class TestCharacter extends ACharacter {

    protected Color c = Color.BLUE;

    public TestCharacter(ControlsModel cModel, float x, float y, float w, float h, float vx, float vy,
                         boolean hasGravity, float mass) {
        super(cModel, x, y, w, h, vx, vy, hasGravity, mass);
    }

    @Override
    public boolean hasCollision(AActor a) {
        if (super.hasCollision(a)) {
            c = Color.RED;
            return true;
        }
        c = Color.BLUE;
        return false;
    }

    @Override
    public void update(double delta) {
        super.update(delta);

        WorldModel.offX =
                ((PreferenceData.window_width_actual * .5) - (w * PreferenceData.scaledW)) - (x * PreferenceData.scaledW);
        WorldModel.offY =
                ((PreferenceData.window_height_actual * .5) - (h * PreferenceData.scaledH)) - (y * PreferenceData.scaledH);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        //Half window width
        double centerX = PreferenceData.window_width_actual * .5;
        double centerY = PreferenceData.window_height_actual * .5;

        // Scaled size
        double scaleW = w * PreferenceData.scaledW;
        double scaleH = h * PreferenceData.scaledH;

        centerX -= scaleW;
        centerY -= scaleH;

        g.setColor(c);
        g.drawRect((int) (centerX), (int) (centerY), (int) (scaleW), (int) (scaleH));
        g.drawString("TC", (int) (centerX) + 3, (int) (centerY) + 12);

    }

}
