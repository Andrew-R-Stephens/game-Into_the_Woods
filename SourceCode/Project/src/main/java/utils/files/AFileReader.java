package utils.files;

import java.io.*;

/**
 * TODO: Add description
 */
public abstract class AFileReader {

    /**
     * The File.
     */
    protected File file;

    public AFileReader(String fullPath) {

    }

    /**
     * Instantiates a new A file reader.
     *
     * @param filePath the file path
     * @param fileName the file name
     * @param fileType the file type
     */
    public AFileReader(String filePath, String fileName, String fileType) {

        try {

            // Initialize temp file
            file = File.createTempFile(fileName, fileType);

            // Write to temporary file
            // Create Input stream
            InputStream is =
                    AFileReader.class.getClassLoader().getResourceAsStream(filePath + fileName + fileType);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader r = new BufferedReader(isr);

            // Create Write stream and parse through
            FileWriter writer = new FileWriter(file);
            String line;
            while (((line = r.readLine())) != null) {
                //System.out.println(line);
                writer.write(line + "\n");
            }

            writer.close();
            r.close();
            isr.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Read boolean.
     *
     * @return the boolean
     */
    public abstract boolean read();


}
