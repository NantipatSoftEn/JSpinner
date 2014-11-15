package ui;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import ui.gamebutton.*;
import ui.winpanel.WinPanel;
import lib.*;
import logic.*;

public class GameScreen extends JPanel{
	
	private List<IRenderable> renderList = new ArrayList<IRenderable>();
	private Board board;
	private PlayerStatus playerStatus;
	private WinPanel winPanel;
	
	public GameScreen(Board board){
		super();
		setPreferredSize(new Dimension(Config.screenWidth, Config.screenHeight));
		setBackground(Color.WHITE);
		this.board = board;
		this.playerStatus = board.getPlayer();
		this.winPanel = new WinPanel();
		for(int j = 0; j < board.getBoardHeight(); j++)
			for(int i = 0; i < board.getBoardWidth(); i++){
				renderList.add(board.getTileAt(i, j));
			}
		renderList.add(playerStatus);
		renderList.addAll(WinPanel.winElements);
		renderList.addAll(Clickable.buttons);
		
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setVisible(true);
		this.requestFocus();
		
		GameBackground gb = new GameBackground();
		renderList.add(gb);
		(new Thread(gb)).start();
		
		/////////////////Mouse/////////////////
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				InputUtility.setPicking(true);
//				InputUtility.setPickedPoint(e.getX(), e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				InputUtility.setPicking(false);
				InputUtility.setMouseReleased(true);
//				InputUtility.setPickedPoint(InputUtility.NULL_POINT, InputUtility.NULL_POINT);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
			
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				InputUtility.setPickedPoint(e.getX(), e.getY());
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				InputUtility.setPickedPoint(e.getX(), e.getY());
			}
		});
		
		///////////////////////key///////////////////////////
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), false);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				InputUtility.setKeyTriggered(e.getKeyCode(), true);
				InputUtility.setKeyPressed(e.getKeyCode(), true);
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
	
	public WinPanel getWinPanel() {
		return winPanel;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Collections.sort(renderList, new Comparator<ui.IRenderable>() {
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
