package controls;

import viewmodels.game.ControlsViewModel;
import viewmodels.game.GameViewModel;

import java.awt.event.KeyEvent;

public class GameKeyControls extends KeyController {

    private ControlsViewModel controlsViewModel;
    private GameViewModel gameViewModel;

    public GameKeyControls(GameViewModel gameViewModel) {
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
                controlsViewModel.setDirectional(ControlsViewModel.Directionals.LEFT, true);
            }
            case KeyEvent.VK_RIGHT -> {
                controlsViewModel.setDirectional(ControlsViewModel.Directionals.RIGHT, true);
            }
            case KeyEvent.VK_UP -> {
                controlsViewModel.setDirectional(ControlsViewModel.Directionals.UP, true);
            }
            case KeyEvent.VK_DOWN -> {
                controlsViewModel.setDirectional(ControlsViewModel.Directionals.DOWN, true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                controlsViewModel.setDirectional(ControlsViewModel.Directionals.LEFT, false);
            }
            case KeyEvent.VK_RIGHT -> {
                controlsViewModel.setDirectional(ControlsViewModel.Directionals.RIGHT, false);
            }
            case KeyEvent.VK_UP -> {
                controlsViewModel.setDirectional(ControlsViewModel.Directionals.UP, false);
            }
            case KeyEvent.VK_DOWN -> {
                controlsViewModel.setDirectional(ControlsViewModel.Directionals.DOWN, false);
            }
        }
    }
}
