package inputs;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundHandler {

    private Clip clip;

    public void playSound(String filePath) {
        try {
            // Load the audio file
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            // Get the clip resource and open the audio stream
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(10);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}