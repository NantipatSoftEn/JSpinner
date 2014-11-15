package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.Config;
import logic.Game;

public class GameWindow extends JFrame {
	
	GameTitle gameTitle;
	Game game;
	
	public GameWindow(){
		super("JSpinner");
		this.setLocationByPlatform(true);
//		setFrame();
		setSize(Config.screenWidth + 16, Config.screenHeight + 24);
		setResizable(false);
		
//		while(true){
//			gameTitle = new GameTitle(this);
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//			}
		
//		
		//	BUG: If Game is initiated after another JPanel, it lost focus and cannot listen to keys
		//	BUG: packing doesn't get the right size
			game = new Game(this, "/res/levels/3x3.txt");
//		}
	}
	
	public void setFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Config.screenWidth + 16, Config.screenHeight + 24);
		setVisible(true);
		setResizable(false);
	}
}
