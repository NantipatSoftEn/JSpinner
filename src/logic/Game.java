package logic;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import lib.InputUtility;
import ui.GameScreen;

public class Game extends JFrame{
	private Board board;
	GameScreen gameScreen;
	GameLogic gameLogic;
	
	public Game(){
		super("JFlipFlop");
		
		board = new Board(9, 9);
		gameScreen = new GameScreen(board);
		gameLogic = new GameLogic(board);
		
		board.shuffle(Board.DEFAULT_SHUFFLE);
		
		setFrame();
		
		while(!gameScreen.getBoard().isWin()){
//		while(true){
			try {
				Thread.sleep(20);
//				Thread.sleep(200);
			} catch(InterruptedException e) {
			}
			gameScreen.repaint();
			gameLogic.update();
			InputUtility.postUpdate();
		}
		JOptionPane.showMessageDialog(null, "WIN", "WIN", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void setFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(gameScreen);
		pack();
		setVisible(true);
		setResizable(false);
	}
}