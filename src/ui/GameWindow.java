package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.Config;
import lib.InputUtility;
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
		
		Container pane = this.getContentPane();
		addListener(pane);
				
//		while(true){
			gameTitle = new GameTitle(this);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			this.remove(gameTitle);
		
		//	BUG: packing doesn't get the right size
			game = new Game(this, "/res/levels/3x3.txt");
//		}
	}
	
	public void addPanel(JPanel jp){
		this.getContentPane().add(jp);
	}
	
	public void setFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Config.screenWidth + 16, Config.screenHeight + 24);
		setVisible(true);
		setResizable(false);
	}
	
	public void addListener(Container pane){
		pane.setFocusable(true);
		pane.requestFocus();
		/////////////////Mouse/////////////////
		pane.addMouseListener(new MouseListener(){
		
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				InputUtility.setPicking(true);
		//		InputUtility.setPickedPoint(e.getX(), e.getY());
			}
		
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				InputUtility.setPicking(false);
				InputUtility.setMouseReleased(true);
		//		InputUtility.setPickedPoint(InputUtility.NULL_POINT, InputUtility.NULL_POINT);
			}
		
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
		});
		
		pane.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				InputUtility.setPickedPoint(e.getX(), e.getY());
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				InputUtility.setPickedPoint(e.getX(), e.getY());
			}
		});
		
		///////////////////////key///////////////////////////
		
		pane.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), false);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				InputUtility.setKeyTriggered(e.getKeyCode(), true);
				InputUtility.setKeyPressed(e.getKeyCode(), true);
			}
		});
	}
}
