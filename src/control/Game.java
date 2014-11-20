package control;

import java.io.IOException;

import javax.print.attribute.standard.Finishings;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import lib.InputUtility;
import logic.Board;
import logic.GameLogic;
import logic.LevelFormatException;
import ui.Clickable;
import ui.GameScreen;

public class Game{
	private Board board;
	private GameScreen gameScreen;
	private GameLogic gameLogic;
	private static String levelDirectory;
	public static volatile boolean finishUpdate = false;
	
	public Game(GameWindow window, String levelDirectory) throws LevelFormatException, IOException{
		
		ScreenState.presentScreen = ScreenState.GAME;
		
//		board = new Board(3, 2);
		board = new Board(levelDirectory);
		gameScreen = new GameScreen(board);
		gameLogic = new GameLogic(board);
		Clickable.board = board;
		Game.levelDirectory = levelDirectory;
		
		//GAME START
		board.shuffle(Board.DEFAULT_SHUFFLE);
		
		window.addPanel(gameScreen);
		window.setFrame();
		window.pack();
		
		while(ScreenState.presentScreen == ScreenState.GAME){
			try {
				Thread.sleep(20);
			} catch(InterruptedException e) {
			}
			gameScreen.repaint();
			gameLogic.update();
			InputUtility.postUpdate();
		}	
	}
	
	public String getLevelDirectory() {
		return levelDirectory;
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}
}
