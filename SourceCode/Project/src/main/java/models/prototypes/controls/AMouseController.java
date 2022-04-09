package models.prototypes.controls;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class AMouseController implements MouseListener, MouseMotionListener {

    protected AControlsModel controlsViewModel;

    protected final int[] mPos = new int[]{-100, -100};

    protected boolean isLeftPressed = false;
    protected boolean isRightPressed = false;

    protected AMouseController(AControlsModel controlsViewModel) {
        this.controlsViewModel = controlsViewModel;
    }

    public boolean isLeftPressed() {
        return isLeftPressed;
    }

    public boolean isRightPressed() {
        return isRightPressed;
    }

    public int[] getPos() {
        return mPos;
    }

    public void resetInput() {
        isLeftPressed = false;
        isRightPressed = false;
    }
}
