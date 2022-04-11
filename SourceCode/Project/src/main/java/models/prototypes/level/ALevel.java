package models.prototypes.level;

import models.actors.gameactors.props.triggers.collectibles.key.KeyCollectible;
import models.actors.gameactors.props.triggers.interactibles.DoorTrigger;
import models.environments.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.level.prop.AProp;
import models.prototypes.level.prop.trigger.ATrigger;
import models.utils.config.ConfigData;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class ALevel implements IDrawable, IHUDDrawable, IUpdatable {

    protected GameEnvironment gameEnvironment;

    protected BufferedImage backgroundImage;
    protected int[] startOrigin = new int[2];
    protected final ArrayList<AProp> levelProps = new ArrayList<>();
    protected DoorTrigger door;

    protected int keyCount = 0;


    public ALevel(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    protected void addProp(AProp prop) {
        levelProps.add(prop);
    }

    public ArrayList<AProp> getLevelProps() {
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

    public void countKeys() {
        for (AActor levelProps : getLevelProps()) {
            if (levelProps instanceof KeyCollectible) {
                this.keyCount++;
            }
        }
    }

    public int getKeyCount() {
        return keyCount;
    }

    public void build() {
        countKeys();
    }

    public void reset() {
        for(AProp p: levelProps) {
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
            if (levelProps instanceof AProp p) {
                p.draw(g);
            }
        }
    }

    @Override
    public void update(float delta) {
        for(AProp p: levelProps) {
            p.update(delta);
        }
    }

    @Override
    public void drawAsHUD(Graphics g) {
        g.setColor(new Color(50, 50,50));
        g.fillRect(0, 0, ConfigData.window_width_actual, ConfigData.window_height_actual);

        for (AActor levelProps : getLevelProps()) {
            if (levelProps instanceof AProp p) {
                p.drawAsHUD(g);
            }
        }
    }

    public void unlockDoor() {
        door.unlock();
    }
}
