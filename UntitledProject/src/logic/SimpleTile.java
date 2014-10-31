package logic;

public class SimpleTile implements Tile{
	private int number;
	
	public SimpleTile(int number){
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	@Override
	public void performEffect() {
		return;		
	}
	
	@Override
	public String toString() {
		return "[S" + number + "]";
	}
}
