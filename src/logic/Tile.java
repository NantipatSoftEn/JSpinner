/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package logic;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.util.Date;

import javax.rmi.CORBA.Util;
import javax.swing.text.Utilities;

import ui.IRenderable;
import util.Config;
import util.DrawingUtility;
import util.Utility;

public abstract class Tile implements IRenderable {
	public static final int NOT_A_TILE = -1; // may check if the tile is null.

	protected int number;
	protected int correctX, correctY;
	protected int currentX, currentY;
	protected int drawX, drawY;
	protected Shape tileRect;
	protected boolean isSelected;
	protected Board board;
	protected boolean isMouseOn;
	protected boolean isEnabled;
	protected boolean isMoving;
	protected Rectangle2D.Double rect;
	protected int z;
	
	public Tile() {
		this.number = 0;
	}
	public Tile(Tile in,Board br){
		this.number = in.number;
		this.board = br;
		setCorrectLocation(in.getCorrectLocation().x, in.getCorrectLocation().y);
		setCurrentLocation(in.getCurrentLocation().x, in.getCurrentLocation().y);			
	}

	public Tile(int number, Board belongsTo, int correctX, int correctY) {
		this.number = number;
		this.board = belongsTo;
		rect = new Rectangle2D.Double(drawX, drawY, board.getTileSize(), board.getTileSize());
		tileRect = rect.getBounds();
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
	public abstract void undoEffect();

	public abstract int getZ();

	public void draw(Graphics g) {
		
		if (number == NOT_A_TILE)
			return;
		
		Graphics2D g2 = (Graphics2D) g;		
		
		int size = board.getTileSize();
		
		drawX = board.getX() + (currentX) * (board.getTileSize() + Config.tileGutter);
		drawY = board.getY() + (currentY) * (board.getTileSize() + Config.tileGutter);
		
		rect = new Rectangle2D.Double(drawX, drawY, board.getTileSize(), board.getTileSize());
		AffineTransform at = new AffineTransform(); 
		
		Double theta = Math.PI / 2 * board.getCurrentFrame() / Config.animationFrameCount; 
		if(board.getLatestMove() != null && board.getLatestMove().dir == Board.CCW)
			theta *= -1;
		if(isMoving){
			at.rotate(theta, board.getMoveCenterX(), board.getMoveCenterY());
		}
		tileRect = at.createTransformedShape(rect);
				
		if(isSelected){
			g.setColor(DrawingUtility.SELECTED);
			g.fillRect(drawX-5, drawY-5, size+10, size+10);
		}
		
//		int gr = Math.abs(128 - number * 255 / (board.getBoardWidth() * board.getBoardHeight()));
//		int re = 255 - number * 255 / (board.getBoardWidth() * board.getBoardHeight());
//		int bl = number * 255 / (board.getBoardWidth() * board.getBoardHeight());
		g.setColor(DrawingUtility.generateRainbow(number, board.getBoardWidth() * board.getBoardHeight()));
		
		if(isMouseOn)
			g.setColor(g.getColor().brighter());
		if(isEnabled)
			g.setColor(g.getColor().brighter());
		
		g2.fill(tileRect);		// <<<<<<<<<<<<<<<<<<<< actually draw
		
		if (isCorrect() && !isMoving){
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			if(board.getBoardWidth() < 5)
				g2.drawImage((Image)DrawingUtility.correctImg, drawX + 10, drawY + 10, size - 20, size - 20, null);
			else
				g2.drawImage((Image)DrawingUtility.correctImg, drawX + 6, drawY + 6, size - 12, size - 12, null);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		
		if(!isEnabled && !isSelected){
			g.setColor(new Color(40,40,40,170));
			g2.fill(tileRect);
		}
		
//		drawNumber("", tileRect, g2);
	}
	
	protected void drawNumber(String prefix, Shape tileRect, Graphics2D g2){
		Font font = new Font("Tahoma", Font.BOLD, 20);
		g2.setColor(Color.WHITE);
		Rectangle2D textBound = tileRect.getBounds2D();
		DrawingUtility.drawStringInBox(prefix + "" + number, 20, (int)textBound.getMinX(), (int)textBound.getMinY(),
				(int)textBound.getWidth(), (int)textBound.getHeight(), DrawingUtility.TEXT_CENTER, g2);
	}

	public String toString() {
		return "[" + number + "]";
	}
}
