package models.actors.platforms;

import models.prototypes.level.prop.AProp;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;
import views.renders.Tile;

/**
 * <p>A Platform is a physical barrier object. It is the fundamental piece that allows
 * the other actors to behave as if in a physical world.</p>
 * @author Andrew Stephens
 */
public class Platform extends AProp implements IUpdatable {

    /**
     * <p>Called from the subtypes, this method initializes the object.</p>
     * @param x The horizontal position, relative to the default dimensions.
     * @param y The y position, relative to the default dimensions.
     * @param w The width, relative to the default dimensions.
     * @param h The height, relative to the default dimensions.
     * @param vx The horizontal velocity.
     * @param vy The vertical velocity.
     * @param hasGravity If the object should be effected by gravity.
     */
    public Platform(
            float x, float y, float w, float h, float vx, float vy,
            boolean hasGravity) {
        super(x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public AProp[] createTiles() {
        AProp[] tiles = super.createTiles();
        int index = 0;
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                float x = roundCoordinate(getX() + (col * Tile.W));
                float y = roundCoordinate(getY() + (row * Tile.H));
                Platform platform = new Platform(x, y, Tile.W, Tile.H, vX, vY, hasGravity);
                tiles[index] = platform;
                index++;
            }
        }
        return tiles;
    }
}
