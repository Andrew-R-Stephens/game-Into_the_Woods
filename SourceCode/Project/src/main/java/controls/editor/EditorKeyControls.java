package controls.editor;

import models.prototypes.controls.AControls;
import models.prototypes.controls.AKeyController;

import java.awt.event.KeyEvent;

/**
 * Dedicated GameEnvironment Controls class that extends the AKeyController.
 */
public class EditorKeyControls extends AKeyController {

    /**
     * <p>Creates the GameKeyControls and references the parent AControls</p>
     * @param controlsViewModel - the AControls parent model
     * @author Andrew Stephens
     */
    public EditorKeyControls(AControls controlsViewModel) {
        super(controlsViewModel);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> {
                controlsModel.setAction(EditorControls.Actions.ESCAPE, true);
            }
            case KeyEvent.VK_A -> {
                controlsModel.setDirectional(EditorControls.Directionals.LEFT, true);
            }
            case KeyEvent.VK_D -> {
                controlsModel.setDirectional(EditorControls.Directionals.RIGHT, true);
            }
            case KeyEvent.VK_W -> {
                controlsModel.setDirectional(EditorControls.Directionals.UP, true);
            }
            case KeyEvent.VK_S -> {
                controlsModel.setDirectional(EditorControls.Directionals.DOWN, true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> {
                controlsModel.setAction(EditorControls.Actions.ESCAPE, false);
            }
            case KeyEvent.VK_A -> {
                controlsModel.setDirectional(EditorControls.Directionals.LEFT, false);
            }
            case KeyEvent.VK_D -> {
                controlsModel.setDirectional(EditorControls.Directionals.RIGHT, false);
            }
            case KeyEvent.VK_W -> {
                controlsModel.setDirectional(EditorControls.Directionals.UP, false);
            }
            case KeyEvent.VK_S -> {
                controlsModel.setDirectional(EditorControls.Directionals.DOWN, false);
            }
        }
    }

}
