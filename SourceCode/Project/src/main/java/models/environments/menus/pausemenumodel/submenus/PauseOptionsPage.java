package models.environments.menus.pausemenumodel.submenus;

import models.prototypes.components.menuviews.types.AButtonView;
import models.prototypes.components.menuviews.types.ASliderView;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p></p>
 */
public class PauseOptionsPage extends AMenu {

    /**
     * <p></p>
     * @param parentModel -
     */
    public PauseOptionsPage(AMenuEnvironment parentModel) {
        super(parentModel);

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        ASliderView<Short> slider_fps = new ASliderView<>(
                getParentEnvironment(),
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
                    if(values.get(i) == Config.frameRate) {
                        current = i;
                        break;
                    }
                }
                System.out.println(current + " / " + itemCount);
            }
            @Override
            public void doSetting() {
                Config.frameRate = values.get(current);
            }
        };

        ASliderView<Config.WindowType> slider_window = new ASliderView<>(
                getParentEnvironment(),
                (int) (centerW - (800 * .5f)),
                400,
                800,
                (int)(btn_height*.5)) {
            @Override
            public void init() {
                values = new ArrayList<>();
                values.addAll(Arrays.asList(Config.WindowType.values()));
                itemCount = values.size();
            }
            @Override
            public void doSetting() {
                if(values.get(current) != values.get(previous)) {
                }
            }
        };

        AButtonView button_back = new AButtonView(
                getParentEnvironment(),
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

                getParentEnvironment().pop();

                return true;
            }
        };
        button_back.setText("Back");
        button_back.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        addComponent(slider_fps);
        addComponent(slider_window);
        addComponent(button_back);
    }

}
