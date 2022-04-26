package models.utils.files;

import java.io.File;

/**
 * <p>The AFileReader class accepts and stores a File.</p>
 * <p>The read method should be defined at instantiation, and it is called at runtime.</p>
 */
public abstract class AFileReader {

    protected File file;

    /**
     * <p>Immediately uses the definition of the abstract read method.</p>
     */
    public AFileReader() {
        read();
    }

    /**
     * <p>Defined by the inheriting class. Should be dedicated to an IO process for files.</p>
     * @return if the file was properly read.
     */
    public abstract boolean read();

}
