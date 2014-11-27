/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package util;

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
	
	public static final BufferedImage gameBG = loadImage("res/img/gameBG.png");
	public static final BufferedImage cloudBG = loadImage("res/img/gameBG_cloud.png");
	public static final BufferedImage logoImg = loadImage("res/img/logo.png");
	public static final BufferedImage cwButtonImg = loadImage("res/img/cwButton.png");
	public static final BufferedImage ccwButtonImg = loadImage("res/img/ccwButton.png");
	public static final BufferedImage backButtonImg = loadImage("res/img/backButton.png");
	public static final BufferedImage helpButtonImg = loadImage("res/img/helpButton.png");
	public static final BufferedImage undoButtonImg = loadImage("res/img/undoButton.png");
	public static final BufferedImage playButtonImg = loadImage("res/img/playButton.png");
	public static final BufferedImage settingsButtonImg = loadImage("res/img/settingsButton.png");
	public static final BufferedImage newGameButtonImg = loadImage("res/img/newGameButton.png");
	public static final BufferedImage openButtonImg = loadImage("res/img/openButton.png");
	public static final BufferedImage soundOffButtonImg = loadImage("res/img/soundOffButton.png");
	public static final BufferedImage soundOnButtonImg = loadImage("res/img/soundOnButton.png");
	public static final BufferedImage aboutButtonImg = loadImage("res/img/aboutButton.png");
	public static final BufferedImage defaultButtonImg = loadImage("res/img/defaultButton.png");
	public static final BufferedImage sleepyTileImg = loadImage("res/img/sleepyTile.png");
	public static final BufferedImage angryTileImg = loadImage("res/img/angryTile.png");
	public static final BufferedImage correctImg = loadImage("res/img/correct.png");
	public static final BufferedImage fightSprite = loadImage("res/img/boomSprite.png");
	
	public static BufferedImage loadImage(String directory){
		try {
			return ImageIO.read(DrawingUtility.class.getClassLoader().getResource(directory));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Resource image not found!", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	public static Color generateRainbow(int number, int total){
		int gr = Math.abs(128 - number * 255 / total);
		int re = 255 - number * 255 / total;
		int bl = number * 255 / total;
		try{
			return new Color(re, gr, bl);
		} catch (IllegalArgumentException e){
			return Color.BLACK;
		}
	}
	
	public static Font getDefaultFont(int size){
		return new Font("Tahoma", Font.BOLD, size);
	}
	
	public static BufferedImage getClickableImg(BufferedImage spriteSheet, int state){
		if(state < 3)
			return spriteSheet.getSubimage(spriteSheet.getWidth() * state / 3, 0, spriteSheet.getWidth() / 3, spriteSheet.getHeight());
		else return null;
	}
	
	public static BufferedImage getFrame(BufferedImage spriteSheet, int frame, int frameCount){
		if(frame < frameCount)
			return spriteSheet.getSubimage(spriteSheet.getWidth() * frame / frameCount, 0, spriteSheet.getWidth() / frameCount, spriteSheet.getHeight());
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
	
	public static void drawStringInBox(String s, int size, int xRef, int yRef, int width, int height, int aligned, Graphics context){
		drawStringInBox(s, DrawingUtility.getDefaultFont(size), xRef, yRef, width, height, aligned, context);
	}
}
