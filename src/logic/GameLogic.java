package logic;

import java.util.ArrayList;
import java.util.List;

import ui.Clickable;
import ui.HelpPanel;
import ui.gamebutton.*;
import ui.winpanel.WinPanel;

public class GameLogic {
	
	private List<IUpdatable> updateList = new ArrayList<IUpdatable>();

	public GameLogic(Board b){
		updateList.add(b);
		updateList.addAll(Clickable.buttons);
		updateList.add(HelpPanel.helpPanel);
//		updateList.addAll(WinPanel.winElements);
	}
	
	public void update(){
		for(IUpdatable logic : updateList){
			logic.update();
		}
	}
}
