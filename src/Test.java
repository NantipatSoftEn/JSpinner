import java.awt.*;

import javax.swing.*;

import ui.GameScreen;
import logic.GameLogic;
import lib.*;

public class Test{

	public static void main(String[] args){
		JFrame f = new JFrame("Test");
		GameScreen gameScreen = new GameScreen();
		GameLogic gameLogic = new GameLogic(gameScreen.getBoard());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(gameScreen);
		f.pack();
		f.setVisible(true);
		f.setResizable(false);
		while(true){
			try {
				Thread.sleep(20);
				//Thread.sleep(200);
			} catch(InterruptedException e) {
			}
			gameScreen.repaint();
			gameLogic.update();
		}
	}
	
}
