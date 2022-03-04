package controls;

import utils.KeyController;
import viewmodels.controls.ControlsViewModel;

import java.awt.event.KeyEvent;

public class GameKeyControls extends KeyController {

    private ControlsViewModel controlsViewModel;

    public GameKeyControls(ControlsViewModel controlsViewModel) {
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
                controlsViewModel.setDirectional(ControlsViewModel.Directionals.LEFT, true);
            }
            case KeyEvent.VK_D -> {
                controlsViewModel.setDirectional(ControlsViewModel.Directionals.RIGHT, true);
            }
            case KeyEvent.VK_W -> {
                controlsViewModel.setDirectional(ControlsViewModel.Directionals.UP, true);
            }
            case KeyEvent.VK_S -> {
                controlsViewModel.setDirectional(ControlsViewModel.Directionals.DOWN, true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A -> {
                controlsViewModel.setDirectional(ControlsViewModel.Directionals.LEFT, false);
            }
            case KeyEvent.VK_D -> {
                controlsViewModel.setDirectional(ControlsViewModel.Directionals.RIGHT, false);
            }
            case KeyEvent.VK_W -> {
                controlsViewModel.setDirectional(ControlsViewModel.Directionals.UP, false);
            }
            case KeyEvent.VK_S -> {
                controlsViewModel.setDirectional(ControlsViewModel.Directionals.DOWN, false);
            }
        }
    }
}
