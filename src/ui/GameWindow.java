package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.Config;
import logic.Game;

public class GameWindow extends JFrame {
	public GameWindow(){
		super("JSpinner");

//		while(true){
//			new GameTitle(this);
//			
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//			}
//		
		//	BUG: If Game is initiated after another JPanel, it lost focus and cannot listen to keys
		//	BUG: If Game is initiated after another JPanel, it lost focus and cannot listen to keys
			new Game(this, "levels/3x3.txt");
//		}
	}
	
	public void setFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
	}
}
