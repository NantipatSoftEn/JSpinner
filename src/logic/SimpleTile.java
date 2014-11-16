package logic;

import java.awt.Graphics;

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
	public String toString() {
		return "[S" + getNumber() + "]";
	}

//	@Override
//	public void draw(Graphics g) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public int getZ() {
		return z;
	}
}
