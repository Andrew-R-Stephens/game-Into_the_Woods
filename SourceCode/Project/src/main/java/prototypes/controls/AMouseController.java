package prototypes.controls;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * TODO: Add description
 */
public abstract class AMouseController implements MouseListener, MouseMotionListener {

    protected AControlsModel controlsViewModel;

    protected final int[] mPos = new int[]{-100, -100};

    protected boolean isLeftPressed = false;
    protected boolean isRightPressed = false;

    protected AMouseController(AControlsModel controlsViewModel) {
        this.controlsViewModel = controlsViewModel;
    }

    /**
     * Is left pressed boolean.
     *
     * @return the boolean
     */
    public boolean isLeftPressed() {
        return isLeftPressed;
    }

    /**
     * Is right pressed boolean.
     *
     * @return the boolean
     */
    public boolean isRightPressed() {
        return isRightPressed;
    }

    /**
     * Get pos int [ ].
     *
     * @return the int [ ]
     */
    public int[] getPos() {
        return mPos;
    }

    /**
     * Reset input.
     */
    public void resetInput() {
        isLeftPressed = false;
        isRightPressed = false;
    }
}
