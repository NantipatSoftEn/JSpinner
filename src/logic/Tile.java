package logic;

import java.awt.*;

import javax.rmi.CORBA.Util;
import javax.swing.text.Utilities;

import ui.DrawingUtility;
import ui.IRenderable;
import lib.Config;
import lib.Utility;

public abstract class Tile implements IRenderable {
	public static final int NOT_A_TILE = -1; // may check if the tile is null.

	private int number;
	private int correctX, correctY;
	private int currentX, currentY;
	private int drawX, drawY;
	private boolean isSelected;
	private Board board;

	public Tile() {
		this.number = 0;
	}

	public Tile(int number, Board belongsTo, int correctX, int correctY) {
		this.number = number;
		this.board = belongsTo;
		setCorrectLocation(correctX, correctY);
		setCurrentLocation(correctX, correctY);
	}

	public void setCorrectX(int x) {
		this.correctX = x;
	}

	public void setCorrectY(int y) {
		this.correctY = y;
	}
	public boolean isCorrect (){
		return correctX == currentX && correctY == currentY;
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
	
	public boolean isATile(){
		return number != NOT_A_TILE;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public void setCurrentLocation(int x, int y) {
		this.currentX = x;
		this.currentY = y;
	}
	
	public Point getCorrectLocation() {
		return new Point(correctX, correctY);
	}
	public Point getCurrentLocation() {
		return new Point(currentX, currentY);
	}
	
	public int getDrawX() {
		return drawX;
	}
	
	public int getDrawY() {
		return drawY;
	}

	public void setCorrectLocation(int i, int j) {
		this.correctX = i;
		this.correctY = j;
	}

	public abstract void performEffect();

	public abstract int getZ();

	public void draw(Graphics g) {
		if (number == NOT_A_TILE)
			return;
		int size = board.getTileSize(); // ScreenManager.getTileSize(); //how to
										// determine tile size?
		drawX = board.getX() + (currentX)
				* (board.getTileSize() + Config.tileGutter);
		drawY = board.getY() + (currentY)
				* (board.getTileSize() + Config.tileGutter);
		Font font = new Font("Tahoma", Font.BOLD, 20);
		
		int gr = Math.abs(128 - number * 255 / (board.getBoardWidth() * board.getBoardHeight()));
		int re = 255 - number * 255 / (board.getBoardWidth() * board.getBoardHeight());
		int bl = number * 255 / (board.getBoardWidth() * board.getBoardHeight());
		g.setColor(new Color(re, gr, bl));

		if (isSelected)
			g.setColor(Color.RED);
		g.fillRect(drawX, drawY, size, size);

		g.setColor(Color.WHITE);
		DrawingUtility.drawStringInBox("" + number, font, drawX, drawY, size, size,
				DrawingUtility.TEXT_CENTER, g);
	}

	public String toString() {
		return "[" + number + "]";
	}
}
