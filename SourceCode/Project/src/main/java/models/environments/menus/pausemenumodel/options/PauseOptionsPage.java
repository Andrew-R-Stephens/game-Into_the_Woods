package models.environments.menus.pausemenumodel.options;

import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.prototypes.environments.menu.components.types.AButtonView;
import models.prototypes.environments.menu.components.types.ASliderView;
import models.utils.config.ConfigData;

import java.awt.*;
import java.util.ArrayList;

public class PauseOptionsPage extends AMenu {

    public PauseOptionsPage(AMenuEnvironment parentModel) {
        super(parentModel);

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        ASliderView slider_fps = new ASliderView(
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

                final int MIN_REFRESH_RATE = 1;
                final int MAX_REFRESH_RATE = dm.getRefreshRate();
                for(short i = MIN_REFRESH_RATE; i <= MAX_REFRESH_RATE; i++) {
                    values.add(i);
                }
                itemCount = values.size();

                current = itemCount;
                int i = 0;
                for(; i < itemCount; i++) {
                    if(values.get(i) == ConfigData.frameRate) {
                        current = i;
                        break;
                    }
                }
                System.out.println(current + " / " + itemCount);
            }
            @Override
            public void doSetting() {
                ConfigData.frameRate = values.get(current);
            }
        };

        ASliderView slider_window = new ASliderView(
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

        AButtonView button_back = new AButtonView(
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
        button_back.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        components.add(slider_fps);
        components.add(slider_window);
        components.add(button_back);
    }

}
