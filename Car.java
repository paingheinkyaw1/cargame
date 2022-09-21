/**
 * Team Project CIS 36B
 * @author Paing Hein Kyaw
 * Abstract Class Car
 */
public abstract class Car {
	private int xPos;
	private int yPos;
	
	public Car() {
		xPos = -1;
		yPos = -1;
	}
	
	public Car(int yPos, int xPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void setXPos(int xPos) {
		this.xPos = xPos;
	}
	
	public void setYPos(int yPos) {
		this.yPos = yPos;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public void increaseXPos() {
		xPos++;
		xPos++;
	}
	
	public void increaseYPos() {
		yPos++;
	}
	
	public void decreaseXPos() {
		xPos--;
		xPos--;
	}
	
	public void decreaseYPos() {
		yPos--;
	}
}