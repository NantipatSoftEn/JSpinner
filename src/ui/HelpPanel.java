package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.Config;
import util.DrawingUtility;
import util.InputUtility;
import logic.Board;
import logic.IUpdatable;
import logic.PlayerStatus;

public class HelpPanel implements IRenderable, IUpdatable {

	private static boolean isVisible;
	private static int PAGES = 3;
	public static int width = 750;
	public static int height = 500;
	public static int x = (Config.screenWidth - width) / 2;
	public static int y = (Config.screenHeight - height) / 2;
	public static int currentFrame = 0;
	public static BufferedImage[] help = new BufferedImage[PAGES];
	public static HelpPanel helpPanel;
	private int k = 0;
	private Rectangle2D prevButton = new Rectangle2D.Double(x + 290, y + 430, 40, 20);
	private Rectangle2D nextButton = new Rectangle2D.Double(x + 420, y + 430, 40, 20);
	private Rectangle2D closeButton = new Rectangle2D.Double(x + 685, y + 25, 40, 40);
	
	static{
		helpPanel = new HelpPanel();
		try{
			help[0] = DrawingUtility.loadImage("res/img/help1.png");
			help[1] = DrawingUtility.loadImage("res/img/help2.png");
			help[2] = DrawingUtility.loadImage("res/img/help3.png");
		} catch(Exception e) {
			System.out.println("!");
		}
	}
	
	private HelpPanel(){
		isVisible = false;
	}
	
	public static void setVisible(boolean isVisible) {
		if(!isVisible)
			currentFrame = 0;
		HelpPanel.isVisible = isVisible;
	}
	
	public static boolean isVisible() {
		return isVisible;
	}
	
	@Override
	public int getZ() {
		return Integer.MAX_VALUE;
	}

	@Override
	public void draw(Graphics g) {
		if(isVisible){
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(help[currentFrame], null, x, y);
		}
	}
	
	@Override
	public void update() {
		if(isVisible && InputUtility.isPicking()){
			if(nextButton.contains((Point2D)InputUtility.getPickedPoint())){
				currentFrame++;
				if(currentFrame >= PAGES)
					currentFrame = 0;
			}
			if(prevButton.contains((Point2D)InputUtility.getPickedPoint())){
				currentFrame--;
				if(currentFrame < 0)
					currentFrame = PAGES - 1;
			}
			if(closeButton.contains((Point2D)InputUtility.getPickedPoint())){
				setVisible(false);
			}
			else if(!this.contains((Point2D)InputUtility.getPickedPoint()) && !Clickable.helpButton.isMouseOn()){
				setVisible(false);
			}
		}
	}
	
	public boolean contains(Point2D p){
		return p.getX() <= x + width - 25 && p.getY() <= y + height - 25 && p.getX() >= x && p.getY() >= y;
	}
}

