package models.environments.game.playerinventory;

import models.actors.triggers.collectibles.key.DoorKey;
import models.prototypes.level.prop.trigger.collectibles.ACollectibleTrigger;

import java.util.ArrayList;

/**
 * <p></p>
 */
public class PlayerInventory {

    protected ArrayList<ACollectibleTrigger> collectibles = new ArrayList<>();

    /**
     * <p></p>
     * @param levelCollectible
     */
    public void addCollectible(ACollectibleTrigger levelCollectible) {
        collectibles.add(levelCollectible);
    }

    /**
     * <p></p>
     * @return
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
     * <p></p>
     */
    public void reset() {
        collectibles.clear();
    }
}
