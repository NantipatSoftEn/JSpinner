package logic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import lib.DrawingUtility;
import lib.Utility;

public class AngryTile extends Tile{

	private static final int ANGRY_SHUFFLE = 5; 
	private static final int MAX_ANGRINESS = 2; 
	private int angriness;
	
	public AngryTile() {
		super();
		angriness = 0;
	}
	
	public AngryTile(int number, Board belongsTo, int correctX, int correctY) {
		super(number, belongsTo, correctX, correctY);
		angriness = 0;
	}
	
	@Override
	public void performEffect() {
		if(currentX < board.getBoardWidth() - 1 && board.getTileAt(currentX + 1, currentY) instanceof AngryTile ||
				currentX > 0 && board.getTileAt(currentX - 1, currentY) instanceof AngryTile ||
				currentY < board.getBoardHeight() - 1 && board.getTileAt(currentX, currentY + 1) instanceof AngryTile ||
				currentY > 0 && board.getTileAt(currentX, currentY - 1) instanceof AngryTile)
			angriness++;
		else {
			angriness = 0;
		}
		if(angriness >= MAX_ANGRINESS){
			board.animatedShuffle(ANGRY_SHUFFLE);
			angriness = 0;
			//TODO set cannot undo??
		}
	}
	
	@Override
	public void undoEffect() {
//		currentLock--;
//		if(currentLock < 0){
//			isLocked = !isLocked;
//			currentLock = 2;
//		}
		//TODO HOW DO I UNDO THIS ???
	}
	
	@Override
	public String toString() {
		return "[A" + getNumber() + "]";
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		Graphics2D g2 = (Graphics2D) g;
		if(!isMoving){
			BufferedImage frame;
			if(angriness >= 1)
				frame = DrawingUtility.getFrame(DrawingUtility.angryTileImg, 1, 2);
			else
				frame = DrawingUtility.getFrame(DrawingUtility.angryTileImg, 0, 2);
			g2.drawImage((Image)frame, drawX, drawY, board.getTileSize(), board.getTileSize(), null);
		}
		drawNumber("", tileRect, g2);
	}

	@Override
	public int getZ() {
		return z;
	}
}
