package models.actors.gameactors.props.triggers.interactibles;

import models.environments.EnvironmentsHandler;
import models.environments.game.GameEnvironment;
import models.environments.menus.mainmenu.MainMenuEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.level.prop.trigger.prop.APropTrigger;
import models.sprites.SpriteSheet;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.util.HashMap;

public class DoorTrigger extends APropTrigger implements IDrawable, IHUDDrawable, IUpdatable {

    public ActionType actionState = ActionType.CLOSED;
    public enum ActionType {
        CLOSED, OPEN
    }

    protected HashMap<ActionType, SpriteSheet> spriteSheets = new HashMap<>();

    public DoorTrigger(GameEnvironment gameEnvironment, float x, float y, float w, float h, float vx, float vy,
                         int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(gameEnvironment, x, y, w, h, vx, vy, MAX_CYCLES, hasGravity, canMoveOnCollision);

        spriteSheets.put(actionState, Resources.getSpriteSheet("door_spritesheet"));
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        return super.hasCollision(a, delta);
    }

    @Override
    public void doAction() {

        boolean success = gameEnvironment.getLevelModel().navigateNextLevel();
        if(success) {
            gameEnvironment.reset();
        } else {
            gameEnvironment.reset();
            MainMenuEnvironment mainMenuEnvironment = gameEnvironment.parentEnvironmentsModel
                    .swapToEnvironmentType(EnvironmentsHandler.EnvironmentType.MAIN_MENU, true).getMenuEnvironment();
            mainMenuEnvironment.navigateToLevelSelectPage();
            gameEnvironment.parentEnvironmentsModel.applyEnvironment();
        }

    }

    @Override
    public void update(float delta) {
        super.update(delta);
        /*
        if(isActivated) {
            spriteSheet.update(delta);
            if(spriteSheet.isLastFrame()) {
                isActivated = false;
                spriteSheet.setCurrentFrame(0);
            }
        }
        */
    }

    @Override
    public void draw(Graphics g) {
        /*
        double offsetX = ((x * ConfigData.scaledW) + (Camera.x));
        double offsetY = ((y * ConfigData.scaledH) + (Camera.y));

        double scaledW = w * ConfigData.scaledW;
        double scaledH = h * ConfigData.scaledH;
        spriteSheet.draw(g, (int)offsetX, (int)offsetY, (int)scaledW, (int)scaledH);
        */


        super.draw(g);
    }
}
