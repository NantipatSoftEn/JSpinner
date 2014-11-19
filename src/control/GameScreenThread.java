package control;

import ui.GameScreen;
import lib.InputUtility;
import logic.GameLogic;

public class GameScreenThread implements Runnable {
	
	GameScreen screen;
	
	public GameScreenThread(GameScreen screen) {
		this.screen = screen;
	}
	
	@Override
	public void run() {
		while(ScreenState.presentScreen == ScreenState.GAME){
			screen.repaint();
			Game.finishUpdate = false;
			while(!Game.finishUpdate){
				try {
					wait();
				} catch (InterruptedException e) {
				} 
			}
		}
	}

}
