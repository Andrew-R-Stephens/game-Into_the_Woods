package models.prototypes.level.prop;

import models.levels.LevelsList;
import models.prototypes.actor.AActor;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;
import views.renders.Tile;

/**
 * <p>AProp is an abstract entity type that derives from AActor. This type is simply a wrapper class for Level
 * props.</p>
 * <p>Level props should be allowed to draw to the Map Overlay, which is why the drawToHUD() method is added.</p>
 * @author Andrew Stephens
 */
public abstract class AProp extends AActor implements IUpdatable {

    public enum Side {
        TOP, BOTTOM, START, END, BODY
    }

    public Side side = Side.BODY;

    protected int cols = 1;
    protected int rows = 1;

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
    protected AProp(float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(x * LevelsList.WORLD_SCALE,
                y * LevelsList.WORLD_SCALE,
                w * LevelsList.WORLD_SCALE,
                h * LevelsList.WORLD_SCALE,
                vx, vy, hasGravity);
    }

    public void isHighlighted(boolean b) {
        isHighlighted = b;
    }

    public AProp[] createTiles() {
        cols = Math.max(1, (int)Math.ceil(Math.ceil(w) / Tile.W));
        rows = Math.max(1, (int)Math.ceil(Math.ceil(h) / Tile.H));
        return new AProp[rows * cols];
    }

    public void calcSubImages() {}

    @Override
    public void update(float delta) {
        super.update(delta);

        setX(x += vX);
        setY(y += vY);

    }

    public boolean equals(Object obj) {
        if (obj instanceof AProp op) {
            return getX() != op.getX() && getY() != op.getY();
        } else return false;
    }

    public void reset() { }
}
