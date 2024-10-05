package models.prototypes.level;

import models.actors.platforms.Platform;
import models.actors.triggers.collectibles.key.DoorKey;
import models.actors.triggers.interactibles.Door;
import models.actors.triggers.interactibles.Spikes;
import models.actors.viewport.Viewport;
import models.environments.game.background.ParallaxBackground;
import models.prototypes.actor.AActor;
import models.prototypes.environments.AEnvironment;
import models.prototypes.level.prop.AProp;
import models.prototypes.level.propChunk.PropChunk;
import models.prototypes.level.propChunk.PropChunks;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;
import views.renders.tile.platform.APlatformTile;
import views.renders.tile.platform.PlatformBodyTile;
import views.renders.tile.platform.PlatformBottomTile;
import views.renders.tile.platform.PlatformTopTile;
import views.renders.tile.spike.ASpikesTile;
import views.renders.tile.spike.SpikesTile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static models.camera.Camera.camX;
import static models.camera.Camera.camY;
import static models.utils.config.Config.scaledH_zoom;
import static models.utils.config.Config.scaledW_zoom;

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
    //protected ArrayList<AProp> levelProps = new ArrayList<>();
    private PropChunks chunks;
    /**<p>The Door that allows the player to exit the level.</p>*/
    protected Door door;

    private PlatformTopTile platformTileTop;
    private PlatformBottomTile platformTileBottom;
    private PlatformBodyTile platformTileBody;
    private ASpikesTile spikesTile;

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
        //levelProps.add(prop);
    }

    /*
    public ArrayList<AProp> getLevelProps() {
        return levelProps;
    }
    */

    public void setLocalChunks(Viewport viewport) {
        chunks.setLocal(viewport);
    }

    /**
     * <p>Gets the props of the level.</p>
     * @return The list of props in the level.
     */
    public ArrayList<PropChunk> getLocalChunks() {
        return chunks.getLocal();
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
        parallaxBackground.addLayer(backgroundImage);
    }

    /**
     * <p>Counts the number of keys that exist in the level.</p>
     */
    public void countKeys() {
        /*for (AActor levelProps : getPropsInChunk()) {
            if (levelProps instanceof DoorKey) {
                this.keyCount++;
            }
        }*/
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
    public void build(LevelModelRW.LevelModel levelModel) {

        platformTileTop = new PlatformTopTile(getResources(), levelModel.typeImages.get("platform").get(0));
        platformTileBody = new PlatformBodyTile(getResources(), levelModel.typeImages.get("platform").get(1));
        platformTileBottom = new PlatformBottomTile(getResources(), levelModel.typeImages.get("platform").get(2));
        spikesTile = new SpikesTile(getResources(), levelModel.typeImages.get("spikes").get(0));

        /*
        ArrayList<AProp> tempTiles = new ArrayList<>();
        for (Prop p : levelModel.props) {
            AProp prop = createProp(levelModel, p);
            if (prop != null) {
                AProp[] tiles = prop.createTiles();
                tempTiles.addAll(Arrays.asList(tiles));
            }
        }

        levelProps.clear();
        levelProps.addAll(tempTiles);
        */

        chunks = new PropChunks(levelModel);

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
        /*for(AProp p: levelProps) {
            if(p instanceof ATrigger t) {
                t.reset();
            }
        }*/
        chunks.resetAll();
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
    public void update(float delta) {

        for(PropChunk chunk: getLocalChunks()) {
            for(AProp[] propsO: chunk.getAllProps()) {
                for (AActor prop : propsO) {
                    if (prop == null) continue;
                    prop.update(delta);
                }
            }
        }
        chunks.update();
    }

    @Override
    public void draw(Graphics2D g) {
        parallaxBackground.draw(g);

        for(PropChunk chunk: getLocalChunks()) {
            drawChunk(g, chunk.getBounds());
            for(AProp[] propsO: chunk.getAllProps()) {
                for (AActor prop : propsO) {
                    if (prop == null) continue;
                    if (prop instanceof Platform platform) {
                        switch(platform.side) {
                            case TOP -> {
                                platformTileTop.draw(g, platform);
                            }
                            case BOTTOM -> {
                                platformTileBottom.draw(g, platform);
                            }
                            default -> platformTileBody.draw(g, platform);
                        }
                    } else if (prop instanceof Spikes spikes) {
                        spikesTile.draw(g, spikes);
                    }
                }
            }
        }
    }

    @Override
    public void drawAsHUD(Graphics2D g) {
        g.setColor(new Color(50, 50,50));
        g.fillRect(0, 0, Config.window_width_actual, Config.window_height_actual);

        for(PropChunk chunk: getLocalChunks()) {
            for(AProp[] propsO: chunk.getAllProps()) {
                for (AActor prop : propsO) {
                    if (prop == null) continue;
                    if (prop instanceof Platform platform) {
                        switch(platform.side) {
                            case TOP -> {
                                platformTileTop.drawAsHUD(g, platform);
                            }
                            case BOTTOM -> {
                                platformTileBottom.drawAsHUD(g, platform);
                            }
                            default -> platformTileBody.drawAsHUD(g, platform);
                        }
                    } else if (prop instanceof Spikes spikes) {
                        spikesTile.drawAsHUD(g, spikes);
                    }
                }
            }
        }
    }

    private void drawChunk(Graphics g, int[] bounds) {
        g.setColor(Color.DARK_GRAY);

        float offsetX = ((bounds[0] * scaledW_zoom) + (camX));
        float offsetY = ((bounds[1] * scaledH_zoom) + (camY));

        g.setColor(Color.BLUE);
        g.drawRect(
                (int) Math.floor(offsetX),
                (int) Math.floor(offsetY),
                (int) Math.floor(bounds[2] * scaledW_zoom) + 1,
                (int) Math.floor(bounds[3] * scaledH_zoom) + 1
        );

    }

}
