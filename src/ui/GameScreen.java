package ui;

import java.util.*;
import java.util.List;
import java.awt.*;

import javax.swing.*;

import lib.*;
import logic.*;

public class GameScreen extends JPanel{
	
	private List<IRenderable> renderList = new ArrayList<IRenderable>();
	private Board board;
	private PlayerStatus playerStatus;
	
	public GameScreen(){
		super();
		setPreferredSize(new Dimension(Config.screenWidth, Config.screenHeight));
		setBackground(Color.WHITE);
		this.board = new Board(6, 6);
		this.playerStatus = new PlayerStatus();
		renderList.add(board);
		renderList.add(playerStatus);
	}
	
	public addRenderableObject(IRenderable r){
		renderList.add(r);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Collections.sort(renderList, new Comparator<lib.IRenderable>() {
			public int compare(IRenderable r1, IRenderable r2){
				if(r1.getZ() > r2.getZ()) return 1;
				else if(r1.getZ() == r2.getZ()) return 0;
				else if(r1.getZ() < r2.getZ()) return -1;
				else return 0;
			}
		});
//		System.out.println(renderList.toString());
		for(int i = 0; i < renderList.size(); i++){
			renderList.get(i).draw(g);
		}
	}
}
