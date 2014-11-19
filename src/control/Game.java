package control;

import java.io.IOException;

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
	private static boolean playing;
	private static String levelDirectory;
	
	public Game(GameWindow window, String levelDirectory) throws LevelFormatException, IOException{
		
//		board = new Board(3, 2);
//		try{
			board = new Board(levelDirectory);
//		} catch (LevelFormatException e) {
//			JOptionPane.showMessageDialog(null, e.getMessage());
//		}
		gameScreen = new GameScreen(board);
		gameLogic = new GameLogic(board);
		Clickable.board = board;
		Game.levelDirectory = levelDirectory;
		Game.playing = true;
		
		//GAME START
		board.shuffle(Board.DEFAULT_SHUFFLE);
		
		window.addPanel(gameScreen);
		window.setFrame();
		window.pack();
		
		while(playing){
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
	
	public static void setPlaying(boolean playing) {
		Game.playing = playing;
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}
}
