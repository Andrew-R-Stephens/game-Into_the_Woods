package models.controls.menu;

import models.controls.MenuControlsModel;
import prototypes.controls.AKeyController;

import java.awt.event.KeyEvent;

/**
 * Menu Key Controls define the controls for Main Manu or Pause Menu navigation with Key input.
 */
public class MenuKeyControls extends AKeyController {

    private final MenuControlsModel controlsViewModel;

    /**
     * Instantiates a new Menu key controls.
     *
     * @param controlsViewModel the controls view model
     */
    public MenuKeyControls(MenuControlsModel controlsViewModel) {
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
                //controlsViewModel.setAction(MenuControlsModel.Actions.ESCAPE, true);
            }case KeyEvent.VK_ENTER -> {
                controlsViewModel.setAction(MenuControlsModel.Actions.ENTER, true);
            }

            case KeyEvent.VK_LEFT -> {
                controlsViewModel.setDirectional(MenuControlsModel.Directionals.LEFT, true);
            }
            case KeyEvent.VK_RIGHT -> {
                controlsViewModel.setDirectional(MenuControlsModel.Directionals.RIGHT, true);
            }
            case KeyEvent.VK_UP -> {
                controlsViewModel.setDirectional(MenuControlsModel.Directionals.UP, true);
            }
            case KeyEvent.VK_DOWN -> {
                controlsViewModel.setDirectional(MenuControlsModel.Directionals.DOWN, true);
            }


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> {
                controlsViewModel.setAction(MenuControlsModel.Actions.ESCAPE, false);
            }
            case KeyEvent.VK_ENTER -> {
                controlsViewModel.setAction(MenuControlsModel.Actions.ENTER, false);
            }

            case KeyEvent.VK_LEFT -> {
                controlsViewModel.setDirectional(MenuControlsModel.Directionals.LEFT, false);
            }
            case KeyEvent.VK_RIGHT -> {
                controlsViewModel.setDirectional(MenuControlsModel.Directionals.RIGHT, false);
            }
            case KeyEvent.VK_UP -> {
                controlsViewModel.setDirectional(MenuControlsModel.Directionals.UP, false);
            }
            case KeyEvent.VK_DOWN -> {
                controlsViewModel.setDirectional(MenuControlsModel.Directionals.DOWN, false);
            }
        }
    }

}
