/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package ui.winpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import ui.Clickable;
import ui.IRenderable;
import util.AudioUtility;
import util.Config;
import util.DrawingUtility;
import util.HighScoreUtility;
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
	private static PlayerStatus player;
	private static boolean soundPlayed = false;
	private static WinPanel panel;
	static{
		panel = new WinPanel();
	}
	
	private WinPanel(){
		isVisible = false;
		
		winElements.add(this);
//		winElements.add(new RestartButton());
//		winElements.add(new GoBackButton());
	}
	
	public static void setVisible(boolean isVisible) {
		if(!isVisible){
			currentFrame = frameCount;
			soundPlayed = false;
		} else { 
			if(!soundPlayed){
				AudioUtility.playSound(AudioUtility.solvedSound);
				soundPlayed = true;
			}
		}
		WinPanel.isVisible = isVisible;
	}
	
	public static void setPlayer(PlayerStatus player) {
		WinPanel.player = player;
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
			
			//reset sizes
			x = 0;
			y = Config.screenHeight / 2 - 100;
			width = Config.screenWidth;
			height = 200;			
			
			g.setColor(new Color(10,10,10,180 - 180 * currentFrame / frameCount));
			g.fillRect(x, y, width, height);
			Font font;
			g.setColor(Color.WHITE);
			String winning;
			int fontSize = 70;
			if(Config.screenWidth < 900){
				fontSize -= (900 - Config.screenWidth) / 15;
				System.out.println(fontSize);
			}
			if(player.getBestScore() != -1 || player.getBestScore() < player.getMoved()){
				font = new Font("Tahoma", Font.BOLD, fontSize - 15); 
				winning = "NEW BEST SCORE! " + player.getMoved() + " MOVES";
			} else {
				font = new Font("Tahoma", Font.BOLD, fontSize);
				winning = "SOLVED! " + player.getMoved() + " MOVES";
			}
			DrawingUtility.drawStringInBox(winning, font, x - width * currentFrame / frameCount, y, width, height / 2, DrawingUtility.TEXT_CENTER, g);
			if(currentFrame > 0){
				currentFrame--;
			}
		}
	}

}

