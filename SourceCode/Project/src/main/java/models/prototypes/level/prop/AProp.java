package models.prototypes.level.prop;

import models.prototypes.actor.AActor;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;

public abstract class AProp extends AActor implements IDrawable, IHUDDrawable, IUpdatable {

    protected AProp(float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public void draw(Graphics g) {
    }

    public abstract void drawAsHUD(Graphics g);

    @Override
    public void update(float delta) {
        super.update(delta);
    }

}
