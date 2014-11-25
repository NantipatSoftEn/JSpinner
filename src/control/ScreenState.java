package control;

public enum ScreenState {
	TITLE, LEVEL_SELECT, GAME,NEXT_LEVEL;
	public static ScreenState presentScreen = TITLE;
	public static boolean isAdventure = false;
	public static String nextLevel;
}
