package models.prototypes.level.prop;

import models.prototypes.actor.AActor;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;

/**
 * <p></p>
 */
public abstract class AProp extends AActor implements IDrawable, IHUDDrawable, IUpdatable {

    /**
     * <p></p>
     * @param resources -
     * @param x -
     * @param y -
     * @param w -
     * @param h -
     * @param vx -
     * @param vy -
     * @param hasGravity -
     */
    protected AProp(Resources resources, float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(resources, x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public void draw(Graphics2D g) {
    }

    /**
     * <p></p>
     * @param g
     */
    public abstract void drawAsHUD(Graphics2D g);

}
