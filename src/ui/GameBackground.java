package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import lib.Config;

public class GameBackground implements IRenderable, Runnable {
	
	private volatile double theta = 0.002;
	private BufferedImage gb = DrawingUtility.gameBG;
	private int x = (Config.screenWidth - gb.getWidth()) / 2;;
	private int y = (Config.screenWidth - gb.getHeight()) / 2;
	private AffineTransform at = new AffineTransform();
	private AffineTransformOp ato;
	
	public GameBackground() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		//STILL LAG.... DIDN'T USE
		while(true){
			x = (Config.screenWidth - gb.getWidth()) / 2;
			y = (Config.screenWidth - gb.getHeight()) / 2;
			at.rotate(theta, gb.getWidth() / 2, gb.getHeight() / 2);
			ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC); 
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		}
	}
	
	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
//		g2.drawImage(gb, ato, x, y);
		g2.drawImage(DrawingUtility.gameBG, null, x, y);
	}

}
