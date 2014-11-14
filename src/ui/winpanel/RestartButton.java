package ui.winpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import logic.Board;
import ui.Clickable;
import ui.DrawingUtility;

public class RestartButton extends Clickable{
	
	public RestartButton(){
		type = Clickable.RECTANGLE;
		x = WinPanel.x + WinPanel.width / 2 + 10;
		y = WinPanel.y + WinPanel.height / 2 + 30;
		width = 200;
		height = 40;
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
			DrawingUtility.drawStringInBox("RESTART", font, x, y, width, height-5, DrawingUtility.TEXT_CENTER, g);
		}
	}
	
	@Override
	public void onClickAction() {
		if(WinPanel.isVisible()){
			Clickable.board.shuffle(Board.DEFAULT_SHUFFLE);
			Clickable.board.newGame();
		}
	}
}