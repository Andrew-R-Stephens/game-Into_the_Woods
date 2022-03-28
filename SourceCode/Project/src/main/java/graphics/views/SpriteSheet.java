package graphics.views;

import utils.drawables.IDrawable;
import utils.files.AFileLoader;
import utils.files.AFileReader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

/**
 * TODO: Add description
 */
public class SpriteSheet {

    private BufferedImage spritesheet;

    private ArrayList<Sprite> sprites = new ArrayList<>();
    private int currentSprite = 0;

    public SpriteSheet(BufferedImage spritesheet, String jsonFile) {
        this.spritesheet = spritesheet;
        AFileReader reader = new AFileReader(jsonFile) {
            @Override
            public boolean read() {
                try {

                    // Initialize temp file
                    file = File.createTempFile(jsonFile.split("\\.")[0], ".json");

                    // Write to temporary file
                    // Create Input stream
                    InputStream is =
                            AFileReader.class.getClassLoader().getResourceAsStream(jsonFile);
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
                return true;
            }
        };
        reader.read();
    }

    public void draw(Graphics g, float scaleW, float scaleH, int x, int y, int w, int h) {
        g.drawImage(
                sprites.get(currentSprite).getSubImage(spritesheet),
                (int)(x * scaleW),
                (int)(y * scaleH),
                w,
                h,
                null);
    }
}
