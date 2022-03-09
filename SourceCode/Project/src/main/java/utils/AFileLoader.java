package utils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * TODO: Add description
 */
public abstract class AFileLoader {

    protected File file;

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
