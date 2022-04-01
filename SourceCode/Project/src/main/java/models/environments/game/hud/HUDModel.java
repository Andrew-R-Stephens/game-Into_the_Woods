package models.environments.game.hud;

import models.environments.game.GameEnvironment;
import models.environments.game.hud.components.MapOverlay;
import models.environments.game.hud.components.PlayerStatsOverlay;
import models.environments.game.hud.components.TimeKeeperOverlay;
import utils.drawables.IDrawable;
import utils.updates.IUpdatable;

import java.awt.*;

/**
 * The type Hud model.
 */
public class HUDModel implements IDrawable, IUpdatable {

    /**
     * The Game model.
     */
    protected GameEnvironment gameModel;

    private final MapOverlay map;
    private final PlayerStatsOverlay stats;
    private final TimeKeeperOverlay timer;

    /**
     * Instantiates a new Hud model.
     *
     * @param gameModel the game model
     */
    public HUDModel(GameEnvironment gameModel) {
        this.gameModel = gameModel;

        timer = new TimeKeeperOverlay(0, 0, 300, 120);
        stats = new PlayerStatsOverlay(301, 0, 200, 120);
        map = new MapOverlay(1800, 0, 120, 120);
    }

    //TODO
    @Override
    public void draw(Graphics g) {
        map.draw(g);
        stats.draw(g);
        timer.draw(g);
    }

    //TODO
    @Override
    public void update(float delta) {
        map.update(delta);
        stats.update(delta);
        timer.update(delta);
    }


}
