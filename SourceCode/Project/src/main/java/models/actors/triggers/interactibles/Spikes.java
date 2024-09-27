package models.actors.triggers.interactibles;

import models.actors.platforms.Platform;
import models.actors.player.PlayerAvatar;
import models.camera.Camera;
import models.environments.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.environments.AEnvironment;
import models.prototypes.level.prop.AProp;
import models.prototypes.level.prop.trigger.ATrigger;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;
import views.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * <p>Spikes are a harmful obstacle. Touching the obstacle immediately kills the player.</p>
 * @author Andrew Stephens
 */
public class Spikes extends ATrigger implements IDrawable, IHUDDrawable, IUpdatable {

    int cols = Math.max(1, (int)Math.ceil(w / (float) Tile.W));
    int rows = Math.max(1, (int)Math.ceil(h / (float) Tile.H));

    /** The native image for this platform. */
    private final BufferedImage imageRaw;
    /** The final drawn image */
    private BufferedImage image;

    /**
     * <p>Called from the subtypes, this method initializes the object. Also initializes the respective spriteSheet.</p>
     * @param resources The resources of the parent Environment
     * @param x The horizontal position, relative to the default dimensions.
     * @param y The y position, relative to the default dimensions.
     * @param w The width, relative to the default dimensions.
     * @param h The height, relative to the default dimensions.
     * @param vx The horizontal velocity.
     * @param vy The vertical velocity.
     * @param MAX_CYCLES The number of times this object can create an action. -1 is infinite.
     */
    public Spikes(Resources resources, AEnvironment environment, float x, float y, float w, float h, float vx, float vy,
                  int MAX_CYCLES) {
        super(resources, environment, x, y, w, h, vx, vy, MAX_CYCLES, false, false);

        imageRaw = resources.getImage("spikes");
        calcSubImages();
    }

    public Spikes(Resources resources, AEnvironment environment, float x, float y, float w, float h, int MAX_CYCLES) {
        super(resources, environment, x, y, w, h, 0, 0, MAX_CYCLES, false, false);

        imageRaw = resources.getImage("spikes");
        calcSubImages();
    }

    @Override
    public void calcSubImages() {

        cols = Math.max(1, (int)Math.ceil(w / (float) Tile.W)) + 1;
        rows = Math.max(1, (int)Math.ceil(h / (float) Tile.H)) + 1;

        System.out.println(cols + " " + rows);

        if(imageRaw != null) {
            Image tempImage = imageRaw.getScaledInstance(
                    Tile.W,
                    Tile.H,
                    BufferedImage.SCALE_SMOOTH
            );

            BufferedImage tempPlatformImage = new BufferedImage(
                    Tile.W * cols,
                    Tile.H * rows,
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D bGr = tempPlatformImage.createGraphics();

            for(int col = 0; col < cols; col++) {
                float x = (int) (col * Tile.W * Config.scaledW_zoom);
                float y = 0;

                bGr.drawImage(
                        tempImage,
                        (int) Math.floor(x),
                        (int) Math.floor(y),
                        (int) Math.floor((Tile.W) * Config.scaledW_zoom) + 1,
                        (int) Math.floor((Tile.H) * Config.scaledH_zoom) + 1,
                        null
                );
            }
            bGr.dispose();

            image = tempPlatformImage.getSubimage(
                    0, 0,
                    Math.max((int)w, 1), Math.max((int)h, 1));
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);

        float offsetX = ((x * Config.scaledW_zoom) + (Camera.camX));
        float offsetY = ((y * Config.scaledH_zoom) + (Camera.camY));

        g.drawImage(image,
                (int) Math.floor(offsetX),
                (int) Math.floor(offsetY),
                (int) Math.floor(w * Config.scaledW_zoom) + 1,
                (int) Math.floor(h * Config.scaledH_zoom) + 1,
                null);

        if(Config.DEBUG && isHighlighted) {
            Color c = Color.RED;
            g.setColor(c);
            g.drawRect((int) (offsetX), (int) (offsetY),
                    (int) (w * Config.scaledW_zoom),
                    (int) (h * Config.scaledH_zoom));
            g.drawString(x + " " + y, (int) (offsetX), (int) (offsetY));
        }
    }

    @Override
    public void drawAsHUD(Graphics2D g) {
        g.setColor(Color.RED);

        float[] offset = Camera.getRelativeOffsetBy(x, y, Camera.SCALE_MINIMAP);
        float[] scale = Camera.getRelativeScaleBy(w, h, Camera.SCALE_MINIMAP);

        g.fillRect((int) offset[0], (int) offset[1], (int) scale[0], (int) scale[1]);

    }

    @Override
    public void doAction() {
        if(environment instanceof GameEnvironment ge) {
            ge.doPlayerDeath();
        }
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        if(!(a instanceof PlayerAvatar)) {
            return false;
        }

        boolean hasCollision = super.hasCollision(a, delta);

        if(MAX_CYCLES != -1) {
            if (currentCycles > MAX_CYCLES) {
                return false;
            }
        }

        if(hasCollision) {
            doAction();
            currentCycles++;
        }

        return hasCollision;
    }

    @Override
    public AProp[] createTiles() {
        AProp[] tiles = super.createTiles();
        for(int i = 0; i < tiles.length; i++) {
            float x = getX() * i / Tile.W, y = getY() * i / Tile.H;
            Spikes platform = new Spikes(
                    resources, environment,
                    x, y, w, h, vX, vY, MAX_CYCLES
            );
            tiles[i] = platform;
        }
        System.out.println(tiles.length);
        return tiles;
    }
}
