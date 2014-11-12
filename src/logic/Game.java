package logic;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import lib.InputUtility;
import ui.GameScreen;
import ui.button.Clickable;

public class Game extends JFrame{
	private Board board;
	GameScreen gameScreen;
	private GameLogic gameLogic;
	private boolean playing;
	
	public Game(){
		super("JFlipFlop");
		
<<<<<<< HEAD
<<<<<<< HEAD
		board = new Board(5, 5);
=======
//		board = new Board(6, 4);
=======
		//board = new Board(1, 1);
>>>>>>> 13db05f3565f092c607e1fd3f7163665b5bee046
		board = new Board("levels/test.txt");
>>>>>>> acf01ec8f9ab9259b75c125db82ee329ec54c161
		gameScreen = new GameScreen(board);
		gameLogic = new GameLogic(board);
		Clickable.board = board;
		
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
