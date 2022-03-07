package utils;

import java.io.*;

public abstract class AXMLParser {

    protected String path, name, type;
    protected File file;

    public AXMLParser(String filePath, String fileName, String fileType) {

        path = filePath;
        name = fileName;
        type = fileType;

        try {
            // Initialize temp file
            file = File.createTempFile(fileName+fileType, fileType);
            System.out.println(filePath + fileName + fileType);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public abstract boolean read();



}
