/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package ui.winpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import control.ScreenState;
import logic.Board;
import ui.Clickable;
import util.DrawingUtility;

public class RestartButton extends Clickable{
	
	public RestartButton(){
		type = Clickable.RECTANGLE;
		updatePosition();
	}

	@Override
	public int getZ() {
		return Integer.MAX_VALUE - 99;
	}

	@Override
	public void draw(Graphics g) {
		if(WinPanel.isVisible()){
			g.setColor(Color.LIGHT_GRAY);
			if(isMouseOn()){
				g.setColor(Color.LIGHT_GRAY.brighter());
			}
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			Font font = new Font("Tahoma", Font.PLAIN, 20);
			DrawingUtility.drawStringInBox("PLAY AGAIN", font, x, y, width, height-5, DrawingUtility.TEXT_CENTER, g);
		}
	}
	
	@Override
	public void updatePosition() {
		width = 200;
		height = 40;
		if(ScreenState.isAdventure){
			x = WinPanel.x + WinPanel.width / 2 - width - 10;
			y = WinPanel.y + WinPanel.height / 2 + 30;
		}else{
			x = WinPanel.x + WinPanel.width / 2 + 10;
			y = WinPanel.y + WinPanel.height / 2 + 30;
		}
		isVisible = WinPanel.isVisible();
	}
	
	@Override
	public void onClickAction() {
		if(WinPanel.isVisible()){
			Clickable.board.shuffle(Board.DEFAULT_SHUFFLE);
			board.setCheated(false);
			Clickable.board.newGame();
			WinPanel.setVisible(false);
		}
	}
}
