package logic;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import lib.InputUtility;
import ui.GameScreen;

public class Game extends JFrame{
	private Board board;
	private GameScreen gameScreen;
	private GameLogic gameLogic;
	private boolean playing;
	
	public Game(){
		super("JFlipFlop");
		
<<<<<<< HEAD
		board = new Board(5, 5);
=======
//		board = new Board(6, 4);
		board = new Board("levels/test.txt");
>>>>>>> acf01ec8f9ab9259b75c125db82ee329ec54c161
		gameScreen = new GameScreen(board);
		gameLogic = new GameLogic(board);
		
		//board.shuffle(Board.DEFAULT_SHUFFLE);
		
		setFrame();
		
//		while(!gameScreen.getBoard().isWin()){
		while(true){
			try {
				Thread.sleep(20);
//				Thread.sleep(200);
			} catch(InterruptedException e) {
			}
			gameScreen.repaint();
			gameLogic.update();
			InputUtility.postUpdate();
		}
//		JOptionPane.showMessageDialog(null, "WIN", "WIN", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void setFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(gameScreen);
		pack();
		setVisible(true);
		setResizable(false);
	}
}
