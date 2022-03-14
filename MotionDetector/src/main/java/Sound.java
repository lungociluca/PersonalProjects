
import java.io.File;
import javax.sound.sampled.*;

public class Sound {

    private Clip clip;
    private String path = "sample4.wav";

    public Sound() {
        try {
            File file = new File("sample4.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        }catch(Exception e) {
            System.out.println("Can't play " + path + " audio file.");
        }
    }

    public void play() {
        clip.start();
    }

    public void stop() {
        clip.stop();
    }
}
