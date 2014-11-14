package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import lib.Config;

public class GameBackground implements IRenderable {
	
	private double theta = 0;
	
	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int x = (Config.screenWidth - DrawingUtility.gameBG.getWidth()) / 2;
		int y = (Config.screenWidth - DrawingUtility.gameBG.getHeight()) / 2;
//		AffineTransform at = new AffineTransform();
//		at.rotate(theta, Config.screenWidth / 2, Config.screenHeight / 2);
//		theta += 0.01;
//		g2.drawImage(DrawingUtility.gameBG, new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC), x, y);
		g2.drawImage(DrawingUtility.gameBG, null, x, y);
	}

}
