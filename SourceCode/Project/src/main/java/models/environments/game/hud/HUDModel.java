package models.environments.game.hud;

import models.environments.game.GameEnvironment;
import models.environments.game.hud.components.MapOverlay;
import models.environments.game.hud.components.PlayerStatsOverlay;
import models.environments.game.hud.components.TimeKeeperOverlay;
import models.environments.game.playerinventory.PlayerInventory;
import utils.config.ConfigData;
import utils.drawables.IDrawable;
import utils.files.Resources;
import utils.updates.IUpdatable;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * The type Hud model.
 */
public class HUDModel implements IDrawable, IUpdatable {

    /**
     * The Game model.
     */
    protected GameEnvironment gameEnvironment;
    protected PlayerInventory inventory;

    private MapOverlay map;
    private PlayerStatsOverlay stats;
    private TimeKeeperOverlay timer;

    public void init(GameEnvironment gameEnvironment, PlayerInventory inventory) {
        this.gameEnvironment = gameEnvironment;
        this.inventory = inventory;

        timer = new TimeKeeperOverlay(gameEnvironment,0, 0, 300, 120);
        stats = new PlayerStatsOverlay(gameEnvironment,301, 0, 200, 120);
        map = new MapOverlay(gameEnvironment, 1800, 0, 120, 120);
    }

    @Override
    public void update(float delta) {
        map.update(delta);
        stats.update(delta);
        timer.update(delta);
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(
                Resources.getFont("bahnschrift")
                        .deriveFont(AffineTransform.getScaleInstance(.8, 1))
                        .deriveFont(Font.PLAIN, 18 * ConfigData.scaledW));

        map.draw(g);
        stats.draw(g);
        timer.draw(g);

        g.setFont(null);
    }

}
