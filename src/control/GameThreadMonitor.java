/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package control;

import java.awt.event.KeyEvent;

import ui.GameScreen;
import util.InputUtility;
import logic.GameLogic;

public class GameThreadMonitor {
//	GameThreadMonitor.
	private GameLogic logic;
	private GameScreen screen;
	private boolean finishUpdate;
	
	public GameThreadMonitor(GameLogic logic, GameScreen screen) {
		this.logic = logic;
		this.screen = screen;
	}
	
	public synchronized void updateScreen(){
		screen.repaint();
		finishUpdate = false;
		notifyAll();
		while(!finishUpdate && ScreenState.presentScreen == ScreenState.GAME){
			try {
				wait();
			} catch (InterruptedException e) {
			} 
		}
	}

	public synchronized void updateLogic(){
		logic.update();
		finishUpdate = true;
		notifyAll();
		InputUtility.postUpdate();
		while(finishUpdate && ScreenState.presentScreen == ScreenState.GAME){
			try {
				wait();
			} catch (InterruptedException e) {
			} 
		}
	}
}
