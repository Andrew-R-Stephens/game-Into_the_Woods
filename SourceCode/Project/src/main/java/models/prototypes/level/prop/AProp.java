package models.prototypes.level.prop;

import models.prototypes.actor.AActor;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;

/**
 * <p>AProp is an abstract entity type that derives from AActor. This type is simply a wrapper class for Level
 * props.</p>
 * <p>Level props should be allowed to draw to the Map Overlay, which is why the drawToHUD() method is added.</p>
 * @author Andrew Stephens
 */
public abstract class AProp extends AActor implements IDrawable, IHUDDrawable, IUpdatable {

    /**
     * <p>Called from the subtypes, this method initializes the object with position and size relative to the
     * default dimensions.</p>
     * @param resources The resources of the parent Environment
     * @param x The horizontal position, relative to the default dimensions.
     * @param y The y position, relative to the default dimensions.
     * @param w The width, relative to the default dimensions.
     * @param h The height, relative to the default dimensions.
     * @param vx The horizontal velocity.
     * @param vy The vertical velocity.
     * @param hasGravity If the object should be effected by gravity.
     */
    protected AProp(Resources resources, float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(resources, x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public void draw(Graphics2D g) {
    }

    @Override
    public abstract void drawAsHUD(Graphics2D g);

}
