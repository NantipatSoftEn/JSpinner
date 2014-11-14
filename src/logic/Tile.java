package logic;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Date;

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
	private boolean isMouseOn;
	private boolean isEnabled;
	private boolean isMoving;
	protected int z;
	
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
	
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
		if(isMoving)
			z = 15000;
		else
			z = 10000;
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

	public boolean isMouseOn() {
		return isMouseOn;
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}
	
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public void setMouseOn(boolean isMouseOn) {
		this.isMouseOn = isMouseOn;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public void setCurrentLocation(int x, int y) {
		this.currentX = x;
		this.currentY = y;
	}
	
	public void setDrawLocation(){
		drawX = board.getX() + (currentX) * (board.getTileSize() + Config.tileGutter);
		drawY = board.getY() + (currentY) * (board.getTileSize() + Config.tileGutter);
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
		
		Graphics2D g2 = (Graphics2D) g;		
		
		int size = board.getTileSize();
		
		drawX = board.getX() + (currentX) * (board.getTileSize() + Config.tileGutter);
		drawY = board.getY() + (currentY) * (board.getTileSize() + Config.tileGutter);
		Font font = new Font("Tahoma", Font.BOLD, 20);
		
		Rectangle2D.Double rect = new Rectangle2D.Double(drawX, drawY, board.getTileSize(), board.getTileSize());
		AffineTransform at = new AffineTransform(); 
		
		Double theta = Math.PI / 2 * board.getCurrentFrame() / Config.animationFrameCount; 
		if(board.getLatestMove() != null && board.getLatestMove().dir == Board.CCW)
			theta *= -1;
		if(isMoving){
			at.rotate(theta, board.getMoveCenterX(), board.getMoveCenterY());
		}
		Shape tileRect = at.createTransformedShape(rect);
				
		if(isSelected){
			g.setColor(DrawingUtility.SELECTED);
			g.fillRect(drawX-5, drawY-5, size+10, size+10);
		}
		
		int gr = Math.abs(128 - number * 255 / (board.getBoardWidth() * board.getBoardHeight()));
		int re = 255 - number * 255 / (board.getBoardWidth() * board.getBoardHeight());
		int bl = number * 255 / (board.getBoardWidth() * board.getBoardHeight());
		g.setColor(new Color(re, gr, bl));
		
		if(isMouseOn)
			g.setColor(g.getColor().brighter());
		if(isEnabled)
			g.setColor(g.getColor().brighter());
		
		g2.fill(tileRect);		// <<<<<<<<<<<<<<<<<<<< actually draw
		
		if(!isEnabled && !isSelected){
			g.setColor(new Color(40,40,40,170));
			g2.fill(tileRect);
		}
		
		if (isCorrect() && !isMoving){
			if(!isEnabled && !isSelected)
				g.setColor(DrawingUtility.CORRECT.darker());
			else
				g.setColor(DrawingUtility.CORRECT);
			g.fillOval(drawX + 10, drawY + 10, size - 20, size - 20);
			g.setColor((new Color(re, gr, bl)));
			if(isMouseOn)
				g.setColor(g.getColor().brighter());
			if(isEnabled)
				g.setColor(g.getColor().brighter());
			if(!isEnabled && !isSelected)
				g.setColor(g.getColor().darker());
			g.fillOval(drawX + 15, drawY + 15, size - 30, size - 30);
		}
		
		g.setColor(Color.WHITE);
		Rectangle2D textBound = tileRect.getBounds2D();
		DrawingUtility.drawStringInBox("" + number, font, (int)textBound.getMinX(), (int)textBound.getMinY(),
				(int)textBound.getWidth(), (int)textBound.getHeight(), DrawingUtility.TEXT_CENTER, g);
	}

	public String toString() {
		return "[" + number + "]";
	}
}
