package models.environments.game.hud;

import models.environments.game.GameModel;
import props.prototypes.window.environments.AEnvironment;

import java.awt.*;

public class HUDModel extends AEnvironment {

    protected GameModel gameModel;

    public HUDModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void draw(Graphics g) {
        //TODO
        g.drawRect( 10, 10, 500, 500);
    }

    @Override
    public void update(float delta) {
        //TODO
    }
}
