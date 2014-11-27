/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package ui.gamebutton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

import control.Game;
import control.ScreenState;
import ui.Clickable;
import ui.IRenderable;
import util.Config;
import util.DrawingUtility;
import util.InputUtility;
import logic.Board;

public class LevelButton extends Clickable{
	private Color color;
	private String text, levelDirectory;
	private boolean isAdventure;
	
	public LevelButton(int x, int y, int size, Color color, String text, String levelDirectory, boolean isClassicGame){
		type = Clickable.RECTANGLE;
		this.x = x;
		this.y = y;
		width = size;
		height = size;
		this.color = color;
		this.text = text;
		this.levelDirectory = levelDirectory;
		this.isAdventure = isClassicGame;
	}

	@Override
	public int getZ() {
		return 10000;
	}

	@Override
	public void draw(Graphics g) {
		Color drawingColor = color;
		if(isMouseOn()){
			drawingColor = drawingColor.brighter();
			if(InputUtility.isMouseDown())
				drawingColor = drawingColor.brighter();
		}
		g.setColor(drawingColor);
		g.fillRect(x, y, width, height);
		g.setColor(Color.WHITE);
		DrawingUtility.drawStringInBox(text, 20, x, y, width, height, DrawingUtility.TEXT_CENTER, g);
	}

	@Override
	public void updatePosition() {	
	}
	
	@Override
	public void onClickAction() {
		ScreenState.nextLevel = levelDirectory;
		ScreenState.presentScreen = ScreenState.GAME;
		ScreenState.isAdventure = this.isAdventure;
	}
}
