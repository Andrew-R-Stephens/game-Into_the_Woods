package models.prototypes.level.propChunk;

import models.actors.platforms.Platform;
import models.actors.triggers.interactibles.Spikes;
import models.actors.viewport.Viewport;
import models.prototypes.level.LevelModelRW;
import models.prototypes.level.prop.AProp;
import models.prototypes.level.prop.AProp.Side;
import models.textures.meshes.Tile;

import java.awt.*;
import java.util.*;

import static models.prototypes.level.prop.AProp.Side.*;

public class PropChunks {

    private PropChunk[][] propChunks = new PropChunk[0][0];

    private ArrayList<PropChunk> localChunks = new ArrayList<>();

    float arrShiftX = 0;
    float arrShiftY = 0;
    int arrMaxX = 0;
    int arrMaxY = 0;

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

        arrShiftX = Math.max(0f, Math.abs(minX));
        arrShiftY = Math.max(0f, Math.abs(minY));
        arrMaxX = (int)(maxX+1 + Math.ceil(arrShiftX));
        arrMaxY = (int)(maxY+1 + Math.ceil(arrShiftY));

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

        assignPropMeshes();

    }

    private void assignPropMeshes() {
        for(int j = 0; j < propChunks.length; j++) {
            for(int i = 0; i < propChunks[j].length; i++) {

                PropChunk chunk = propChunks[j][i];
                if(chunk == null) continue;

                AProp[][] propsAbove = null;
                int py = j - 1;
                PropChunk tempChunk;
                if(py >= 0 && ((tempChunk = propChunks[py][i]) != null)) {
                    propsAbove = tempChunk.getAllProps();
                }
                AProp[][] propsBelow = null;
                py = j + 1;
                if(py < propChunks.length && ((tempChunk = propChunks[py][i]) != null)) {
                    propsBelow = tempChunk.getAllProps();
                }
                AProp[][] propsLeft = null;
                int px = i - 1;
                if(px >= 0 && ((tempChunk = propChunks[j][px]) != null)) {
                    propsLeft = tempChunk.getAllProps();
                }
                AProp[][] propsRight = null;
                px = i + 1;
                if(px < propChunks[j].length && ((tempChunk = propChunks[j][px]) != null)) {
                    propsRight = tempChunk.getAllProps();
                }

                AProp[][] chunkProps = chunk.getAllProps();
                for(int y = 0; y < chunkProps.length; y++) {
                    for (int x = 0; x < chunkProps[y].length; x++) {
                        assignPropMesh(chunkProps, y, x, propsAbove, propsBelow, propsLeft, propsRight);
                    }
                }
            }
        }
    }

    private void assignPropMesh(AProp[][] chunkProps,
                                int y, int x,
                                AProp[][] propsAbove, AProp[][] propsBelow,
                                AProp[][] propsLeft, AProp[][] propsRight) {

        AProp chunkProp = chunkProps[y][x];
        if(chunkProp == null) return;

        //     x
        //   [][][]
        // y [][][]
        //   [][][]
        boolean[][] sidesMatrix = new boolean[3][3]; // y, x

        //check above
        AProp targetProp = null;
        int pY = y - 1;
        if(pY >= 0) {
            targetProp = chunkProps[pY][x];
        } else if(propsAbove != null){
            targetProp = propsAbove[chunkProps.length-1][x];
        }
        if(targetProp == null || !(targetProp.getClass() == chunkProp.getClass())) {
            // Include Top
            sidesMatrix[0][1] = true;
        }

        //check below
        pY = y + 1;
        if(pY < chunkProps.length) {
            targetProp = chunkProps[pY][x];
        } else if(propsBelow != null){
            targetProp = propsBelow[0][x];
        }
        if(targetProp == null || !(targetProp.getClass() == chunkProp.getClass())) {
            // Include Bottom
            sidesMatrix[2][1] = true;
        }

        //check right
        int pX = x - 1;
        if(pX >= 0) {
            targetProp = chunkProps[y][pX];
        } else if(propsLeft != null){
            targetProp = propsLeft[y][chunkProps.length-1];
        }
        if(targetProp == null || !(targetProp.getClass() == chunkProp.getClass())) {
            // Include End
            sidesMatrix[1][2] = true;
        }

        //check left
        pX = x + 1;
        if(pX < chunkProps[y].length) {
            targetProp = chunkProps[y][pX];
        } else if(propsRight != null){
            targetProp = propsRight[y][0];
        }
        if(targetProp == null || !(targetProp.getClass() == chunkProp.getClass())) {
            // Include Start
            sidesMatrix[1][0] = true;
        }

        chunkProp.meshFlag = matrixToMeshMask(sidesMatrix);

    }

    private int matrixToMeshMask(boolean[][] matrix) {

        int mask = BODY.flag;

        if(matrix[0][1]) mask = mask + TOP.flag;
        if(matrix[2][1]) mask = mask + BOTTOM.flag;
        if(matrix[1][2]) mask = mask + START.flag;
        if(matrix[1][0]) mask = mask + END.flag;

        System.out.println("Flag: " + mask + " | "
                + (mask & TOP.flag) + " " + (mask & BOTTOM.flag) + " " +
                + (mask & START.flag) + " " + (mask & END.flag));

        return mask - 1;
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

        ArrayList<PropChunk> chunks = new ArrayList<>();
        for(PropChunk[] pCO: propChunks) {
            if(pCO == null) continue;

            for(PropChunk pC: pCO) {
                if(pC == null) continue;

                if(pC.getRectangle().intersects(
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
