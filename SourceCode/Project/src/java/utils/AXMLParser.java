package utils;

import java.io.File;

public abstract class AXMLParser {

    protected File file;
    public AXMLParser(String file) {

        try {
            this.file = new File(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract boolean read();

}
