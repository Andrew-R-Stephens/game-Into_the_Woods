package models.prototypes.level;

import models.actors.platforms.Platform;
import models.actors.triggers.interactibles.Door;
import models.actors.triggers.interactibles.Spikes;
import models.actors.viewport.Viewport;
import models.environments.game.background.ParallaxBackground;
import models.prototypes.actor.AActor;
import models.prototypes.environments.AEnvironment;
import models.prototypes.level.prop.AProp;
import models.prototypes.level.propChunk.PropChunk;
import models.prototypes.level.propChunk.PropChunks;
import models.textures.meshes.platform.*;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;
import models.textures.meshes.spike.ASpikesTile;
import models.textures.meshes.spike.SpikesTile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static models.camera.Camera.camX;
import static models.camera.Camera.camY;
import static models.prototypes.level.prop.AProp.Side.*;
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
    public PropChunks allChunks;
    /**<p>The Door that allows the player to exit the level.</p>*/
    protected Door door;

    private APlatformTile platformTileBody,
            platformTileTop, platformTileBottom, platformTileStart,  platformTileEnd,
            platformTileCorner0, platformTileCorner1, platformTileCorner2, platformTileCorner3,
            platformTileVertical, platformTileHorizontal, platformTileFullStart, platformTileFullEnd;

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
        if(allChunks != null) {
            allChunks.setLocal(viewport);
        }
    }

    /**
     * <p>Gets the props of the level.</p>
     * @return The list of props in the level.
     */
    public ArrayList<PropChunk> getLocalChunks() {
        if(allChunks == null) {
            return new ArrayList<>(0);
        }
        return allChunks.getLocal();
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

        configureTileImages(levelModel);

        allChunks = new PropChunks(levelModel);

        build();
    }

    private void configureTileImages(LevelModelRW.LevelModel levelModel) {

        BufferedImage platformBody = getResources().getImage(levelModel.typeImages.get("platform").get(0));
        BufferedImage platformTop = getResources().getImage(levelModel.typeImages.get("platform").get(1));
        BufferedImage platformBottom = getResources().getImage(levelModel.typeImages.get("platform").get(2));
        BufferedImage platformStart = getResources().getImage(levelModel.typeImages.get("platform").get(3));
        BufferedImage platformEnd = getResources().getImage(levelModel.typeImages.get("platform").get(4));

        platformTileBody = new APlatformTile(new BufferedImage[]{ platformBody });

        platformTileTop = new APlatformTile(new BufferedImage[]{ platformBody, platformTop });
        platformTileBottom = new APlatformTile(new BufferedImage[]{ platformBody, platformBottom });
        platformTileStart  = new APlatformTile(new BufferedImage[]{ platformBody, platformStart });
        platformTileEnd = new APlatformTile(new BufferedImage[]{ platformBody, platformEnd });

        platformTileVertical  = new APlatformTile(
                new BufferedImage[]{ platformBody, platformTop, platformBottom });
        platformTileHorizontal = new APlatformTile(
                new BufferedImage[]{ platformBody, platformStart, platformEnd });

        platformTileFullStart  = new APlatformTile(
                new BufferedImage[]{ platformBody, platformTop, platformBottom, platformStart },
                new boolean[]{true, false, true, false}
        );
        platformTileFullEnd = new APlatformTile(
                new BufferedImage[]{ platformBody, platformTop, platformBottom, platformEnd },
                new boolean[]{false, true, false, true}
        );

        platformTileCorner0 = new APlatformTile(
                new BufferedImage[]{ platformBody, platformStart, platformTop },
                new boolean[]{true, false, false, false}
        );
        platformTileCorner1 = new APlatformTile(
                new BufferedImage[]{ platformBody, platformEnd, platformTop },
                new boolean[]{false, true, false, false}
        );
        platformTileCorner2 = new APlatformTile(
                new BufferedImage[]{ platformBody, platformStart, platformBottom },
                new boolean[]{false, false, true, false}
        );
        platformTileCorner3  = new APlatformTile(
                new BufferedImage[]{ platformBody, platformEnd, platformBottom },
                new boolean[]{false, false, false, true}
        );

        spikesTile = new SpikesTile(getResources(), levelModel.typeImages.get("spikes").get(0));
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
        allChunks.resetAll();
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
        allChunks.update();
    }

    @Override
    public void draw(Graphics2D g) {
        parallaxBackground.draw(g);

        for(PropChunk chunk: getLocalChunks()) {

            for(AProp[] propsO: chunk.getAllProps()) {
                for (AActor prop : propsO) {
                    if (prop == null) continue;
                    if (prop instanceof Platform platform) {
                        int meshFlag = platform.meshFlag;

                        if((meshFlag & (TOP.flag | BOTTOM.flag | START.flag)) == (TOP.flag | BOTTOM.flag | START.flag)) platformTileFullStart.draw(g, platform);
                        else if((meshFlag & (TOP.flag | BOTTOM.flag | END.flag)) == (TOP.flag | BOTTOM.flag | END.flag)) platformTileFullEnd.draw(g, platform);
                        else if((meshFlag & (TOP.flag | START.flag)) == (TOP.flag | START.flag)) platformTileCorner0.draw(g, platform);
                        else if((meshFlag & (TOP.flag | END.flag)) == (TOP.flag | END.flag)) platformTileCorner1.draw(g, platform);
                        else if((meshFlag & (BOTTOM.flag | START.flag)) == (BOTTOM.flag | START.flag)) platformTileCorner2.draw(g, platform);
                        else if((meshFlag & (BOTTOM.flag | END.flag)) == (BOTTOM.flag | END.flag)) platformTileCorner3.draw(g, platform);
                        else if((meshFlag & (TOP.flag | BOTTOM.flag)) == (TOP.flag | BOTTOM.flag)) platformTileVertical.draw(g, platform);
                        else if((meshFlag & (START.flag | END.flag)) == (START.flag | END.flag)) platformTileHorizontal.draw(g, platform);
                        else if(meshFlag == TOP.flag) platformTileTop.draw(g, platform);
                        else if(meshFlag == BOTTOM.flag) platformTileBottom.draw(g, platform);
                        else if(meshFlag == START.flag) platformTileStart.draw(g, platform);
                        else if(meshFlag == END.flag) platformTileEnd.draw(g, platform);
                        else platformTileBody.draw(g, platform);

                    } else if (prop instanceof Spikes spikes) {
                        spikesTile.draw(g, spikes);
                    }
                }
            }

            drawChunkDebug(g, chunk);

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
                        int flag = platform.meshFlag;

                        if(flag == TOP.flag) platformTileTop.drawAsHUD(g, platform);
                        else if(flag == BOTTOM.flag) platformTileBottom.drawAsHUD(g, platform);
                        else if(flag == START.flag) platformTileStart.drawAsHUD(g, platform);
                        else if(flag == END.flag) platformTileEnd.drawAsHUD(g, platform);
                        else if((flag & (TOP.flag | START.flag)) == (TOP.flag | START.flag)) platformTileCorner0.drawAsHUD(g, platform);
                        else if((flag & (TOP.flag | END.flag)) == (TOP.flag | END.flag)) platformTileCorner1.drawAsHUD(g, platform);
                        else if((flag & (BOTTOM.flag | START.flag)) == (BOTTOM.flag | START.flag)) platformTileCorner2.drawAsHUD(g, platform);
                        else if((flag & (BOTTOM.flag | END.flag)) == (BOTTOM.flag | END.flag)) platformTileCorner3.drawAsHUD(g, platform);
                        else platformTileBody.drawAsHUD(g, platform);
                    } else if (prop instanceof Spikes spikes) {
                        spikesTile.drawAsHUD(g, spikes);
                    }
                }
            }
        }
    }

    private void drawChunkDebug(Graphics g, PropChunk chunk) {
        g.setColor(Color.DARK_GRAY);

        int[] bounds = chunk.getBounds();

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
