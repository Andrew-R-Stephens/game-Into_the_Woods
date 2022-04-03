package models.controls.game;

import models.controls.GameControlsModel;
import prototypes.controls.AControlsModel;
import prototypes.controls.AKeyController;

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
                controlsModel.setAction(GameControlsModel.Actions.ESCAPE, true);
            }

            case KeyEvent.VK_A -> {
                controlsModel.setDirectional(GameControlsModel.Directionals.LEFT, true);
            }
            case KeyEvent.VK_D -> {
                controlsModel.setDirectional(GameControlsModel.Directionals.RIGHT, true);
            }
            case KeyEvent.VK_W -> {
                controlsModel.setDirectional(GameControlsModel.Directionals.UP, false);
            }
            case KeyEvent.VK_S -> {
                controlsModel.setDirectional(GameControlsModel.Directionals.DOWN, true);
            }

            case KeyEvent.VK_SPACE -> {
                ((GameControlsModel) controlsModel).setAbility(GameControlsModel.Abilities.JUMP, true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> {
                controlsModel.setAction(GameControlsModel.Actions.ESCAPE, false);
            }
            case KeyEvent.VK_A -> {
                controlsModel.setDirectional(GameControlsModel.Directionals.LEFT, false);
            }
            case KeyEvent.VK_D -> {
                controlsModel.setDirectional(GameControlsModel.Directionals.RIGHT, false);
            }
            case KeyEvent.VK_W -> {
                controlsModel.setDirectional(GameControlsModel.Directionals.UP, false);
            }
            case KeyEvent.VK_S -> {
                controlsModel.setDirectional(GameControlsModel.Directionals.DOWN, false);
            }

            case KeyEvent.VK_SPACE -> {
                ((GameControlsModel) controlsModel).setAbility(GameControlsModel.Abilities.JUMP, false);
            }
        }
    }

}
