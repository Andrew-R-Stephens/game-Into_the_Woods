package models.environments.menu.pausemenumodel;

import models.environments.game.GameEnvironment;
import models.environments.menu.pausemenumodel.primarymenu.PauseMenuPage;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import utils.config.ConfigData;

import java.awt.*;

/**
 * The type Pause menu model.
 */
public class PauseMenuModel extends AMenuModel {

    protected GameEnvironment gameEnvironment;

    /**
     * Instantiates a new Pause menu model.
     */
    public PauseMenuModel() {
        PauseMenuPage landingMenu = new PauseMenuPage(this);

        initPage(landingMenu);
    }

    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(50, 0, 50, 50));
        g.fillRect(0, 0, ConfigData.window_width_actual, ConfigData.window_height_actual);

        super.draw(g);

        g.setColor(Color.RED);
        g.drawString("Pause Menu!", 20, 20);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void reset() {

    }
}
