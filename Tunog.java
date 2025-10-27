import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Tunog {
	private Clip clip;

	Tunog(String fname) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(fname));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void play() {
		if (clip != null) {
			clip.stop();
			clip.setFramePosition(0);
			clip.start();
		}
	}

	public void stop() {
		if (clip != null) {
			clip.stop();
		}
	}

	public void loop() {
		if (clip != null) {
			clip.stop();
			clip.setFramePosition(0);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
}