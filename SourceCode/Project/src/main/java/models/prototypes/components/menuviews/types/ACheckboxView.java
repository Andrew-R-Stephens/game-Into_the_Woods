package models.prototypes.components.menuviews.types;

import models.prototypes.components.menuviews.AMenuComponent;
import models.prototypes.controls.AMouseController;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * <p>The ASliderView is a class that contains a clickable button that moves based on user mouse input and mouse
 * position. Notches will cause the slider to snap to specific locations, the quantity of notches (and therefore snaps)
 * are based on the number of items in the list that this slider will move through.</p>
 * @author Andrew Stephens
 */
public abstract class ACheckboxView extends AMenuComponent {


    /**<p>The slider button.</p>*/
    protected final AButtonView button;

    /**<p>The background track image.</p>*/
    private final BufferedImage backgroundImage;

    /**
     * <p>Initializes the ASliderView</p>
     * @param parentMenuModel The AMenuEnvironment containing the Menu that contains this component.
     * @param x The horizontal position of the component
     * @param y The vertical position of the component
     * @param w The width of the component
     * @param h The height of the component
     */
    public ACheckboxView(AMenuEnvironment parentMenuModel, int x, int y, int w, int h) {
        super(parentMenuModel, x, y, w, h);

        backgroundImage = getParentEnvironment().getResources().getImage("checkbox_square");

        BufferedImage buttonImg = getParentEnvironment().getResources().getImage("checkbox_check");
        button = new AButtonView(getParentEnvironment(), x, y, w, h) {
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
                    ACheckboxView.this.isPressed = onClick(mc.getPos()[0], mc.getPos()[1]);
                    if(isPressed) {
                        playSound();
                        setPlaySound(false);
                    }
                } else {
                    ACheckboxView.this.isPressed = false;
                    if(isPressed) {
                        isEnabled = !isEnabled;
                    }
                    isPressed = false;
                    setPlaySound(true);
                }
            }
        };
        button.setBackgroundImage(buttonImg);
        button.setImageScaling(ImageScale.FIT_CENTERED);

        init();

    }

    /**
     * <p>Defined by the encapsulating class. Used to initializes specific content for each individual iteration.</p>
     */
    public abstract void init();

    /**
     * <p>Defined by the encapsulating object at instantiation.</p>
     */
    public abstract void doSetting();

    @Override
    public boolean onClick(float x, float y) {
        return false;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);

        float sW = Config.scaledW, sH = Config.scaledH;

        if(backgroundImage != null) {
            switch (scaleType) {

                case FIT_CENTERED -> {
                    float sBW = w / (float)backgroundImage.getWidth();
                    float sBH = h / (float)backgroundImage.getHeight();
                    float s = Math.min(sBW, sBH);
                    g.drawImage(backgroundImage,
                            (int)(((x * sW) + (w * sW * .5f)) - (backgroundImage.getWidth() * sW * s * .5f)),
                            (int)(((y * sH) + (h * sH * .5f)) - (backgroundImage.getHeight() * sH * s * .5f)),
                            (int)(backgroundImage.getWidth() * sW * s),
                            (int)(backgroundImage.getHeight() * sH * s),
                            null);

                }
                case CENTER_CROP -> {
                    float sBW = w / (float)backgroundImage.getWidth();
                    float sBH = h / (float)backgroundImage.getHeight();
                    float s = Math.min(sBW, sBH);
                    int     nx = (int)((backgroundImage.getWidth() - (backgroundImage.getWidth() * s)) * .5f),
                            nw = (int)(backgroundImage.getWidth() * s);
                    BufferedImage croppedImage =
                            backgroundImage.getSubimage(
                                    nx, 0,
                                    nw, backgroundImage.getHeight()
                            );
                    g.drawImage(croppedImage,
                            (int)(x * sW),
                            (int)(y * sH),
                            (int)(w * sW),
                            (int)(h * sH),
                            null);
                }
                default -> {
                    g.drawImage(backgroundImage, (int)(x * sW), (int)(y * sH), (int)(w * sW), (int)(h * sH), null);

                }
            }
        }

        button.draw(g);
    }

    @Override
    public void registerInput() {
        if(button != null) {
            button.registerInput();
            if(button.isPressed()) {
                doSetting();
            }
        }
    }

}
