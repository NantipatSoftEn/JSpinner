/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package logic;

public class LevelFormatException extends Exception{
	public LevelFormatException(String directory){
		super("Invalid level file format: " + directory);
	}
}
