package ui;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
		this.board = new Board(6, 5);
		board.shuffle(Board.DEFAULT_SHUFFLE);
		this.playerStatus = board.getPlayer();
		renderList.add(board);
		renderList.add(playerStatus);
		
		/////////////////Mouse/////////////////
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				InputUtility.setPicking(true);
				InputUtility.setPickedPoint(e.getX(), e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				InputUtility.setPicking(false);
				InputUtility.setPickedPoint(InputUtility.NULL_POINT, InputUtility.NULL_POINT);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		
		//////////////////////////////////////
	}
	
	public void addRenderableObject(IRenderable r){
		renderList.add(r);
	}
	
	public Board getBoard() {
		return board;
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
