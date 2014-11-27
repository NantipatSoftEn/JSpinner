/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package util;

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

import javax.swing.JOptionPane;
import javax.xml.ws.handler.MessageContext.Scope;

public class HighScoreUtility {
	public static boolean highscoreok = true;
	private static ArrayList<BestScoreRecord> scoreList = new ArrayList<BestScoreRecord>();
	private static String direc = ".highscore.";

	/*
	 * high score format (highscore.txt):
	 * 
	 * [filename] [bestmove]
	 * 
	 * example:
	 * 
	 * 3x3.txt 20 4x4.txt 53 etc.
	 */

	static {
		// load (and decode?) file into the list
		Scanner in = null;
		try {
			in = new Scanner(new File(direc));
			while (in.hasNext()) {
				String s = in.nextLine();
				String[] rec = s.trim().split(" ");
				String level = rec[0];
				int best = Integer.parseInt(rec[1]);
				scoreList.add(new BestScoreRecord(level, best));	
			}
		} catch (FileNotFoundException e) {
			highscoreok = false;
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Invalid Highscore file format.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Invalid Highscore file format.", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public static int getBestScore(String levelFileDirectory) {
		// return best score of the level
		for (BestScoreRecord x : scoreList) {
//			System.out.println(x.getLevelFileDirectory() + " vs " + levelFileDirectory);
			if (x.getLevelFileDirectory().equals(levelFileDirectory)) {
				return x.getBestScore();
			}
		}

		return -1;
	}

	public static void updateBestScore(String levelFileDirectory, int sc) {
		// if there are no record, add new line of high score record at the end
		if(scoreList != null)
			for (BestScoreRecord x : scoreList) {
				try{
					if (x.getLevelFileDirectory().equals(levelFileDirectory)) {
						x.setBest(sc);
						return;
					}
				} catch(NullPointerException e){
					continue;
				}
			}
		scoreList.add(new BestScoreRecord(levelFileDirectory, sc));
	}

	public static void writeBestScoreRecord() {
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
	
	public static void resetBestScores(){
		scoreList.clear();
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(direc), "utf-8"));
			writer.write("");
		} catch (IOException ex) {
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
			}
		}
	}

}


class BestScoreRecord {
	protected String levelFileDirectory;
	protected int bestScore;

	public BestScoreRecord(String x) {
		String[] rec = x.split(" ");
		this.levelFileDirectory = rec[0];
		this.bestScore = Integer.parseInt(rec[1]);
	}
	
	public BestScoreRecord(String level, int best) {
		this.levelFileDirectory = level;
		this.bestScore = best;
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
