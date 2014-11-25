package control;

public enum ScreenState {
	TITLE, LEVEL_SELECT, GAME, NEXT_LEVEL, REFRESH_TITLE;
	public static ScreenState presentScreen = TITLE;
	public static boolean isAdventure = false;
	public static String nextLevel;
}
