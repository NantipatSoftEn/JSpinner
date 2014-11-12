package ui.button;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import ui.IRenderable;
import lib.Config;
import lib.InputUtility;
import logic.Board;

public class ShuffleButton extends Clickable implements IRenderable {
	
	
	public ShuffleButton(){
		type = Clickable.CIRCLE;
		x = Config.screenWidth - 40;
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
			g.setColor(Color.RED);
		}
		g.fillOval(x, y, width, height);
	}
	
	@Override
	public void onClickAction() {
		Clickable.board.shuffle(Board.DEFAULT_SHUFFLE);
	}
}
