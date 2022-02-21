package utils;

import java.io.File;

public abstract class XMLParser {

    protected File file;
    public XMLParser(String file) {

        try {
            this.file = new File(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract boolean read();

}
