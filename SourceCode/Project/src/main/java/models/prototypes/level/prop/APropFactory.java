package models.prototypes.level.prop;

import models.actors.platforms.Platform;
import models.actors.triggers.interactibles.Spikes;
import models.prototypes.level.LevelModelRW;

public class APropFactory {

    public static AProp createProp(
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


}
