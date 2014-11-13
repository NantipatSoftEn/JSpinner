package lib;

import java.awt.Point;

public class InputUtility {
	private static boolean picking;
	private static Point pickedPoint;
	private static boolean keyPressed[] = new boolean[256];
	private static boolean keyTriggered[] = new boolean[256];
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
	
	public static void setKeyPressed(int key, boolean pressed) {
		InputUtility.keyPressed[key] = pressed;
	}
	
	public static boolean getKeyPressed(int key) {
		return keyPressed[key];
	}
	
	public static void setKeyTriggered(int key, boolean triggered) {
		if(!keyTriggered[key]){
			InputUtility.keyTriggered[key] = triggered;
		}
	}
	
	public static boolean getKeyTriggered(int key) {
		return keyTriggered[key];
	}
	
	public static void postUpdate(){
		picking = false;
		keyTriggered = new boolean[256];
	}
}
