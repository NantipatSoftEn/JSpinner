/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package control;

public enum ScreenState {
	TITLE, LEVEL_SELECT, GAME, NEXT_LEVEL, REFRESH_TITLE;
	public static ScreenState presentScreen = TITLE;
	public static boolean isAdventure = false;
	public static String nextLevel;
}
