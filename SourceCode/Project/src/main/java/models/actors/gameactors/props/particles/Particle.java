package models.actors.gameactors.props.particles;

import models.prototypes.actor.pawn.APawn;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;

public class Particle extends APawn implements IDrawable, IUpdatable {

    public Particle(Resources resources, float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(resources, x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

}
