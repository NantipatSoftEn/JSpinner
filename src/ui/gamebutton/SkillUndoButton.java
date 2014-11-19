package ui.gamebutton;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import ui.Clickable;
import ui.DrawingUtility;
import ui.IRenderable;
import lib.Config;
import lib.InputUtility;
import logic.Board;

public class SkillUndoButton extends Clickable implements IRenderable {
	public SkillUndoButton(){
		type = Clickable.CIRCLE;
		width = 50;
		height = 50;
		x = Config.screenWidth - 2 * width - 10;
		y = Config.screenHeight - height - 5;
	}

	@Override
	public int getZ() {
		return 10000;
	}

	@Override
	public void draw(Graphics g) {
		drawButton(g, DrawingUtility.defaultButtonImg);
	}

	@Override
	public void onClickAction() {
		JOptionPane.showMessageDialog(null, "SKILL UNDO");
	}
}
