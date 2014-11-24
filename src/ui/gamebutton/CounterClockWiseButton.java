package ui.gamebutton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import lib.Config;
import lib.DrawingUtility;
import lib.InputUtility;
import logic.Board;
import logic.IUpdatable;
import logic.Tile;
import ui.Clickable;
import ui.IRenderable;

public class CounterClockWiseButton extends Clickable implements IRenderable,
		IUpdatable {

	private boolean isVisible = false;
	
	public CounterClockWiseButton(){
		type = Clickable.CIRCLE;
		x = Config.screenWidth / 2 - 100;
		y = 100;
		width = 60;
		height = 60;
		mute();
	}

	@Override
	public int getZ() {
		return 20000;
	}
	
	@Override
	public void draw(Graphics g) {
		if(isVisible){
			drawButton(g, DrawingUtility.ccwButtonImg);
		}
	}
	
	@Override
	public void onClickAction() {
		if(isVisible){
			board.flip(board.getFlipX(), board.getFlipY(), board.getFlipSize(), Board.CCW, true);
			board.clearSelected();
			board.setRepeatMoveEnebled(true);
		}
	}
	
	// Getters & Setters

	public boolean isVisible() {
		return isVisible;
	}
	
	public void setVisible(boolean isVisible) {
		if(isVisible){
			Tile flipping = board.getTileAt(board.getFlipX(), board.getFlipY());
			int size = (board.getFlipSize() + 1) * board.getTileSize() + Config.tileGutter * board.getFlipSize();
			x = flipping.getDrawX() - width - 5;
			y = flipping.getDrawY() + size / 2 - 25;
		}
		this.isVisible = isVisible;
	}
	
}
