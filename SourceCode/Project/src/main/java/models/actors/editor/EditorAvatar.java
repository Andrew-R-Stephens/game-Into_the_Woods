package models.actors.editor;

import controls.editor.EditorControls;
import models.camera.Camera;
import models.prototypes.actor.AActor;
import models.prototypes.actor.pawn.editor.AEditorAvatar;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;

/**
 * <p>The PlayerAvatar is one of the most complex actors. It takes direct control from the GameControls and will
 * react to the controls. Its position controls the Camera's viewport. The PlayerAvatar is the primary
 * actor subtype that all actors check collisions with. The PlayerAvatar will have direct impact on the game levels,
 * the other actors within a level.</p>
 * @author Andrew Stephens
 */
public class EditorAvatar extends AEditorAvatar implements IDrawable, IUpdatable {

    /**
     * <p>Called from the subtypes, this method initializes the object.</p>
     * @param resources The resources of the parent Environment
     * @param cModel The GameControls of the parent Environment
     * @param x The horizontal position, relative to the default dimensions.
     * @param y The y position, relative to the default dimensions.
     * @param w The width, relative to the default dimensions.
     * @param h The height, relative to the default dimensions.
     * @param vx The horizontal velocity.
     * @param vy The vertical velocity.
     */
    public EditorAvatar(
            Resources resources, EditorControls cModel,
            float x, float y, float w, float h, float vx, float vy) {
        super(resources, cModel, x - w, y - h, w, h, vx, vy, false);
    }

    @Override
    public void reset(int[] characterOrigin) {
        super.reset(characterOrigin);
    }

    @Override
    public boolean checkCollision(AActor a, float delta) {
        return super.checkCollision(a, delta);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        float tx =
                (float)(((Config.window_width_actual * .5) - (w * Config.scaledW_zoom)) - (x * Config.scaledW_zoom));
        float ty =
                (float)(((Config.window_height_actual * .5) - (h * Config.scaledH_zoom)) - (y * Config.scaledH_zoom));

        Camera.moveTo(tx, ty);
    }

    @Override
    public void draw(Graphics2D g) {

        g.setColor(Color.CYAN);

    }

}