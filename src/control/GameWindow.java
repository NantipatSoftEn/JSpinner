package control;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ui.GameBackground;
import ui.GameTitle;
import ui.LevelSelectScreen;
import lib.Config;
import lib.InputUtility;
import logic.LevelFormatException;

public class GameWindow extends JFrame {
	
	private GameTitle gameTitle;
	private Game game;
	private LevelSelectScreen levelSelect;
	public static GameBackground gameBackground;
	
	public GameWindow(){
		super("JSpinner");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
		}
		this.setLocationByPlatform(true);
		Container pane = this.getContentPane();
		addListener(pane);
		setFrame();
		setSize(Config.screenWidth + 16, Config.screenHeight + 24);
		setResizable(false);		
				
		gameBackground = new GameBackground();
		(new Thread(gameBackground)).start();
		
		ScreenState.presentScreen = ScreenState.TITLE;
		
		while(true){
			if(ScreenState.presentScreen == ScreenState.TITLE){
				gameTitle = new GameTitle(this);
				this.remove(gameTitle);
			}
			
			if(ScreenState.presentScreen == ScreenState.LEVEL_SELECT){
				levelSelect = new LevelSelectScreen(this);
				this.remove(levelSelect);
			}
			
			//	BUG: packing doesn't get the right size
			else if(ScreenState.presentScreen == ScreenState.GAME){
				try {
					game = new Game(this, "/res/levels/testFreeze.txt");
					this.remove((JPanel) (game.getGameScreen()));
				} catch (LevelFormatException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e){
					JOptionPane.showMessageDialog(null, "Level file not found.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			else{
				JOptionPane.showMessageDialog(null, "Error! some error in screen loop");
			}
		}
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
