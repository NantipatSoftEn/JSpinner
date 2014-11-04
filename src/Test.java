import java.awt.*;

import javax.swing.*;

import ui.GameScreen;
import lib.*;

public class Test{

	public static void main(String[] args){
		JFrame f = new JFrame("Test");
		GameScreen t = new GameScreen();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(t);
		f.pack();
		f.setVisible(true);
		t.repaint();
	}
	
}
