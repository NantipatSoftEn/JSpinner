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
import ui.gamebutton.*;
import control.GameWindow;
import control.ScreenState;
import lib.Config;
import lib.InputUtility;
import logic.IUpdatable;

public class GameTitle extends JPanel {
	
	private List<IRenderable> renderList = new ArrayList<IRenderable>();
	private List<IUpdatable> updateList = new ArrayList<IUpdatable>();
	
	public GameTitle(GameWindow window) {
		super();
		
		ScreenState.presentScreen = ScreenState.TITLE;
		
		window.addPanel(this);
		window.setFrame();
		setPreferredSize(new Dimension(Config.screenWidth, Config.screenHeight));
		window.pack();

		renderList.add(new PlayButton());
		updateList.add(new PlayButton());

		renderList.add(new SettingsButton());
		updateList.add(new SettingsButton());
		
		while(ScreenState.presentScreen == ScreenState.TITLE){
			
			try{
				Thread.sleep(20);
			} catch(InterruptedException e) {
			}
			
			repaint();
			update();
			
			//update
			if(InputUtility.getKeyTriggered(KeyEvent.VK_SPACE)){
				ScreenState.presentScreen = ScreenState.LEVEL_SELECT;
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
