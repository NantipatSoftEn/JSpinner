package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

import ui.gamebutton.ClockWiseButton;
import ui.gamebutton.CounterClockWiseButton;
import ui.gamebutton.ShuffleButton;
import ui.gamebutton.UndoButton;
import ui.winpanel.RestartButton;
import lib.InputUtility;
import logic.Board;
import logic.GameLogic;
import logic.IUpdatable;

public abstract class Clickable implements IRenderable, IUpdatable{
	protected int x, y;
	protected int width, height;
	protected int type;
	protected boolean isVisible = true;
	public static int RECTANGLE = 0;
	public static int CIRCLE = 1;
	public static List<Clickable> buttons = new ArrayList<Clickable>();
	public static Board board;
	public static ClockWiseButton cwButton = new ClockWiseButton();
	public static CounterClockWiseButton ccwButton = new CounterClockWiseButton();
	
	static{
		buttons.add(new ShuffleButton());
		buttons.add(new UndoButton());
		buttons.add(cwButton);
		buttons.add(ccwButton);
		buttons.add(new RestartButton());
	}
	
	public Clickable(){	
	}
	
	public boolean isMouseOn(){
		if(type == RECTANGLE){
			boolean validX = InputUtility.getPickedPoint().getX() >= x && InputUtility.getPickedPoint().getX() <= x + width;
			boolean validY = InputUtility.getPickedPoint().getY() >= y && InputUtility.getPickedPoint().getY() <= y + height;
			return validX && validY;
		} else if(type == CIRCLE){
			int mx = (int) InputUtility.getPickedPoint().getX();
			int my = (int) InputUtility.getPickedPoint().getY();
//			System.out.println(InputUtility.getPickedPoint());
			int r = Math.min(width, height) / 2; 
			return (mx - (x + r)) * (mx - (x + r)) + (my - (y + r)) * (my - (y + r)) <= r * r;
		} else {
			return false;
		}
	}
	
	public void update(){
//		System.out.println(isMouseOn());
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