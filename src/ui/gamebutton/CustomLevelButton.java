package ui.gamebutton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.*;

import control.Game;
import control.ScreenState;
import ui.Clickable;
import ui.IRenderable;
import lib.Config;
import lib.DrawingUtility;
import lib.InputUtility;
import logic.Board;

public class CustomLevelButton extends Clickable{
	
	public CustomLevelButton(){
		type = Clickable.CIRCLE;
		width = 50;
		height = 50;
		x = Config.screenWidth - width - 5;
		y = Config.screenHeight - height - 5;
	}

	@Override
	public int getZ() {
		return 10000;
	}

	@Override
	public void draw(Graphics g) {
		drawButton(g, DrawingUtility.openButtonImg);
	}

	@Override
	public void onClickAction() {
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "JSpinner Level Files (*.jsl, *.txt)", "txt", "jsl");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	ScreenState.nextLevel = chooser.getSelectedFile().getAbsolutePath();
	    	ScreenState.presentScreen = ScreenState.GAME;
	    	ScreenState.isAdventure = false;
	    } else {
	    	ScreenState.presentScreen = ScreenState.LEVEL_SELECT;
	    }
	}
}
