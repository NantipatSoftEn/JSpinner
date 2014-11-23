package ui.gamebutton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

import control.Game;
import control.ScreenState;
import ui.Clickable;
import ui.IRenderable;
import lib.Config;
import lib.DrawingUtility;
import lib.InputUtility;
import logic.Board;

public class SettingsButton extends Clickable implements IRenderable {
	public SettingsButton(){
		type = Clickable.CIRCLE;
		width = 50;
		height = 50;
		x = 5;
		y = Config.screenHeight - width - 5;
	}

	@Override
	public int getZ() {
		return 10000;
	}

	@Override
	public void draw(Graphics g) {
		drawButton(g, DrawingUtility.settingsButtonImg);
	}

	@Override
	public void onClickAction() {
		JOptionPane.showMessageDialog(null, "Settings");
	}
}
