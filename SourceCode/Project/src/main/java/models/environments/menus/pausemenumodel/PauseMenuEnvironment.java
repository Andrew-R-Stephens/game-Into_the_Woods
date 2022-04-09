package models.environments.menus.pausemenumodel;

import models.environments.game.GameEnvironment;
import models.prototypes.window.environments.menu.AMenuModel;
import models.utils.config.ConfigData;
import models.utils.drawables.IDrawable;
import views.menus.pausemenu.PauseMenuPage;

import java.awt.*;

public class PauseMenuEnvironment extends AMenuModel implements IDrawable {

    protected GameEnvironment gameEnvironment;

    public PauseMenuEnvironment() {
        PauseMenuPage landingMenu = new PauseMenuPage(this);

        initPage(landingMenu);
    }

    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    public void popToFirst() {
        while(getStackDepth() > 1) {
            pop();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, ConfigData.window_width_actual, ConfigData.window_height_actual);

        super.draw(g);
    }

    @Override
    public void onExit() {
        super.onExit();
        popToFirst();
    }

    @Override
    public void startBackgroundAudio() {
    }

    @Override
    public void reset() {

    }
}
