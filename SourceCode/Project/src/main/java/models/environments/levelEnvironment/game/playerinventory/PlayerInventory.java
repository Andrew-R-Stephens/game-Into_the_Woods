package models.environments.levelEnvironment.game.playerinventory;

import models.actors.triggers.collectibles.key.DoorKey;
import models.prototypes.level.prop.trigger.collectibles.ACollectibleTrigger;

import java.util.ArrayList;

/**
 * <p>The PlayerInventory contains relevant ad-hoc information about the player's collectibles status within the
 * current level.</p>
 * @author Andrew Stephens
 */
public class PlayerInventory {

    /**<p>The collectibles that the player has aquired from the level.</p>*/
    protected ArrayList<ACollectibleTrigger> collectibles = new ArrayList<>();

    /**
     * <p>When the player collects a Collectible item, the item is added to the inventory. These are later compared
     * against their type to determine the quantity of each or the effect of each.</p>
     * @param levelCollectible The collectible that the Player is said to have obtained.
     */
    public void addCollectible(ACollectibleTrigger levelCollectible) {
        collectibles.add(levelCollectible);
    }

    /**
     * <p>Used to obtain the number of Keys that the player has aquired.</p>
     * @return Cannot be less than 0 or more than the number of
     * keys that exist in the level.
     */
    public int getKeyCount() {
        int count = 0;
        for(ACollectibleTrigger c: collectibles) {
            if(c instanceof DoorKey) {
                count ++;
            }
        }
        return count;
    }

    /**
     * <p>Removes all of the current collectibles from the collection.</p>
     */
    public void reset() {
        collectibles.clear();
    }
}
