package models.environments.game.hud.components;

import models.data.PreferenceData;
import props.prototypes.window.environments.game.AOverlayComponent;

import java.awt.*;

public class TimeKeeperOverlay extends AOverlayComponent {

    public TimeKeeperOverlay(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        float sW = (float) PreferenceData.scaledW, sH = (float)PreferenceData.scaledH;

        g.setColor(Color.PINK);
        g.fillRect((int)(x * sW), (int)(y * sH), (int)(w * sW), (int)(h * sH));
        g.setColor(Color.white);
        g.drawString("Time Overlay", (int)((x * sW) + (20 * sW)), (int)((y * sH) + (20 * sH)));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
