package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import lib.Config;

public class GameTitle extends JPanel {
	private List<IRenderable> renderList = new ArrayList<IRenderable>();
	public GameTitle(GameWindow window) {
		super();
		window.addPanel(this);
		window.setFrame();
		setPreferredSize(new Dimension(Config.screenWidth, Config.screenHeight));
		window.pack();
//		this.setFocusable(true);
//		this.requestFocus();
//		this.addKeyListener(new KeyListener() {
//			
//			@Override
//			public void keyTyped(KeyEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void keyReleased(KeyEvent arg0) {
//				// TODO Auto-generated method stub
//				JOptionPane.showMessageDialog(null, "!");
//			}
//			
//			@Override
//			public void keyPressed(KeyEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//DrawBackground
		int x = (Config.screenWidth - DrawingUtility.gameBG.getWidth()) / 2;
		int y = (Config.screenWidth - DrawingUtility.gameBG.getHeight()) / 2;
		g2.drawImage(DrawingUtility.gameBG, null, x, y);
		
		//DrawLogo
		Font font = new Font("Tahoma", Font.BOLD, 70);
		DrawingUtility.drawStringInBox("JSpinner", font, 0, 0, Config.screenWidth, Config.screenHeight, DrawingUtility.TEXT_CENTER, g2);
	}
}
