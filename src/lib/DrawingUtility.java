package lib;

import java.awt.*;
import java.awt.geom.*;

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
	
//	public static int getTextXCentered(String s, Font font, Graphics context, int horizontalRef){
//		FontMetrics fm = context.getFontMetrics(font);
//		Rectangle2D strBound = fm.getStringBounds(s, context);
//		int x = horizontalRef - (int) strBound.getWidth() / 2;
//		return x;
//	}
//	
//	public static int getTextXAlignedLeft(String s, Font font, Graphics context, int horizontalRef){
//		return horizontalRef;
//	}
//	
//	public static int getTextXAlignedRight(String s, Font font, Graphics context, int horizontalRef){
//		FontMetrics fm = context.getFontMetrics(font);
//		Rectangle2D strBound = fm.getStringBounds(s, context);
//		int x = horizontalRef - (int) strBound.getWidth();
//		return x;
//	}
//	
//	public static Point getCenteredTextPosition(String s, Font font, Point topLeft, Point bottomRight, Graphics context){
//		//return point (x, y) for drawing text centered in a box
//		FontMetrics fm = context.getFontMetrics(font);
//		Rectangle2D strBound = fm.getStringBounds(s, context);
//		int xRef = (topLeft.getX() + bottomRight.getX()) / 2;
//		int yRef = (topLeft.getY() + bottomRight.getY()) / 2;
//		int xDraw = xRef - (int) strBound.getWidth() / 2; 
//		int yDraw = yRef + (int) strBound.getHeight() / 2; 
//		return new Point(xDraw, yDraw);
//	}
//	
//	public static Point getCenteredTextPosition(String s, Font font, int xRef, int yRef, Graphics context){
//		//return point (x, y) for drawing text centered in crossing reference lines (xRef and yRef)
//		FontMetrics fm = context.getFontMetrics(font);
//		Rectangle2D strBound = fm.getStringBounds(s, context);
//		int xDraw = xRef - (int) strBound.getWidth() / 2; 
//		int yDraw = yRef + (int) strBound.getHeight() / 2; 
//		return new Point(xDraw, yDraw);
//	}
	
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
