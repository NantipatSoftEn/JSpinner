package lib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.ws.handler.MessageContext.Scope;

public class HighScoreUtility {
	public static boolean highscoreok = true;
	private static ArrayList<BestScoreRecord> scoreList = new ArrayList<BestScoreRecord>();
	private static String direc = "Highscore.jsp";

	/*
	 * high score format (highscore.txt):
	 * 
	 * [filename] [bestmove]
	 * 
	 * example:
	 * 
	 * 3x3.txt 20 4x4.txt 53 etc.
	 */

	private static List<BestScoreRecord> bestScoreRecord = new ArrayList<BestScoreRecord>();

	static {
		// load (and decode?) file into the list
		Scanner in = null;
		try {
			in = new Scanner(new File(direc));
			while (in.hasNext()) {
				scoreList.add(new BestScoreRecord(in.nextLine()));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			highscoreok = false;
			e.printStackTrace();
		}

	}

	public static int getBestScore(String levelFileDirectory) {
		// return best score of the level
		for (BestScoreRecord x : scoreList) {
			if (x.getLevelFileDirectory().equals(levelFileDirectory)) {
				return x.getBestScore();
			}
		}
		return -1;
	}

	public static void updateBestScore(String levelFileDirectory, int sc) {
		// if there are no record, add new line of high score record at the end
		for (BestScoreRecord x : scoreList) {
			if (x.getLevelFileDirectory().equals(levelFileDirectory)) {
				x.setBest(sc);
			}
		}
	}

	private static void writeBestScoreRecord() {
		// write (and encode?) best score file
		// execute when exit program
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(direc), "utf-8"));
			while (!scoreList.isEmpty()) {
				writer.write(scoreList.remove(0).toString() + "\n");
			}

		} catch (IOException ex) {
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
			}
		}
	}

}

// private void updateHighScore() {
// Writer writer = null;
//
// try {
// writer = new BufferedWriter(new OutputStreamWriter(
// new FileOutputStream(directory), "utf-8"));
// writer.write("" + board.length + " " + board[0].length + "\n"
// /*+ moveLimit*/ + "\n" + move.size());
// for(int i =0 ;i<board[0].length;i++)
// for(int j=0;j<board.length;j++)
// {
// if(board[i][j].isATile())
// writer.write("S ");
// else
// writer.write("- ");
// }
// } catch (IOException ex) {
// } finally {
// try {
// writer.close();
// } catch (Exception ex) {
// }
// }
// }

class BestScoreRecord {
	protected String levelFileDirectory;
	protected int bestScore;

	public BestScoreRecord(String x) {
		String[] rec = x.split(" ");
		this.levelFileDirectory = rec[0];
		this.bestScore = Integer.parseInt(rec[1]);
	}

	protected void setBest(int in) {
		bestScore = in;
	}

	public String toString() {
		return levelFileDirectory + " " + bestScore;
	}

	public int getBestScore() {
		return bestScore;
	}

	public String getLevelFileDirectory() {
		return levelFileDirectory;
	}
}
