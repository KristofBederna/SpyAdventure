package spyAdventure.common;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundManager {
    Clip clip;
    URL[] soundURL = new URL[10];

    public SoundManager() {
        soundURL[0] = getClass().getResource("/Assets/Music/James_Bond_007_Theme_8-Bit_Remix.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * 0.5f) + gainControl.getMinimum();
            gainControl.setValue(gain);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
