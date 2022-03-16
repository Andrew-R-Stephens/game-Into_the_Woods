package models.controls.menu;

import models.controls.ControlsModel;
import models.environments.game.GameModel;
import props.prototypes.controls.AKeyController;

import java.awt.event.KeyEvent;

/**
 * Menu Key Controls define the controls for Main Manu or Pause Menu navigation with Key input.
 */
public class MenuKeyControls extends AKeyController {

    private ControlsModel controlsViewModel;
    private GameModel gameViewModel;

    public MenuKeyControls(ControlsModel controlsViewModel, GameModel gameViewModel) {
        this.controlsViewModel = controlsViewModel;
        this.gameViewModel = gameViewModel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> {
                controlsViewModel.setAction(ControlsModel.Actions.ESCAPE, true);
            }case KeyEvent.VK_ENTER -> {
                controlsViewModel.setAction(ControlsModel.Actions.ENTER, true);
            }

            case KeyEvent.VK_LEFT -> {
                controlsViewModel.setDirectional(ControlsModel.Directionals.LEFT, true);
            }
            case KeyEvent.VK_RIGHT -> {
                controlsViewModel.setDirectional(ControlsModel.Directionals.RIGHT, true);
            }
            case KeyEvent.VK_UP -> {
                controlsViewModel.setDirectional(ControlsModel.Directionals.UP, true);
            }
            case KeyEvent.VK_DOWN -> {
                controlsViewModel.setDirectional(ControlsModel.Directionals.DOWN, true);
            }


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> {
                controlsViewModel.setAction(ControlsModel.Actions.ESCAPE, false);
            }
            case KeyEvent.VK_ENTER -> {
                controlsViewModel.setAction(ControlsModel.Actions.ENTER, false);
            }

            case KeyEvent.VK_LEFT -> {
                controlsViewModel.setDirectional(ControlsModel.Directionals.LEFT, false);
            }
            case KeyEvent.VK_RIGHT -> {
                controlsViewModel.setDirectional(ControlsModel.Directionals.RIGHT, false);
            }
            case KeyEvent.VK_UP -> {
                controlsViewModel.setDirectional(ControlsModel.Directionals.UP, false);
            }
            case KeyEvent.VK_DOWN -> {
                controlsViewModel.setDirectional(ControlsModel.Directionals.DOWN, false);
            }
        }
    }

}
