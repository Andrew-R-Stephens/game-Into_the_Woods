package models.controls.game;

import props.prototypes.controls.AControlsModel;
import props.prototypes.controls.AKeyController;

import java.awt.event.KeyEvent;

/**
 * Game Key Controls define the controls for Character navigation and ability with Key input.
 */
public class GameKeyControls extends AKeyController {

    private final GameControlsModel controlsViewModel;

    public GameKeyControls(AControlsModel controlsViewModel) {
        this.controlsViewModel = (GameControlsModel) controlsViewModel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> {
                System.out.println("Quitting");
                System.exit(1);
            }

            case KeyEvent.VK_A -> {
                controlsViewModel.setDirectional(GameControlsModel.Directionals.LEFT, true);
            }
            case KeyEvent.VK_D -> {
                controlsViewModel.setDirectional(GameControlsModel.Directionals.RIGHT, true);
            }
            case KeyEvent.VK_W -> {
                controlsViewModel.setDirectional(GameControlsModel.Directionals.UP, false);
            }
            case KeyEvent.VK_S -> {
                controlsViewModel.setDirectional(GameControlsModel.Directionals.DOWN, true);
            }

            case KeyEvent.VK_SPACE -> {
                controlsViewModel.setAbility(GameControlsModel.Abilities.JUMP, true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A -> {
                controlsViewModel.setDirectional(GameControlsModel.Directionals.LEFT, false);
            }
            case KeyEvent.VK_D -> {
                controlsViewModel.setDirectional(GameControlsModel.Directionals.RIGHT, false);
            }
            case KeyEvent.VK_W -> {
                controlsViewModel.setDirectional(GameControlsModel.Directionals.UP, false);
            }
            case KeyEvent.VK_S -> {
                controlsViewModel.setDirectional(GameControlsModel.Directionals.DOWN, false);
            }

            case KeyEvent.VK_SPACE -> {
                controlsViewModel.setAbility(GameControlsModel.Abilities.JUMP, false);
            }
        }
    }
}
