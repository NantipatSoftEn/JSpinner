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

public class AboutButton extends Clickable {
	public AboutButton(){
		type = Clickable.CIRCLE;
		updatePosition();
	}

	@Override
	public int getZ() {
		return 10000;
	}

	@Override
	public void draw(Graphics g) {
		drawButton(g, DrawingUtility.aboutButtonImg);
	}

	@Override
	public void updatePosition() {
		width = 50;
		height = 50;
		x = Config.screenWidth - width - 5;
		y = Config.screenHeight - 55;
	}
	
	@Override
	public void onClickAction() {
		JOptionPane.showMessageDialog(null, "JSpinner: a game by\n"
								+ "Thanawit Prasongpongchai 5631045321\n"
								+ "Phatrasek Jirabovonvisut 5630469621\n"
								+ "for course 2110215 PROG METH (2014/1)\n"
								+ "Computer Engineering Chulalongkorn University", "About", JOptionPane.INFORMATION_MESSAGE);
	}
}
