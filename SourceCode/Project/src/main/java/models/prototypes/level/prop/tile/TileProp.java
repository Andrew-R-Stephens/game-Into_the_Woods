package models.prototypes.level.prop.tile;

import models.prototypes.level.prop.AProp;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;
import views.Tile;

import java.awt.*;

public class TileProp<T extends AProp> implements IDrawable, IUpdatable {

    private final float W = Tile.W, H = Tile.H;

    public TileProp() {

    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void update(float delta) {

    }
}
