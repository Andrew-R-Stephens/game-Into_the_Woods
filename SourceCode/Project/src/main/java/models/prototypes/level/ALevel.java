package models.prototypes.level;

import models.actors.triggers.collectibles.key.DoorKey;
import models.actors.triggers.interactibles.Door;
import models.environments.game.GameEnvironment;
import models.environments.game.background.ParallaxBackground;
import models.prototypes.actor.AActor;
import models.prototypes.level.prop.AProp;
import models.prototypes.level.prop.trigger.ATrigger;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * <p>ALevel standardizes the data of a level.</p>
 * @author Andrew Stephens
 */
public abstract class ALevel implements IDrawable, IHUDDrawable, IUpdatable {

    /**<p>The parent GameEnvironment.</p>*/
    protected GameEnvironment gameEnvironment;

    /**<p>The moving background for the level.</p>*/
    private final ParallaxBackground scrollingBackground = new ParallaxBackground();

    /**<p>The spawning point of the player.</p>*/
    protected int[] startOrigin = new int[2];
    /**<p>The Props that exist within this level.</p>*/
    protected final ArrayList<AProp> levelProps = new ArrayList<>();
    /**<p>The Door that allows the player to exit the level.</p>*/
    protected Door door;

    /**<p>The number of keys that exist in the level.</p>*/
    protected int keyCount = 0;

    /**
     * <p>Creates a level and obtains reference to the game environment of the level.</p>
     * @param gameEnvironment The parent game environment
     */
    public ALevel(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * <p>Adds a new Prop to the Level.</p>
     * @param prop The prop to be added.
     */
    protected void addProp(AProp prop) {
        levelProps.add(prop);
    }

    /**
     * <p>Gets the props of the level.</p>
     * @return The list of props in the level.
     */
    public ArrayList<AProp> getLevelProps() {
        return levelProps;
    }

    /**
     * <p>Gets the defined spawn origin point for the player to start in.</p>
     * @return The defined spawn origin.
     */
    public int[] getCharacterOrigin() {
        return startOrigin;
    }

    /**
     * <p>Sets the spawn origin point for the player to start in.</p>
     * @param x The horizontal position of the origin.
     * @param y The vertical position of the origin.
     */
    public void setStartOrigin(int x, int y) {
        startOrigin = new int[]{x, y};
    }

    /**
     * <p>Adds a background layer to the parallax background in the level.</p>
     * @param backgroundImage The background image to be added as a layer of the background
     */
    protected void addBackgroundLayer(BufferedImage backgroundImage) {
        scrollingBackground.addLayer(backgroundImage);
    }

    /**
     * <p>Counts the number of keys that exist in the level.</p>
     */
    public void countKeys() {
        for (AActor levelProps : getLevelProps()) {
            if (levelProps instanceof DoorKey) {
                this.keyCount++;
            }
        }
    }

    /**
     * <p>Retrieves the number of keys in the level.</p>
     * @return The number of Keys in the level
     */
    public int getKeyCount() {
        return keyCount;
    }

    /**
     * <p>Builds the level.</p>
     */
    public void build() {
        countKeys();
    }

    /**
     * <p>Resets the props of each level.</p>
     */
    public void reset() {
        for(AProp p: levelProps) {
            if(p instanceof ATrigger t) {
                t.reset();
            }
        }
    }

    /**
     * <p>Gets the reference to the Resources.</p>
     * @return The reference to Resources.
     */
    public Resources getResources() {
        return gameEnvironment.getResources();
    }

    /**
     * <p>Unlocks the door of the level.</p>
     */
    public void unlockDoor() {
        door.unlock();
    }

    @Override
    public void draw(Graphics2D g) {
        scrollingBackground.draw(g);

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
    public void drawAsHUD(Graphics2D g) {
        g.setColor(new Color(50, 50,50));
        g.fillRect(0, 0, Config.window_width_actual, Config.window_height_actual);

        for (AActor levelProps : getLevelProps()) {
            if (levelProps instanceof AProp p) {
                p.drawAsHUD(g);
            }
        }

    }

}
