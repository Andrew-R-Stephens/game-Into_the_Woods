package models.environments.game.hud.components;

import models.data.PreferenceData;
import props.prototypes.window.environments.game.AOverlayComponent;

import java.awt.*;

public class MapOverlay extends AOverlayComponent {

    public MapOverlay(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        float sW = PreferenceData.scaledW, sH = PreferenceData.scaledH;

        g.setColor(Color.PINK);
        g.fillRect((int)(x * sW), (int)(y * sH), (int)(w * sW), (int)(h * sH));
        g.setColor(Color.white);
        g.drawString("Map Overlay", (int)((x * sW) + (20 * sW)), (int)((y * sH) + (20 * sH)));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
