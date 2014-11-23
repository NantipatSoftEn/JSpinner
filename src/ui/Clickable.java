package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

import ui.gamebutton.*;
import ui.winpanel.*;
import lib.DrawingUtility;
import lib.InputUtility;
import logic.*;

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
		buttons.add(new NextLevelButton());
		buttons.add(new HelpButton());
		buttons.add(new BackButton());
		buttons.add(new SkillSwapButton());
		buttons.add(new SkillUndoButton());
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
			//TODO should i use mousepicking or mousereleased?
			if(InputUtility.isMouseReleased()){
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
	
	protected void drawButton(Graphics g, BufferedImage buttonSprite){
		Graphics2D g2 = (Graphics2D) g;
		if(!isMouseOn())
			g2.drawImage(DrawingUtility.getClickableImg(buttonSprite, DrawingUtility.STATE_NORMAL), null, x, y);
		else
			if(InputUtility.isMouseDown())
				g2.drawImage(DrawingUtility.getClickableImg(buttonSprite, DrawingUtility.STATE_CLICK), null, x, y);
			else	
				g2.drawImage(DrawingUtility.getClickableImg(buttonSprite, DrawingUtility.STATE_HOVER), null, x, y);
	}
}
