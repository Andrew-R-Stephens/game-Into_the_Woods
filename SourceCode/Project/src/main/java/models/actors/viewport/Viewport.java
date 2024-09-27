package models.actors.viewport;

import models.camera.Camera;
import models.prototypes.actor.AActor;
import models.prototypes.actor.pawn.character.ACharacter;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;

/**
 * <p>The PlayerAvatar is one of the most complex actors. It takes direct control from the GameControls and will
 * react to the controls. Its position controls the Camera's viewport. The PlayerAvatar is the primary
 * actor subtype that all actors check collisions with. The PlayerAvatar will have direct impact on the game levels,
 * the other actors within a level.</p>
 * @author Andrew Stephens
 */
public class Viewport extends ACharacter implements IDrawable, IUpdatable, IHUDDrawable {

    /**
     * <p>Called from the subtypes, this method initializes the object.</p>
     * @param x The horizontal position, relative to the default dimensions.
     * @param y The y position, relative to the default dimensions.
     * @param w The width, relative to the default dimensions.
     * @param h The height, relative to the default dimensions.
     */
    public Viewport(
            float x, float y, float w, float h) {
        super(null, null, x, y, w, h, 0, 0, false);
    }

    @Override
    public void reset(int[] characterOrigin) {
        super.reset(characterOrigin);
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        return super.hasCollision(a, delta);
    }

    @Override
    public void update(float delta) { }

    @Override
    public void draw(Graphics2D g) {

        // Scaled size
        float scaleW = w * Config.scaledW_zoom;
        float scaleH = h * Config.scaledH_zoom;

        //Half window width
        float centerX = (x * Config.scaledW_zoom) + (Camera.camX);
        float centerY = (y * Config.scaledH_zoom) + (Camera.camY);

        g.setColor(new Color(228, 0, 255, 100));
        g.fillRect(
                (int) centerX, (int) centerY,
                (int) scaleW, (int) scaleH
        );

    }

    @Override
    public void drawAsHUD(Graphics2D g) {
        g.setColor(new Color(228, 0, 255, 64));

        float[] offset = Camera.getRelativeOffsetBy(x, y, Camera.SCALE_MINIMAP);
        float[] scale = Camera.getRelativeScaleBy(w, h, Camera.SCALE_MINIMAP);

        g.fillRect((int) offset[0], (int) offset[1], (int) scale[0], (int) scale[1]);
    }
}