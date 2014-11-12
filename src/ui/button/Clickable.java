package ui.button;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

import ui.GameScreen;
import ui.IRenderable;
import lib.InputUtility;
import logic.Board;
import logic.GameLogic;
import logic.IUpdatable;

public abstract class Clickable implements IRenderable, IUpdatable{
	protected int x, y;
	protected int width, height;
	protected int type;
	public static int RECTANGLE = 0;
	public static int CIRCLE = 1;
	public static List<Clickable> buttons = new ArrayList<Clickable>();
	public static Board board;
	
	static{
		buttons.add(new ShuffleButton());
		buttons.add(new UndoButton());
	}
	
	public Clickable(){	
	}
	
	public boolean isMouseOn(){
		if(type == RECTANGLE){
			boolean validX = InputUtility.getPickedPoint().getX() >= x && InputUtility.getPickedPoint().getX() <= x + width;
			boolean validY = InputUtility.getPickedPoint().getY() >= y && InputUtility.getPickedPoint().getX() <= y + height;
			return validX && validY;
		} else if(type == CIRCLE){
			int mx = (int) InputUtility.getPickedPoint().getX();
			int my = (int) InputUtility.getPickedPoint().getY();
			int r = Math.min(width, height) / 2; 
			return (mx - (x + r)) * (mx - (x + r)) + (my - (y + r)) * (my - (y + r)) <= r * r;
		} else
			return false;
	}
	
	public void update(){
		if(isMouseOn()){
			mouseOnAction();
			if(InputUtility.isPicking()){
				onClickAction();
			}
		}
	}
	
	public void onClickAction(){
		
	}
	
	public void mouseOnAction(){
		
	}
	
	public abstract int getZ();
	public abstract void draw(Graphics g);
}
