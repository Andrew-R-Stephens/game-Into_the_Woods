package props.prototypes.window.environments.menu.components;

import models.controls.menu.MenuMouseControls;
import models.data.PreferenceData;
import props.prototypes.window.environments.menu.AMenuModel;
import utils.drawables.IDrawable;
import utils.updates.IUpdatable;

import javax.swing.*;
import java.awt.*;

public abstract class AMenuButton implements IUpdatable, IDrawable {

    protected AMenuModel parentMenuModel;

    JButton b;

    private String text = "";
    private final int x, y, w, h;

    public AMenuButton(AMenuModel parentMenuModel, int x, int y, int w, int h) {
        this.parentMenuModel = parentMenuModel;

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    protected boolean isInBounds(float mx, float my) {
        mx /= PreferenceData.scaledW;
        my /= PreferenceData.scaledH;

        boolean horizBound = (mx >= x && mx <= (x + w));
        boolean vertBound = (my >= y && my <= (y + h));

        return horizBound && vertBound;
    }

    public abstract boolean onClick(float x, float y);

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        float sW = PreferenceData.scaledW, sH = PreferenceData.scaledH;
        g.drawRect((int)(x * sW), (int)(y * sH), (int)(w * sW), (int)(h * sH));

        g.drawString(text, (int)((x * sW) + (5 * sW)), (int)((y * sH) + (20 * sH)));
    }

    @Override
    public void update(float delta) {
    }

    public void registerInput() {
        if(parentMenuModel.getMouseController() instanceof MenuMouseControls mc) {

            if (mc.isLeftPressed()) {
                if(onClick(mc.getPos()[0], mc.getPos()[1])) {
                    mc.resetInput();
                }
            }

        }
    }

    public void setText(String s) {
        text = s;
    }
}
