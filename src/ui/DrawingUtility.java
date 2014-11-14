package ui;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class DrawingUtility {
	
	//Text Metric Tools
	public static final int TEXT_TOPLEFT = 0;
	public static final int TEXT_TOP = 1;
	public static final int TEXT_TOPRIGHT = 2;	
	public static final int TEXT_LEFT = 3;
	public static final int TEXT_CENTER = 4;
	public static final int TEXT_RIGHT = 5;
	public static final int TEXT_BOTTOMLEFT = 6;
	public static final int TEXT_BOTTOM = 7;
	public static final int TEXT_BOTTOMRIGHT = 8;
	
	public static final Color SELECTED = Color.GREEN;
	public static final Color CORRECT = new Color(0, 220, 0);
	
	public static final int STATE_NORMAL = 0;
	public static final int STATE_HOVER = 1;
	public static final int STATE_CLICK = 2;
	
	public static final BufferedImage cwButtonImg = loadImage("res/img/cwButton.png");
	public static final BufferedImage ccwButtonImg = loadImage("res/img/ccwButton.png");
	public static final BufferedImage gameBG = loadImage("res/img/gameBG.png");
	
	public static BufferedImage loadImage(String directory){
		try {
			return ImageIO.read(DrawingUtility.class.getClassLoader().getResource(directory));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Image not found!", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	public static BufferedImage getClickableImg(BufferedImage spriteSheet, int state){
		if(state < 3)
			return spriteSheet.getSubimage(spriteSheet.getWidth() * state / 3, 0, spriteSheet.getWidth() / 3, spriteSheet.getHeight());
		else return null;
	}
	
	public static void drawStringAt(String s, Font font, int xRef, int yRef, int aligned, Graphics context){
		FontMetrics fm = context.getFontMetrics(font);
		Rectangle2D strBound = fm.getStringBounds(s, context);
		int xDraw, yDraw;
		if(aligned == TEXT_TOPLEFT){
			xDraw = xRef;
			yDraw = yRef + (int) strBound.getHeight();
		} else if(aligned == TEXT_TOP){
			xDraw = xRef - (int) strBound.getWidth() / 2;
			yDraw = yRef + (int) strBound.getHeight();
		} else if(aligned == TEXT_TOPRIGHT){
			xDraw = xRef - (int) strBound.getWidth();
			yDraw = yRef + (int) strBound.getHeight();
		} else if(aligned == TEXT_LEFT){
			xDraw = xRef;
			yDraw = yRef + (int) strBound.getHeight() / 2;
		} else if(aligned == TEXT_CENTER){
			xDraw = xRef - (int) strBound.getWidth() / 2;
			yDraw = yRef + (int) strBound.getHeight() / 2;
		} else if(aligned == TEXT_RIGHT){
			xDraw = xRef - (int) strBound.getWidth();
			yDraw = yRef + (int) strBound.getHeight() / 2;
		} else if(aligned == TEXT_BOTTOMLEFT){
			xDraw = xRef;
			yDraw = yRef;
		} else if(aligned == TEXT_BOTTOM){
			xDraw = xRef - (int) strBound.getWidth() / 2;
			yDraw = yRef;
		} else if(aligned == TEXT_BOTTOMRIGHT){
			xDraw = xRef - (int) strBound.getWidth();
			yDraw = yRef;
		} else  {
			//default alignment: bottomleft
			xDraw = xRef;
			yDraw = yRef;
		}
		Font tmpFont = context.getFont();
		context.setFont(font);
		context.drawString(s, xDraw, yDraw);
		context.setFont(tmpFont);	//return to original font
	}
	
	public static void drawStringInBox(String s, Font font, int xRef, int yRef, int width, int height, int aligned, Graphics context){
		//int align determines the position of the string in the box.
		FontMetrics fm = context.getFontMetrics(font);
		Rectangle2D strBound = fm.getStringBounds(s, context);
		int xDraw, yDraw;
		if(aligned == TEXT_TOPLEFT){
			xDraw = xRef;
			yDraw = yRef + (int) strBound.getHeight();
		} else if(aligned == TEXT_TOP){
			xDraw = xRef + width / 2 - (int) strBound.getWidth() / 2;
			yDraw = yRef + (int) strBound.getHeight();
		} else if(aligned == TEXT_TOPRIGHT){
			xDraw = xRef + width - (int) strBound.getWidth();
			yDraw = yRef + (int) strBound.getHeight();
		} else if(aligned == TEXT_LEFT){
			xDraw = xRef;
			yDraw = yRef + height / 2 + (int) strBound.getHeight() / 2;
		} else if(aligned == TEXT_CENTER){
			xDraw = xRef + width / 2 - (int) strBound.getWidth() / 2;
			yDraw = yRef + height / 2 + (int) strBound.getHeight() / 2;
		} else if(aligned == TEXT_RIGHT){
			xDraw = xRef + width - (int) strBound.getWidth();
			yDraw = yRef + height / 2 + (int) strBound.getHeight() / 2;
		} else if(aligned == TEXT_BOTTOMLEFT){
			xDraw = xRef;
			yDraw = yRef + height;
		} else if(aligned == TEXT_BOTTOM){
			xDraw = xRef + width / 2 - (int) strBound.getWidth() / 2;
			yDraw = yRef + height;
		} else if(aligned == TEXT_BOTTOMRIGHT){
			xDraw = xRef + width - (int) strBound.getWidth();
			yDraw = yRef + height;
		} else  {
			//default alignment: bottomleft
			xDraw = xRef;
			yDraw = yRef;
		}
		Font tmpFont = context.getFont();
		context.setFont(font);
		context.drawString(s, xDraw, yDraw);
		context.setFont(tmpFont);	//return to original font
	}
	
}
