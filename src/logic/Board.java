/**
 * @author Thanawit Prasongpongchai (5631045321)
 * @author Phatrasek Jirabovonvisut (5630xxxx21)
 */

package logic;

import java.awt.*;

import javax.rmi.CORBA.Util;

import lib.*;

public class Board implements IRenderable, IUpdatable {
	public static final int DEFAULT_SHUFFLE = 1000;

	private Tile[][] board;
	private int x, y, width, height, selected = 0;
	private int tileSize;
	private Point forFlip[] = new Point[2];

	public Board(int width, int height) {
		board = new Tile[width][height];
		initiateBoard();
	}

	// public Board(File f){
	// File format:
	// w h
	// x x x x x
	// x x x x x
	// (board of width w and height h)
	// where x can be ... "-" means no tile, "S(number)" means SimpleTile etc.
	// Scanner in = new Scanner(f);
	// String board = in.next();
	// }

	public void initiateBoard() {
		int k = 1;
		for (int j = 0; j < board[0].length; j++) {
			for (int i = 0; i < board.length; i++) {
				board[i][j] = new SimpleTile(k, this);
				k++;
			}
		}

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

	public void flip(int x1, int y1, int x2, int y2) {
		if (x1 == x2) {
			if (y2 < y1) {
				int tmp = y1;
				y1 = y2;
				y2 = tmp;
			}
			for (int i = y1; i < (y1 + y2+1) / 2; i++) {
				Tile tmp = board[x1][i];
				board[x1][i] = board[x1][y2 - (i - y1)];
				board[x1][y2 - (i - y1)] = tmp;
			}
		} else if (y1 == y2) {
			if (x2 < x1) {
				int tmp = x1;
				x1 = x2;
				x2 = tmp;
			}
			for (int i = x1; i < (x1 + x2+1) / 2; i++) {
				Tile tmp = null;
				tmp = board[i][y1];
				board[i][y1] = board[x2 - (i - x1)][y1];
				board[x2 - (i - x1)][y1] = tmp;

			}
		}else{
			if (y2 < y1) {
				int tmp = y1;
				y1 = y2;
				y2 = tmp;
			}
			if (x2 < x1) {
				int tmp = x1;
				x1 = x2;
				x2 = tmp;
			}
			for (int i = x1 ; i<=x2;i++){
				flip(i,y1,i,y2);
			}
			for (int i = y1;i<=y2;i++){
				flip(x1,i,x2,i);
				
			}
			
		}
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				board[i][j].setCurrentLocation(i, j);
			}
	}

	public void shuffle(int times) {
		// still not available with non-rectangle boards;
		// and also non-linear flips.
		for (int i = 0; i < times; i++) {
			int direction = Utility.random(0, 2);
			if (direction == 0) {
				// horizontal
				int x = Utility.random(0, board.length);
				int ya = Utility.random(0, board[x].length);
				int yb = Utility.random(0, board[x].length);
				flip(x, ya, x, yb);
			} else if (direction == 1) {
				// vertical
				int y = Utility.random(0, board[0].length);
				int xa = Utility.random(0, board.length);
				int xb = Utility.random(0, board.length);
				flip(xa, y, xb, y);
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

	// public static void main(String[] args) {
	// Board a = new Board(6, 6);
	// System.out.println(a);
	// a.shuffle(Board.DEFAULT_SHUFFLE);
	// System.out.println(a);
	// }

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

	public void update() {
		// for each game loop...
		// shuffle(1); //just for testing screen update
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				if (Utility.isPointOnTile(InputUtility.getPickedPoint(), this,
						i, j)) {
					if (InputUtility.isPicking()) {
						if (!board[i][j].isSelected()) {
							board[i][j].setSelected(true);
							forFlip[selected] = new Point(i, j);
							selected++;

						} else {
							board[i][j].setSelected(false);
							if (board[(int) forFlip[0].getX()][(int) forFlip[0]
									.getY()] == board[i][j])
								forFlip[0] = forFlip[1];
							selected--;
						}
						if (selected == 2) {
							selected = 0;
							flip((int) forFlip[0].getX(),
									(int) forFlip[0].getY(),
									(int) forFlip[1].getX(),
									(int) forFlip[1].getY());
							board[(int) forFlip[0].getX()][(int) forFlip[0]
									.getY()].setSelected(false);
							board[(int) forFlip[1].getX()][(int) forFlip[1]
									.getY()].setSelected(false);
						}
					}
				}
			}
	}
}
