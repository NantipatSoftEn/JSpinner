package logic;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class SimpleTile extends Tile{

	public SimpleTile() {
		super();
	}
	public SimpleTile(Tile in,Board br){
		super (in,br);
	}
	public SimpleTile(int number, Board belongsTo, int correctX, int correctY) {
		super(number, belongsTo, correctX, correctY);
	}
	
	@Override
	public void performEffect() {
		return;		
	}
	
	@Override
	public void undoEffect() {
		return;		
	}
	
	@Override
	public String toString() {
		return "[S" + getNumber() + "]";
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		Graphics2D g2 = (Graphics2D) g;
		drawNumber("", tileRect, g2);
	}

	@Override
	public int getZ() {
		return z;
	}
}
