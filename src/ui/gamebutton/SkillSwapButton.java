package ui.gamebutton;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import ui.Clickable;
import ui.IRenderable;
import lib.Config;
import lib.InputUtility;
import logic.Board;

public class SkillSwapButton extends Clickable implements IRenderable {
	public SkillSwapButton(){
		type = Clickable.CIRCLE;
		x = Config.screenWidth - 50;
		y = Config.screenHeight - 50;
		width = 40;
		height = 40;
	}

	@Override
	public int getZ() {
		return 10000;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		if(isMouseOn()){
			g.setColor(Color.LIGHT_GRAY);
		}
		g.fillOval(x, y, width, height);
	}

	@Override
	public void onClickAction() {
		JOptionPane.showMessageDialog(null, "SKILL UNDO");
	}
}
