/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package util;

import java.util.ArrayList;
import java.util.List;

public class HighScoreUtility {
	
	class BestScoreRecord{
		protected String levelFileDirectory;
		protected int bestScore;
		public BestScoreRecord(String levelFileDirectory, int bestScore) {
			this.levelFileDirectory = levelFileDirectory;
			this.bestScore = bestScore;
		}
		public String toString(){
			return levelFileDirectory + " " + bestScore;
		}
		public int getBestScore() {
			return bestScore;
		}
		public String getLevelFileDirectory() {
			return levelFileDirectory;
		}
	}
	
	/*
	 * high score format (highscore.txt):
	 * 
	 * [filename] [bestmove]
	 * 
	 * example:
	 * 
	 * 3x3.txt 20
	 * 4x4.txt 53
	 * etc.
	 */
	
	private static List<BestScoreRecord> bestScoreRecord = new ArrayList<BestScoreRecord>();
	
	static{
		//load (and decode?) file into the list
	}
	
	public static int getBestScore(String levelFileDirectory){
		//return best score of the level
		return 0;
	}
	
	public static void updateBestScore(String levelFileDirectory){
		//if there are no record, add new line of high score record at the end
	}
	
	private static String writeBestScoreRecord(){
		//write (and encode?) best score file
		//execute when exit program
		return null;
	}
	
//	private void updateHighScore() {
//		Writer writer = null;
//
//		try {
//			writer = new BufferedWriter(new OutputStreamWriter(
//					new FileOutputStream(directory), "utf-8"));
//			writer.write("" + board.length + " " + board[0].length + "\n"
//					/*+ moveLimit*/ + "\n" + move.size());
//			for(int i =0 ;i<board[0].length;i++)
//				for(int j=0;j<board.length;j++)
//				{
//					if(board[i][j].isATile())
//						writer.write("S ");
//					else
//						writer.write("- ");
//				}
//		} catch (IOException ex) {
//		} finally {
//			try {
//				writer.close();
//			} catch (Exception ex) {
//			}
//		}
//	}
}
