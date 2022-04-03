package models.environments.game.playerinventory;

import props.objects.levelprops.gametriggers.collectibles.key.LevelKey;
import prototypes.level.prop.trigger.collectibles.ALevelCollectible;

import java.util.ArrayList;

public class PlayerInventory {

    protected ArrayList<ALevelCollectible> collectibles = new ArrayList<>();

    public void addCollectible(ALevelCollectible levelCollectible) {
        collectibles.add(levelCollectible);
    }

    public int getKeysCount() {
        int count = 0;
        for(ALevelCollectible c: collectibles) {
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
