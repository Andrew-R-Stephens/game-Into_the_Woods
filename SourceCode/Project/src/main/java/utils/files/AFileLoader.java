package utils.files;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * TODO: Add description
 */
public abstract class AFileLoader {

    /**
     * The File.
     */
    protected File file;

    /**
     * Sets file.
     *
     * @param filename the filename
     * @throws NullPointerException  the null pointer exception
     * @throws FileNotFoundException the file not found exception
     */
    protected void setFile(String filename) throws NullPointerException, FileNotFoundException {
        if (filename == null) {
            throw new NullPointerException("File name cannot be null");
        }

        file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException("File " + file.getName() + " does not exist.");
        }

    }

}
