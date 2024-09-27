package models.prototypes.level.prop;

import models.levels.LevelsList;
import models.prototypes.actor.AActor;
import models.prototypes.level.prop.tile.TileProp;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;
import views.Tile;

import java.awt.*;
import java.util.ArrayList;

/**
 * <p>AProp is an abstract entity type that derives from AActor. This type is simply a wrapper class for Level
 * props.</p>
 * <p>Level props should be allowed to draw to the Map Overlay, which is why the drawToHUD() method is added.</p>
 * @author Andrew Stephens
 */
public abstract class AProp extends AActor implements IDrawable, IHUDDrawable, IUpdatable {

    protected boolean isHighlighted = false;

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
        super(resources,
                x * LevelsList.WORLD_SCALE,
                y * LevelsList.WORLD_SCALE,
                w * LevelsList.WORLD_SCALE,
                h * LevelsList.WORLD_SCALE,
                vx, vy, hasGravity);
    }

    @Override
    public void draw(Graphics2D g) {}

    @Override
    public abstract void drawAsHUD(Graphics2D g);

    public void isHighlighted(boolean b) {
        isHighlighted = b;
    }

    public AProp[] createTiles() {
        int cols = Math.max(1, (int)Math.ceil(w / (float) Tile.W)) + 1;
        int rows = Math.max(1, (int)Math.ceil(h / (float) Tile.H)) + 1;
        System.out.println("rows: " + rows + "; cols: " + cols);
        return new AProp[rows * cols];
    }

    public void calcSubImages() {}

    @Override
    public void update(float delta) {
        super.update(delta);

        setX(x += vX);
        setY(y += vY);

    }

}
