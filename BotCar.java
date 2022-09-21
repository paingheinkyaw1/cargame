import java.util.Random;

public class BotCar extends Car {
	private final static int trackWidth = 11;
	private static int numBotCars;
	private int botNumber;
	
	public BotCar() {
    	Random random = new Random();
		super.setYPos(0);
		
		do {
			super.setXPos(random.nextInt(trackWidth));
		} while ((super.getXPos() % 2 == 0));
    	
    	numBotCars++;
    	botNumber = numBotCars;
	}

	public void moveBots() {
		super.increaseYPos();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (!(o instanceof BotCar)) {
			return false;
		} else {
			BotCar b = (BotCar) o;
			return (super.getXPos() == b.getXPos()) && (super.getYPos() == b.getYPos());
		}
	}
	
	@Override
	public String toString() {
		return "V";
	}
}