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

import ui.gamebutton.BackButton;
import control.GameWindow;
import control.ScreenState;
import lib.Config;
import lib.InputUtility;
import logic.IUpdatable;

public class LevelSelectScreen extends JPanel {
	
	private List<IRenderable> renderList = new ArrayList<IRenderable>();
	private List<IUpdatable> updateList = new ArrayList<IUpdatable>();
	
	public LevelSelectScreen(GameWindow window) {
		super();
		
		ScreenState.presentScreen = ScreenState.LEVEL_SELECT;
		
		window.addPanel(this);
		window.setFrame();
		setPreferredSize(new Dimension(Config.screenWidth, Config.screenHeight));
		window.pack();
		
		renderList.add(new BackButton());
		updateList.add(new BackButton());
		
		while(ScreenState.presentScreen == ScreenState.LEVEL_SELECT){
			repaint();
			try{
				Thread.sleep(20);
			} catch(InterruptedException e) {
			}
			
			repaint();
			update();
			
			//update
			if(InputUtility.getKeyTriggered(KeyEvent.VK_SPACE)){
				ScreenState.presentScreen = ScreenState.GAME;
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
	
		for(int i = 0; i < renderList.size(); i++){
			renderList.get(i).draw(g);
		}
	}
	
	public void update(){
		for(int i = 0; i < updateList.size(); i++){
			updateList.get(i).update();
		}
	}
}
