package ui.gamebutton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import ui.Clickable;
import ui.DrawingUtility;
import ui.IRenderable;
import lib.Config;
import lib.InputUtility;
import logic.Board;

public class UndoButton extends Clickable implements IRenderable {
	
	public UndoButton(){
		type = Clickable.CIRCLE;
		width = 50;
		height = 50;
		x = Config.screenWidth - 105;
		y = 5;
	}

	@Override
	public int getZ() {
		return 10000;
	}

	@Override
	public void draw(Graphics g) {
		drawButton(g, DrawingUtility.undoButtonImg);
	}
	
	@Override
	public void onClickAction() {
		Clickable.board.undo();
	}
}
