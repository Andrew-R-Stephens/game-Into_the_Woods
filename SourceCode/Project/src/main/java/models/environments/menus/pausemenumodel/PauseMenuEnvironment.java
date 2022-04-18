package models.environments.menus.pausemenumodel;

import controls.MenuControls;
import models.environments.EnvironmentsHandler;
import models.environments.game.GameEnvironment;
import models.environments.menus.pausemenumodel.submenus.PauseMenuPage;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;

import java.awt.*;

public class PauseMenuEnvironment extends AMenuEnvironment implements IDrawable {

    protected GameEnvironment gameEnvironment;

    public void init(EnvironmentsHandler parentEnvironmentsModel, MenuControls controlsModel,
                     GameEnvironment gameEnvironment) {
        super.init(parentEnvironmentsModel, controlsModel);

        this.gameEnvironment = gameEnvironment;

        PauseMenuPage landingMenu = new PauseMenuPage(this);

        initPage(landingMenu);
    }

    public void popToFirst() {
        while(getStackDepth() > 1) {
            pop();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, Config.window_width_actual, Config.window_height_actual);

        super.draw(g);
    }

    @Override
    public void onExit() {
        super.onExit();
        reset();
    }

    @Override
    public void startBackgroundAudio() {
    }

    @Override
    public void reset() {
        popToFirst();
    }
}
