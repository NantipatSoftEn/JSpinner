/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package ui.gamebutton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

import control.Game;
import control.ScreenState;
import ui.Clickable;
import ui.HelpPanel;
import ui.IRenderable;
import ui.winpanel.WinPanel;
import util.Config;
import util.DrawingUtility;
import util.InputUtility;
import logic.Board;

public class BackButton extends Clickable implements IRenderable {
	public BackButton(){
		type = Clickable.CIRCLE;
		updatePosition();
	}

	@Override
	public int getZ() {
		return 10000;
	}

	@Override
	public void draw(Graphics g) {
		drawButton(g, DrawingUtility.backButtonImg);
	}

	@Override
	public void updatePosition() {
		x = 5;
		y = Config.screenHeight - 55;
		width = 50;
		height = 50;
	}
	
	@Override
	public void onClickAction() {
//		JOptionPane.showMessageDialog(null, "BACK");
		if(ScreenState.presentScreen == ScreenState.LEVEL_SELECT)
			ScreenState.presentScreen = ScreenState.TITLE;
		if(ScreenState.presentScreen == ScreenState.GAME)
			if(JOptionPane.showConfirmDialog(null, "Are you sure you want to go back?\nGame progress will not be saved.", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				ScreenState.presentScreen = ScreenState.LEVEL_SELECT;
		WinPanel.setVisible(false);
		HelpPanel.setVisible(false);
	}
}
