package models.prototypes.window.environments.menu.components.types;

import models.prototypes.controls.AMouseController;
import models.prototypes.window.environments.menu.AMenuEnvironment;
import models.prototypes.window.environments.menu.components.AMenuComponent;
import models.utils.config.ConfigData;
import models.utils.resources.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AMenuSlider extends AMenuComponent {

    private final AMenuButton button;

    public AMenuSlider(AMenuEnvironment parentMenuModel, int x, int y, int w, int h) {
        super(parentMenuModel);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        BufferedImage buttonImg = Resources.getImage("button_slider");
        float scaleW = (float)buttonImg.getHeight() / h;

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
                        //mc.reset();
                        playSound = false;
                    }
                } else {
                    AMenuSlider.this.isPressed = false;
                    isPressed = false;
                    playSound = true;
                    isInBounds(mc.getPos()[0], mc.getPos()[1]);
                }
            }
        };
        button.setBackgroundImage(Resources.getImage("button_slider"));
        button.setImageScaling(ImageScale.FIT_CENTERED);
    }

    public void moveToCursor(int x, int y) {

        x = (int)(x / ConfigData.scaledW);
        y = (int)(y / ConfigData.scaledH);

        if(x + x - button.x + button.w > this.x + this.w) {
            x = this.x + this.w - button.w;
        }

        System.out.println("moving " + x + " " + y);

        if(x < this.x) {
            x = this.x;
        }
        if(button.x > (this.x + this.w - button.w)) {
            x = (this.x + this.w) - button.w;
            System.out.println(x);
        }

        button.setX(x);
    }

    @Override
    public boolean onClick(float x, float y) {
        return false;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(
                (int)(ConfigData.scaledW * x),
                (int)(ConfigData.scaledW * y),
                (int)(ConfigData.scaledW * w),
                (int)(ConfigData.scaledW * h));

        button.draw(g);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void registerInput() {
        if(button != null) {
            button.registerInput();
            if(button.isPressed) {
                moveToCursor(parentMenuEnvironment.getMouseController().getPos()[0], parentMenuEnvironment.getMouseController().getPos()[1]);
            }
        }
    }

    public void setText(String s) {
        text = s;
    }

}
