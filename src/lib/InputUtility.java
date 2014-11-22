package lib;

import java.awt.Point;

public class InputUtility {
	private static boolean picking;
	private static boolean mouseDown;
	private static Point pickedPoint;
	private static boolean keyPressed[] = new boolean[256];
	private static boolean keyTriggered[] = new boolean[256];
	private static boolean mouseReleased = false;
	public static final int NULL_POINT = -100;
	
	static{
		pickedPoint = new Point(NULL_POINT, NULL_POINT);
	}
	
	public static synchronized void setPicking (boolean in){
		picking=in;
	}
	
	public static synchronized void setPickedPoint (int x ,int y){
		pickedPoint = new Point (x,y);
	}
	
	public static void setMouseDown(boolean mouseDown) {
		InputUtility.mouseDown = mouseDown;
	}
	
	public static boolean isMouseDown() {
		return mouseDown;
	}
	
	public static synchronized boolean isPicking (){
		return picking;
	}
	
	public static synchronized Point getPickedPoint (){
		return pickedPoint;
	}
	
	public static synchronized void setKeyPressed(int key, boolean pressed) {
		try{
			InputUtility.keyPressed[key] = pressed;
		} catch (ArrayIndexOutOfBoundsException e) {
			return;
		}
	}
	
	public static synchronized boolean getKeyPressed(int key) {
		try{
			return keyPressed[key];
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}	
	}
	
	public static synchronized void setKeyTriggered(int key, boolean triggered) {
		try{
			if(!keyTriggered[key])
				InputUtility.keyTriggered[key] = triggered;
		} catch (ArrayIndexOutOfBoundsException e) {
		}	
			
	}
	
	public static synchronized boolean getKeyTriggered(int key) {
		try{
			return keyTriggered[key];
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public static synchronized void setMouseReleased(boolean mouseReleased) {
		InputUtility.mouseReleased = mouseReleased;
	}
	
	public static synchronized boolean isMouseReleased() {
		return mouseReleased;
	}
	
	public static synchronized void postUpdate(){
		picking = false;
		keyTriggered = new boolean[256];
		mouseReleased = false;
	}
}
