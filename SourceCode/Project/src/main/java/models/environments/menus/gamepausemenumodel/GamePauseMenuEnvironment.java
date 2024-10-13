package models.environments.menus.gamepausemenumodel;

import controls.menu.MenuControls;
import models.environments.EnvironmentType;
import models.environments.EnvironmentsHandler;
import models.environments.levelEnvironment.game.GameEnvironment;
import models.environments.menus.gamepausemenumodel.submenus.GamePauseMenuPage;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;

import java.awt.*;

/**
 * <p>PauseMenuEnvironment is a subtype of AMenuEnvironment. It derives behavior from the AMenu class and is contained
 * within the GameEnvironment. This class handles menu-based user interaction from within the Game Environment.</p>
 * @author Andrew Stephens
 */
public class GamePauseMenuEnvironment extends AMenuEnvironment implements IDrawable {

    /**<p>The GameEnvironment that this pause menu belonds to</p>*/
    protected GameEnvironment gameEnvironment;

    /**
     * <p>Initializes the MainMenuEnvironment with the parent EnvironmentsHandler and necessary MenuControls.</p>
     * <p>Sets the default page to the PauseMenuPage.</p>
     * @param environmentsHandler The EnvironmentsHandler that contains this MenuEnvironment
     * @param controlsModel The MenuControls that is required for this Environment
     * @param gameEnvironment The parent GameEnvironment
     */
    public void init(EnvironmentsHandler environmentsHandler, MenuControls controlsModel,
                     GameEnvironment gameEnvironment) {
        super.init(environmentsHandler, controlsModel);

        this.gameEnvironment = gameEnvironment;

        GamePauseMenuPage landingMenu = new GamePauseMenuPage(this, EnvironmentType.GAME);

        initPage(landingMenu);
    }

    /**
     * <p>Pops the top-most AMenus until the landing page is found.</p>
     */
    public void popToFirst() {
        while(getStackDepth() > 1) {
            pop();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, Config.window_width_actual, Config.window_height_actual);

        super.draw(g);
    }

    @Override
    public void onExit() {
        //super.onExit();
        reset();
    }

    @Override
    public void startBackgroundAudio() {
        gameEnvironment.startBackgroundAudio();
    }

    @Override
    public void stopBackgroundAudio() {
        gameEnvironment.stopBackgroundAudio();
    }

    @Override
    public void setAudioPlayer() {

    }

    @Override
    public void reset() {
        popToFirst();
    }
}
