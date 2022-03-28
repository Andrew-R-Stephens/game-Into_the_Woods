package models.environments.game.playerinventory;

import props.objects.levelprops.gametriggers.collectibles.ALevelCollectible;

import java.util.ArrayList;

public class PlayerInventory {

    protected ArrayList<ALevelCollectible> collectibles = new ArrayList<>();

    public void addCollectible(ALevelCollectible levelCollectible) {
        collectibles.add(levelCollectible);
    }
}
