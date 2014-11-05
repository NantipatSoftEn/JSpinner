package lib;

public class InputUtility {
	private static boolean picking;
	private static Point pickedPoint;
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
}
