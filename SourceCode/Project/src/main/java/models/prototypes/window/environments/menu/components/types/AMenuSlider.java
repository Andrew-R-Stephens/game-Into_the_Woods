package models.prototypes.window.environments.menu.components.types;

import models.prototypes.controls.AMouseController;
import models.prototypes.window.environments.menu.AMenuEnvironment;
import models.prototypes.window.environments.menu.components.AMenuComponent;
import models.utils.config.ConfigData;
import models.utils.resources.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class AMenuSlider extends AMenuComponent {

    protected final AMenuButton button;

    protected ArrayList<Short> values;
    protected int itemCount = 2;
    protected int current = 0;
    protected float notchDistance = 0;

    public AMenuSlider(AMenuEnvironment parentMenuModel, int x, int y, int w, int h) {
        super(parentMenuModel);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        BufferedImage buttonImg = Resources.getImage("button_slider");
        float scaleW = (float)buttonImg.getHeight() / h * .5f;

        button = new AMenuButton(parentMenuEnvironment, x, y, (int)(scaleW * h), h) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }
                isPressed = true;
                return true;
            }

            @Override
            public void registerInput() {
                AMouseController mc = parentMenuEnvironment.getMouseController();
                if (mc.isLeftPressed()) {
                    AMenuSlider.this.isPressed = onClick(mc.getPos()[0], mc.getPos()[1]);
                    if(isPressed) {
                        playSound();
                        playSound = false;
                    }
                } else {
                    AMenuSlider.this.isPressed = false;
                    isPressed = false;
                    playSound = true;
                }
            }
        };
        button.setBackgroundImage(Resources.getImage("button_slider"));
        button.setImageScaling(ImageScale.FIT_CENTERED);

        init();

        build();
    }

    public abstract void init();

    public void moveSliderTo(int x, int y) {

        x = (int)(x / ConfigData.scaledW);
        if(x < this.x) {
            x = this.x;
        }

        int newX = (int)((x - this.x) / notchDistance);
        if(newX > itemCount -1) {
            newX = itemCount -1;
        }

        current = newX;

        x = (int)(this.x + (newX * notchDistance));
        button.setX(x);

    }

    public abstract void doSetting();

    @Override
    public boolean onClick(float x, float y) {
        return false;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        button.draw(g);
    }

    @Override
    public void registerInput() {
        if(button != null) {
            button.registerInput();
            if(button.isPressed) {
                moveSliderTo(parentMenuEnvironment.getMouseController().getPos()[0], parentMenuEnvironment.getMouseController().getPos()[1]);
                doSetting();
            }
        }
    }

    public void build() {
        notchDistance = (w - button.w) / (float)(itemCount -1);
        button.x = this.x + (int)(notchDistance*current);
    }

    public void setText(String s) {
        text = s;
    }

}
