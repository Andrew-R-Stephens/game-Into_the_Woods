package controls;

import objects.actors.TestActor;
import utils.MouseController;
import viewmodels.game.GameViewModel;

import java.awt.event.MouseEvent;
import java.util.Random;

public class GameMouseControls extends MouseController {

    private final GameViewModel gameViewModel;

    public GameMouseControls(GameViewModel gameViewModel) {
        this.gameViewModel = gameViewModel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gameViewModel.addGameObject(
                new TestActor(
                        e.getX(), e.getY(),
                        10,
                        10,
                        new Random().nextInt(-10, 10),
                        new Random().nextInt(-10, 10),
                        20)
        );
        System.out.println("Clicked!");
    }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) {  }

}
