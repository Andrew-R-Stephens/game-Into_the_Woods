package models.actors.triggers.interactibles;

import models.actors.player.PlayerAvatar;
import models.environments.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.environments.AEnvironment;
import models.prototypes.level.prop.AProp;
import models.prototypes.level.prop.trigger.ATrigger;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;
import models.textures.meshes.Tile;

/**
 * <p>Spikes are a harmful obstacle. Touching the obstacle immediately kills the player.</p>
 * @author Andrew Stephens
 */
public class Spikes extends ATrigger implements IDrawable, IHUDDrawable, IUpdatable {

    /**
     * <p>Called from the subtypes, this method initializes the object. Also initializes the respective spriteSheet.</p>
     * @param resources The resources of the parent Environment
     * @param x The horizontal position, relative to the default dimensions.
     * @param y The y position, relative to the default dimensions.
     * @param w The width, relative to the default dimensions.
     * @param h The height, relative to the default dimensions.
     * @param vx The horizontal velocity.
     * @param vy The vertical velocity.
     * @param MAX_CYCLES The number of times this object can create an action. -1 is infinite.
     */
    public Spikes(Resources resources, AEnvironment environment,
                  float x, float y, float w, float h, float vx, float vy,
                  int MAX_CYCLES) {
        super(resources, environment, x, y, w, h, vx, vy, MAX_CYCLES, false, false);
    }

    public Spikes(
                  float x, float y, float w, float h, float vx, float vy,
                  int MAX_CYCLES) {
        super(x, y, w, h, vx, vy, MAX_CYCLES, false, false);
    }

    @Override
    public AProp[] createTiles() {
        AProp[] tiles = super.createTiles();
        int index = 0;
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                float x = roundCoordinate(getX() + (col * Tile.W));
                float y = roundCoordinate(getY() + (row * Tile.H));
                Spikes spike = new Spikes(x, y, Tile.W, Tile.H, vX, vY, MAX_CYCLES);
                tiles[index] = spike;
                index++;
            }
        }
        return tiles;
    }

    @Override
    public void doAction() {
        if(environment instanceof GameEnvironment ge) {
            ge.doPlayerDeath();
        }
    }

    @Override
    public boolean checkCollision(AActor a, float delta) {
        if(!(a instanceof PlayerAvatar)) {
            return false;
        }

        boolean hasCollision = super.checkCollision(a, delta);

        if(MAX_CYCLES != -1) {
            if (currentCycles > MAX_CYCLES) {
                return false;
            }
        }

        if(hasCollision) {
            doAction();
            currentCycles++;
        }

        return hasCollision;
    }

}
