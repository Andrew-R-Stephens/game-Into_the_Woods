package models.environments.menus.mainmenu.options;

import models.prototypes.window.environments.menu.AMenu;
import models.prototypes.window.environments.menu.AMenuEnvironment;
import models.prototypes.window.environments.menu.components.types.AMenuButton;
import models.prototypes.window.environments.menu.components.types.AMenuSlider;
import models.utils.config.ConfigData;
import models.utils.resources.Resources;

import java.util.ArrayList;

public class OptionsPage extends AMenu {

    public OptionsPage(AMenuEnvironment parentModel) {
        super(parentModel);

        image_background = Resources.getImage("menubackground");

        float mx = ConfigData.DEFAULT_WINDOW_WIDTH * .5f;

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        AMenuSlider slider_fps = new AMenuSlider(
                parentMenuModel,
                (int) (mx - (800 * .5f)),
                300,
                800,
                btn_height) {
            @Override
            public void init() {
                values = new ArrayList<>();
                for(int i = 30; i <= 144; i++) {
                    values.add(i);
                }
                itemCount = values.size();
            }
            @Override
            public void doSetting() {
                System.out.println(values.get(current));
            }
        };

        AMenuSlider slider_window = new AMenuSlider(
                parentMenuModel,
                (int) (mx - (800 * .5f)),
                400,
                800,
                btn_height) {
            @Override
            public void init() {
                values = new ArrayList<>();
                for(int i = 0; i <= 5; i++) {
                    values.add(i);
                }
                itemCount = values.size();
            }
            @Override
            public void doSetting() {
                System.out.println(values.get(current));
            }
        };

        AMenuButton button_back = new AMenuButton(
                parentMenuModel,
                (int) (mx - (btn_width * .5f)),
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
