package controls;

import viewmodels.controls.ControlsModel;
import viewmodels.game.GameModel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuControls implements KeyListener {

    private ControlsModel controlsViewModel;
    private GameModel gameViewModel;

    public MenuControls(ControlsModel controlsViewModel, GameModel gameViewModel) {
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
                System.out.println("Quitting");
                System.exit(1);
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
