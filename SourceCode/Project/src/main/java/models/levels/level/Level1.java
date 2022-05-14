package models.levels.level;

import models.actors.platforms.Platform;
import models.actors.triggers.collectibles.key.DoorKey;
import models.actors.triggers.interactibles.Door;
import models.actors.triggers.interactibles.Spikes;
import models.actors.triggers.interactibles.Spring;
import models.camera.Camera;
import models.environments.game.GameEnvironment;
import models.levels.LevelsList;
import models.prototypes.level.ALevel;
import models.prototypes.level.prop.trigger.prop.APropTrigger;

/**
 * The first level.
 *
 * Forest level; 5 Keys; 1 door; 8 Spikes; 14 Springs; 4 Generic triggers (used for camera zoom)
 */
public class Level1 extends ALevel {

    /**
     * Creates the first Level.
     * @param gameModel
     */
    public Level1(GameEnvironment gameModel) {
        super(gameModel);

        setStartOrigin(
                (int)(1988 * LevelsList.WORLD_SCALE),
                (int)(2110 * LevelsList.WORLD_SCALE));

        build();

        addBackgroundLayer(getResources().getImage("forest_1"));
        addBackgroundLayer(getResources().getImage("forest_2"));
        addBackgroundLayer(getResources().getImage("forest_3"));
    }

    @Override
    public void build() {
        // Spikes
        addProp(new Spikes(getResources(), gameEnvironment, 7165, 1928, 284, 97, 0, 0, 1));
        addProp(new Spikes(getResources(), gameEnvironment, 5250, 3900, 151, 109, 0, 0, 1));
        addProp(new Spikes(getResources(), gameEnvironment, 2274, 3429, 973, 88, 0, 0, 1));
        addProp(new Spikes(getResources(), gameEnvironment, 1805, 3640, 227, 82, 0, 0, 1));
        addProp(new Spikes(getResources(), gameEnvironment, 1152, 3649, 73, 66, 0, 0, 1));
        addProp(new Spikes(getResources(), gameEnvironment, 71, 2168, 371, 103, 0, 0, 1));
        addProp(new Spikes(getResources(), gameEnvironment, 192, 1248, 271, 113, 0, 0, 1));

        // Platforms
        addProp(new Platform(getResources(),"platform_level1", -500,    -500,     1221, 1630, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", -500,    1122,  578,  158, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", -500,    1280,  703, 769, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 119,  1339,  271, 240, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", -500,    2045,  580,  226, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", -500,    2059,  950, 1791, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 444,  3258,  715, 792, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 1156, 2157,  1122,341, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 1710, 2770,  570, 141, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 1958, 2906,  321, 401, 0, 0, false));
        //10
        addProp(new Platform(getResources(),"platform_level1", 2040, 3304, 239, 414, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 2268, 3509, 991, 541, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3249, 3300, 419, 750, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3250, 3231, 349, 74, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3340, 3098, 258, 134, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3340, 2769, 599, 331, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3150, 2809, 199, 39, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3781, 3093, 157, 116, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3927, 2529, 212, 681, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3515, 2320, 623, 218, 0, 0, false));
        //20
        addProp(new Platform(getResources(),"platform_level1", 2707, 2322, 811,  278, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 2702, 2251, 568,  79, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 2450, 2792, 332,  179,0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 2450, 2597, 369,  190,0 , 0, false));
        addProp(new Platform(getResources(),"platform_level1", 2239, 2484, 470,  115,0 ,0, false));
        addProp(new Platform(getResources(),"platform_level1", 2176, 2211, 535,  278, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 2351, 2132, 329,  83, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 1619, 2030, 410,  127, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 1144, 2151, 1036, 349, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 781,  2488, 762,  584, 0, 0, false));
        //30
        addProp(new Platform(getResources(),"platform_level1", 1008, 2329, 141,  171 , 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 909,  -500, 852,  562, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 1749, -500, 4077, 800, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 5820, -500, 1620, 1989, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 7439, -500, 111,  2525, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 5615, 2026, 1935, 1297, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 5276, 3310, 2274, 116, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4847, 3422, 2703, 48, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 5375, 3462, 2175, 588, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3841, 3982, 1550, 68, 0, 0, false));
        //40
        addProp(new Platform(getResources(),"platform_level1", 3657, 3826, 200, 224, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3660, 3382, 377, 76, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3660, 3458, 212, 100, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3660, 3559, 113, 70, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4120, 2752, 122, 336, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4238, 2839, 299, 193, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4539, 2916, 269, 82, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4676, 3003, 138, 70, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4803, 2947, 315, 42, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4816, 2991, 190, 103, 0, 0, false));
        //50
        addProp(new Platform(getResources(),"platform_level1", 4807, 2950, 311, 45, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 5000, 2991, 262, 60, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 5157, 3065, 241, 39, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 5437, 2466, 200, 246,0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 5008, 2022, 623, 454,0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 5462, 1932, 206, 85, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4471, 2075, 560, 302, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4397, 2140, 78,  100,0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4322, 2227, 156, 122, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 6611, 1972, 569, 54, 0, 0, false));
        //60
        addProp(new Platform(getResources(),"platform_level1", 7096, 1481, 358, 166 , 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 7242, 1624, 215, 132, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 5791, 1478, 986, 256, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 5872, 1721, 585, 119, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 5712, 1056, 116, 187,0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 5409, 310,  417, 738, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4000, 3600, 613, 66, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4118, 3614, 510, 70, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4273, 3512, 365, 113, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4525, 3471, 562, 113, 0, 0, false));
        //70
        addProp(new Platform(getResources(),"platform_level1", 4130, 2754, 122, 336, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4236, 2841, 302, 197, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4531, 2922, 271, 81, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4686, 3000, 135, 70, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4801, 2943, 318, 45, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 5003, 2990, 274, 63, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4155, 3305, 253, 45, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3173, 312,  734, 840, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3903, 316,  193, 1476, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 4083, 319,  172, 619, 0, 0, false));
        //80
        addProp(new Platform(getResources(),"platform_level1", 4241, 306,  194, 141, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3263, 1145, 411, 190, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3654, 1135, 265, 122, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 2949, 744,  234, 85, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 2154, 306,  1039,442, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 2133, 728,  476, 182, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 1850, 859,  296, 262, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3310, 1514, 600, 417, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3549, 1921, 231, 237, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 3421, 1912, 135, 134, 0, 0, false));
        //90
        addProp(new Platform(getResources(),"platform_level1", 3161, 1586, 156, 300, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 2980, 1598, 191, 159, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 2859, 1576, 141, 101, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 2738, 1545, 135, 94, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 2880, 1489, 200, 82, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 2446, 1437, 141, 112, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 2300, 1374, 163, 147, 0, 0, false));
        addProp(new Platform(getResources(),"platform_level1", 1144, 3694, 1134, 356, 0, 0, false));

        // Door
        door = new Door(getResources(), gameEnvironment, 6815, 1873, 50, 100,
                0, 0, 1, false, false);
        addProp(door);

        // Door Open Animation Trigger
        addProp(new APropTrigger(getResources(), gameEnvironment, 6600, 1673, 450, 300,
                0, 0, 1,false, false) {
            @Override
            public void doAction() {
                door.onReceive();
                Camera.zoomTarget = 1.25f;
            }
        });

        // ZOOM TRIGGER
        addProp(new APropTrigger(getResources(), gameEnvironment, 1028, 2495, 1040, 1250,
                0, 0, -1,false, false) {
            @Override
            public void doAction() {
                Camera.zoomTarget = .75f;
            }
        });
        addProp(new APropTrigger(getResources(), gameEnvironment, 2100, 2430, 1360, 1190,
                0, 0, -1,false, false) {
            @Override
            public void doAction() {
                Camera.zoomTarget = .75f;
            }
        });
        addProp(new APropTrigger(getResources(), gameEnvironment, 750, 325, 1575, 1725,
                0, 0, -1,false, false) {
            @Override
            public void doAction() {
                Camera.zoomTarget = .75f;
            }
        });
        addProp(new APropTrigger(getResources(), gameEnvironment, 4140, 325, 1680, 1980,
                0, 0, -1,false, false) {
            @Override
            public void doAction() {
                Camera.zoomTarget = .75f;
            }
        });

        // Keys
        addProp(new DoorKey(getResources(), gameEnvironment, 4744, 510, 100, 50, 0, 0));
        addProp(new DoorKey(getResources(), gameEnvironment, 3710, 2601, 100, 50, 0, 0));
        addProp(new DoorKey(getResources(), gameEnvironment, 4934, 3704, 100, 50, 0, 0));
        addProp(new DoorKey(getResources(), gameEnvironment, 2692, 1375, 100, 50, 0, 0));
        addProp(new DoorKey(getResources(), gameEnvironment, 1919, 713, 100, 50, 0, 0));

        // Springs
        addProp(new Spring(getResources(), gameEnvironment, 1535, 3025, 100, 150, 0, 0, -1,false, false));
        addProp(new Spring(getResources(), gameEnvironment, 1440, 950,  100, 150, 0, 0, -1,false, false));
        addProp(new Spring(getResources(), gameEnvironment, 850, 1220,  100, 150, 0, 0, -1,false, false));
        addProp(new Spring(getResources(), gameEnvironment, 1000, 1550, 100, 150, 0, 0, -1,false, false));
        addProp(new Spring(getResources(), gameEnvironment, 1450, 1725, 100, 150, 0, 0, -1,false, false));
        addProp(new Spring(getResources(), gameEnvironment, 1750, 2000, 100, 150, 0, 0, -1,false, false));
        addProp(new Spring(getResources(), gameEnvironment, 2100, 1600, 100, 150, 0, 0, -1,false, false));
        addProp(new Spring(getResources(), gameEnvironment, 4900, 960,  100, 150, 0, 0, -1,false, false));
        addProp(new Spring(getResources(), gameEnvironment, 4550, 1350, 100, 150, 0, 0, -1,false, false));
        addProp(new Spring(getResources(), gameEnvironment, 4950, 1600, 100, 150, 0, 0, -1,false, false));
        addProp(new Spring(getResources(), gameEnvironment, 5325, 1800, 100, 150, 0, 0, -1,false, false));
        addProp(new Spring(getResources(), gameEnvironment, 2460, 3343, 100, 150, 0, 0, -1,false, false));
        addProp(new Spring(getResources(), gameEnvironment, 2900, 3343, 100, 150, 0, 0, -1,false, false));
        addProp(new Spring(getResources(), gameEnvironment, 2774, 2939, 100, 150, 0, 0, -1,false, false));

        super.build();

    }

}
