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

public class LevelSelect extends JPanel {
	private List<IRenderable> renderList = new ArrayList<IRenderable>();
	public LevelSelect(GameWindow window) {
		super();
		window.addPanel(this);
		window.setFrame();
		setPreferredSize(new Dimension(Config.screenWidth, Config.screenHeight));
		window.pack();

		while(true){
			repaint();
			try{
				Thread.sleep(20);
			} catch(InterruptedException e) {
			}
			
			//update
			if(InputUtility.getKeyTriggered(KeyEvent.VK_SPACE)){
				break;
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
		DrawingUtility.drawStringInBox("Level Select", font, 0, 0, Config.screenWidth, Config.screenHeight * 2 / 3, DrawingUtility.TEXT_CENTER, g2);
	}
}
