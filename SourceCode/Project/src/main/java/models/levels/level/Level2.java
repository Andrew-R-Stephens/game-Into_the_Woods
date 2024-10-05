package models.levels.level;

import models.actors.triggers.interactibles.Door;
import models.camera.Camera;
import models.levels.LevelsList;
import models.prototypes.environments.AEnvironment;
import models.prototypes.level.ALevel;
import models.prototypes.level.prop.AProp;
import models.prototypes.level.prop.trigger.prop.APropTrigger;
import models.prototypes.level.LevelModelRW.LevelModel;

public class Level2 extends ALevel {

    /**
     * Creates the first Level.
     * @param environment
     */
    public Level2(AEnvironment environment, LevelModel levelModel) {
        super(environment);

        setStartOrigin(
                (int)(levelModel.startOrigin.x * LevelsList.WORLD_SCALE),
                (int)(levelModel.startOrigin.y * LevelsList.WORLD_SCALE)
        );

        build(levelModel);

        for(String b: levelModel.backgroundImages) {
            addBackgroundLayer(getResources().getImage(b));
        }

        /*
        setStartOrigin(
                (int)(1988 * LevelsList.WORLD_SCALE),
                (int)(2110 * LevelsList.WORLD_SCALE));

        addBackgroundLayer(getResources().getImage("forest_1"));
        addBackgroundLayer(getResources().getImage("forest_2"));
        addBackgroundLayer(getResources().getImage("forest_3"));
        */
    }

    @Override
    public void build(LevelModel levelModel) {

        /*
        for(LevelModel.Prop p: levelModel.props) {
            AProp prop = createProp(levelModel, p);
            if(prop != null) {
                addProp(prop);
            }
        }
        */

        // Door
        door = new Door(getResources(), environment, 6815, 1873, 50, 100,
                0, 0, 1, false, false);
        addProp(door);

        // Door Open Animation Trigger
        addProp(new APropTrigger(getResources(), environment, 6600, 1673, 450, 300,
                0, 0, 1,false, false) {
            @Override
            public void doAction() {
                door.onReceive();
                Camera.zoomTarget = 1.25f;
            }
        });

        // ZOOM TRIGGER
        addProp(new APropTrigger(getResources(), environment, 1028, 2495, 1040, 1250,
                0, 0, -1,false, false) {
            @Override
            public void doAction() {
                Camera.zoomTarget = .75f;
            }
        });
        addProp(new APropTrigger(getResources(), environment, 2100, 2430, 1360, 1190,
                0, 0, -1,false, false) {
            @Override
            public void doAction() {
                Camera.zoomTarget = .75f;
            }
        });
        addProp(new APropTrigger(getResources(), environment, 750, 325, 1575, 1725,
                0, 0, -1,false, false) {
            @Override
            public void doAction() {
                Camera.zoomTarget = .75f;
            }
        });
        addProp(new APropTrigger(getResources(), environment, 4140, 325, 1680, 1980,
                0, 0, -1,false, false) {
            @Override
            public void doAction() {
                Camera.zoomTarget = .75f;
            }
        });

        super.build();

    }

}
