import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class CarGame implements Display {
	private String[][] board;
	private final static int trackLength = 11;
	private final static int trackWidth = 11;
	
	private PlayerCar p = new PlayerCar();
	private ArrayList<BotCar> botCar = new ArrayList<BotCar>();
	private ArrayList<Integer> scores = new ArrayList<Integer>();
	private int currentScore = 0;
	private static boolean gameOver = false;
	private static boolean programOver = false;
	
	public static void main(String[] args) throws IOException {
		String move, choice;
		boolean wrongMove = false;
		Scanner input = new Scanner(System.in);
		CarGame game = new CarGame();
		
		while (!programOver) {
			System.out.println("Welcome to Horizon Track Game!");
			game.gameSetUp();
			game.currentScore = 0;
			while (!gameOver) {
				
				do {
					System.out.println();
					game.drawBoard();
					System.out.print("\nEnter your move(a/s/d): ");
					move = input.nextLine().toLowerCase();
					try {
						if (move.equals("a")) {
							wrongMove = false;
							int tempX = game.p.getXPos();
							int tempY = game.p.getYPos();

							game.p.decreaseXPos();
							game.board[game.p.getYPos()][game.p.getXPos()] = game.p.toString();
							game.board[tempY][tempX] = " ";
						} else if (move.equals("d")) {
							wrongMove = false;
							int tempX = game.p.getXPos();
							int tempY = game.p.getYPos();

							game.p.increaseXPos();
							game.board[game.p.getYPos()][game.p.getXPos()] = game.p.toString();
							game.board[tempY][tempX] = " ";
						} else if (move.equals("s")) {
							wrongMove = false;
						} else {
							System.out.println("\nWrong move. Please try again.");
							wrongMove = true;
						}
					} catch(ArrayIndexOutOfBoundsException e) {
						System.out.println("\nYour move is out of the track. Please try again.");
						if(move.equals("a")) {
							game.p.increaseXPos();
						} else {
							game.p.decreaseXPos();
						}
						wrongMove = true;
					}
				} while (wrongMove);
				
				PlayerCar.increaseNumTurns();
				game.moveBotCars();
				game.spawnBotCars();
				game.placeBotCars();
				
				if(game.checkPlayerCollisionBot()) {
					game.board[game.p.getYPos()][game.p.getXPos()] = "X";
					gameOver = true;
				}
				
			}
			game.drawBoard();
			game.scores.add(game.currentScore);
			System.out.println("\nGame Over! Your car crashed.");
			System.out.print("Play again(y/n): ");
			choice = input.nextLine().toLowerCase();
			
			System.out.println();
			if(choice.equals("y")) {
				programOver = false;
				gameOver = false;
			} else {
				programOver = true;
			}
		}
		
		game.writeScores();
		
		System.out.println("Your scores: ");
		game.printScores();
		
		game.bubbleSort();
		
		System.out.println("\nYour highest score: " + game.scores.get(0));
		
		System.out.print("\nGoodbye!");
	}
	
	public void initializeBoard() {
		board = new String[trackLength][trackWidth];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (j % 2 == 0) {
					board[i][j] = "|";
				} else {
					board[i][j] = " ";
				}
			}
		}
	}

	public void drawBoard() {
		for (int i = 0; i < board.length - 1; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	public void gameSetUp() {
		p = new PlayerCar();
		initializeBoard();
		board[p.getYPos()][p.getXPos()] = p.toString();
		botCar = new ArrayList<BotCar>();
		spawnBotCars();
		placeBotCars();
	}
	
	public void spawnBotCars() {
		BotCar b1 = new BotCar();
        botCar.add(b1);
	}
	
	public void placeBotCars() {
		for (int i = 0; i < botCar.size(); i++) {
			board[botCar.get(i).getYPos()][botCar.get(i).getXPos()] = botCar.get(i).toString();
		}
	}
	
	public void moveBotCars() {
		for (int i = 0; i < botCar.size(); i++) {
			board[botCar.get(i).getYPos()][botCar.get(i).getXPos()] = " ";
			botCar.get(i).moveBots();	
		}
		checkBotCollisionBoard();
		placeBotCars();
	}
	
	public void checkBotCollisionBoard() {
		for (int i = 0; i < botCar.size(); i++) {
			if(botCar.get(i).getYPos() == 10) {
				botCar.remove(i);
				currentScore++;
			}
		}
	}
	
	public boolean checkPlayerCollisionBot() {
		for (int i = 0; i < botCar.size(); i++) {
			if(p.getYPos() == botCar.get(i).getYPos() && p.getXPos() == botCar.get(i).getXPos()) {
				return true;
			}
		}
		return false;
	}
	
	public void bubbleSort() {
		int temp;
		for (int i = 0; i <= scores.size() - 2; i++) {
			for (int j = 0; j <= scores.size() - i - 2; j++) {
				if (scores.get(j) < scores.get(j + 1)) {
					temp = scores.get(j);
					scores.set(j, scores.get(j + 1));
					scores.set(j + 1, temp);
				}
			}
		}
		return;
	}
	
	@Override
	public void printScores() {
		for (int i = 0; i < scores.size(); i++) {
			System.out.println("Attempt #" + (i + 1) + ": " + scores.get(i));
		}
	}
	
	@Override
	public void writeScores() throws IOException {
		File outFile = new File("scores.txt");
		PrintWriter out = new PrintWriter(outFile);

		for(int i = 0; i < scores.size(); i++) {
			out.println(scores.get(i));
		}

		out.close();
	}
}	