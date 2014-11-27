/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package logic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import util.DrawingUtility;
import util.Utility;

public class SleepyTile extends Tile{

	private boolean isLocked;
	private int currentLock;
	private static int lockCount = 3;
	
	public SleepyTile() {
		super();
		isLocked = Utility.random(0, 2) > 0 ? true : false;
		currentLock = Utility.random(0, 4);
	}
	
	public SleepyTile(int number, Board belongsTo, int correctX, int correctY) {
		super(number, belongsTo, correctX, correctY);
		isLocked = Utility.random(0, 2) > 0 ? true : false;
		currentLock = Utility.random(0, 4);
	}
	
	public boolean isLocked() {
		return isLocked;
	}
	
	@Override
	public void performEffect() {
		currentLock++;
		if(currentLock >= lockCount){
			isLocked = !isLocked;
			currentLock = 0;
		}
	}
	
	@Override
	public void undoEffect() {
		currentLock--;
		if(currentLock < 0){
			isLocked = !isLocked;
			currentLock = 2;
		}
	}
	
	@Override
	public String toString() {
		return "[Z" + getNumber() + "]";
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		Graphics2D g2 = (Graphics2D) g;
		if(!isMoving){
			BufferedImage frame;
			if(isLocked)
				frame = DrawingUtility.getFrame(DrawingUtility.sleepyTileImg, 3 + currentLock, 6);
			else
				frame = DrawingUtility.getFrame(DrawingUtility.sleepyTileImg, currentLock, 6);
			g2.drawImage((Image)frame, drawX, drawY, board.getTileSize(), board.getTileSize(), null);
		}
		drawNumber("", tileRect, g2);
	}

	@Override
	public int getZ() {
		return z;
	}
}
