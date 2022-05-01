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

    private AdvancedPlayer player = null;

    private boolean isPlaying = false;

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

    private BufferedInputStream getBuffInstream(String filepath) {
        InputStream resourceBuff = SuperPlayer.class.getResourceAsStream(filepath);

        if(resourceBuff == null) {
            System.out.println("Buffer is null");
            return null;
        }

        return new BufferedInputStream(resourceBuff);
    }

    public void play() {
        if(Config.audioEnabled) {
            try {
                isPlaying = true;
                player.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
                isPlaying = false;
            }
        }
    }

    public void stop() {
        isPlaying = false;
        player.close();
    }

    public void close() {
        isPlaying = false;
        player.close();
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
