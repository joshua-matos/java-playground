//import java.util.*;
//
////Build a hash map -----
//
////Store the user in the hash map ------
////track the previous user -
////check if there is a previous user
////(store who played last, so we can check the hashmap with name)
////increment count for each user correctly
////what happens when enter in the n or no ----
//
//
//public class ApplicationBeta {
//	java.time.LocalDateTime now = java.time.LocalDateTime.now();
//	static ApplicationBeta applicationBeta = new ApplicationBeta(true);
//	ArrayList<String> stringArray = new ArrayList<>();
//	StringBuilder appendHistory = new StringBuilder();
//	Random randomNumber = new Random();
//	int count = 0;
//	int randomGeneratedNumber;
//	String userName;
//	boolean keepPlayingTheGame;
//	boolean resetApplicatiton;
//	String wouldYouLikeToPlayAgain;
//	String userNumberGuessed;
//	int lowestAmountOfGuesses;
//
//	HashMap<String, Integer> leaderBoards = new HashMap<>();
//	String previousUser = "";
//
//	//Constructor with default of playing the game
//	ApplicationBeta(boolean keepPlayingTheGame) {
//		this.keepPlayingTheGame = keepPlayingTheGame;
//	}
//
//	//Main program
//	public static void main(String[] args) {
//
//		if (applicationBeta.getPlayingStatus()) {
//			//application.startTheGame();
//		}
//
//	}
//
//	//Set the condition for playing the game.
//	public void setKeepPlaying(boolean keepPlayingTheGame) {
//		this.keepPlayingTheGame = keepPlayingTheGame;
//		this.wouldYouLikeToPlayAgain = "";
//	}
//
//	//get the boolean set by setKeepPlaying
//	public boolean getPlayingStatus() {
//		return keepPlayingTheGame;
//	}
//
//	public void startTheGame() {
//
//		System.out.println("Ready player one: ( Guess a number between 1 and 11 )");
//		randomGeneratedNumber = randomNumber.nextInt(10) + 1;
//
//		//Total guesses at game start
//		int totalGuesses = 1;
//
//		/*
//		set how many times we call
//		if we have not called the count is 0,
//		and we prompt for user guess and username
//		*/
//		if (applicationBeta.count <= 0) {
//			System.out.print("Enter your guess:");
//			userNumberGuessed = String.valueOf(Prompt.present());
//			System.out.print("Enter your name:");
//			applicationBeta.userName = Prompt.present();
//			applicationBeta.count++;
//
//
//			//store user
//			if (!leaderBoards.containsKey(userName)) {
//				leaderBoards.put(applicationBeta.userName, applicationBeta.count);
//			}
//
//
//		} else {
//			//the user has
//			System.out.print("Enter your guess:");
//			userNumberGuessed = String.valueOf(Prompt.present());
//		}
//
//
//		while (applicationBeta.getPlayingStatus()) {
//
//			if (Integer.parseInt(userNumberGuessed) == randomGeneratedNumber && randomGeneratedNumber != 0) {
//				//User guessed on the first try
//				if (totalGuesses == 1) {
//					System.out.printf("Congrats! %s You guessed correctly, the number was %s", applicationBeta.userName,randomGeneratedNumber);
//
//					lowestAmountOfGuesses = 1;
//					appendHistory.delete(0, appendHistory.length());
//					stringArray.clear();
//
//				} else {
//					int currentAmountOfGuesses = stringArray.size();
//					if(lowestAmountOfGuesses > currentAmountOfGuesses) {
//						lowestAmountOfGuesses = currentAmountOfGuesses;
//					}
//
//					StringBuilder joinString = new StringBuilder();
//					for (String eachstring : stringArray) {
//						joinString.append(appendHistory.append(eachstring).append(","));
//					}
//
//
//					String commaseparatedlist = appendHistory.toString();
//					// remove last comma
//					if (commaseparatedlist.length() > 0)
//						commaseparatedlist
//								= commaseparatedlist.substring(
//								0, commaseparatedlist.length() - 1);
//
//
//					System.out.println("Congrats!" + applicationBeta.userName + " You guessed correctly," +
//							" the number was " + randomGeneratedNumber + "! Previous guess(es): " + commaseparatedlist);
//
//					appendHistory.delete(0, appendHistory.length());
//					stringArray.clear();
//				}
//
//
//				//Prompt would you like to play again
//				System.out.println("Do you want to play again? (y/n)");
//
//				//play again prompt
//				wouldYouLikeToPlayAgain = Prompt.present();
//
//				if (wouldYouLikeToPlayAgain.toLowerCase(Locale.ROOT).equals("y") || wouldYouLikeToPlayAgain.toLowerCase(Locale.ROOT).equals("yes")) {
//
//					applicationBeta.setKeepPlaying(true);
//					//clear the array and history
//					appendHistory.delete(0, appendHistory.length());
//					stringArray.clear();
//
//
//					System.out.println("New game started!");
//					System.out.print("Enter your guess:");
//					//ask for user input
//					applicationBeta.userNumberGuessed = Prompt.present();
//					//generate a new user number
//					randomGeneratedNumber = randomNumber.nextInt(10) + 1;
//
//				} else {
//					//the user does not want to play again, set boolean for while loop to false
//					applicationBeta.setKeepPlaying(false);
//				}
//
//			}
//
//			//tell the user the number is too high
//			else if (Integer.parseInt(userNumberGuessed) < randomGeneratedNumber) {
//				System.out.println("Sorry," + applicationBeta.userName + "! You guessed incorrectly. Try guessing a higher number!");
//				totalGuesses++;
//				leaderBoards.put(applicationBeta.userName, totalGuesses);
//				stringArray.add(userNumberGuessed);
//				System.out.print("Enter your guess:");
//				userNumberGuessed = Prompt.present();
//			}
//			//too low
//			else if (Integer.parseInt(userNumberGuessed) > randomGeneratedNumber) {
//				System.out.println("Not correct, try guessing a lower number!");
//				totalGuesses++;
//				leaderBoards.put(applicationBeta.userName, totalGuesses);
//				stringArray.add(userNumberGuessed);
//				System.out.print("Enter your guess:");
//				userNumberGuessed = Prompt.present();
//			}
//
//
//		}
//
//
//	}
//
//}
//
//
//
///*
//
//
//    FOR REFERENCE, USE BELOW CODE TO COLLECT INPUT (THE USER'S GUESS)
//    ---
//
//    int userGuess; // the user's guess
//    System.out.println("Guess: "); // message prompting the user to input a number in the terminal
//    userGuess = Prompt.present(); // collect the user's input (their guess) as userGuess
//
//
// */