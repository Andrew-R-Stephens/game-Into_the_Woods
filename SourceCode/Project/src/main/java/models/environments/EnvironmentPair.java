package models.environments;

import models.prototypes.environments.AEnvironment;
import models.prototypes.threading.ARunnable;
import models.prototypes.views.ACanvas;

public class EnvironmentPair {
        AEnvironment environment;
        ACanvas canvas;
        ARunnable updateRunnable;
        ARunnable renderRunnable;

        public EnvironmentPair(AEnvironment environment, ACanvas canvas, ARunnable uRunnable, ARunnable rRunnable) {
            this.environment = environment;
            this.canvas = canvas;
            this.updateRunnable = uRunnable;
            this.renderRunnable = rRunnable;
        }
    }