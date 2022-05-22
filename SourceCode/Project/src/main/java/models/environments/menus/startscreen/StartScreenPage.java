package models.environments.menus.startscreen;

import models.environments.menus.mainmenu.submenus.MainMenuPage;
import models.prototypes.components.menuviews.AMenuComponent;
import models.prototypes.components.menuviews.types.AButtonView;
import models.prototypes.components.menuviews.types.AImageView;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;

import java.awt.image.BufferedImage;

/**
 * <p>This is the default landing page on startup. Contains a single menu button that says "start".</p>
 * @author Andrew Stephens
 */
public class StartScreenPage extends AMenu {

    /**
     * <p>Creates the Start Screen Page</p>
     * @param parentEnvironment the Menu's parent Menu Environment
     */
    public StartScreenPage(AMenuEnvironment parentEnvironment) {

        super(parentEnvironment);

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        BufferedImage logo = getResources().getImage("logo");
        int logoWidth = (int)(logo.getWidth() * .75f);
        int logoHeight = (int)(logo.getHeight() * .75f);

        AImageView gameTitle = new AImageView(
                getParentEnvironment(),
                (int)(centerW - (logoWidth * .5f)),
                (int)((centerH * .5f) - (logoHeight * .5f)),
                logoWidth,
                logoHeight,
                logo,
                AMenuComponent.ImageScale.FIT_CENTERED
        );

        AButtonView startButton = new AButtonView(
                getParentEnvironment(),
                (int)(centerW - (btn_width * .5f)),
                (int)((centerH * 1.25f) - (btn_height * .5f)),
                btn_width,
                btn_height,
                "Start button",
                AButtonView.ImageScale.FIT_CENTERED
        ){
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)){
                    return false;
                }
                navigateToMainMenuPage();

                return true;
            }
        };

        addComponent(gameTitle);
        addComponent(startButton);
    }

    /**
     * <p>Navigates the user to the Main Menu Page</p>
     * @return the MainMenuPage to visit.
     */
    public MainMenuPage navigateToMainMenuPage() {
        getParentEnvironment().push(new MainMenuPage(getParentEnvironment()));
        return (MainMenuPage) (getParentEnvironment().peek());
    }
}
