/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package ui.gamebutton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import logic.Board;
import logic.IUpdatable;
import logic.Tile;
import ui.Clickable;
import ui.IRenderable;
import util.Config;
import util.DrawingUtility;
import util.InputUtility;

public class ClockWiseButton extends Clickable implements IRenderable,
		IUpdatable {

	private boolean isVisible = false;
	
	public ClockWiseButton(){
		type = Clickable.CIRCLE;
		width = 60;
		height = 60;
		updatePosition();
		mute();
	}

	@Override
	public int getZ() {
		return 20000;
	}
	
	@Override
	public void draw(Graphics g) {
		if(isVisible){
			drawButton(g, DrawingUtility.cwButtonImg);
		}
	}
	
	@Override
	public void updatePosition() {
	}
	
	@Override
	public void onClickAction() {
		if(isVisible){
			board.flip(board.getFlipX(), board.getFlipY(), board.getFlipSize(), Board.CW, true);
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
			int size = (board.getFlipSize() + 1) * board.getTileSize() + Config.tileGutter * (board.getFlipSize());
			x = flipping.getDrawX() + size + 5;
			y = flipping.getDrawY() + size / 2 - 25;
		}
		this.isVisible = isVisible;
	}
	
}
