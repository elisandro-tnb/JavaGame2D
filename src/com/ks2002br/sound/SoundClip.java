package com.ks2002br.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundClip {

	private Clip clip = null;

	public SoundClip(String fileName, float volume) {

		File soundFile = new File(fileName);

		try {

			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = audioIn.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);

			clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioIn);

			// Controle de Volume
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volume);

			System.out.println("[ DEBUG SOUNDCLIP ] = SUCESSO AO CARREGAR ARQUIVO : " + soundFile);

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
			System.err.println("[ DEBUG SOUNDCLIP ] OPPS, DEU RUIM, ARQUIVO DE SOM  INVALIDO OU INEXISTENTE..... ");

		}

	}

	public void play() {
		if (clip != null) {
			clip.stop();
			clip.setFramePosition(0);
			clip.start();
		}
	}

	public void playLoop() {

		try {

			new Thread() {

				public void run() {
					clip.loop(MAX_PRIORITY);
				}

			}.start();

		} catch (Throwable e) {
			System.err.println("IIII  deu ruim  " + e);
		}

	}

	public void stop() {
		clip.stop();
		clip.setFramePosition(0);
	}

}
