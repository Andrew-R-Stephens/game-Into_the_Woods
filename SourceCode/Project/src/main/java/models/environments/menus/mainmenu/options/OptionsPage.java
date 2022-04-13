package models.environments.menus.mainmenu.options;

import models.prototypes.window.environments.menu.AMenu;
import models.prototypes.window.environments.menu.AMenuEnvironment;
import models.prototypes.window.environments.menu.components.types.AMenuButton;
import models.prototypes.window.environments.menu.components.types.AMenuSlider;
import models.utils.config.ConfigData;
import models.utils.resources.Resources;

import java.awt.*;
import java.util.ArrayList;

public class OptionsPage extends AMenu {

    public OptionsPage(AMenuEnvironment parentModel) {
        super(parentModel);

        image_background = Resources.getImage("menubackground");

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        AMenuSlider slider_fps = new AMenuSlider(
                parentMenuModel,
                (int) (centerW - (800 * .5f)),
                300,
                800,
                btn_height) {
            @Override
            public void init() {
                values = new ArrayList<>();

                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                GraphicsDevice[] gs = ge.getScreenDevices();
                DisplayMode dm = gs[0].getDisplayMode();

                final int MAX_REFRESH_RATE = dm.getRefreshRate();
                for(short i = 30; i <= MAX_REFRESH_RATE; i++) {
                    values.add(i);
                }
                itemCount = values.size();

                current = itemCount;
                int i = 0;
                for(; i < values.size(); i++) {
                    if(values.get(i) == ConfigData.frameRate) {
                        current = i;
                        break;
                    }
                }

            }
            @Override
            public void doSetting() {
                ConfigData.frameRate = values.get(current);
            }
        };

        AMenuSlider slider_window = new AMenuSlider(
                parentMenuModel,
                (int) (centerW - (800 * .5f)),
                400,
                800,
                (int)(btn_height*.5)) {
            @Override
            public void init() {
                values = new ArrayList<>();
                for(short i = 0; i <= 5; i++) {
                    values.add(i);
                }
                itemCount = values.size();
            }
            @Override
            public void doSetting() {
                System.out.println("Setting" + values.get(current));
            }
        };

        AMenuButton button_back = new AMenuButton(
                parentMenuModel,
                (int) (centerW - (btn_width * .5f)),
                800,
                btn_width,
                btn_height
        ) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuEnvironment.pop();

                return true;
            }
        };
        button_back.setText("Back");
        button_back.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        components.add(slider_fps);
        components.add(slider_window);
        components.add(button_back);
    }

}
