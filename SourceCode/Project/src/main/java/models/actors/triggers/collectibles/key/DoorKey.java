package models.actors.triggers.collectibles.key;

import models.camera.Camera;
import models.environments.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.environments.AEnvironment;
import models.prototypes.level.prop.trigger.collectibles.ACollectibleTrigger;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;

/**
 * <p>The DoorKey acts as a collectible. The item is "collected" if the player crosses the boundary into that item.</p>
 * <p>As a collectible, it is added to the Player's Inventory once acquired.</p>
 * <p>If acquired, the object reference is passed to the Inventory, and this item is set to effectively invisible
 * by setting its state to not active.</p>
 * @author Andrew Stephens
 */
public class DoorKey extends ACollectibleTrigger implements IDrawable, IUpdatable {

    /**
     * <p>Called from the subtypes, this method initializes the object.</p>
     * @param resources The resources of the parent Environment
     * @param environment The GameEnvironment of the parent Environment
     * @param x The horizontal position, relative to the default dimensions.
     * @param y The y position, relative to the default dimensions.
     * @param w The width, relative to the default dimensions.
     * @param h The height, relative to the default dimensions.
     * @param vx The horizontal velocity.
     * @param vy The vertical velocity.
     */
    public DoorKey(Resources resources, AEnvironment environment, float x, float y, float w, float h, float vx, float vy) {
        super(resources, environment, x, y, w, h, vx, vy, 1,false, false);
    }

    public DoorKey(Resources resources, AEnvironment environment, float x, float y, float w, float h) {
        super(resources, environment, x, y, w, h, 0, 0, 1,false, false);
    }

    @Override
    public boolean checkCollision(AActor a, float delta) {
        return super.checkCollision(a, delta);
    }

    @Override
    public void doAction() {
        super.doAction();

        if(environment instanceof GameEnvironment ge &&
                ge.getPlayerInventory().getKeyCount() >= ge.getCurrentLevel().getKeyCount()) {
            ge.getLevelsList().getCurrentLevel().unlockDoor();
        }

        resources.getAudioPlayer("door_key").play();
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics2D g) {
        if(!isActive) {
            return;
        }

        float[] offset = Camera.getRelativeOffset(x, y);
        float[] scale = Camera.getRelativeScale(w, h);

        g.drawImage(
                resources.getImage("key"),
                (int)offset[0],
                (int)offset[1],
                (int)scale[0],
                (int)scale[1],
                null);

        if(Config.DEBUG && isHighlighted) {
            Color c = Color.RED;
            g.setColor(c);
            g.drawRect((int) (offset[0]), (int) (offset[1]),
                    (int) (w * Config.scaledW_zoom),
                    (int) (h * Config.scaledH_zoom));
            g.drawString(x + " " + y, (int) (offset[0]), (int) (offset[1]));
        }
    }

    @Override
    public void drawAsHUD(Graphics2D g) {

        g.setColor(Color.GREEN);

        float[] offset = Camera.getRelativeOffsetBy(x, y, Camera.SCALE_MINIMAP);
        float[] scale = Camera.getRelativeScaleBy(w, h, Camera.SCALE_MINIMAP);

        g.fillRect((int) offset[0], (int) offset[1], (int) scale[0], (int) scale[1]);

    }

}
