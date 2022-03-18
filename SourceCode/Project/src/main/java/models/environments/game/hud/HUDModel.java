package models.environments.game.hud;

import models.environments.game.GameModel;
import props.prototypes.window.environments.AEnvironment;

import java.awt.*;

public class HUDModel extends AEnvironment {

    protected GameModel gameModel;
    /*
    TODO: Implement all of these

    private MapOverlay map;
    private PlayerStatsOverlay stats;
    private TimeKeeper timer;
     */

    public HUDModel(GameModel gameModel) {
        this.gameModel = gameModel;

        /*
        TODO: Implement all of these

        private MapOverlay map;
        private PlayerStatsOverlay stats;
        private TimeKeeper timer;

         */
    }

    //TODO
    @Override
    public void draw(Graphics g) {
        g.drawString( "HUD", 500, 500);

        /*
        TODO: Implement all of these

        map.draw(g);
        stats.draw(g);
        timer.draw(g);

        */
    }

    //TODO
    @Override
    public void update(float delta) {
        /*
        TODO: Implement all of these

        map.update(delta);
        stats.update(delta);
        timer.update(delta);

        */
    }
}
