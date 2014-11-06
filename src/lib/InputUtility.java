package lib;

import java.awt.Point;

public class InputUtility {
	private static boolean picking;
	private static Point pickedPoint;
	public static final int NULL_POINT = -100;

	static{
		pickedPoint = new Point(NULL_POINT, NULL_POINT);
	}
	
	public static void setPicking (boolean fucker){
		picking=fucker;
	}
	public static void setPickedPoint (int x ,int y){
		pickedPoint = new Point (x,y);
	}
	public static boolean isPicking (){
		return picking;
	}
	public static Point getPickedPoint (){
		return pickedPoint;
	}
	public static void postUpdate(){
		picking = false;
	}
	
}
