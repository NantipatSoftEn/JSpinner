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

public class PlayButton extends Clickable implements IRenderable {
	public PlayButton(){
		type = Clickable.CIRCLE;
		width = 50;
		height = 50;
		x = (Config.screenWidth - width) / 2;
		y = (Config.screenHeight - height) / 2;
	}

	@Override
	public int getZ() {
		return 10000;
	}

	@Override
	public void draw(Graphics g) {
		drawButton(g, DrawingUtility.defaultButtonImg);
	}

	@Override
	public void onClickAction() {
		ScreenState.presentScreen = ScreenState.LEVEL_SELECT;
//		ScreenState.presentScreen = ScreenState.GAME;
	}
}
