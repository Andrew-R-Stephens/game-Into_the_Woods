package models.prototypes.level.propChunk;

import models.actors.platforms.Platform;
import models.actors.triggers.interactibles.Spikes;
import models.actors.viewport.Viewport;
import models.prototypes.actor.AActor;
import models.prototypes.level.LevelModelRW;
import models.prototypes.level.prop.AProp;
import views.renders.Tile;

import java.awt.*;
import java.util.*;

public class PropChunks {

    private PropChunk[][] propChunks = new PropChunk[0][0];

    private ArrayList<PropChunk> localChunks = new ArrayList<>();

    public PropChunks(LevelModelRW.LevelModel levelModel) {

        ArrayList<AProp> tempProps = new ArrayList<>();
        float minX = 0, maxX = 0, minY = 0, maxY = 0;

        for (LevelModelRW.LevelModel.Prop p : levelModel.props) {

            AProp prop = createProp(levelModel, p);
            if (prop != null) {

                AProp[] subProps = prop.createTiles();
                for(AProp subProp : subProps) {
                    float currChunkX = (subProp.getX() / (PropChunk.SIZE * Tile.W));
                    float currChunkY = (subProp.getY() / (PropChunk.SIZE * Tile.H));
                    minX = Math.min(minX, currChunkX);
                    maxX = Math.max(maxX, currChunkX);
                    minY = Math.min(minY, currChunkY);
                    maxY = Math.max(maxY, currChunkY);

                    tempProps.add(subProp);
                }
            }
        }

        float arrShiftX = Math.max(0f, Math.abs(minX));
        float arrShiftY = Math.max(0f, Math.abs(minY));
        int arrMaxX = (int)(maxX+1 + Math.ceil(arrShiftX));
        int arrMaxY = (int)(maxY+1 + Math.ceil(arrShiftY));

        propChunks = new PropChunk[arrMaxY][arrMaxX];

        for (AProp p : tempProps) {

            int chunkX = (int) Math.floor(p.getX() / (PropChunk.SIZE * Tile.W));
            int chunkY = (int) Math.floor(p.getY() / (PropChunk.SIZE * Tile.H));

            int chunkBaseX = (int)Math.ceil(chunkX + arrShiftX);
            int chunkBaseY = (int)Math.ceil(chunkY + arrShiftY);

            PropChunk chunk;
            if((chunk = propChunks[chunkBaseY][chunkBaseX]) == null) {
                chunk = propChunks[chunkBaseY][chunkBaseX] =
                        new PropChunk(
                                chunkBaseX, chunkBaseY,
                                (int) (chunkX * (PropChunk.SIZE * Tile.W)),
                                (int) (chunkY * (PropChunk.SIZE * Tile.H))
                        );
            }

            chunk.addProp(p);

        }

        for(int j = 0; j < propChunks.length; j++) {
            for(int i = 0; i < propChunks[j].length; i++) {

                PropChunk chunk = propChunks[j][i];
                if(chunk == null) continue;

                AProp[][] propsAbove = null;
                if(j-1 > 0) {
                    propsAbove = propChunks[j - 1][i].getAllProps();
                }
                AProp[][] propsBelow = null;
                if(j+1 < propChunks.length) {
                    propsBelow = propChunks[j + 1][i].getAllProps();
                }
                AProp[][] propsLeft = null;
                if(i-1 > 0) {
                    propsLeft = propChunks[j][i-1].getAllProps();
                }
                AProp[][] propsRight = null;
                if(i+1 < propChunks[j].length) {
                    propsRight = propChunks[j][i+1].getAllProps();
                }

                AProp[][] chunkProps = chunk.getAllProps();
                for(int y = 0; y < chunkProps.length; y++) {
                    for (int x = 0; x < chunkProps[y].length; x++) {
                        AProp chunkProp = chunkProps[y][x];
                        if(chunkProp != null) continue;

                        boolean canCheckVertical = true;
                        boolean canCheckHorizontal = true;

                        //check top
                        AProp targetProp = null;
                        int pY = y - 1;
                        if(pY >= 0) {
                            targetProp = chunkProps[pY][x];
                        } else if(propsAbove != null){
                            targetProp = propsAbove[chunkProps.length-1][x];
                        }
                        if(targetProp != null && targetProp.side != AProp.Side.TOP) {
                            targetProp.side = AProp.Side.BOTTOM;
                        }

                        //check bottom
                        pY = y + 1;
                        if(pY < chunkProps.length) {
                            targetProp = chunkProps[pY][x];
                        } else if(propsBelow != null){
                            targetProp = propsBelow[0][x];
                        }
                        if(targetProp != null) {
                            targetProp.side = AProp.Side.TOP;
                        }

                        //check left
                        int pX = x - 1;
                        if(pX >= 0) {
                            targetProp = chunkProps[y][pX];
                        } else if(propsLeft != null){
                            targetProp = propsLeft[y][chunkProps.length-1];
                        }
                        if(targetProp != null) {
                            //targetProp.side = AProp.Side.START;
                        }

                        //check right
                        pX = x + 1;
                        if(pX < chunkProps[y].length) {
                            targetProp = chunkProps[y][pX];
                        } else if(propsRight != null){
                            targetProp = propsRight[y][0];
                        }
                        if(targetProp != null) {
                            //targetProp.side = AProp.Side.END;
                        }

                    }
                }
            }
        }

    }

    public AProp createProp(
            LevelModelRW.LevelModel levelModel,
            LevelModelRW.LevelModel.Prop propData
    ) {
        AProp outProp = null;
        switch (propData.type) {
            case "platform": {
                outProp = new Platform(
                        propData.coords.x,
                        propData.coords.y,
                        propData.dims.w,
                        propData.dims.h,
                        propData.motion.vX,
                        propData.motion.vY,
                        propData.hasGravity
                );
                break;
            }
            case "spikes": {
                outProp = new Spikes(
                        propData.coords.x,
                        propData.coords.y,
                        propData.dims.w,
                        propData.dims.h,
                        propData.motion.vX,
                        propData.motion.vY,
                        propData.maxCycles
                );
                break;
            }
            /*case "spring": {
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
            }*/
        }
        return outProp;
    }

    public void resetAll() {
        for(PropChunk[] p0: propChunks) {
            for(PropChunk p: p0) {
                if(p != null) {
                    p.resetAll();
                }
            }
        }
    }

    public void update() {

    }

    public ArrayList<PropChunk> getChunksIn(Viewport viewport) {

        float origRatioW = PropChunk.SIZE * Tile.W;
        float origRatioH = PropChunk.SIZE * Tile.H;

        ArrayList<PropChunk> chunks = new ArrayList<>();
        for(PropChunk[] pCO: propChunks) {
            if(pCO == null) continue;

            for(PropChunk pC: pCO) {
                if(pC == null) continue;

                for(AProp[] pO: pC.getAllProps()) {
                    for(AProp p: pO) {
                        if(p == null) continue;
                        p.setCanRender(false);
                    }
                }

                if(new Rectangle(
                        pC.getBounds()[0], pC.getBounds()[1],
                        pC.getBounds()[2], pC.getBounds()[3]
                    ).intersects(
                        new Rectangle(
                            (int)viewport.getX(), (int)viewport.getY(),
                            (int)viewport.getW(), (int)viewport.getH()
                    ))
                ) {
                    chunks.add(pC);
                }
            }
        }

        return chunks;
    }

    public void setLocal(Viewport viewport) {
        localChunks = getChunksIn(viewport);
    }

    public ArrayList<PropChunk> getLocal() {
        return localChunks;
    }

    public PropChunk[][] getAllChunks() {
        return propChunks;
    }
}
