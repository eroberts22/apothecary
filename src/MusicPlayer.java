import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * MusicPlayer Class
 * @author Elizabeth Roberts
 * @version 1.00, 24 January 2021
 */

public class MusicPlayer {

    /**
     * initialize MusicPlayer object
     */
    MusicPlayer() {}

    /**
     * play audio file on loop
     * @throws IOException io
     * @throws LineUnavailableException line unavailable
     * @throws UnsupportedAudioFileException unsupported audio
     */
    public void playMusic() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("src/resources/music/eerie_melody.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
}
