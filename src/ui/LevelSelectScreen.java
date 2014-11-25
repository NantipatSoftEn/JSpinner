package ui;

import java.awt.Color;
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
import ui.gamebutton.CustomLevelButton;
import ui.gamebutton.LevelButton;
import control.GameWindow;
import control.ScreenState;
import lib.Config;
import lib.DrawingUtility;
import lib.InputUtility;
import logic.IUpdatable;

public class LevelSelectScreen extends JPanel {
	
	private List<IRenderable> renderList = new ArrayList<IRenderable>();
	private List<IUpdatable> updateList = new ArrayList<IUpdatable>();
	
	private int bpr = 4; // block per row
	private int size = 100;
	private int g = 10; // gutter
	private int startX = Config.screenWidth / 2 - (bpr * size + (bpr - 1) * g) / 2;
	private int startY = Config.screenHeight / 2 -200;
	private int catGutter = 140;
	
	public LevelSelectScreen(GameWindow window) {
		super();
		
		ScreenState.presentScreen = ScreenState.LEVEL_SELECT;
		
		window.addPanel(this);
		window.setFrame();
		setPreferredSize(new Dimension(Config.screenWidth, Config.screenHeight));
		window.pack();
		
		addBoth(new BackButton());
		for(int i = 3; i <= 6; i++)
			addBoth(new LevelButton(startX + (size + g) * (i - 3), startY, size, DrawingUtility.generateRainbow(i - 3, 3), i + "x" + i, "/res/levels/" + i + "x" + i + ".txt", false));
		for(int i = 1; i <= 12; i++){
			addBoth(new LevelButton(startX + (size + g) * ((i - 1) % bpr), startY + catGutter + (size + g) * ((i - 1) / bpr), size, DrawingUtility.generateRainbow(i - 1, 12), "" + i, "/res/levels/lvl" + i + ".txt", true));
		}
		
		addBoth(new CustomLevelButton());
		
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
				ScreenState.nextLevel = "/res/levels/testAngry.txt";
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
		
		DrawingUtility.drawStringInBox("Select Level", 40, 0, startY - 100, Config.screenWidth, 60, DrawingUtility.TEXT_CENTER, g2);
		
		Font font = new Font("Tahoma", Font.PLAIN, 22);
		g2.setColor(Color.GRAY);
		DrawingUtility.drawStringInBox("Classic Game", font, 0, startY, Config.screenWidth, -10, DrawingUtility.TEXT_BOTTOM, g2);
		DrawingUtility.drawStringInBox("Adventure Mode", font, 0, startY, Config.screenWidth, catGutter - 10, DrawingUtility.TEXT_BOTTOM, g2);
	
		for(int i = 0; i < renderList.size(); i++){
			renderList.get(i).draw(g);
		}
	}
	
	public void update(){
		for(int i = 0; i < updateList.size(); i++){
			updateList.get(i).update();
		}
	}
	
	private void addBoth(IRenderable a){
		if(a instanceof IUpdatable){
			renderList.add(a);
			updateList.add((IUpdatable)a);
		}
	}
}
