package logic;

import java.util.ArrayList;
import java.util.List;

import lib.IUpdatable;

public class GameLogic {
	
	private List<IUpdatable> updateList = new ArrayList<IUpdatable>();

	public GameLogic(Board b){
		updateList.add(b);
	}
	
	public void update(){
		for(IUpdatable logic : updateList){
			logic.update();
		}
	}
}
