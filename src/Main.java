import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Main Class
 * @author Elizabeth Roberts
 * @version 1.00, 24 January 2021
 */

public class Main {

    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        MainWindow m = new MainWindow();
        m.showWindow(); // show game window

        MusicPlayer mp = new MusicPlayer();
        mp.playMusic(); // play game music
    }
}
