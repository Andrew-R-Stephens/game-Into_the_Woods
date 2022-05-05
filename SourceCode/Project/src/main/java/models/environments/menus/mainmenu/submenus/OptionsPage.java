package models.environments.menus.mainmenu.submenus;

import models.prototypes.components.menuviews.types.AButtonView;
import models.prototypes.components.menuviews.types.ACheckboxView;
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
    /**<p>The user-defined window dimension</p>*/
    private int selectedWindowWidth = Config.window_width_selected,
            selectedWindowHeight = Config.window_height_selected;
    /**<p>The user-defined window dimension</p>*/
    private boolean selectedAudioEnabled = Config.audioEnabled;

    /**
     * <p>Builds the Options page.</p>
     * @param parentEnvironment The parent AMenuEnvironment
     */
    public OptionsPage(AMenuEnvironment parentEnvironment) {
        super(parentEnvironment);

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        int textSizeW = btn_width, textSizeH = (int)(btn_height * .5f);


        // ===========
        // FPS
        // ===========

        ATextView text_fpsTitle = new ATextView(
                getParentEnvironment(),
                (int) (centerW - (btn_width * 3 * .5f)),
                (200-(textSizeH)),
                btn_width * 3,
                (textSizeH),
                "FPS Limit")
        {
        };
        text_fpsTitle.setBackgroundColor(new Color(255, 255, 255, 150));

        ATextView text_fps = new ATextView(
                getParentEnvironment(),
                (int) (centerW - (btn_width * .5f)) - btn_width,
                (int)(200 + (btn_height * .5f) - (textSizeH * .5f)),
                textSizeW,
                textSizeH,
                selectedFramerate + "")
        {
        };
        text_fps.setBackgroundColor(new Color(255, 255, 255, 100));

        ASliderView<Short> slider_fps = new ASliderView<>(
                getParentEnvironment(),
                (int) (centerW - (btn_width * .5f)),
                200,
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
                    if (values.get(i) == selectedFramerate) {
                        current = i;
                        break;
                    }
                }

                button.setX(this.x + (int)(notchDistance*current));
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

        // ===========
        // WINDOW TYPE
        // ===========

        ATextView text_windowTypeTitle = new ATextView(
                getParentEnvironment(),
                (int) (centerW - (btn_width * 3 * .5f)),
                (350 - textSizeH),
                btn_width * 3,
                textSizeH,
                "Window Type")
        {
        };
        text_windowTypeTitle.setBackgroundColor(new Color(255, 255, 255, 150));

        ATextView text_windowType = new ATextView(
                getParentEnvironment(),
                (int) (centerW - (btn_width * .5f)) - btn_width,
                (int)(350 + (btn_height * .5f) - (textSizeH * .5f)),
                textSizeW,
                textSizeH,
                selectedWindowType.getName())
        {
        };
        text_windowType.setBackgroundColor(new Color(255, 255, 255, 100));

        ASliderView<Config.WindowType> slider_windowType = new ASliderView<>(
                getParentEnvironment(),
                (int) (centerW - (btn_width * .5f)),
                350,
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
                    text_windowType.setText(selectedWindowType.getName());
                }
            }
        };

        // ===========
        // WINDOW DIMS
        // ===========
        ATextView text_windowDimsTitle = new ATextView(
                getParentEnvironment(),
                (int) (centerW - (btn_width * 3 * .5f)),
                (550 - textSizeH),
                btn_width * 3,
                textSizeH,
                "Window Dimensions")
        {
        };
        text_windowDimsTitle.setBackgroundColor(new Color(255, 255, 255, 150));

        ATextView text_windowDims = new ATextView(
                getParentEnvironment(),
                (int) (centerW - (btn_width * .5f)) - btn_width,
                (int)(550 + (btn_height * .5f) - (textSizeH * .5f)),
                textSizeW,
                textSizeH,
                selectedWindowWidth + " x " + selectedWindowHeight)
        {
        };
        text_windowDims.setBackgroundColor(new Color(255, 255, 255, 100));

        ASliderView<Dimension> slider_windowDims = new ASliderView<>(
                getParentEnvironment(),
                (int) (centerW - (btn_width * .5f)),
                550,
                btn_width * 2,
                btn_height) {
            @Override
            public void init() {
                Dimension[] dimensions = new Dimension[Config.getDisplayData().getWindowDimensions().size()];
                Config.getDisplayData().getWindowDimensions().toArray(dimensions);
                values.addAll(Arrays.asList(dimensions));
                itemCount = values.size();

                current = itemCount;
                int i = 0;
                for(; i < values.size(); i++) {
                    if(values.get(i).width == Config.getWindowWidthSelected() &&
                            values.get(i).height == Config.getWindowHeightSelected()) {
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
                    selectedWindowWidth = (int)values.get(current).getWidth();
                    selectedWindowHeight = (int)values.get(current).getHeight();
                    text_windowDims.setText(selectedWindowWidth + " x " + selectedWindowHeight);
                }
            }
        };

        ATextView text_audioEnabled = new ATextView(
                getParentEnvironment(),
                (int) (centerW - (btn_width * .5f)) - btn_width,
                (int)(650 + (btn_height * .5f) - (textSizeH * .5f)),
                textSizeW,
                textSizeH,
                "Enable Audio")
        {};
        text_audioEnabled.setBackgroundColor(new Color(255, 255, 255, 100));

        ACheckboxView checkbox_audioEnabled = new ACheckboxView(
                getParentEnvironment(),
                text_audioEnabled.getX() + text_audioEnabled.getW() + 50,
                text_audioEnabled.getY(),
                text_audioEnabled.getH(),
                text_audioEnabled.getH()
        ) {
            @Override
            public void init() {
                button.setEnabled(selectedAudioEnabled);
                System.out.println(selectedAudioEnabled);
            }

            @Override
            public void doSetting() {
                selectedAudioEnabled = !button.isEnabled;
                System.out.println(selectedAudioEnabled);
            }
        };


        // ===========
        // APPLY
        // ===========

        button_apply = new AButtonView(
                getParentEnvironment(),
                (int) (centerW - (btn_width * 1.25f)),
                800,
                btn_width,
                btn_height
        ) {
            @Override
            public void update(float delta) {
                super.update(delta);

                button_apply.setEnabled(
                        Config.frameRate != selectedFramerate ||
                        Config.getWindowType() != selectedWindowType ||
                        (selectedWindowWidth != Config.getWindowWidthSelected() &&
                                selectedWindowHeight != Config.getWindowHeightSelected()) ||
                        Config.audioEnabled != selectedAudioEnabled
                );
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

                boolean rebuildWindow = false;

                if(Config.frameRate != selectedFramerate) {
                    Config.frameRate = selectedFramerate;
                }

                if(Config.getWindowType() != selectedWindowType) {
                    Config.setWindowType(selectedWindowType);

                    rebuildWindow = true;
                }

                if((Config.window_width_selected != selectedWindowWidth) &&
                        (Config.window_height_selected != selectedWindowHeight)) {
                    Config.window_width_selected = selectedWindowWidth;
                    Config.window_height_selected = selectedWindowHeight;

                    rebuildWindow = true;
                }

                if(Config.audioEnabled != selectedAudioEnabled) {
                    Config.audioEnabled = selectedAudioEnabled;

                    if(Config.audioEnabled) {
                        System.out.println("Starting Background Audio from Options");
                        System.out.println(getEnvironmentsHandler().getCurrentEnvironment().getClass());
                        getEnvironmentsHandler().getCurrentEnvironment().startBackgroundAudio();
                    } else {
                        getEnvironmentsHandler().getCurrentEnvironment().stopBackgroundAudio();
                    }

                    rebuildWindow = true;
                }

                if(rebuildWindow) {
                    getEnvironmentsHandler().rebuildWindow();
                }

                getEnvironmentsHandler().getSaveData().save();

                return true;
            }
        };
        button_apply.setEnabled(false);
        button_apply.setText("Apply");
        button_apply.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        // ===========
        // BACK
        // ===========

        int backbtn_w = (int)(btn_width * .5f);
        AButtonView button_back = new AButtonView(
                getParentEnvironment(),
                (int) (centerW + (backbtn_w * .25f)),
                800,
                backbtn_w,
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

        addComponent(text_windowTypeTitle);
        addComponent(text_windowType);
        addComponent(slider_windowType);

        addComponent(text_windowDimsTitle);
        addComponent(text_windowDims);
        addComponent(slider_windowDims);

        addComponent(text_audioEnabled);
        addComponent(checkbox_audioEnabled);

        addComponent(button_apply);
        addComponent(button_back);
    }

}
