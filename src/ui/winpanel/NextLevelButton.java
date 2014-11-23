package ui.winpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import lib.DrawingUtility;
import logic.Board;
import ui.Clickable;

public class NextLevelButton extends Clickable{
	
	public NextLevelButton(){
		type = Clickable.RECTANGLE;
		width = 200;
		height = 40;
		x = WinPanel.x + WinPanel.width / 2 + 10;
		y = WinPanel.y + WinPanel.height / 2 + 30;
	}

	@Override
	public int getZ() {
		return Integer.MAX_VALUE - 99;
	}

	@Override
	public void draw(Graphics g) {
		if(WinPanel.isVisible()){
			g.setColor(DrawingUtility.CORRECT);
			if(isMouseOn()){
				g.setColor(DrawingUtility.CORRECT.brighter());
			}
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			Font font = new Font("Tahoma", Font.PLAIN, 20);
			DrawingUtility.drawStringInBox("NEXT LEVEL >>", font, x, y, width, height-5, DrawingUtility.TEXT_CENTER, g);
		}
	}
	
	@Override
	public void onClickAction() {
		if(WinPanel.isVisible()){
			JOptionPane.showMessageDialog(null, "Next Level");
			Clickable.board.shuffle(Board.DEFAULT_SHUFFLE);
			Clickable.board.newGame();
		}
	}
}
