package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import util.DrawingUtility;
import logic.IUpdatable;

public class GameAnimation implements IRenderable, IUpdatable {

	private boolean isVisible = false;
	private int currentFrame, frameCount;
	private BufferedImage animationSprite;
	int cx, cy;
	
	public static GameAnimation fightAnim = new GameAnimation(DrawingUtility.fightSprite, 8);
	
	public GameAnimation(BufferedImage sprite, int frameCount){
		this.animationSprite = sprite;
		this.frameCount = frameCount;
	}
	
	public void playAt(int cx, int cy){
		isVisible = true;
		currentFrame = -1;
		this.cx = cx;
		this.cy = cy;
	}
	
	@Override
	public void update() {
		if(isVisible){
			currentFrame++;
			if(currentFrame >= frameCount * 4){
				isVisible = false;
			}
		}
	}

	@Override
	public int getZ() {
		return 30000;
	}

	@Override
	public void draw(Graphics g) {
		if(isVisible){
			Graphics2D g2 = (Graphics2D) g;
			int x = cx - animationSprite.getWidth() / frameCount / 2;
			int y = cy - animationSprite.getHeight() / 2;
//			System.out.println(currentFrame + "/" + frameCount);
			g2.drawImage(DrawingUtility.getFrame(animationSprite, currentFrame/4, frameCount), null, x, y);
		}
	}

}
