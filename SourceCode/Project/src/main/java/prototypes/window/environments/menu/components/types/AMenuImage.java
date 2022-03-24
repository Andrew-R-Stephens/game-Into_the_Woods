package prototypes.window.environments.menu.components.types;

import utils.config.PreferenceData;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.AMenuComponent;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The abstract type AMenuButton. Implements IUpdatable and IDrawable.
 * <p>
 * This is to be used for the creation of anonymous buttons.
 * This can also be used as a parent class for specific AMenuButton types.
 */
public class AMenuImage extends AMenuComponent {

    /**
     * Instantiates a new A menu button.
     * @param parentMenuModel the menu Model
     * @param x               the x coordinate
     * @param y               the y coordinate
     * @param w               the width
     * @param h               the height
     * @param bufferedImage   the Image to display
     * @param scaleType       the Scale type of the image
     */
    public AMenuImage(AMenuModel parentMenuModel, int x, int y, int w, int h, BufferedImage bufferedImage, ImageScale scaleType) {
        super(parentMenuModel);

        setBackgroundImage(bufferedImage);
        setImageScaling(scaleType);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

    }

    /**
     * Instantiates a new A menu button. Defaults the width and height to 200 x 50, scaled to the window size.
     *
     * @param parentMenuModel the menu Model
     * @param x               the x coordinate
     * @param y               the y coordinate
     * @param bufferedImage   the Image to display
     * @param scaleType       the Scale type of the image
     */
    public AMenuImage(AMenuModel parentMenuModel, int x, int y, BufferedImage bufferedImage, ImageScale scaleType) {
        super(parentMenuModel);

        setBackgroundImage(bufferedImage);
        setImageScaling(scaleType);

        this.x = x;
        this.y = y;
        this.w = 200;
        this.h = 50;

    }

    @Override
    public boolean onClick(float x, float y) {
        return false;
    }

    @Override
    public void draw(Graphics g) {

        g.setColor(new Color(255, 0, 0, 100));
        float sW = PreferenceData.scaledW, sH = PreferenceData.scaledH;

        g.drawRect((int)(x * sW), (int)(y * sH), (int)(w * sW), (int)(h * sH));

        if(backgroundImage != null) {
            switch (scaleType) {
                case FIT_CENTERED -> {
                    float sBW = w / (float)backgroundImage.getWidth();
                    float sBH = h / (float)backgroundImage.getHeight();
                    float s = Math.min(sBW, sBH);
                    g.drawImage(backgroundImage,
                            (int)(((x * sW) + (w * sW * .5f)) - (backgroundImage.getWidth() * s * .5f)),
                            (int)(((y * sH) + (h * sH * .5f)) - (backgroundImage.getHeight() * s * .5f)),
                            (int)(backgroundImage.getWidth() * s),
                            (int)(backgroundImage.getHeight() * s),
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

        if(isFocused) {
            g.setColor(new Color(255, 255, 255, 50));
            g.fillRect((int) (x * sW), (int) (y * sH), (int) (w * sW), (int) (h * sH));
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void registerInput() {
        // DO NOTHING
    }

}
