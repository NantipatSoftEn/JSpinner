/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package ui.winpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import control.ScreenState;
import logic.Board;
import ui.Clickable;
import util.DrawingUtility;

public class NextLevelButton extends Clickable{
	
	public NextLevelButton(){
		type = Clickable.RECTANGLE;
		updatePosition();
	}

	@Override
	public int getZ() {
		return Integer.MAX_VALUE - 99;
	}

	@Override
	public void draw(Graphics g) {
		if(WinPanel.isVisible() && ScreenState.isAdventure){
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
	public void updatePosition() {
		width = 200;
		height = 40;
		x = WinPanel.x + WinPanel.width / 2 + 10;
		y = WinPanel.y + WinPanel.height / 2 + 30;
	}
	
	@Override
	public void onClickAction() {
		if(WinPanel.isVisible() && ScreenState.isAdventure){
			ScreenState.presentScreen = ScreenState.NEXT_LEVEL;
			WinPanel.setVisible(false);
		}
	}
}
