package logic;

import java.awt.*;

import javax.rmi.CORBA.Util;
import javax.swing.text.Utilities;

import lib.Config;
import lib.DrawingUtility;
import lib.IRenderable;
import lib.Utility;

public abstract class Tile implements IRenderable {
	public static final int NOT_A_BLOCK = -1; // may check if the tile is null.

	private int number;
	private int correctX, correctY;
	private int currentX, currentY;
	private boolean isSelected;
	private Board board;
	
	public Tile(){
		this.number = 0;
	}
	
	public Tile(int number, Board belongsTo) {
		this.number = number;
		this.board = belongsTo;
		correctX = (number - 1) % this.board.getBoardWidth();
		correctY = (number - 1) / this.board.getBoardWidth();
		currentX = (number - 1) % this.board.getBoardWidth();
		currentY = (number - 1) / this.board.getBoardWidth();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public void setCurrentLocation(int x, int y) {
		this.currentX = x;
		this.currentY = y;
	}

	public abstract void performEffect();
	public abstract int getZ();
	
	public void draw(Graphics g){
		if(number == NOT_A_BLOCK)
			return;
		int size = board.getTileSize(); //ScreenManager.getTileSize(); //how to determine tile size?
		int x = board.getX() + (currentX) * (board.getTileSize() + Config.tileGutter);
		int y = board.getY() + (currentY) * (board.getTileSize() + Config.tileGutter);
		Font font = new Font("Tahoma", Font.BOLD, 20);
		
		int gr = Math.abs(128 - number * 255 / (board.getBoardWidth() * board.getBoardHeight()));
		int re = 255 - number * 255 / (board.getBoardWidth() * board.getBoardHeight());
		int bl = number * 255 / (board.getBoardWidth() * board.getBoardHeight());
		g.setColor(new Color(re, gr, bl));
		
		if(isSelected)
			g.setColor(Color.RED);
		g.fillRect(x, y, size, size);
		
		g.setColor(Color.WHITE);
		DrawingUtility.drawStringInBox("" + number, font, x, y, size, size, DrawingUtility.TEXT_CENTER, g);
	}

	public String toString() {
		return "[" + number + "]";
	}
}
