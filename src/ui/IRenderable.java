package ui;

import java.awt.Graphics;

public interface IRenderable {
	int getZ();
	void draw(Graphics g);
}
