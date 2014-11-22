package control;

import lib.InputUtility;
import logic.GameLogic;

public class GameLogicThread implements Runnable {
	
	GameLogic logic;
	GameThreadMonitor monitor;
	
	public GameLogicThread(GameLogic logic, GameThreadMonitor monitor) {
		this.logic = logic;
		this.monitor = monitor;
	}
	
	@Override
	public void run() {
		while(ScreenState.presentScreen == ScreenState.GAME){
			monitor.updateLogic();
			try{
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
	}
}
