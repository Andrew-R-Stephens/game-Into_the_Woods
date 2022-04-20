package models.utils.files;

import java.io.File;

/**
 * <p></p>
 */
public abstract class AFileReader {

    protected File file;

    /**
     * <p></p>
     */
    public AFileReader() {
        read();
    }

    /**
     * <p></p>
     * @return
     */
    public abstract boolean read();

}
