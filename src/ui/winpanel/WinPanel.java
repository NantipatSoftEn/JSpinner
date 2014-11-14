package ui.winpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import ui.Clickable;
import ui.DrawingUtility;
import ui.IRenderable;
import lib.Config;
import logic.Board;
import logic.PlayerStatus;

public class WinPanel implements IRenderable {

	private static boolean isVisible;
	public static int x = 0;
	public static int y = 200;
	public static int width = Config.screenWidth;
	public static int height = Config.screenHeight - 2 * y;
	public static List<IRenderable> winElements = new ArrayList<IRenderable>();
	public static final int frameCount = 8;
	public static int currentFrame = frameCount;
	
	public WinPanel(){
		isVisible = false;
		
		winElements.add(this);
		winElements.add(new RestartButton());
	}
	
	public static void setVisible(boolean isVisible) {
		if(!isVisible)
			currentFrame = frameCount;
		WinPanel.isVisible = isVisible;
	}
	
	public static boolean isVisible() {
		return isVisible;
	}
	
	@Override
	public int getZ() {
		return Integer.MAX_VALUE - 100;
	}

	@Override
	public void draw(Graphics g) {
		if(isVisible){
			g.setColor(new Color(10,10,10,180 - 180 * currentFrame / frameCount));
			g.fillRect(x, y, width, height);
			Font font = new Font("Tahoma", Font.BOLD, 70); 
			g.setColor(Color.WHITE);
			String winning = "SOLVED!: " + PlayerStatus.getMoved() + " MOVES";
			DrawingUtility.drawStringInBox(winning, font, x - width * currentFrame / frameCount, y, width, height / 2, DrawingUtility.TEXT_CENTER, g);
			if(currentFrame > 0){
				currentFrame--;
			}
		}
	}

}

