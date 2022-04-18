package models.prototypes.level.prop;

import models.prototypes.actor.AActor;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;

public abstract class AProp extends AActor implements IDrawable, IHUDDrawable, IUpdatable {

    protected AProp(Resources resources, float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(resources, x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public void draw(Graphics2D g) {
    }

    public abstract void drawAsHUD(Graphics2D g);

    @Override
    public void update(float delta) {
        super.update(delta);
    }

}
