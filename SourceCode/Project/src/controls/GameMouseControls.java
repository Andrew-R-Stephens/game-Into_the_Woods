package controls;

import game.objects.entities.Player;
import viewmodels.game.GameViewModel;

import java.awt.event.MouseEvent;

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
        gameViewModel.addGameObject(new Player(e.getX(), e.getY(), 10, 10, 0, 0, 9.8));
        System.out.println("Clicked!");
    }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) {  }

}
