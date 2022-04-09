package models.actors.gameactors.props.platforms;

import models.prototypes.actor.AActor;
import models.prototypes.level.prop.ALevelProp;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;

public class TestLevelProp extends ALevelProp implements IDrawable, IUpdatable {

    public TestLevelProp(float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        if (super.hasCollision(a, delta)) {
            c = Color.RED;
            return true;
        }
        c = Color.BLUE;
        return false;
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