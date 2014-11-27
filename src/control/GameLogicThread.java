/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package control;

import util.InputUtility;
import logic.GameLogic;

public class GameLogicThread implements Runnable {
	
	private GameLogic logic;
	private GameThreadMonitor monitor;
	
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
