package media;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class AudioPlayer {
    private static Clip LongMusic = null;

    private static final Map<Object, File> sounds = new HashMap<>();

    public static void addAudioByFilePath(Object audioName, File file) {
        sounds.put(audioName, file);
    }

    public static void addAudioByFilePath(Object audioName, String path) {
        sounds.put(audioName, Paths.get(path).toFile());
    }

    public static void playSounds(Object audioName) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sounds.get(audioName));
            AudioFormat format = audioInputStream.getFormat();
            long frames = audioInputStream.getFrameLength();
            clip.open(audioInputStream);
            double durationInSeconds = (frames+0.0) / format.getFrameRate();
            if(durationInSeconds > 80){
                LongMusic = clip;
            }
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopSounds() {
        try {
            LongMusic.stop();
            LongMusic = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
