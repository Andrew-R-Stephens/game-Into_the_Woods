package models.environments.menus.mainmenu;

import graphics.ui.menu.components.MenuButton;
import models.environments.menus.mainmenu.children.*;
import props.prototypes.window.environments.menu.AMenuBundle;

import java.awt.*;

public class MainMenuModel extends AMenuBundle {

    public MainMenuModel() {

        NewGameMenu newGameMenu = new NewGameMenu();
        LevelSelectMenu levelSelectMenu = new LevelSelectMenu();
        HelpMenu helpMenu = new HelpMenu();
        OptionsMenu optionsMenu = new OptionsMenu();
        QuitMenu quitMenu = new QuitMenu();

        addPage(newGameMenu);
        addPage(levelSelectMenu);
        addPage(helpMenu);
        addPage(optionsMenu);
        addPage(quitMenu);

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("Main Menu!", 20, 20);

        for(MenuButton b: menuButtons)
            b.draw(g);
    }

    @Override
    public void update(float delta) {

    }
}
