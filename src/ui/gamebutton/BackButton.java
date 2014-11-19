package ui.gamebutton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

import control.Game;
import control.ScreenState;
import ui.Clickable;
import ui.DrawingUtility;
import ui.IRenderable;
import lib.Config;
import lib.InputUtility;
import logic.Board;

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
		drawButton(g, DrawingUtility.backButtonImg);
	}

	@Override
	public void onClickAction() {
//		JOptionPane.showMessageDialog(null, "BACK");
		if(ScreenState.presentScreen == ScreenState.LEVEL_SELECT)
			ScreenState.presentScreen = ScreenState.TITLE;
		if(ScreenState.presentScreen == ScreenState.GAME)
			ScreenState.presentScreen = ScreenState.LEVEL_SELECT;
	}
}
