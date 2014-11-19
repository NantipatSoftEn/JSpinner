package logic;

public class LevelFormatException extends Exception{
	public LevelFormatException(String directory){
		super("Invalid level file format: " + directory);
	}
}
