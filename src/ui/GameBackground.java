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
	private int x = (Config.screenWidth - gb.getWidth()) / 2;
	private int y = (Config.screenHeight - gb.getHeight());
	private AffineTransform at = new AffineTransform();
	private AffineTransformOp ato;
	private int cloudWidth = DrawingUtility.cloudBG.getWidth();
	private int anim1X = -cloudWidth;
	private int anim2X = 0;
	private int anim3X = cloudWidth;
	
	public GameBackground() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		//STILL LAG.... DIDN'T USE
		while(true){
//			at.rotate(theta, gb.getWidth() / 2, gb.getHeight() / 2);
//			ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
			anim1X = ((anim1X + 1) % (cloudWidth * 3));
			anim2X = ((anim2X + 1) % (cloudWidth * 3));
			anim3X = ((anim3X + 1) % (cloudWidth * 3));
//			System.out.println(anim1X + " " + anim2X + " " + anim3X);
			try {
				Thread.sleep(40);
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
		g2.drawImage(DrawingUtility.cloudBG, null, x + anim1X - cloudWidth, y);
		g2.drawImage(DrawingUtility.cloudBG, null, x + anim2X - cloudWidth, y);
		g2.drawImage(DrawingUtility.cloudBG, null, x + anim3X - cloudWidth, y);
	}

}
