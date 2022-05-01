package models.utils.audio;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import models.utils.config.Config;
import models.utils.resources.Resources;

import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * <p>A wrapper for the AdvancedPlayer.</p>
 * @author Andrew Stephens
 */
public class SuperPlayer {

    /**
     * The AdvancedPlayer for this audio source
     */
    private AdvancedPlayer player = null;

    /**
     * If this audio is playing
     */
    private boolean isPlaying = false;

    /**
     * Creates a new SuperPlayer which acts to wrap the AdvancedPlayer for more functionality
     * @param filepath The path of the audio file
     */
    public SuperPlayer(String filepath) {
        BufferedInputStream ins = getBuffInstream(filepath);
        if(ins != null) {
            try {
                player = new AdvancedPlayer(ins);
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets the BufferedInputStream of the classpath for the specified audio file
     * @param filepath The path of the audio file
     * @return The BufferedInputStream for the audio file
     */
    private BufferedInputStream getBuffInstream(String filepath) {
        InputStream resourceBuff = SuperPlayer.class.getResourceAsStream(filepath);

        if(resourceBuff == null) {
            System.out.println("Buffer is null");
            return null;
        }

        return new BufferedInputStream(resourceBuff);
    }

    /**
     * Starts the audio clip and records that it is playing.
     */
    public void play() {
        if(Config.audioEnabled) {
            Thread t = new Thread(() -> {
                try {
                    isPlaying = true;
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                    isPlaying = false;
                }
            });
            t.start();
        }
    }

    /**
     * Closes the audio clip and records that it is not playing.
     */
    public void close() {
        isPlaying = false;
        player.close();
    }

    /**
     * Gets if the audio file is playing.
     * @return if the audio file is playing.
     */
    public boolean isPlaying() {
        return isPlaying;
    }
}
