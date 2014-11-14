package ui.gamebutton;

import java.awt.Color;
import java.awt.Graphics;

import ui.Clickable;
import ui.IRenderable;
import lib.Config;
import lib.InputUtility;
import logic.Board;

public class UndoButton extends Clickable implements IRenderable {
	
	public UndoButton(){
		type = Clickable.CIRCLE;
		x = Config.screenWidth - 100;
		y = 10;
		width = 40;
		height = 40;
	}

	@Override
	public int getZ() {
		return 10000;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		if(isMouseOn()){
			g.setColor(Color.LIGHT_GRAY);
		}
		g.fillOval(x, y, width, height);
	}
	
	@Override
	public void onClickAction() {
		Clickable.board.undo();
	}
}
