package control;

import ui.GameScreen;
import lib.InputUtility;
import logic.GameLogic;

public class GameScreenThread implements Runnable {
	
	GameScreen screen;
	GameThreadMonitor monitor;
	
	public GameScreenThread(GameScreen screen, GameThreadMonitor monitor) {
		this.screen = screen;
		this.monitor = monitor;
	}
	
	@Override
	public void run() {		   
		while(ScreenState.presentScreen == ScreenState.GAME){
			monitor.updateScreen();
		}
	}
}
