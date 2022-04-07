package controls.game;

import controls.GameControls;
import models.prototypes.controls.AControlsModel;
import models.prototypes.controls.AKeyController;

import java.awt.event.KeyEvent;

/**
 * Game Key Controls define the controls for Character navigation and ability with Key input.
 */
public class GameKeyControls extends AKeyController {

    /**
     * Instantiates a new Game key controls.
     *
     * @param controlsViewModel the controls view model
     */
    public GameKeyControls(AControlsModel controlsViewModel) {
        super(controlsViewModel);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> {
                //System.out.println("Quitting");
                //System.exit(1);
                controlsModel.setAction(GameControls.Actions.ESCAPE, true);
            }

            case KeyEvent.VK_A -> {
                controlsModel.setDirectional(GameControls.Directionals.LEFT, true);
            }
            case KeyEvent.VK_D -> {
                controlsModel.setDirectional(GameControls.Directionals.RIGHT, true);
            }
            case KeyEvent.VK_W -> {
                controlsModel.setDirectional(GameControls.Directionals.UP, false);
            }
            case KeyEvent.VK_S -> {
                controlsModel.setDirectional(GameControls.Directionals.DOWN, true);
            }

            case KeyEvent.VK_SPACE -> {
                ((GameControls) controlsModel).setAbility(GameControls.Abilities.JUMP, true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> {
                controlsModel.setAction(GameControls.Actions.ESCAPE, false);
            }
            case KeyEvent.VK_A -> {
                controlsModel.setDirectional(GameControls.Directionals.LEFT, false);
            }
            case KeyEvent.VK_D -> {
                controlsModel.setDirectional(GameControls.Directionals.RIGHT, false);
            }
            case KeyEvent.VK_W -> {
                controlsModel.setDirectional(GameControls.Directionals.UP, false);
            }
            case KeyEvent.VK_S -> {
                controlsModel.setDirectional(GameControls.Directionals.DOWN, false);
            }

            case KeyEvent.VK_SPACE -> {
                ((GameControls) controlsModel).setAbility(GameControls.Abilities.JUMP, false);
            }
        }
    }

}
