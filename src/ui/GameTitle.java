package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.GameWindow;
import lib.Config;
import lib.InputUtility;

public class GameTitle extends JPanel {
	
	private List<IRenderable> renderList = new ArrayList<IRenderable>();
	private boolean isOnThisScreen;
	
	public GameTitle(GameWindow window) {
		super();
		window.addPanel(this);
		window.setFrame();
		setPreferredSize(new Dimension(Config.screenWidth, Config.screenHeight));
		window.pack();
		isOnThisScreen = true;

		while(isOnThisScreen){
			repaint();
			
			try{
				Thread.sleep(20);
			} catch(InterruptedException e) {
			}
			
			//update
			if(InputUtility.getKeyTriggered(KeyEvent.VK_SPACE)){
				isOnThisScreen = false;
			}
			InputUtility.postUpdate();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//DrawBackground
		GameWindow.gameBackground.draw(g);
		
		//DrawLogo
		Font font = new Font("Tahoma", Font.BOLD, 70);
		DrawingUtility.drawStringInBox("JSpinner", font, 0, 0, Config.screenWidth, Config.screenHeight * 2 / 3, DrawingUtility.TEXT_CENTER, g2);

		//should be a clickable button
		font = new Font("Tahoma", Font.BOLD, 30);
		DrawingUtility.drawStringInBox("Press spacebar to start", font, 0, Config.screenHeight * 2 / 3, Config.screenWidth, Config.screenHeight * 5 / 6, DrawingUtility.TEXT_TOP, g2);
	}
}
