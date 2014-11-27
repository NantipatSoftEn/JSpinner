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
	private int moved = 0;
	private int bestScore;
	private Board board;
	private boolean lockMove = false;
	
	public PlayerStatus(Board playing){
		lockMove = false;
		this.board = playing;
		this.bestScore = HighScoreUtility.getBestScore(board.getDirectory());
	}
	
	public void move() {
		if(!lockMove){
			moved++;
		}
	}
	
	public void decreaseMove() {
		if(!lockMove)
			moved--;
	}
	
	public void resetMove(){
		moved = 0;
		lockMove = false;
	}
	
	public int getMoved(){
		return moved;
	}
	
	public int getBestScore() {
		return bestScore;
	}
	
	public void setLockMove(boolean lockMove) {
		this.lockMove = lockMove;
	}
	
	public void win(){
		if(bestScore!=moved){
			if((bestScore == -1 || moved < bestScore) && moved > 0){
				bestScore = moved;
				HighScoreUtility.updateBestScore(board.getDirectory(), moved);
			}
		}
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
		String best = bestScore >= 0 ? "" + bestScore : "-";
		DrawingUtility.drawStringInBox("BEST: " + best, subFont, 10, 0, Config.screenWidth, Config.topBarHeight, DrawingUtility.TEXT_LEFT, g);
		DrawingUtility.drawStringInBox("" + moved, mainFont, 0, 0, Config.screenWidth, Config.topBarHeight, DrawingUtility.TEXT_CENTER, g);
	}
}
