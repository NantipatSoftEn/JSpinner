package ui.gamebutton;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import ui.Clickable;
import ui.IRenderable;
import util.AudioUtility;
import util.Config;
import util.DrawingUtility;
import util.InputUtility;
import logic.Board;

public class ToggleSoundButton extends Clickable implements IRenderable {
	public ToggleSoundButton(){
		type = Clickable.CIRCLE;
		updatePosition();
		isMuted = true;
	}

	@Override
	public int getZ() {
		return 10000;
	}

	@Override
	public void draw(Graphics g) {
		if(AudioUtility.isMuted())
			drawButton(g, DrawingUtility.soundOnButtonImg);
		else
			drawButton(g, DrawingUtility.soundOffButtonImg);
	}
	
	@Override
	public void updatePosition() {
		width = 50;
		height = 50;
		x = width + 5;
		y = Config.screenHeight - height - 5;
	}
	
	@Override
	public void onClickAction() {
		AudioUtility.setMuted(!AudioUtility.isMuted());
		if(AudioUtility.isMuted())
			AudioUtility.bgm.stop();
		else
			AudioUtility.bgm.loop();
	}
}
