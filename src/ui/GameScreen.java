package ui;

import java.awt.*;

import javax.swing.*;

import lib.*;

public class GameScreen extends JPanel{
	public GameScreen(){
		super();
		setPreferredSize(new Dimension(Config.screenWidth, Config.screenHeight));
		setBackground(Color.WHITE);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font subFont = new Font("Tahoma", Font.BOLD, 25);
		Font mainFont = new Font("Tahoma", Font.BOLD, 40);
		g.setColor(Color.BLACK);
		DrawingUtility.drawStringInBox("BEST 10", subFont, 0, 0, getWidth(), Config.topBarHeight, DrawingUtility.TEXT_LEFT, g);
		DrawingUtility.drawStringInBox("10", mainFont, 0, 0, getWidth(), Config.topBarHeight, DrawingUtility.TEXT_CENTER, g);
	}
}
