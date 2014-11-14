package logic;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import lib.InputUtility;
import ui.Clickable;
import ui.GameScreen;
import ui.GameWindow;

public class Game{
	private Board board;
	GameScreen gameScreen;
	private GameLogic gameLogic;
	private boolean playing;
	private static String levelDirectory;
	
	public Game(GameWindow window, String levelDirectory){
		
//		board = new Board(3, 2);
		board = new Board(levelDirectory);
		gameScreen = new GameScreen(board);
		gameLogic = new GameLogic(board);
		Clickable.board = board;
		Game.levelDirectory = levelDirectory;
		
		//GAME START
		board.shuffle(Board.DEFAULT_SHUFFLE);
		
		window.add(gameScreen);
		window.setFrame();
		
		while(true){
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
}
