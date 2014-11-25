package ui.gamebutton;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import ui.Clickable;
import ui.IRenderable;
import util.Config;
import util.DrawingUtility;
import util.InputUtility;
import logic.Board;

public class ShuffleButton extends Clickable implements IRenderable {
	public ShuffleButton(){
		type = Clickable.CIRCLE;
		width = 50;
		height = 50;
		x = Config.screenWidth - width - 5;
		y = 5;
	}

	@Override
	public int getZ() {
		return 10000;
	}

	@Override
	public void draw(Graphics g) {
		drawButton(g, DrawingUtility.newGameButtonImg);
	}
	
	@Override
	public void onClickAction() {
		Clickable.board.newGame();
	}
}
