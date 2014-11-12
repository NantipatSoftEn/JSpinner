package lib;

import java.awt.Point;

import logic.Board;

public class Utility {
	public static int random(int start, int end){
		//inclusive start, exclusive end
		return start + (int)(Math.random() * (end - start));
	}
	
	public static boolean isPointInRect(Point p, int rectX, int rectY, int width, int height){
		return p.getX() >= rectX && p.getX() >= rectY && p.getX() <= rectX + width && p.getY() <= rectY + height;
	}
	
	public static boolean isPointOnTile(Point p, Board board, int boardX, int boardY){
		boolean b;
		Point tileTopLeft = board.getTileLocation(boardX, boardY);
		b = (p.getX() >= tileTopLeft.getX());
		b = b && (p.getY() >= tileTopLeft.getY());
		b = b && (p.getX() <= tileTopLeft.getX() + board.getTileSize());
		b = b && (p.getY() <= tileTopLeft.getY() + board.getTileSize());	
		return b;
	}
}
