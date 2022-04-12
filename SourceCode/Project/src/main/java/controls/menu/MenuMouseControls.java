package controls.menu;

import models.prototypes.controls.AControls;
import models.prototypes.controls.AMouseController;

import java.awt.event.MouseEvent;

public class MenuMouseControls extends AMouseController {

    public MenuMouseControls(AControls controlsViewModel) {
        super(controlsViewModel);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setPos(e.getX(), e.getY());
        //reset();
    }

}
