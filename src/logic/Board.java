/**
 * @author Thanawit Prasongpongchai (5631045321)
 * @author Phatrasek Jirabovonvisut (5630xxxx21)
 */

package logic;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.io.*;
import java.util.*;

import javax.rmi.CORBA.Util;
import javax.swing.JOptionPane;

import ui.IRenderable;
import ui.button.Clickable;
import lib.*;

public class Board implements IRenderable, IUpdatable {
	public static final int DEFAULT_SHUFFLE = 2000;
	public static final int CW = -1;	
	public static final int CCW = 1;	

	private Tile[][] board;
	private int x, y, width, height, selected = 0;
	private int tileSize;
	private int bestScore, moveLimit;
	private PlayerStatus player;
	private Point forFlip[] = new Point[2];
	private List<Move> move;

	public Board(int width, int height) {
		board = new Tile[width][height];
		player = new PlayerStatus(this);
		move = new ArrayList<Move>();
		initiateBoard();
		adjustCenter();	
	}

	public Board(String directory) {
		try {
			Scanner in = new Scanner(new File(directory));
			String tileInfo;
			int boardX = in.nextInt();
			int boardY = in.nextInt();
			this.player = new PlayerStatus(this);
			this.moveLimit = in.nextInt();
			this.bestScore = in.nextInt();
			board = new Tile[boardX][boardY];
			int k = 1;
			for (int i = 0; i < boardY; i++) {
				for (int j = 0; j < boardX; j++) {
					tileInfo = in.next();
					if (tileInfo.substring(0, 1).equalsIgnoreCase("S"))
						board[j][i] = new SimpleTile(k++, this, i, j);
					if (tileInfo.substring(0, 1).equalsIgnoreCase("-"))
						board[j][i] = new SimpleTile(Tile.NOT_A_TILE, this, i,
								j);
					board[j][i].setCurrentLocation(j, i);
					// WHY DOES THIS WORK... I DONT KNOW... WHY DO I HAVE TO SET
					// CURRENT LOCATION TWICE!!!!
					// System.out.print(board[j][i]);
				}
				// System.out.println();
			}
			adjustCenter();
			// for (int i = 0; i < board.length; i++)
			// for (int j = 0; j < board[0].length; j++) {
			// board[i][j].setCurrentLocation(i, j);
			// }
			move = new ArrayList<Move>();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error loading file", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "File format error", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void initiateBoard() {
		int k = 1;
		for (int j = 0; j < board[0].length; j++) {
			for (int i = 0; i < board.length; i++) {
				board[i][j] = new SimpleTile(k, this, i, j);
				System.out.print(board[i][j]);
				k++;
			}
			System.out.println();
		}
	}

	public void adjustCenter() {
		int H = Config.screenHeight;
		int G = Config.tileGutter;
		int M = Config.margin;
		int h = board[0].length;
		int T = Config.topBarHeight;
		tileSize = (H + G - 2 * T - 2 * M - h * G) / h;

		width = board.length * tileSize + (board.length - 1)
				* Config.tileGutter; // board width
		height = board[0].length * tileSize + (board[0].length - 1)
				* Config.tileGutter; // board height

		this.x = Config.screenWidth / 2 - width / 2;
		this.y = Config.topBarHeight + Config.margin;

		System.out.println(x + " " + y + " " + width + " " + height + " "
				+ tileSize + " " + Config.tileGutter);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Point getLocation() {
		return new Point(x, y);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getBoardHeight() {
		return board[0].length;
	}

	public int getBoardWidth() {
		return board.length;
	}

	public int getTileSize() {
		return tileSize;
	}

	public Point getTileLocation(int i, int j) {
		int tx = i * (tileSize + Config.tileGutter) + x;
		int ty = j * (tileSize + Config.tileGutter) + y;
		return new Point(tx, ty);
	}
	
	public Tile getTileAt(int i, int j){
		return board[i][j];
	}

	public PlayerStatus getPlayer() {
		return player;
	}

	public int getBestScore() {
		return bestScore;
	}
	
	public int getFlipX(){
		return (int) forFlip[0].getX();
	}
	
	public int getFlipY(){
		return (int) forFlip[0].getY();
	}
	
	public int getFlipSize(){
		return Math.abs(forFlip[0].x - forFlip[1].x);
	}
	
	public void clearSelected(){
		selected = 0;
		for(int j = 0; j < board[0].length; j++){
			for(int i = 0; i < board.length; i++){
				board[i][j].setSelected(false);
			}
		}
	}

//	public void flip(int x1, int y1, int x2, int y2) {
//		int dx = x2 - x1;
//		int dy = y2 - y1;
//
//		if (y2 < y1) {
//			int tmp = y1;
//			y1 = y2;
//			y2 = tmp;
//		}
//		if (x2 < x1) {
//			int tmp = x1;
//			x1 = x2;
//			x2 = tmp;
//		}
//
//		if (dx == 0) {
//			for (int i = y1; i < (y1 + y2 + 1) / 2; i++) {
//				Tile tmp = board[x1][i];
//				board[x1][i] = board[x1][y2 - (i - y1)];
//				board[x1][y2 - (i - y1)] = tmp;
//			}
//		} else if (dy == 0) {
//			for (int i = x1; i < (x1 + x2 + 1) / 2; i++) {
//				Tile tmp = null;
//				tmp = board[i][y1];
//				board[i][y1] = board[x2 - (i - x1)][y1];
//				board[x2 - (i - x1)][y1] = tmp;
//			}
//		} else if (dx == dy || dx == -dy) {
//
//			if (dx * dy > 0) {
//				for (int i = 0; i <= x2 - x1; i++) {
//					for (int j = 0; j < +(y2 - y1 - i); j++) {
//						Tile tmp = board[x1 + i][y1 + j];
//						board[x1 + i][y1 + j] = board[x2 - j][y2 - i];
//						board[x2 - j][y2 - i] = tmp;
//					}
//				}
//			}
//			if (dx * dy < 0) {
//				for (int i = 0; i <= x2 - x1; i++) {
//					for (int j = i + 1; j <= (y2 - y1); j++) {
//						Tile tmp = board[x1 + i][y1 + j];
//						board[x1 + i][y1 + j] = board[x1 + j][y1 + i];
//						board[x1 + j][y1 + i] = tmp;
//					}
//				}
//			}
//		}
//		for (int i = 0; i < board.length; i++)
//			for (int j = 0; j < board[0].length; j++) {
//				board[i][j].setCurrentLocation(i, j);
//			}
//	}
	
	public void flip(int x, int y, int size, int direction, boolean isPlaying){
		//new move logic: rotation
		Tile tmp;
		int s = size;
		if(direction == CCW){
			for(int j = 0; j < (size + 1) / 2; j++){
				for(int i = j; i < s; i++){
					tmp = board[x + i][y + j];
					board[x + i][y + j] = board[x + size - j][y + i];
					board[x + size - j][y + i] = board[x + size - i][y + size - j];
					board[x + size - i][y + size - j] = board[x + j][y + size - i];
					board[x + j][y + size - i] = tmp;
				}
				s--;
			}
		} else if(direction == CW) {
			for(int j = 0; j < (size + 1) / 2; j++){
				for(int i = j; i < s; i++){
					tmp = board[x + i][y + j];
					board[x + i][y + j] = board[x + j][y + size - i];
					board[x + j][y + size - i] = board[x + size - i][y + size - j];
					board[x + size - i][y + size - j] = board[x + size - j][y + i];
					board[x + size - j][y + i] = tmp;
				}
				s--;
			}
		}
		for (int j = y; j <= y + size; j++)
			for (int i = x; i <= x + size; i++)
				board[i][j].setCurrentLocation(i, j);
		if(isPlaying){
			move.add(new Move(x, y, size, direction));
			player.move();
		}
	}

	public void undo(){
		int i = move.size() - 1;
		if(move.size() > 0){
			Move latest = move.get(i);
			flip(latest.x, latest.y, latest.size, latest.dir * -1, false);
			move.remove(i);
			player.decreaseMove();
		} else {
			JOptionPane.showMessageDialog(null, "not Undoable!");
		}
	}
	
	public boolean isValidMove(int x1, int y1, int x2, int y2){
		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);
		if(x2 < x1){ int tmp = x1; x1 = x2; x2 = tmp;}
		if(y2 < y1){ int tmp = y1; y1 = y2; y2 = tmp;}
		if((dx != dy) || (dx == 0 && dy == 0)){
			return false;
		}
		for(int j = y1; j <= y2; j++){
			for(int i = x1; i <= x2; i++){
				if(!board[i][j].isATile()){
					return false;
				}
			}
		}
		return true;
	}

	public void shuffle(int times) {
		player.resetMove();
		move = new ArrayList<Move>();
		for (int i = 0; i < times; i++) {
			int x = Utility.random(0, board.length);
			int y = Utility.random(0, board[0].length);
			int dir = Utility.random(0, 4); 
				// 0 for NE 
				// 1 for SE 
				// 2 for SW 
				// 3 for NW
			int size;
						
			if(dir == 0){
				size = Utility.random(0, Math.min(board[0].length - x, y));
				y -= size;
			}
			else if(dir == 1){
				size = Utility.random(0, Math.min(board[0].length - x, board.length - y));
			}
			else if(dir == 2){
				size = Utility.random(0, Math.min(x, board.length - y));
				x -= size;
			}
			else if(dir == 3){
				size = Utility.random(0, Math.min(x, y));
				x -= size;
				y -= size;
			}
			else size = 0;
						
			int rotation = Utility.random(0, 2) == 0 ? CW : CCW;
			if(isValidMove(x, y, x + size, y + size)){
				flip(x, y, size, rotation, false);
			}
		}
	}

	public String toString() {
		String out = "";
		for (int j = 0; j < board[0].length; j++) {
			for (int i = 0; i < board.length; i++) {
				out += board[i][j] + ", ";
			}
			out += "\n";
		}
		return out;
	}

	// IRenderable Implementation

	public int getZ() {
		return 10000;
	}

	public void draw(Graphics g) {
		for (int i = 0; i < board[0].length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[j][i].draw(g);
			}
		}
	}

	public boolean isWin() {
		boolean check = true;
		for (int j = 0; j < board[0].length; j++) {
			for (int i = 0; i < board.length; i++) {
				if (!board[i][j].isCorrect())
					check = false;
			}
		}
		return check;
	}

	public void update() {
		// for each game loop...
		// shuffle(1); //just for testing screen update
		if(selected < 2){
			Clickable.cwButton.setVisible(false);
			Clickable.ccwButton.setVisible(false);
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[0].length; j++) {
					if (Utility.isPointOnTile(InputUtility.getPickedPoint(), this, i, j)) {
						if (InputUtility.isPicking()) {
							board[i][j].setSelected(true);
							forFlip[selected] = new Point(i, j);
							selected++;
						}
					}
				}
			}
		} else if(selected == 2) {
			if(isValidMove((int) forFlip[0].getX(), (int) forFlip[0].getY(), (int) forFlip[1].getX(), (int) forFlip[1].getY())){
				int x1 = (int) forFlip[0].getX();
				int y1 = (int) forFlip[0].getY();
				int x2 = (int) forFlip[1].getX();
				int y2 = (int) forFlip[1].getY();
				forFlip[0].setLocation(Math.min(x1,x2),	Math.min(y1,y2));
				forFlip[1].setLocation(Math.max(x1,x2),	Math.max(y1,y2));
				Clickable.cwButton.setVisible(true);
				Clickable.ccwButton.setVisible(true);
				if(InputUtility.getKeyTriggered(KeyEvent.VK_LEFT))
					Clickable.ccwButton.onClickAction();
				if(InputUtility.getKeyTriggered(KeyEvent.VK_RIGHT))
					Clickable.cwButton.onClickAction();
			} else {
				clearSelected();
			}
		}
	}
}

class Move{
	public int x, y;
	public int dir;
	public int size;
	public Move(int x, int y, int size, int dir){
		this.x = x;
		this.y = y;
		this.size = size;
		this.dir = dir;
	}
}
