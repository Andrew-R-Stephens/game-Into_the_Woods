package models.prototypes.level;

import models.actors.platforms.Platform;
import models.actors.triggers.collectibles.key.DoorKey;
import models.actors.triggers.interactibles.Door;
import models.actors.triggers.interactibles.Spikes;
import models.actors.triggers.interactibles.Spring;
import models.environments.game.background.ParallaxBackground;
import models.prototypes.actor.AActor;
import models.prototypes.environments.AEnvironment;
import models.prototypes.level.prop.AProp;
import models.prototypes.level.prop.tile.TileProp;
import models.prototypes.level.prop.trigger.ATrigger;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;
import models.prototypes.level.LevelData.LevelModel.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * <p>ALevel standardizes the data of a level.</p>
 * @author Andrew Stephens
 */
public abstract class ALevel implements IDrawable, IHUDDrawable, IUpdatable {

    /**<p>The parent GameEnvironment.</p>*/
    protected AEnvironment environment;

    /**<p>The moving background for the level.</p>*/
    private final ParallaxBackground parallaxBackground = new ParallaxBackground();

    /**<p>The spawning point of the player.</p>*/
    protected int[] startOrigin = new int[2];
    /**<p>The Props that exist within this level.</p>*/
    protected ArrayList<AProp> levelProps = new ArrayList<>();
    /**<p>The Door that allows the player to exit the level.</p>*/
    protected Door door;

    protected final ArrayList<AProp> tileProps = new ArrayList<>();

    /**<p>The number of keys that exist in the level.</p>*/
    protected int keyCount = 0;

    /**
     * <p>Creates a level and obtains reference to the game environment of the level.</p>
     * @param environment The parent game environment
     */
    public ALevel(AEnvironment environment) {
        this.environment = environment;
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

    public AProp createProp(
            LevelData.LevelModel levelModel,
            LevelData.LevelModel.Prop propData
    ) {
        AProp outProp = null;
        switch (propData.type) {
            case "platform": {
                String[] arr = new String[0];
                outProp = new Platform(
                        getResources(),
                        levelModel.typeImages.get(
                                propData.type
                        ).toArray(arr),
                        propData.coords.x,
                        propData.coords.y,
                        propData.dims.w,
                        propData.dims.h,
                        propData.hasGravity
                );
                break;
            }
            case "spikes": {
                outProp = new Spikes(
                        getResources(),
                        environment,
                        propData.coords.x,
                        propData.coords.y,
                        propData.dims.w,
                        propData.dims.h,
                        propData.maxCycles
                );
                break;
            }
            case "spring": {
                outProp = new Spring(
                        getResources(),
                        environment,
                        levelModel.typeImages.get(
                                propData.type
                        ).get(0),
                        propData.coords.x,
                        propData.coords.y,
                        propData.dims.w,
                        propData.dims.h,
                        propData.maxCycles
                );
                break;
            }
            case "doorKey": {
                outProp = new DoorKey(
                        getResources(),
                        environment,
                        propData.coords.x,
                        propData.coords.y,
                        propData.dims.w,
                        propData.dims.h
                );
                break;
            }
            case "door": {
                outProp = new Door(
                        getResources(),
                        environment,
                        levelModel.typeImages.get(
                                propData.type
                        ).get(0),
                        propData.coords.x,
                        propData.coords.y,
                        propData.dims.w,
                        propData.dims.h,
                        propData.maxCycles
                );
                break;
            }
        }
        return outProp;
    }

    /**
     * <p>Adds a background layer to the parallax background in the level.</p>
     * @param backgroundImage The background image to be added as a layer of the background
     */
    protected void addBackgroundLayer(BufferedImage backgroundImage) {
        parallaxBackground.addLayer(backgroundImage);
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
    public void build(LevelData.LevelModel levelModel) {

        ArrayList<AProp> allTiles = new ArrayList<>();
        for (Prop p : levelModel.props) {
            AProp prop = createProp(levelModel, p);
            if (prop != null) {
                AProp[] tiles = prop.createTiles();
                Collections.addAll(allTiles, tiles);
            }
        }
        levelProps = allTiles;

        build();
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
        return environment.getResources();
    }

    /**
     * <p>Unlocks the door of the level.</p>
     */
    public void unlockDoor() {
        door.unlock();
    }

    @Override
    public void draw(Graphics2D g) {
        parallaxBackground.draw(g);

        for (AActor levelProps : getLevelProps()) {
            if (levelProps instanceof AProp p && p.canRender()) {
                p.draw(g);
            }
        }
    }

    @Override
    public void update(float delta) {
        for(AProp p: levelProps) {
            if(p == null) continue;
            p.update(delta);
        }
    }

    @Override
    public void drawAsHUD(Graphics2D g) {
        g.setColor(new Color(50, 50,50));
        g.fillRect(0, 0, Config.window_width_actual, Config.window_height_actual);

        for (AActor p : getLevelProps()) {
            if(p == null) continue;
            p.drawAsHUD(g);
        }

    }

}
