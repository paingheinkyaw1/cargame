public class PlayerCar extends Car {
	
	private static int numTurns;
	
	public PlayerCar() {
		super(9, 5);
		numTurns = 0;
	}
	
	public static void increaseNumTurns() {
		numTurns++;
	}
	
	public static int getNumTurns() {
		return numTurns;
	}
	
	@Override
	public String toString() {
		return "A";
	}
}