/**
 * @author Thanawit Prasongpongchai (5631045321)
 * @author Phatrasek Jirabovonvisut (5630469621)
 */

package logic;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

import javax.rmi.CORBA.Util;
import javax.swing.JOptionPane;

import ui.Clickable;
import ui.GameAnimation;
import ui.GameScreen;
import ui.HelpPanel;
import ui.IRenderable;
import ui.winpanel.WinPanel;
import lib.*;

public class Board implements IUpdatable {
	public static final int DEFAULT_SHUFFLE = 2000;
	public static final int CW = -1;
	public static final int CCW = 1;

	private Tile[][] board;
	private int x, y, width, height, selected = 0;
	private int tileSize;
	private int bestScore;
	private PlayerStatus player;
	private Point forFlip[] = new Point[2];
	private List<Move> move;
	private Point moveCenter = new Point(0, 0);
	private boolean isPlaying = true;
	private boolean isCheated = false;
	private String directory;
	private int currentFrame = 0;
	private boolean effectPerFormed = true;
	private int animatedShuffle = 0;
	private boolean repeatMoveEnebled = true;

	public Board(int width, int height) {
		board = new Tile[width][height];
		player = new PlayerStatus(this);
		move = new ArrayList<Move>();
		initiateBoard();
		adjustCenter();
	}
	
	public Board (Board in){
		this.directory = in.directory;
		String tileInfo;
		int boardX = in.board.length;
		int boardY = in.board[0].length;
		this.player = new PlayerStatus(this);
//		this.bestScore = in.bestScore;
//		use this vvv
		this.bestScore = HighScoreUtility.getBestScore(in.directory);
		board = new Tile[boardX][boardY];
		int k = 1;
		for (int i = 0; i < boardY; i++) {
			for (int j = 0; j < boardX; j++) {
				this.board[x][y]= new SimpleTile(in.board[x][y],this);
			}
		}
		adjustCenter();
		// for (int i = 0; i < board.length; i++)
		// for (int j = 0; j < board[0].length; j++) {
		// board[i][j].setCurrentLocation(i, j);
		// }
		move = new ArrayList<Move>();
		
	}

	public Board(String directory) throws LevelFormatException, IOException {
		try {
			Scanner in;
//			in = new Scanner(new File(Board.class.getClassLoader().getResource(directory).toURI()));
//			in = new Scanner(new File(directory));
			try{
				if(directory.startsWith("/res"))
					in = new Scanner(getClass().getResourceAsStream(directory));
				else
					in = new Scanner(new File(directory));
			} catch (NullPointerException e){
				throw new IOException();
			}
			String tileInfo;
			int boardX = in.nextInt();
			int boardY = in.nextInt();
			this.player = new PlayerStatus(this);
//			this.bestScore = in.nextInt();
//			use this vvv
			this.bestScore = HighScoreUtility.getBestScore(directory);
			
			board = new Tile[boardX][boardY];
			
			int k = 1;
			for (int i = 0; i < boardY; i++) {
				for (int j = 0; j < boardX; j++) {
					if(in.hasNext())
						tileInfo = in.next();
					else{
						throw new LevelFormatException(directory);
					}
					
					if (tileInfo.substring(0, 1).equalsIgnoreCase("S"))
						board[j][i] = new SimpleTile(k++, this, j, i);
					else if (tileInfo.substring(0, 1).equalsIgnoreCase("Z"))
						board[j][i] = new SleepyTile(k++, this, j, i);
					else if (tileInfo.substring(0, 1).equalsIgnoreCase("A"))
						board[j][i] = new AngryTile(k++, this, j, i);
					else if (tileInfo.substring(0, 1).equalsIgnoreCase("-"))
						board[j][i] = new SimpleTile(Tile.NOT_A_TILE, this, j, i);
					else{
						System.out.println(tileInfo.substring(0, 1));
						throw new LevelFormatException(directory);
						
					}
				}
			}
			adjustCenter();
			move = new ArrayList<Move>();
		}
		catch (NumberFormatException e) {
			throw new LevelFormatException(directory);
		} 
		catch (InputMismatchException e) {
			throw new LevelFormatException(directory);
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

		// System.out.println(x + " " + y + " " + width + " " + height + " "
		// + tileSize + " " + Config.tileGutter);
	}

	public void newGame() {
		player = new PlayerStatus(this);
		move = new ArrayList<Move>();
		this.shuffle(Board.DEFAULT_SHUFFLE);
		isPlaying = true;
		isCheated = false;
		clearSelected();
		setEnables();
		WinPanel.setVisible(false);
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

	public int getMoveCenterX() {
		return (int) moveCenter.getX();
	}

	public int getMoveCenterY() {
		return (int) moveCenter.getY();
	}

	public Point getTileLocation(int i, int j) {
		int tx = i * (tileSize + Config.tileGutter) + x;
		int ty = j * (tileSize + Config.tileGutter) + y;
		return new Point(tx, ty);
	}

	public Tile getTileAt(int i, int j) {
		return board[i][j];
	}

	public PlayerStatus getPlayer() {
		return player;
	}

	public int getBestScore() {
		return bestScore;
	}

	public int getFlipX() {
		return (int) forFlip[0].getX();
	}

	public int getFlipY() {
		return (int) forFlip[0].getY();
	}

	public int getFlipSize() {
		return Math.abs(forFlip[0].x - forFlip[1].x);
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public Move getLatestMove() {
		try{
			return move.get(move.size() - 1);
		} catch(IndexOutOfBoundsException e){
			return null;
		}
	}
	
	public void setRepeatMoveEnebled(boolean repeatMoveEnebled) {
		this.repeatMoveEnebled = repeatMoveEnebled;
	}
	
	public void clearSelected() {
		selected = 0;
		for (int j = 0; j < board[0].length; j++) {
			for (int i = 0; i < board.length; i++) {
				board[i][j].setSelected(false);
			}
		}
	}
	
	public void flip(int x, int y, int size, int direction, boolean isPlaying){
		if(isValidMove(x, y, x + size, y + size, isPlaying)){
			//set move center for drawing animation
			int cx = (board[x][y].getDrawX() + board[x + size][y].getDrawX() + tileSize) / 2;
			int cy = (board[x][y].getDrawY() + board[x][y + size].getDrawY() + tileSize) / 2;
			moveCenter = new Point(cx, cy);
			
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
			
			if(isPlaying){
				AudioUtility.flipSound.play();
				move.add(new Move(x, y, size, direction));
	//			System.out.println("("+x+","+y+")"+" "+size+"--"+direction);
				player.move();
				currentFrame = 0;
				for(int j = 0; j <= size; j++){
					for(int i = 0; i <= size; i++){
						board[x + i][y + j].setMoving(true);
					}
				}
				
//				for (int j = 0; j < board[0].length; j++) {
//					for (int i = 0; i < board.length; i++) {
//						board[i][j].performEffect();
//					}
//				}
				
			} else {
				setBoard();
			}
		}
	}

	public void undo() {
		int i = move.size() - 1;
		try {
			Move latest = move.get(i);
			move.remove(i);
			flip(latest.x, latest.y, latest.size, latest.dir * -1, false);
			player.decreaseMove();
			for (int j = 0; j < board[0].length; j++) {
				for (int k = 0; k < board.length; k++) {
					board[k][j].undoEffect();
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "No moves left to undo!");
		}
	}
	
	public boolean isValidMove(int x1, int y1, int x2, int y2, boolean playing){

		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);
		if (x2 < x1) {
			int tmp = x1;
			x1 = x2;
			x2 = tmp;
		}
		if (y2 < y1) {
			int tmp = y1;
			y1 = y2;
			y2 = tmp;
		}
		if ((dx != dy) || (dx == 0 && dy == 0)) {
			return false;
		}
		try {
			for (int j = y1; j <= y2; j++) {
				for (int i = x1; i <= x2; i++) {
					if (!board[i][j].isATile()) {
						return false;
					}
					if(playing && ((board[i][j] instanceof SleepyTile) && ((SleepyTile) board[i][j]).isLocked())){
						return false;
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return true;
	}

	public void shuffle(int times) {
//		boolean isReset = true;
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

			if (dir == 0) {
				size = Utility.random(0, Math.min(board[0].length - x, y));
				y -= size;
			} else if (dir == 1) {
				size = Utility.random(0,
						Math.min(board[0].length - x, board.length - y));
			} else if (dir == 2) {
				size = Utility.random(0, Math.min(x, board.length - y));
				x -= size;
			} else if (dir == 3) {
				size = Utility.random(0, Math.min(x, y));
				x -= size;
				y -= size;
			} else
				size = 0;

			int rotation = Utility.random(0, 2) == 0 ? CW : CCW;
			flip(x, y, size, rotation, false);

		}
	}
	
	public void animatedShuffle(int times){
		animatedShuffle = times;
		repeatMoveEnebled = false;
		player.setLockMove(true);
	}
	
	public void randomMove(){
		move = new ArrayList<Move>();
		int x, y, dir, size;
		do{
			x = Utility.random(0, board.length);
			y = Utility.random(0, board[0].length);
			dir = Utility.random(0, 4);
			// 0 for NE
			// 1 for SE
			// 2 for SW
			// 3 for NW
			if (dir == 0) {
				size = Utility.random(0, Math.min(board[0].length - x, y));
				y -= size;
			} else if (dir == 1) {
				size = Utility.random(0,
						Math.min(board[0].length - x, board.length - y));
			} else if (dir == 2) {
				size = Utility.random(0, Math.min(x, board.length - y));
				x -= size;
			} else if (dir == 3) {
				size = Utility.random(0, Math.min(x, y));
				x -= size;
				y -= size;
			} else
				size = 0;
		} while(!isValidMove(x, y, x + size, y + size, false));
		int rotation = Utility.random(0, 2) == 0 ? CW : CCW;
		flip(x, y, size, rotation, true);
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

	public boolean isWin() {
		boolean check = true;
		for (int j = 0; j < board[0].length; j++) {
			for (int i = 0; i < board.length; i++) {
				if (!board[i][j].isCorrect()) {
					check = false;
				}
			}
		}
		return check;
	}

	public void update() {
		// for each game loop...
		
		//FOR THE SAKE OF DEBUGGING
		if(InputUtility.getKeyPressed(KeyEvent.VK_Z))
			if(InputUtility.getKeyPressed(KeyEvent.VK_X))
				if(InputUtility.getKeyPressed(KeyEvent.VK_C))
						cheat();
								
		//update location (if not playing animation)
		
		if(isPlaying && !HelpPanel.isVisible()){
			if(currentFrame >= Config.animationFrameCount){
				setBoard();
				if(!effectPerFormed && animatedShuffle == 0){
					for (int j = 0; j < board[0].length; j++) {
						for (int i = 0; i < board.length; i++) {
							board[i][j].performEffect();
						}
					}
					effectPerFormed = true;
				}
				if(animatedShuffle > 0){
					randomMove();
					animatedShuffle--;
					move.clear();
				}else{
					player.setLockMove(false);
				}
			} else {
				effectPerFormed = false;
				currentFrame++;
			}

			boolean same = selected == 2
					&& forFlip[0].getX() == forFlip[1].getX()
					&& forFlip[0].getY() == forFlip[1].getY();
			if (move.size() > 0 && selected == 0 && animatedShuffle == 0) {
				Move latest = move.get(move.size() - 1);

				if (InputUtility.getKeyTriggered(KeyEvent.VK_LEFT) && repeatMoveEnebled) {
					setBoard();
					flip(latest.x, latest.y, latest.size, Board.CCW, true);
				}

				if (InputUtility.getKeyTriggered(KeyEvent.VK_RIGHT) && repeatMoveEnebled) {
					setBoard();
					flip(latest.x, latest.y, latest.size, Board.CW, true);
				}
			}
			if (selected < 2) {
				Clickable.cwButton.setVisible(false);
				Clickable.ccwButton.setVisible(false);
				for (int i = 0; i < board.length; i++) {
					for (int j = 0; j < board[0].length; j++) {
						if (Utility.isPointOnTile(
								InputUtility.getPickedPoint(), this, i, j)) {
							board[i][j].setMouseOn(true);
							if (InputUtility.isPicking()
									|| (InputUtility.isMouseReleased() && selected > 0)) {
								if (InputUtility.isMouseReleased()
										&& selected > 0
										&& forFlip[0].equals(new Point(i, j))) {
									continue;
								}
								board[i][j].setSelected(true);
								forFlip[selected] = new Point(i, j);
								selected++;
							}
						} else {
							board[i][j].setMouseOn(false);
						}
					}
				}
				if (same)
					selected = 1;
			} else if(selected == 2) {
				if(isValidMove((int) forFlip[0].getX(), (int) forFlip[0].getY(), (int) forFlip[1].getX(), (int) forFlip[1].getY(), true)){
					int x1 = (int) forFlip[0].getX();
					int y1 = (int) forFlip[0].getY();
					int x2 = (int) forFlip[1].getX();
					int y2 = (int) forFlip[1].getY();
					forFlip[0].setLocation(Math.min(x1, x2), Math.min(y1, y2));
					forFlip[1].setLocation(Math.max(x1, x2), Math.max(y1, y2));
					Clickable.cwButton.setVisible(true);
					Clickable.ccwButton.setVisible(true);
					if (InputUtility.getKeyTriggered(KeyEvent.VK_LEFT)
							|| InputUtility.getKeyTriggered(KeyEvent.VK_A)) {
						Clickable.ccwButton.onClickAction();
					}
					if (InputUtility.getKeyTriggered(KeyEvent.VK_RIGHT)
							|| InputUtility.getKeyTriggered(KeyEvent.VK_D)) {
						Clickable.cwButton.onClickAction();
					}
				} else {
					clearSelected();
				}
				if (InputUtility.isPicking()
						&& !Clickable.ccwButton.isMouseOn()
						&& !Clickable.cwButton.isMouseOn())
					clearSelected();
			}

			// set enabled for tiles
			setEnables();
			isPlaying = !isWin();

		} else {
			setBoard();
			if (isWin() || isCheated)
				WinPanel.setVisible(true);

			if (isWin()) {
				if (bestScore > move.size())
					HighScoreUtility.updateBestScore(directory);

			}

		}
	}

	public void setEnables() {
		if (selected == 0) {
			for (int j = 0; j < board[0].length; j++) {
				for (int i = 0; i < board.length; i++) {
					board[i][j].setEnabled(true);
				}
			}
		} else if (selected == 1) {
			for (int j = 0; j < board[0].length; j++) {
				for (int i = 0; i < board.length; i++) {
					if(isValidMove((int)forFlip[0].getX(), (int)forFlip[0].getY(), i, j, true)){
						board[i][j].setEnabled(true);
					} else {
						board[i][j].setEnabled(false);
					}
				}
			}
		} else if (selected == 2) {
			for (int j = 0; j < board[0].length; j++) {
				for (int i = 0; i < board.length; i++) {
					if (forFlip[0].getX() <= i && i <= forFlip[1].getX()
							&& forFlip[0].getY() <= j && j <= forFlip[1].getY()) {
						board[i][j].setEnabled(true);
					} else {
						board[i][j].setEnabled(false);
					}
				}
			}
		}
	}

	public void setBoard() {
		for (int j = 0; j < board[0].length; j++) {
			for (int i = 0; i < board.length; i++) {
				board[i][j].setCurrentLocation(i, j);
				board[i][j].setDrawLocation();
				board[i][j].setMoving(false);
			}
		}
	}

	private void cheat() {
		isPlaying = false;
		isCheated = true;
	}
}

class Move {
	public int x, y;
	public int dir;
	public int size;

	public Move(int x, int y, int size, int dir) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.dir = dir;
	}
}
