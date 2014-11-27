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
import ui.*;
import util.Config;
import util.DrawingUtility;
import util.InputUtility;
import logic.Board;

public class SettingsButton extends Clickable {
	public SettingsButton(){
		type = Clickable.CIRCLE;
		updatePosition();
	}

	@Override
	public int getZ() {
		return 10000;
	}

	@Override
	public void draw(Graphics g) {
		drawButton(g, DrawingUtility.settingsButtonImg);
	}

	@Override
	public void updatePosition() {
		width = 50;
		height = 50;
		x = 5;
		y = Config.screenHeight - width - 5;
	}
	
	@Override
	public void onClickAction() {
		GameSettings.settingsFrame.setVisible(true);
	}
}
