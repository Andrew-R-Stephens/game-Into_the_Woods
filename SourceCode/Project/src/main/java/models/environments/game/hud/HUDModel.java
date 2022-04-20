package models.environments.game.hud;

import models.environments.game.GameEnvironment;
import models.environments.game.hud.components.MapOverlay;
import models.environments.game.hud.components.PlayerStatsOverlay;
import models.environments.game.hud.components.TimeKeeperOverlay;
import models.environments.game.playerinventory.PlayerInventory;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * <p></p>
 */
public class HUDModel implements IDrawable, IUpdatable {

    protected GameEnvironment gameEnvironment;
    protected PlayerInventory inventory;

    private MapOverlay map;
    private PlayerStatsOverlay stats;
    private TimeKeeperOverlay timer;

    /**
     * <p></p>
     * @param gameEnvironment -
     * @param inventory -
     * @param mapOverlay -
     * @param playerStatsOverlay -
     * @param timeKeeperOverlay -
     */
    public void init(GameEnvironment gameEnvironment, PlayerInventory inventory, MapOverlay mapOverlay,
                     PlayerStatsOverlay playerStatsOverlay, TimeKeeperOverlay timeKeeperOverlay) {
        this.gameEnvironment = gameEnvironment;
        this.inventory = inventory;
        this.map = mapOverlay;
        this.stats = playerStatsOverlay;
        this.timer = timeKeeperOverlay;

        map.init(gameEnvironment, Config.DEFAULT_WINDOW_WIDTH - 200, 0, 200, 200);
        stats.init(gameEnvironment,301, 0, 200, 200);
        timer.init(gameEnvironment,0, 0, 300, 200);
    }

    /**
     * <p></p>
     */
    public void reset() {
        map.reset();
    }

    @Override
    public void update(float delta) {
        map.update(delta);
        stats.update(delta);
        timer.update(delta);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setFont(
                gameEnvironment.getResources().getFont("bahnschrift")
                        .deriveFont(AffineTransform.getScaleInstance(.8, 1))
                        .deriveFont(Font.PLAIN, 18 * Config.scaledW));

        map.draw(g);
        stats.draw(g);
        timer.draw(g);

        g.setFont(null);
    }

}
