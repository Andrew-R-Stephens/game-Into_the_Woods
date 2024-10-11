package models.prototypes.level.propChunk;

import models.prototypes.level.prop.AProp;
import models.textures.meshes.Tile;

import java.awt.*;

public class PropChunk {

    private final int relX, relY;
    private final int x, y;
    public static final int SIZE = (int) Tile.W;
    private AProp[][] props = new AProp[SIZE][SIZE];

    //private boolean canRender = false;

    public PropChunk(int x, int y, int relX, int relY) {
        this.x = x;
        this.y = y;
        this.relX = relX;
        this.relY = relY;
    }

    public void addProp(AProp prop) {

        int blockX = (int)Math.abs((prop.getX() - relX) / Tile.W);
        int blockY = (int)Math.abs((prop.getY() - relY) / Tile.H);

        //if(props[blockY][blockX] == null) {
            props[blockY][blockX] = prop;
        //}

    }

    public void removeProp(AProp prop) {

        int blockX = (int)Math.abs((prop.getX() - relX) / Tile.W);
        int blockY = (int)Math.abs((prop.getY() - relY) / Tile.H);

        props[blockY][blockX] = null;
    }

    public void replaceProp(AProp newProp) {

        int blockX = (int)Math.abs((newProp.getX() - relX) / Tile.W);
        int blockY = (int)Math.abs((newProp.getY() - relY) / Tile.H);

        props[blockY][blockX] = newProp;
    }

    /*
    private int getBlockCoord(float point) {
        float chunkPoint = (float) Math.floor(point) / PropChunk.SIZE / Tile.W;
        if(chunkPoint < 0) { chunkPoint = (float) Math.ceil(chunkPoint); }
        int blockPoint = (int)((chunkPoint / (float)Math.ceil(chunkPoint)) * PropChunk.SIZE);
        if(blockPoint == PropChunk.SIZE) { blockPoint = PropChunk.SIZE -1; }
        return blockPoint;
    }
    */

    public int[] getBounds() {
        return new int[] { relX, relY, SIZE * SIZE, SIZE * SIZE };
    }

    public Rectangle getRectangle() {
        return new Rectangle ( relX, relY, SIZE * SIZE, SIZE * SIZE );
    }

    public void resetAll() {
        for(AProp[] row: props) {
            for(AProp prop: row) {
                if(prop != null) {
                    prop.reset();
                }
            }
        }
    }

    public AProp[][] getAllProps() {
        return props;
    }

}
