package models.prototypes.environments.menu.components.types;

import models.prototypes.controls.AMouseController;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.prototypes.environments.menu.components.AMenuComponent;
import models.utils.config.Config;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class ASliderView extends AMenuComponent {

    protected final AButtonView button;

    private final BufferedImage trackImage;
    private final BufferedImage notchImage;

    protected ArrayList<Short> values;
    protected int itemCount = 2;
    protected int current = 0;
    protected float notchDistance = 0;

    public ASliderView(AMenuEnvironment parentMenuModel, int x, int y, int w, int h) {
        super(parentMenuModel);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        trackImage = getParentEnvironment().getResources().getImage("slider_track");
        notchImage = getParentEnvironment().getResources().getImage("slider_notch");

        BufferedImage buttonImg = getParentEnvironment().getResources().getImage("button_slider");
        float scaleW = (float)buttonImg.getHeight() / h * .5f;

        button = new AButtonView(getParentEnvironment(), x, y, (int)(scaleW * h), h) {
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
                AMouseController mc = getParentEnvironment().getMouseController();
                if (mc.isLeftPressed()) {
                    ASliderView.this.isPressed = onClick(mc.getPos()[0], mc.getPos()[1]);
                    if(isPressed) {
                        playSound();
                        setPlaySound(false);
                    }
                } else {
                    ASliderView.this.isPressed = false;
                    isPressed = false;
                    setPlaySound(true);
                }
            }
        };
        button.setBackgroundImage(getParentEnvironment().getResources().getImage("button_slider"));
        button.setImageScaling(ImageScale.FIT_CENTERED);

        init();

        build();
    }

    public abstract void init();

    public void moveSliderTo(int x, int y) {

        x = (int)(x / Config.scaledW);
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
    public void draw(Graphics2D g) {
        super.draw(g);

        float scaleH = .1f;
        g.drawImage(
                trackImage,
                (int) (x * Config.scaledW),
                (int)((y * Config.scaledH) + (.5f * h * Config.scaledH) - (.5f *  scaleH * h * Config.scaledH)),
                (int) (w * Config.scaledW),
                (int) (scaleH * h * Config.scaledH), null);

        if((Config.scaledW * notchImage.getWidth() * (h / (float)notchImage.getHeight())) > (w / notchDistance)) {
            for (int i = 0; i < w; i++) {
                g.drawImage(notchImage,
                        (int) (((x + i) * Config.scaledW)),
                        (int) (y * Config.scaledW),
                        (int) (Config.scaledW * notchImage.getWidth() * h / notchImage.getHeight()),
                        (int) (h * Config.scaledH),
                        null);
                i += notchDistance;
            }
        }

        button.draw(g);
    }

    @Override
    public void registerInput() {
        if(button != null) {
            button.registerInput();
            if(button.isPressed()) {
                moveSliderTo(getParentEnvironment().getMouseController().getPos()[0], getParentEnvironment().getMouseController().getPos()[1]);
                doSetting();
            }
        }
    }

    public void build() {
        notchDistance = (w - button.getW()) / (float)(itemCount -1);
        button.setX(this.x + (int)(notchDistance*current));
    }

    public void setText(String s) {
        text = s;
    }

}
