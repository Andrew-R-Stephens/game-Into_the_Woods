package models.environments.game.playerinventory;

import models.actors.gameactors.props.triggers.collectibles.key.LevelKey;
import models.prototypes.level.prop.trigger.collectibles.ACollectibleTrigger;

import java.util.ArrayList;

public class PlayerInventory {

    protected ArrayList<ACollectibleTrigger> collectibles = new ArrayList<>();

    public void addCollectible(ACollectibleTrigger levelCollectible) {
        collectibles.add(levelCollectible);
    }

    public int getKeyCount() {
        int count = 0;
        for(ACollectibleTrigger c: collectibles) {
            if(c instanceof LevelKey) {
                count ++;
            }
        }
        return count;
    }

    public void reset() {
        collectibles.clear();
    }
}
