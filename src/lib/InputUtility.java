package lib;

import java.awt.Point;

public class InputUtility {
	private static boolean picking;
	private static Point pickedPoint;
	private static boolean keyPressed[] = new boolean[256];
	private static boolean keyTriggered[] = new boolean[256];
	private static boolean mouseReleased = false;
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
		try{
			InputUtility.keyPressed[key] = pressed;
		} catch (ArrayIndexOutOfBoundsException e) {
			return;
		}
	}
	
	public static boolean getKeyPressed(int key) {
		try{
			return keyPressed[key];
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}	
	}
	
	public static void setKeyTriggered(int key, boolean triggered) {
		try{
			if(!keyTriggered[key])
				InputUtility.keyTriggered[key] = triggered;
		} catch (ArrayIndexOutOfBoundsException e) {
		}	
			
	}
	
	public static boolean getKeyTriggered(int key) {
		try{
			return keyTriggered[key];
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public static void setMouseReleased(boolean mouseReleased) {
		InputUtility.mouseReleased = mouseReleased;
	}
	
	public static boolean isMouseReleased() {
		return mouseReleased;
	}
	
	public static void postUpdate(){
		picking = false;
		keyTriggered = new boolean[256];
		mouseReleased = false;
	}
}
