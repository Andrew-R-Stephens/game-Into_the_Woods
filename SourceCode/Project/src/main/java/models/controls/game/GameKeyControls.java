package models.controls.game;

import models.controls.ControlsModel;
import utils.controllers.AKeyController;

import java.awt.event.KeyEvent;

/**
 * Game Key Controls define the controls for Character navigation and ability with Key input.
 */
public class GameKeyControls extends AKeyController {

    private final ControlsModel controlsViewModel;

    public GameKeyControls(ControlsModel controlsViewModel) {
        this.controlsViewModel = controlsViewModel;
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
                controlsViewModel.setDirectional(ControlsModel.Directionals.LEFT, true);
            }
            case KeyEvent.VK_D -> {
                controlsViewModel.setDirectional(ControlsModel.Directionals.RIGHT, true);
            }
            case KeyEvent.VK_W -> {
                controlsViewModel.setDirectional(ControlsModel.Directionals.UP, false);
            }
            case KeyEvent.VK_S -> {
                controlsViewModel.setDirectional(ControlsModel.Directionals.DOWN, true);
            }

            case KeyEvent.VK_SPACE -> {
                controlsViewModel.setAbility(ControlsModel.Abilities.JUMP, true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A -> {
                controlsViewModel.setDirectional(ControlsModel.Directionals.LEFT, false);
            }
            case KeyEvent.VK_D -> {
                controlsViewModel.setDirectional(ControlsModel.Directionals.RIGHT, false);
            }
            case KeyEvent.VK_W -> {
                controlsViewModel.setDirectional(ControlsModel.Directionals.UP, false);
            }
            case KeyEvent.VK_S -> {
                controlsViewModel.setDirectional(ControlsModel.Directionals.DOWN, false);
            }

            case KeyEvent.VK_SPACE -> {
                controlsViewModel.setAbility(ControlsModel.Abilities.JUMP, false);
            }
        }
    }
}
