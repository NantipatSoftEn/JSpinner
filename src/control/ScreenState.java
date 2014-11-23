package control;

public enum ScreenState {
	TITLE, LEVEL_SELECT, GAME;
	public static ScreenState presentScreen = TITLE;
	public static String nextLevel;
}
