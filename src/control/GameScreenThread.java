/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package control;

import ui.GameScreen;
import util.InputUtility;
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
