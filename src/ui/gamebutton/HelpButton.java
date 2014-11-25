package ui.gamebutton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

import ui.Clickable;
import ui.HelpPanel;
import ui.IRenderable;
import util.Config;
import util.DrawingUtility;
import util.InputUtility;
import logic.Board;

public class HelpButton extends Clickable implements IRenderable {
	public HelpButton(){
		type = Clickable.CIRCLE;
		width = 50;
		height = 50;
		x = Config.screenWidth - width - 5;
		y = Config.screenHeight - 55;
	}

	@Override
	public int getZ() {
		return 10000;
	}

	@Override
	public void draw(Graphics g) {
		drawButton(g, DrawingUtility.helpButtonImg);
	}

	@Override
	public void onClickAction() {
		HelpPanel.helpPanel.setVisible(!HelpPanel.helpPanel.isVisible());
	}
}
