package lib;

public class Utility {
	public static int random(int start, int end){
		//inclusive start, exclusive end
		return start + (int)(Math.random() * (end - start));
	}
}
