package props.gameactors;

import data.PreferenceData;
import proptypes.types.actor.AActor;
import proptypes.types.actor.pawn.character.ACharacter;
import viewmodels.game.WorldModel;

import java.awt.*;

public class TestCharacter extends ACharacter {

    protected Color c = Color.BLUE;

    public TestCharacter(float x, float y, float w, float h, float vx, float vy, boolean hasGravity, float mass) {
        super(x, y, w, h, vx, vy, hasGravity, mass);
    }

    @Override
    public boolean hasCollision(AActor a) {
        if(super.hasCollision(a)) {
            c = Color.RED;
            return true;
        }
        c = Color.BLUE;
        return false;
    }

    @Override
    public void update(double delta) {
        super.update(delta);

        WorldModel.offX = ((PreferenceData.window_width * .5) - (w * PreferenceData.scaledW)) - (x * PreferenceData.scaledW);
        WorldModel.offY = ((PreferenceData.window_height * .5) - (h * PreferenceData.scaledH)) - (y * PreferenceData.scaledH);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        /*
        double sW = PreferenceData.scaledW, sH = PreferenceData.scaledH;

        g.setColor(c);
        g.drawRect((int)(x*sW), (int)(y*sH), (int)(w*sW), (int)(h*sH));
        g.drawLine((int)(x*sW), (int)(y*sH), (int)((x*sW)+(w*sW)), (int)((y*sW) + (h*sH)));
        g.drawLine((int)((x*sW)+(w*sW)), (int)(y*sH), (int)(x*sW), (int)((y*sW) + (h*sH)));
        g.setColor(Color.CYAN);
        g.drawRect((int)((x*sW) - (jumpBufferHoriz*sW)), (int)(y*sH), (int)((w*sW) + (jumpBufferHoriz*sW*2)), (int)(h*sH));
        g.drawRect((int)(x*sW), (int)(y*sH), (int)(w*sW), (int)((h*sH)+(jumpBufferVert*sH)));
        */

        //Half window width
        double centerX = PreferenceData.window_width * .5 * WorldModel.zoomLevel;
        double centerY = PreferenceData.window_height * .5 * WorldModel.zoomLevel;

        // Scaled size
        double scaleW = w * PreferenceData.scaledW * WorldModel.zoomLevel;
        double scaleH = h * PreferenceData.scaledH * WorldModel.zoomLevel;

        centerX -= scaleW;
        centerY -= scaleH;

        g.setColor(c);
        g.drawRect((int)(centerX), (int)(centerY), (int)(scaleW), (int)(scaleH));
        g.drawString("TC", (int)(x* scaleW) + 3, (int)(y* scaleH) + 12);

    }

}
