package logic;

public interface Tile {
	public int NOT_A_BLOCK = -1; // may check if the tile is null.
	
	public int getNumber();
	public void setNumber(int number);
	public abstract void performEffect();
	
	public String toString();
}
