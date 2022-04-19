package models.environments.menus.mainmenu.submenus;

import models.prototypes.components.menuviews.types.ATextView;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.prototypes.components.menuviews.types.AButtonView;
import models.prototypes.components.menuviews.types.ASliderView;
import models.utils.config.Config;

import java.awt.*;
import java.util.ArrayList;

public class OptionsPage extends AMenu {

    public OptionsPage(AMenuEnvironment parentModel) {
        super(parentModel);

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        int textSizeW = btn_width, textSizeH = (int)(btn_height * .5f);

        ATextView text_fpsTitle = new ATextView(
                getParentEnvironment(),
                (int) (centerW - (btn_width * 3 * .5f)),
                (int)(300-(textSizeH)),
                btn_width * 3,
                (int)(textSizeH)){
        };
        text_fpsTitle.setText("FPS Limit");

        ATextView text_fps = new ATextView(
                getParentEnvironment(),
                (int) (centerW - (btn_width * .5f)) - btn_width,
                (int)(300 + (btn_height * .5f) - (textSizeH * .5f)),
                textSizeW,
                textSizeH){
        };
        text_fps.setText("" + Config.frameRate);
        text_fps.setBackgroundImage(getResources().getImage("slider_track"));
        text_fps.setBackgroundColor(new Color(255, 255, 255, 100));

        ASliderView slider_fps = new ASliderView(
                getParentEnvironment(),
                (int) (centerW - (btn_width * .5f)),
                300,
                btn_width * 2,
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
                for(; i < values.size(); i++) {
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
                text_fps.setText("" + Config.frameRate);
            }
        };
        slider_fps.showNotches(false);

        ASliderView slider_window = new ASliderView(
                getParentEnvironment(),
                (int) (centerW - (btn_width * .5f)),
                400,
                btn_width * 2,
                (int)(btn_height * .5f)) {
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

        addComponent(text_fpsTitle);
        addComponent(text_fps);
        addComponent(slider_fps);
        addComponent(slider_window);
        addComponent(button_back);
    }

}
