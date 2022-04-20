package models.environments.menus.mainmenu;

import controls.MenuControls;
import models.camera.Camera;
import models.environments.EnvironmentsHandler;
import models.environments.menus.startscreen.StartScreenPage;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p></p>
 */
public class MainMenuEnvironment extends AMenuEnvironment {

    protected BufferedImage backgroundImage;

    /**
     * <p></p>
     * @param environmentsHandler -
     * @param menuControlsModel -
     */
    public void init(EnvironmentsHandler environmentsHandler, MenuControls menuControlsModel) {
        super.init(environmentsHandler, menuControlsModel);

        backgroundImage = getResources().getImage("menubackground");

        StartScreenPage startPage = new StartScreenPage(this);
        push(startPage);

        Camera.camX = Config.scaledW * Config.DEFAULT_WINDOW_WIDTH * .5f;
        Camera.camY = Config.scaledH * Config.DEFAULT_WINDOW_HEIGHT * .5f;
        Camera.targX = Config.scaledW * Config.DEFAULT_WINDOW_WIDTH * .5f;
        Camera.targY = Config.scaledH * Config.DEFAULT_WINDOW_HEIGHT * .5f;
    }

    /**
     * <p></p>
     * @return
     */
    public AMenu getTopPage() {
        return peek();
    }

    /**
     * <p></p>
     */
    public void popToFirst() {
        while(getStackDepth() > 1) {
            pop();
        }
    }

    /**
     * <p></p>
     */
    public void navigateToLevelSelectPage() {
        ((StartScreenPage)peek()).navigateToMainMenuPage().navigateToLevelSelectPage();
    }

    @Override
    public void update(float delta) {

        int x = getMouseController().getPos()[0];
        int y = getMouseController().getPos()[1];

        float tx =
                (Config.DEFAULT_WINDOW_WIDTH * Config.scaledW * .5f) - (x * .5f);
        float ty =
                (Config.DEFAULT_WINDOW_HEIGHT * Config.scaledH * .5f) - (y * .5f);

        Camera.moveTo(tx, ty);

        super.update(delta);

    }

    @Override
    public void draw(Graphics2D g) {

        if(backgroundImage != null) {
            g.drawImage(backgroundImage,
                    (int)((((Camera.camX * Config.scaledW) - (Config.DEFAULT_WINDOW_WIDTH * Config.scaledW)) * .10f)),
                    (int)((((Camera.camY * Config.scaledW) - (Config.DEFAULT_WINDOW_HEIGHT * Config.scaledH)) * .10f)),
                    (int)(Config.DEFAULT_WINDOW_WIDTH * 1.2f * Config.scaledW),
                    (int)(Config.DEFAULT_WINDOW_HEIGHT * 1.2f * Config.scaledH),
                    null);
        }

        super.draw(g);
    }

    @Override
    public void onExit() {
        super.onExit();

        popToFirst();
    }

    @Override
    public void startBackgroundAudio() {
        audioPlayer = getResources().playAudio("mainmenu");
    }

    @Override
    public void reset() {
        popToFirst();
        super.reset();
    }

}