package ui.gamebutton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

import ui.Clickable;
import ui.DrawingUtility;
import ui.IRenderable;
import lib.Config;
import lib.InputUtility;
import logic.Board;
import logic.Game;

public class BackButton extends Clickable implements IRenderable {
	public BackButton(){
		type = Clickable.CIRCLE;
		x = 5;
		y = Config.screenHeight - 55;
		width = 50;
		height = 50;
	}

	@Override
	public int getZ() {
		return 10000;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(!isMouseOn())
			g2.drawImage(DrawingUtility.getClickableImg(DrawingUtility.backButtonImg, DrawingUtility.STATE_NORMAL), null, x, y);
		else
			if(InputUtility.isPicking())
				g2.drawImage(DrawingUtility.getClickableImg(DrawingUtility.backButtonImg, DrawingUtility.STATE_CLICK), null, x, y);
			else	
				g2.drawImage(DrawingUtility.getClickableImg(DrawingUtility.backButtonImg, DrawingUtility.STATE_HOVER), null, x, y);
	}

	@Override
	public void onClickAction() {
		JOptionPane.showMessageDialog(null, "BACK");
		Game.setPlaying(false);
	}
}
