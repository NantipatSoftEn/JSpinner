package logic;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import lib.InputUtility;
import ui.GameScreen;
import ui.GameWindow;
import ui.button.Clickable;

public class Game{
	private Board board;
	GameScreen gameScreen;
	private GameLogic gameLogic;
	private boolean playing;
	
	public Game(GameWindow window, String levelDirectory){
		
		//board = new Board(1, 1);
		board = new Board(levelDirectory);
		gameScreen = new GameScreen(board);
		gameLogic = new GameLogic(board);
		Clickable.board = board;
		
		//board.shuffle(Board.DEFAULT_SHUFFLE);
		window.getContentPane().add(gameScreen);
		window.setFrame();
//		setFrame();
		
//		while(!gameScreen.getBoard().isWin()){
		while(true){
			try {
				Thread.sleep(20);
			} catch(InterruptedException e) {
			}
			gameScreen.repaint();
			gameLogic.update();
			InputUtility.postUpdate();
		}
//		JOptionPane.showMessageDialog(null, "WIN", "WIN", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
}
