package models.environments.levelEnvironment.game.hud;

import models.environments.levelEnvironment.game.GameEnvironment;
import models.environments.levelEnvironment.game.hud.components.MapOverlay;
import models.environments.levelEnvironment.game.hud.components.PlayerStatsOverlay;
import models.environments.levelEnvironment.game.hud.components.TimeKeeperOverlay;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * <p>The HUDModel is the handler class for all AOverlayComponents of the GameEnvironment. It acts to update and
 * draw all AOverlayComponents as needed.</p>
 * @author Andrew Stephens
 */
public class HUDModel implements IDrawable, IUpdatable {

    /**<p>The parent GameEnvironment</p>*/
    protected GameEnvironment gameEnvironment;

    /**<p>The local MapOverlay</p>*/
    private MapOverlay map;
    /**<p>The local PlayerStatsOverlay</p>*/
    private PlayerStatsOverlay stats;
    /**<p>The local TimeKeeperOverlay</p>*/
    private TimeKeeperOverlay timer;

    /**
     * <p>Initializes the HUDModel with pre-created AOverlayComponent objects.</p>
     * @param gameEnvironment The parent GameEnvironment where the HUD components will be drawn to.
     * @param mapOverlay The MapOverlay HUD component.
     * @param playerStatsOverlay The PlayerStatsOverlay HUD Component.
     * @param timeKeeperOverlay The TimeKeeperOverlay HUD Component.
     */
    public void init(GameEnvironment gameEnvironment, MapOverlay mapOverlay,
                     PlayerStatsOverlay playerStatsOverlay, TimeKeeperOverlay timeKeeperOverlay) {
        this.gameEnvironment = gameEnvironment;
        this.map = mapOverlay;
        this.stats = playerStatsOverlay;
        this.timer = timeKeeperOverlay;

        map.init(gameEnvironment, Config.DEFAULT_WINDOW_WIDTH - 200, 0, 200, 200);
        stats.init(gameEnvironment,301, 0, 200, 200);
        timer.init(gameEnvironment,0, 0, 300, 200);
    }

    /**
     * <p>Acts to reset the map as-needed. Normally called during the reset of the parent GameEnvironment.</p>
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
