package logic;

import java.awt.*;
import javax.swing.*;
import lib.*;

public class PlayerStatus implements IRenderable{
	private int moved = 0;

	public PlayerStatus(){
		
	}
	
	public void move() {
		moved++;
	}
	
	public int getMoved(){
		return moved;
	}
	
	@Override
	public int getZ() {
		return Integer.MAX_VALUE;
	}
	
	@Override
	public void draw(Graphics g) {
		Font subFont = new Font("Tahoma", Font.BOLD, 25);
		Font mainFont = new Font("Tahoma", Font.BOLD, 40);
		g.setColor(Color.BLACK);
		DrawingUtility.drawStringInBox("BEST 10", subFont, 0, 0, Config.screenWidth, Config.topBarHeight, DrawingUtility.TEXT_LEFT, g);
		DrawingUtility.drawStringInBox("" + moved, mainFont, 0, 0, Config.screenWidth, Config.topBarHeight, DrawingUtility.TEXT_CENTER, g);
	}
}
