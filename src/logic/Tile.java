package logic;

import java.awt.*;

import lib.DrawingUtility;
import lib.IRenderable;

public abstract class Tile implements IRenderable {
	public static final int NOT_A_BLOCK = -1; // may check if the tile is null.

	private int number;
	private boolean isSelected;

	public Tile(){
		this.number = 0;
	}
	
	public Tile(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public abstract void performEffect();
	public abstract int getZ();
	
	public void draw(Graphics g){
		System.out.println("*");
		if(number == NOT_A_BLOCK)
			return;
		int size = 50; //ScreenManager.getTileSize(); //how to determine tile size?
//		int x = 50, y = 70;
		int x = ((number - 1) % 6) * 60 + 120, y = 70 + ((number - 1) / 6 * 60);
		Font font = new Font("Tahoma", Font.BOLD, 20);
		g.setColor(Color.BLACK);
		g.fillRect(x, y, size, size);
		g.setColor(Color.WHITE);
		DrawingUtility.drawStringInBox("" + number, font, x, y, size, size, DrawingUtility.TEXT_CENTER, g);
	}

	public String toString() {
		return "[" + number + "]";
	}
}
