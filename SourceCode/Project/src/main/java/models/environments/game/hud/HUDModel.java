package models.environments.game.hud;

import models.environments.game.GameModel;
import models.environments.game.hud.components.MapOverlay;
import models.environments.game.hud.components.PlayerStatsOverlay;
import models.environments.game.hud.components.TimeKeeperOverlay;
import props.prototypes.window.environments.AEnvironment;

import java.awt.*;

public class HUDModel extends AEnvironment {

    protected GameModel gameModel;

    private MapOverlay map;
    private PlayerStatsOverlay stats;
    private TimeKeeperOverlay timer;

    public HUDModel(GameModel gameModel) {
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
    }


}
