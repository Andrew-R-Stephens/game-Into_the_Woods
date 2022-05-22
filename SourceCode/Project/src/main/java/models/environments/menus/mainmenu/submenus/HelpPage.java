package models.environments.menus.mainmenu.submenus;

import models.prototypes.components.menuviews.AMenuComponent;
import models.prototypes.components.menuviews.types.AButtonView;
import models.prototypes.components.menuviews.types.AImageView;
import models.prototypes.components.menuviews.types.ATextView;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p>The Help Page, containing text content to assist the player.</p>
 * @author Andrew Stephens
 */
public class HelpPage extends AMenu {

    /**
     * <p>Builds the Help page.</p>
     * @param parentEnvironment The parent AMenuEnvironment
     */
    public HelpPage(AMenuEnvironment parentEnvironment) {
        super(parentEnvironment);

        float mx = Config.DEFAULT_WINDOW_WIDTH * .5f;
        float my = Config.DEFAULT_WINDOW_HEIGHT * .5f;

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        ATextView text_title = new ATextView(
                getParentEnvironment(),
                (int)mx - btn_width, 100,
                (btn_width * 2), (int)(btn_height * .75f),
                "HELP",
                new Color(255, 255, 255, 150)
                ) {};

        AButtonView button_objectives = new AButtonView(
                getParentEnvironment(),
                (int) (mx - (btn_width * .5f)),
                225,
                btn_width,
                btn_height,
                "Objectives",
                AButtonView.ImageScale.FIT_CENTERED
        ) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                getParentEnvironment().push(new HelpObjectivesPage(getParentEnvironment()));

                return true;
            }
        };

        AButtonView button_obstacles = new AButtonView(
                getParentEnvironment(),
                (int) (mx - (btn_width * .5f)),
                button_objectives.getY() + btn_height + 25,
                btn_width,
                btn_height,
                "Obstacles",
                AButtonView.ImageScale.FIT_CENTERED
        ) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                getParentEnvironment().push(new HelpObstaclesPage(getParentEnvironment()));

                return true;
            }
        };

        AButtonView button_controls = new AButtonView(
                getParentEnvironment(),
                (int) (mx - (btn_width * .5f)),
                button_obstacles.getY() + btn_height + 25,
                btn_width,
                btn_height,
                "Controls",
                AButtonView.ImageScale.FIT_CENTERED
        ) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                getParentEnvironment().push(new HelpControlsPage(getParentEnvironment()));

                return true;
            }
        };

        AButtonView button_credits = new AButtonView(
                getParentEnvironment(),
                (int) (mx - (btn_width * .5f)),
                button_controls.getY() + btn_height + 25,
                btn_width,
                btn_height,
                "Credits",
                AButtonView.ImageScale.FIT_CENTERED
        ) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                getParentEnvironment().push(new HelpCreditsPage(getParentEnvironment()));

                return true;
            }
        };

        int backbtn_w = (int)(btn_width * .5f);
        AButtonView button_back = new AButtonView(
                getParentEnvironment(),
                (int) (mx - (backbtn_w * .5f)),
                800,
                backbtn_w,
                btn_height,
                "Back",
                AButtonView.ImageScale.FIT_CENTERED
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

        addComponent(text_title);
        addComponent(button_objectives);
        addComponent(button_obstacles);
        addComponent(button_controls);
        addComponent(button_credits);
        addComponent(button_back);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        super.draw(g);
    }

    /**
     * The Objectives Page for the Help Meu
     */
    private class HelpObjectivesPage extends AMenu{

        public HelpObjectivesPage(AMenuEnvironment parentEnvironment) {
            super(parentEnvironment);

            float mx = Config.DEFAULT_WINDOW_WIDTH * .5f;
            float my = Config.DEFAULT_WINDOW_HEIGHT * .5f;

            int btn_width = 400, btn_height = (int)(btn_width * .25);

            ATextView text_title = new ATextView(
                    getParentEnvironment(),
                    (int)mx - btn_width, 100,
                    (btn_width * 2), (int)(btn_height * .75f),
                    "OBJECTIVES",
                    new Color(255, 255, 255, 150)
            ) {};

            BufferedImage bufferedImage_objectives_img = getResources().getImage("help_objectives");

            AImageView img_objectives = new AImageView(
                    getParentEnvironment(),
                    (int) (mx - (bufferedImage_objectives_img.getWidth() * .5f)),
                    text_title.getY() + text_title.getH() + 100,
                    bufferedImage_objectives_img.getWidth(),
                    bufferedImage_objectives_img.getHeight(),
                    bufferedImage_objectives_img,
                    AMenuComponent.ImageScale.FIT_CENTERED
            );

            BufferedImage bufferedImage_objectives_text = getResources().getImage("help_objectives_text");

            AImageView text_objectives = new AImageView(
                    getParentEnvironment(),
                    (int) (mx - (img_objectives.getW() * .5f)),
                    img_objectives.getY() + img_objectives.getH(),
                    img_objectives.getW(),
                    img_objectives.getH(),
                    bufferedImage_objectives_text,
                    AMenuComponent.ImageScale.FIT_CENTERED
            );

            int backbtn_w = (int)(btn_width * .5f);
            AButtonView button_back = new AButtonView(
                    getParentEnvironment(),
                    (int) (mx - (backbtn_w * .5f)),
                    800,
                    backbtn_w,
                    btn_height,
                    "Back",
                    AButtonView.ImageScale.FIT_CENTERED
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

            addComponent(text_title);
            addComponent(img_objectives);
            addComponent(text_objectives);
            addComponent(button_back);
        }

    }

    /**
     * The Objectives Page for the Help Meu
     */
    private class HelpObstaclesPage extends AMenu{

        public HelpObstaclesPage(AMenuEnvironment parentEnvironment) {
            super(parentEnvironment);

            float mx = Config.DEFAULT_WINDOW_WIDTH * .5f;
            float my = Config.DEFAULT_WINDOW_HEIGHT * .5f;

            int btn_width = 400, btn_height = (int)(btn_width * .25);


            ATextView text_title = new ATextView(
                    getParentEnvironment(),
                    (int)mx - btn_width, 100,
                    (btn_width * 2), (int)(btn_height * .75f),
                    "OBSTACLES",
                    new Color(255, 255, 255, 150)
            ) {};

            BufferedImage bufferedImage_obstacles_img = getResources().getImage("help_obstacles");
            AImageView img_obstacles = new AImageView(
                    getParentEnvironment(),
                    (int) (mx - (bufferedImage_obstacles_img.getWidth() * .5f)),
                    text_title.getY() + text_title.getH() + 50,
                    bufferedImage_obstacles_img.getWidth(),
                    bufferedImage_obstacles_img.getHeight(),
                    bufferedImage_obstacles_img,
                    AMenuComponent.ImageScale.FIT_CENTERED
            );

            int backbtn_w = (int)(btn_width * .5f);
            AButtonView button_back = new AButtonView(
                    getParentEnvironment(),
                    (int) (mx - (backbtn_w * .5f)),
                    800,
                    backbtn_w,
                    btn_height,
                    "Back",
                    AButtonView.ImageScale.FIT_CENTERED
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

            addComponent(text_title);
            addComponent(img_obstacles);
            addComponent(button_back);
        }

    }

    /**
     * The Objectives Page for the Help Meu
     */
    private class HelpControlsPage extends AMenu{

        public HelpControlsPage(AMenuEnvironment parentEnvironment) {
            super(parentEnvironment);

            float mx = Config.DEFAULT_WINDOW_WIDTH * .5f;
            float my = Config.DEFAULT_WINDOW_HEIGHT * .5f;

            int btn_width = 400, btn_height = (int)(btn_width * .25);

            ATextView text_title = new ATextView(
                    getParentEnvironment(),
                    (int)mx - btn_width, 100,
                    (btn_width * 2), (int)(btn_height * .75f),
                    "CONTROLS",
                    new Color(255, 255, 255, 150)
            ) {};

            BufferedImage bufferedImage_objectives_img = getResources().getImage("help_controls");
            AImageView img_objectives = new AImageView(
                    getParentEnvironment(),
                    (int) (mx - (1200 * .5f)),
                    text_title.getY() + text_title.getH() + 25,
                    1200,
                    600,
                    bufferedImage_objectives_img,
                    AMenuComponent.ImageScale.FIT_CENTERED
            );

            int backbtn_w = (int)(btn_width * .5f);
            AButtonView button_back = new AButtonView(
                    getParentEnvironment(),
                    (int) (mx - (backbtn_w * .5f)),
                    800,
                    backbtn_w,
                    btn_height,
                    "Back",
                    AButtonView.ImageScale.FIT_CENTERED
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

            addComponent(text_title);
            addComponent(img_objectives);
            addComponent(button_back);
        }

    }

    /**
     * The Credits Page for the Help Meu
     */
    private class HelpCreditsPage extends AMenu{

        public HelpCreditsPage(AMenuEnvironment parentEnvironment) {
            super(parentEnvironment);

            float mx = Config.DEFAULT_WINDOW_WIDTH * .5f;
            float my = Config.DEFAULT_WINDOW_HEIGHT * .5f;

            int btn_width = 400, btn_height = (int)(btn_width * .25);

            ATextView text_title = new ATextView(
                    getParentEnvironment(),
                    (int)mx - btn_width, 100,
                    (btn_width * 2), (int)(btn_height * .75f),
                    "CREDITS",
                    new Color(255, 255, 255, 150)
            ) {};

            /*
             * Andrew Stephens
             */
            ATextView text_title_andrew = new ATextView(
                    getParentEnvironment(),
                    (int)mx - btn_width,
                    text_title.getY() + text_title.getH() + 50,
                    (int)(text_title.getW() * .4),
                    (int)(btn_height * .5f),
                    "Andrew Stephens",
                    new Color(255, 255, 255),
                    new Color(0, 0, 0, 0)
            ) {};

            int subitemHight = (int)(text_title_andrew.getH() * .75f);

            ATextView text_title_andrew_a = new ATextView(
                    getParentEnvironment(),
                    text_title_andrew.getX() + text_title_andrew.getW(),
                    (int)(text_title_andrew.getY() + (subitemHight*.25)),
                    text_title.getW() - text_title_andrew.getW(),
                    subitemHight,
                    "Team Leader",
                    new Color(255, 255, 255),
                    new Color(0, 0, 0, 0)
            ) {};

            ATextView text_title_andrew_b = new ATextView(
                    getParentEnvironment(),
                    text_title_andrew_a.getX(),
                    text_title_andrew_a.getY() + text_title_andrew_a.getH() + 10,
                    text_title.getW() - text_title_andrew.getW(),
                    subitemHight,
                    "Game Director / Designer",
                    new Color(255, 255, 255),
                    new Color(0, 0, 0, 0)
            ) {};

            ATextView text_title_andrew_c = new ATextView(
                    getParentEnvironment(),
                    text_title_andrew_a.getX(),
                    text_title_andrew_b.getY() + text_title_andrew_b.getH() + 10,
                    text_title.getW() - text_title_andrew.getW(),
                    subitemHight,
                    "Game Programmer",
                    new Color(255, 255, 255),
                    new Color(0, 0, 0, 0)
            ) {};

            ATextView text_title_andrew_d = new ATextView(
                    getParentEnvironment(),
                    text_title_andrew_a.getX(),
                    text_title_andrew_c.getY() + text_title_andrew_c.getH() + 10,
                    text_title.getW() - text_title_andrew.getW(),
                    subitemHight,
                    "Software Documentation",
                    new Color(255, 255, 255),
                    new Color(0, 0, 0, 0)
            ) {};

            /*
             * Emiz Intriago
             */
            ATextView text_title_emiz = new ATextView(
                    getParentEnvironment(),
                    (int)mx - btn_width,
                    text_title_andrew_d.getY() + text_title_andrew_d.getH() + 25,
                    text_title_andrew.getW(),
                    text_title_andrew.getH(),
                    "Emiz Intriago",
                    new Color(255, 255, 255),
                    new Color(0, 0, 0, 0)
            ) {};

            ATextView text_title_emiz_a = new ATextView(
                    getParentEnvironment(),
                    text_title_emiz.getX() + text_title_emiz.getW(),
                    (int)(text_title_emiz.getY() + (subitemHight*.25)),
                    text_title.getW() - text_title_emiz.getW(),
                    subitemHight,
                    "Graphics Designer",
                    new Color(255, 255, 255),
                    new Color(0, 0, 0, 0)
            ) {};

            ATextView text_title_emiz_b = new ATextView(
                    getParentEnvironment(),
                    text_title_emiz_a.getX(),
                    text_title_emiz_a.getY() + text_title_emiz_a.getH() + 10,
                    text_title_emiz_a.getW(),
                    subitemHight,
                    "Software Documentation",
                    new Color(255, 255, 255),
                    new Color(0, 0, 0, 0)
            ) {};

            /*
             * Gurkiran Kaur
             */
            ATextView text_title_kiran = new ATextView(
                    getParentEnvironment(),
                    (int)mx - btn_width,
                    text_title_emiz_b.getY() + text_title_emiz_b.getH() + 25,
                    text_title_andrew.getW(),
                    text_title_andrew.getH(),
                    "Gurkiran Kaur",
                    new Color(255, 255, 255),
                    new Color(0, 0, 0, 0)
            ) {};

            ATextView text_title_kiran_a = new ATextView(
                    getParentEnvironment(),
                    text_title_kiran.getX() + text_title_kiran.getW(),
                    (int)(text_title_kiran.getY() + (subitemHight*.25)),
                    text_title.getW() - text_title_kiran.getW(),
                    subitemHight,
                    "Software Documentation",
                    new Color(255, 255, 255),
                    new Color(0, 0, 0, 0)
            ) {};

            /*
             * Miranda Figueras
             */
            ATextView text_title_miranda = new ATextView(
                    getParentEnvironment(),
                    (int)mx - btn_width,
                    text_title_kiran_a.getY() + text_title_kiran_a.getH() + 25,
                    text_title_andrew.getW(),
                    text_title_andrew.getH(),
                    "Miranda Figueras",
                    new Color(255, 255, 255),
                    new Color(0, 0, 0, 0)
            ) {};

            ATextView text_title_miranda_a = new ATextView(
                    getParentEnvironment(),
                    text_title_miranda.getX() + text_title_miranda.getW(),
                    (int)(text_title_miranda.getY() + (subitemHight*.25)),
                    text_title.getW() - text_title_miranda.getW(),
                    subitemHight,
                    "Software Documentation",
                    new Color(255, 255, 255),
                    new Color(0, 0, 0, 0)
            ) {};

            /*
             * Wardah Aziz
             */
            ATextView text_title_wardah = new ATextView(
                    getParentEnvironment(),
                    (int)mx - btn_width,
                    text_title_miranda_a.getY() + text_title_miranda_a.getH() + 25,
                    text_title_andrew.getW(),
                    text_title_andrew.getH(),
                    "Wardah Aziz",
                    new Color(255, 255, 255),
                    new Color(0, 0, 0, 0)
            ) {};

            ATextView text_title_wardah_a = new ATextView(
                    getParentEnvironment(),
                    text_title_wardah.getX() + text_title_wardah.getW(),
                    (int)(text_title_wardah.getY() + (subitemHight*.25)),
                    text_title.getW() - text_title_wardah.getW(),
                    subitemHight,
                    "Software Documentation",
                    new Color(255, 255, 255),
                    new Color(0, 0, 0, 0)
            ) {};

            /*
             * BACK
             */
            int backbtn_w = (int)(btn_width * .5f);
            AButtonView button_back = new AButtonView(
                    getParentEnvironment(),
                    (int) (mx - (backbtn_w * .5f)),
                    800,
                    backbtn_w,
                    btn_height,
                    "Back",
                    AButtonView.ImageScale.FIT_CENTERED
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

            addComponent(text_title);
            addComponent(text_title_andrew);
            addComponent(text_title_andrew_a);
            addComponent(text_title_andrew_b);
            addComponent(text_title_andrew_c);
            addComponent(text_title_andrew_d);
            addComponent(text_title_emiz);
            addComponent(text_title_emiz_a);
            addComponent(text_title_emiz_b);
            addComponent(text_title_kiran);
            addComponent(text_title_kiran_a);
            addComponent(text_title_miranda);
            addComponent(text_title_miranda_a);
            addComponent(text_title_wardah);
            addComponent(text_title_wardah_a);
            addComponent(button_back);
        }

    }
}
