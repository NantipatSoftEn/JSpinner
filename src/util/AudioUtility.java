/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class AudioUtility {
	public static final AudioClip bgm = loadAudio("res/sound/bgm.wav");
	public static final AudioClip clickSound = loadAudio("res/sound/click.wav");
	public static final AudioClip flipSound = loadAudio("res/sound/flip.wav");
	public static final AudioClip fightSound = loadAudio("res/sound/fight.wav");
	public static final AudioClip solvedSound = loadAudio("res/sound/solved.wav");
	private static boolean isMuted;
	
	private static AudioClip loadAudio(String directory){
		try {
			return Applet.newAudioClip(AudioUtility.class.getClassLoader().getResource(directory));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Resource audio not found!", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	public static void setMuted(boolean isMuted) {
		AudioUtility.isMuted = isMuted;
	}
	
	public static boolean isMuted() {
		return isMuted;
	}
	
	public static void playSound(AudioClip au){
		if(!isMuted){
			au.play();
		}
	}
}
