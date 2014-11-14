package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.Config;
import logic.Game;

public class GameWindow extends JFrame {
	public GameWindow(){
		super("JFlipFlop");
		
		//		test screen changing		//
		
//		JPanel p = new JPanel();
//		p.setPreferredSize(new Dimension(300,300));
//		p.setBackground(Color.BLACK);
//		this.getContentPane().add(p);
//		setFrame();
//		
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException e) {
//		}
		
		new Game(this, "levels/3x3.txt");
	}
	
	public void setFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		pack();
	
		setSize(Config.screenWidth,Config.screenHeight);
		System.out.println(this.getWidth());
		setVisible(true);
		setResizable(false);
	}
}
