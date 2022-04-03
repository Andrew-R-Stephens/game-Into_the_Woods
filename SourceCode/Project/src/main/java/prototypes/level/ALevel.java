package prototypes.level;

import models.environments.game.GameEnvironment;
import props.objects.levelprops.gametriggers.collectibles.key.LevelKey;
import props.objects.levels.inventory.LevelCollectibles;
import prototypes.actor.AActor;
import prototypes.level.prop.ALevelProp;
import prototypes.level.prop.trigger.ATrigger;
import prototypes.level.prop.trigger.collectibles.ALevelCollectible;
import utils.config.ConfigData;
import utils.drawables.IDrawable;
import utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * The type A level.
 */
public abstract class ALevel implements IDrawable, IUpdatable {

    protected BufferedImage backgroundImage;

    /**
     * The Game model.
     */
    protected GameEnvironment gameModel;

    protected int[] startOrigin = new int[2];

    protected final ArrayList<ALevelProp> levelProps = new ArrayList<>();

    protected LevelCollectibles collectibles;

    /**
     * Instantiates a new A level.
     *
     * @param gameModel the game model
     */
    public ALevel(GameEnvironment gameModel) {
        this.gameModel = gameModel;
    }

    protected void addProp(ALevelProp prop) {
        levelProps.add(prop);
    }

    /**
     * Gets level props.
     *
     * @return the level props
     */
    public ArrayList<ALevelProp> getLevelProps() {
        return levelProps;
    }

    public int[] getCharacterOrigin() {
        return startOrigin;
    }

    public void setStartOrigin(int x, int y) {
        startOrigin = new int[]{x, y};
    }

    protected void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public abstract void build();

    public void reset() {
        for(ALevelProp p: levelProps) {
            if(p instanceof ATrigger t) {
                t.reset();
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(
                backgroundImage,
                0,
                0,
                ConfigData.window_width_actual,
                ConfigData.window_height_actual,
                null);

        for (AActor levelProps : getLevelProps()) {
            if (levelProps instanceof ALevelProp p) {
                p.draw(g);
            }
        }
    }

    @Override
    public void update(float delta) {

    }

    public void drawHUD(Graphics g) {
        g.setColor(new Color(50, 50,50));
        g.fillRect(0, 0, ConfigData.window_width_actual, ConfigData.window_height_actual);

        for (AActor levelProps : getLevelProps()) {
            if (levelProps instanceof ALevelProp p) {
                p.draw(g);
            }
        }
    }
}
