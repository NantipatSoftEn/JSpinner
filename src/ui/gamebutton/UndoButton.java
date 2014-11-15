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
		x = Config.screenWidth - 105;
		y = 5;
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
			g2.drawImage(DrawingUtility.getClickableImg(DrawingUtility.undoButtonImg, DrawingUtility.STATE_NORMAL), null, x, y);
		else
			if(InputUtility.isPicking())
				g2.drawImage(DrawingUtility.getClickableImg(DrawingUtility.undoButtonImg, DrawingUtility.STATE_CLICK), null, x, y);
			else	
				g2.drawImage(DrawingUtility.getClickableImg(DrawingUtility.undoButtonImg, DrawingUtility.STATE_HOVER), null, x, y);
	}
	
	@Override
	public void onClickAction() {
		Clickable.board.undo();
	}
}
