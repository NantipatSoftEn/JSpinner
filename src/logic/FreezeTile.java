package logic;

import java.awt.Graphics;
import java.awt.Graphics2D;

import lib.Utility;

public class FreezeTile extends Tile{

	private boolean isLocked;
	private int currentLock;
	private static int lockCount = 3;
	
	public FreezeTile() {
		super();
		isLocked = Utility.random(0, 2) > 0 ? true : false;
		currentLock = Utility.random(0, 4);
	}
	
	public FreezeTile(int number, Board belongsTo, int correctX, int correctY) {
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
		return "[F" + getNumber() + "]";
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		Graphics2D g2 = (Graphics2D) g;
		drawNumber("F " + isLocked, tileRect, g2);
	}

	@Override
	public int getZ() {
		return z;
	}
}