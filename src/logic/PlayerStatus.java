/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package logic;

import java.awt.*;

import javax.swing.*;

import ui.IRenderable;
import util.*;

public class PlayerStatus implements IRenderable{
	private static int moved = 0;
	private Board board;
	private static boolean lockMove = false;
	
	public PlayerStatus(Board playing){
		this.board = playing;
	}
	
	public static void move() {
		if(!lockMove)
			moved++;
	}
	
	public static void decreaseMove() {
		if(!lockMove)
			moved--;
	}
	
	public static void resetMove(){
		moved = 0;
	}
	
	public static int getMoved(){
		return moved;
	}
	
	public static void setLockMove(boolean lockMove) {
		PlayerStatus.lockMove = lockMove;
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
		DrawingUtility.drawStringInBox("BEST " + board.getBestScore(), subFont, 10, 0, Config.screenWidth, Config.topBarHeight, DrawingUtility.TEXT_LEFT, g);
		DrawingUtility.drawStringInBox("" + moved, mainFont, 0, 0, Config.screenWidth, Config.topBarHeight, DrawingUtility.TEXT_CENTER, g);
	}
}
