package models.controls.menu;

import models.controls.MenuControlsModel;
import prototypes.controls.AControlsModel;
import prototypes.controls.AKeyController;

import java.awt.event.KeyEvent;

/**
 * Menu Key Controls define the controls for Main Manu or Pause Menu navigation with Key input.
 */
public class MenuKeyControls extends AKeyController{

    /**
     * Instantiates a new Menu key controls.
     *
     * @param controlsModel the controls view model
     */
    public MenuKeyControls(AControlsModel controlsModel) {
        super(controlsModel);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> {
                controlsModel.setAction(MenuControlsModel.Actions.ESCAPE, true);
            }case KeyEvent.VK_ENTER -> {
                controlsModel.setAction(MenuControlsModel.Actions.ENTER, true);
            }

            case KeyEvent.VK_LEFT -> {
                controlsModel.setDirectional(MenuControlsModel.Directionals.LEFT, true);
            }
            case KeyEvent.VK_RIGHT -> {
                controlsModel.setDirectional(MenuControlsModel.Directionals.RIGHT, true);
            }
            case KeyEvent.VK_UP -> {
                controlsModel.setDirectional(MenuControlsModel.Directionals.UP, true);
            }
            case KeyEvent.VK_DOWN -> {
                controlsModel.setDirectional(MenuControlsModel.Directionals.DOWN, true);
            }


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> {
                controlsModel.setAction(MenuControlsModel.Actions.ESCAPE, false);
            }
            case KeyEvent.VK_ENTER -> {
                controlsModel.setAction(MenuControlsModel.Actions.ENTER, false);
            }

            case KeyEvent.VK_LEFT -> {
                controlsModel.setDirectional(MenuControlsModel.Directionals.LEFT, false);
            }
            case KeyEvent.VK_RIGHT -> {
                controlsModel.setDirectional(MenuControlsModel.Directionals.RIGHT, false);
            }
            case KeyEvent.VK_UP -> {
                controlsModel.setDirectional(MenuControlsModel.Directionals.UP, false);
            }
            case KeyEvent.VK_DOWN -> {
                controlsModel.setDirectional(MenuControlsModel.Directionals.DOWN, false);
            }
        }
    }

    public boolean isAction(MenuControlsModel.Actions type) {
        return ((MenuControlsModel) controlsModel).isAction(type);
    }

    public void resetInput() {
        ((MenuControlsModel) controlsModel).resetInput();
    }

}
