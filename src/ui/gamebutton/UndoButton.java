package ui.gamebutton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import ui.Clickable;
import ui.IRenderable;
import util.Config;
import util.DrawingUtility;
import util.InputUtility;
import logic.Board;

public class UndoButton extends Clickable implements IRenderable {
	
	public UndoButton(){
		type = Clickable.CIRCLE;
		updatePosition();
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
	public void updatePosition() {
		width = 50;
		height = 50;
		x = Config.screenWidth - 105;
		y = 5;
	}
	
	@Override
	public void onClickAction() {
		Clickable.board.undo();
	}
}
