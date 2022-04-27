package models.environments.menus.mainmenu.submenus;

import models.prototypes.components.menuviews.types.AButtonView;
import models.prototypes.components.menuviews.types.ASliderView;
import models.prototypes.components.menuviews.types.ATextView;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;

import java.awt.*;
import java.util.Arrays;

/**
 * <p>The Options Page.</p>
 * <p>Gives the user the option to set their target frame limit, their Window style, and the size of their window.</p>
 * @author Andrew Stephens
 */
public class OptionsPage extends AMenu {

    /**<p>The apply button used to accept changes.</p>*/
    private final AButtonView button_apply;

    /**<p>The user-defined framerate</p>*/
    private short selectedFramerate = Config.frameRate;
    /**<p>The user-defined window type</p>*/
    private Config.WindowType selectedWindowType = Config.getWindowType();

    /**
     * <p>Builds the Options page.</p>
     * @param parentEnvironment The parent AMenuEnvironment
     */
    public OptionsPage(AMenuEnvironment parentEnvironment) {
        super(parentEnvironment);

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        int textSizeW = btn_width, textSizeH = (int)(btn_height * .5f);

        ATextView text_fpsTitle = new ATextView(
                getParentEnvironment(),
                (int) (centerW - (btn_width * 3 * .5f)),
                (300-(textSizeH)),
                btn_width * 3,
                (textSizeH),
                "FPS Limit")
        {
        };
        text_fpsTitle.setBackgroundColor(new Color(255, 255, 255, 150));

        ATextView text_fps = new ATextView(
                getParentEnvironment(),
                (int) (centerW - (btn_width * .5f)) - btn_width,
                (int)(300 + (btn_height * .5f) - (textSizeH * .5f)),
                textSizeW,
                textSizeH,
                selectedFramerate + "")
        {
        };
        text_fps.setBackgroundColor(new Color(255, 255, 255, 100));

        ASliderView<Short> slider_fps = new ASliderView<>(
                getParentEnvironment(),
                (int) (centerW - (btn_width * .5f)),
                300,
                btn_width * 2,
                btn_height) {
            @Override
            public void init() {
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                GraphicsDevice gs = ge.getDefaultScreenDevice();
                DisplayMode dm = gs.getDisplayMode();

                short MIN_REFRESH_RATE = 24;
                final short MAX_REFRESH_RATE = (short)dm.getRefreshRate();
                if(MIN_REFRESH_RATE > MAX_REFRESH_RATE) {
                    MIN_REFRESH_RATE = MAX_REFRESH_RATE;
                }
                for(short i = MIN_REFRESH_RATE; i <= MAX_REFRESH_RATE; i++) {
                    values.add(i);
                }
                itemCount = values.size();

                current = itemCount;
                int i = 0;
                for(; i < values.size(); i++) {
                    if (values.get(i) == MAX_REFRESH_RATE) {
                        current = i;
                        break;
                    }
                }
            }
            @Override
            public void doSetting() {
                if(values.get(current) != values.get(previous)) {
                    previous = current;
                    selectedFramerate = values.get(current);
                    text_fps.setText("" + selectedFramerate);
                }
            }
        };
        slider_fps.showNotches(false);

        ATextView text_windowTitle = new ATextView(
                getParentEnvironment(),
                (int) (centerW - (btn_width * 3 * .5f)),
                (450 - textSizeH),
                btn_width * 3,
                textSizeH,
                "Window Type")
        {
        };
        text_windowTitle.setBackgroundColor(new Color(255, 255, 255, 150));

        ATextView text_window = new ATextView(
                getParentEnvironment(),
                (int) (centerW - (btn_width * .5f)) - btn_width,
                (int)(450 + (btn_height * .5f) - (textSizeH * .5f)),
                textSizeW,
                textSizeH,
                selectedWindowType.getName())
        {
        };
        text_window.setBackgroundColor(new Color(255, 255, 255, 100));

        ASliderView<Config.WindowType> slider_window = new ASliderView<>(
                getParentEnvironment(),
                (int) (centerW - (btn_width * .5f)),
                450,
                btn_width * 2,
                btn_height) {
            @Override
            public void init() {
                values.addAll(Arrays.asList(Config.WindowType.values()));
                itemCount = values.size();

                current = itemCount;
                int i = 0;
                for(; i < values.size(); i++) {
                    if(values.get(i) == Config.getWindowType()) {
                        current = i;
                        break;
                    }
                }
                System.out.println(current + " / " + itemCount);
            }
            @Override
            public void doSetting() {
                if(values.get(current) != values.get(previous)) {
                    previous = current;
                    selectedWindowType = values.get(current);
                    text_window.setText(selectedWindowType.getName());
                }
            }
        };

        button_apply = new AButtonView(
                getParentEnvironment(),
                (int) (centerW - (btn_width * .5f)),
                800 - (btn_height * 2),
                btn_width,
                btn_height
        ) {
            @Override
            public void update(float delta) {
                super.update(delta);

                button_apply.setEnabled(
                        Config.frameRate != selectedFramerate ||
                        Config.getWindowType() != selectedWindowType);
            }

            @Override
            public void registerInput() {
                if(!isEnabled) {
                    return;
                }
                super.registerInput();
            }

            @Override
            public boolean onClick(float x, float y) {

                if(!isInBounds(x, y)) {
                    return false;
                }

                if(Config.frameRate != selectedFramerate) {
                    Config.frameRate = selectedFramerate;
                }

                if(Config.getWindowType() != selectedWindowType) {
                    Config.setWindowType(selectedWindowType);
                    getMouseController().reset();
                    getEnvironmentsHandler().rebuildWindow();
                }

                return true;
            }
        };
        button_apply.setEnabled(false);
        button_apply.setText("Apply");
        button_apply.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);


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
        addComponent(text_windowTitle);
        addComponent(text_window);
        addComponent(slider_window);
        addComponent(button_apply);
        addComponent(button_back);
    }

}
