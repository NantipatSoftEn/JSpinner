package control;

import lib.InputUtility;
import logic.GameLogic;

public class GameLogicThread implements Runnable {
	
	GameLogic logic;
	
	public GameLogicThread(GameLogic logic) {
		this.logic = logic;
	}
	
	@Override
	public void run() {
		while(ScreenState.presentScreen == ScreenState.GAME){
			logic.update();
			Game.finishUpdate = true;
			notifyAll();
			InputUtility.postUpdate();
			try{
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
		}
	}

}
