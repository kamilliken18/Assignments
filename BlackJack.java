import java.io.*;
import java.util.*;
import java.util.Scanner;

public class BlackJack {
	
	public static void main(String[] args) throws IOException {
	
	String userName; 
	Player newHighRoller;
	boolean dealHand, userExists;
	int playingGame, handScore, dealerHandScore;
	double cashOnHand, bet;
	
	// gets user input and verifies if they're a new player or returning player. If returning, pulls up all their known information.
	Scanner userInput = new Scanner(System.in);
	System.out.println("Welcome to Casino Black Jack! Please enter your username: "); 
	userName = userInput.nextLine();
	File UserInformation = new File(userName+".txt");
	userExists = UserInformation.exists();
		
		if (userExists) {
			newHighRoller = new Player();
			newHighRoller.loadReturningPlayerInformation(userName);
		}
		else { //makes new player if player doesn't exist already
			newHighRoller = new Player();
			newHighRoller.setPlayerMoney(100.0);
			newHighRoller.setPlayerHands(0);
			newHighRoller.setPlayerWins(0);
		}
	dealHand = true;
	do { 
		// prelude to every hand, lets user know current stats and asks to play another hand. Then asks for bet.
		System.out.println("Current Information");
		System.out.println(userName); 
		System.out.printf("Current Money: %16.2f%n", newHighRoller.getPlayerMoney()); 
		System.out.printf("Current Hands Played: %9d%n", + newHighRoller.getPlayerHands());
		System.out.printf("Current Hands Won: %12d%n", + newHighRoller.getPlayerWins());
		System.out.println("Do you want to play? Please enter 1 for yes and 0 for no. ");
		playingGame = userInput.nextInt(); 
			if (playingGame == 1) {
				dealHand = true;
			}
			else if (playingGame == 0) {
				dealHand = false;
				break;
			}
			else { //corrects for invalid input that is an integer.
				System.out.println("That is not a valid input. Do you want to play? Please re-enter a 1 for yes and a 0 for no. ");
			}
		// allows user to bet on hand
		cashOnHand = newHighRoller.getPlayerMoney();
		System.out.println("How much would you like to bet on this hand? ");
		bet = userInput.nextDouble();
			if (bet < 0.01 || bet > cashOnHand) {
				System.out.println("That is not a valid input, please re-enter your desired bet.");
				bet = userInput.nextDouble();
			} 
		// calls to playing method to play hand, doesn't let dealer play if the user busts, just gives them the best score
		handScore = actualPlay(); 
		if (handScore <= 21) {
			dealerHandScore = dealerPlay();
			}
		else {
			dealerHandScore = 21;
		}
		int numHands = newHighRoller.getPlayerHands() + 1;
		newHighRoller.setPlayerHands(numHands);
		// figures out who won and how; alters the money after the hand is played 
			if (handScore == 21 && dealerHandScore != 21 ) { //blackjack
				bet += (bet*1.5);
				cashOnHand +=bet;
				newHighRoller.setPlayerMoney(cashOnHand);
				int numHandsWon = newHighRoller.getPlayerWins() + 1;
				newHighRoller.setPlayerWins(numHandsWon);
				System.out.println("Congratulations! You won.");
			}
			else if (dealerHandScore > handScore && dealerHandScore <= 21 ) {//lose to the dealer
				cashOnHand -= bet; 
				newHighRoller.setPlayerMoney(cashOnHand);
				System.out.println("Sorry... The house won.");
			}
			else if (handScore > dealerHandScore && handScore <= 21) { //beat the dealer
				bet += bet; 
				cashOnHand += bet; 
				newHighRoller.setPlayerMoney(cashOnHand);
				int numHandsWon = newHighRoller.getPlayerWins() + 1;
				newHighRoller.setPlayerWins(numHandsWon);
				System.out.println("Congratulations! You won.");
			}
			else if (handScore == dealerHandScore) { //push
				bet = 0;
				newHighRoller.setPlayerMoney(cashOnHand);
				System.out.println("It's a push! Sorry...");
			}
			else if (handScore > 21 && dealerHandScore == 21) { //player busts
				cashOnHand -= bet; 
				newHighRoller.setPlayerMoney(cashOnHand);
				System.out.println("Sorry... you busted.");
			}
			else if (dealerHandScore > 21 ) { //dealer busts
				bet += bet; 
				cashOnHand += bet; 
				newHighRoller.setPlayerMoney(cashOnHand);
				int numHandsWon = newHighRoller.getPlayerWins() + 1;
				newHighRoller.setPlayerWins(numHandsWon);
				System.out.println("Congratulations! You won.");
			}

	} while (dealHand);
		System.out.println("Thanks for playing, we'll save your information for next time!");
		newHighRoller.savePlayerInformation(userName);
	
	} // end of main method
	
	// method for game play by user 
	public static int actualPlay() {
		boolean dealCards = true;
		boolean firstCard = true;
		int total = 0;
		int numberAces = 0;
		int outPutScore = 0;
		Scanner hitOrStay = new Scanner(System.in);
		String hOS; 
		//loop for the hand 
			do {
			Card cardOnHand = new Card();
			cardOnHand.getCardScore();
			cardOnHand.toString();
			System.out.println("Your card is "+ cardOnHand);
			total += cardOnHand.getCardScore(); 
				if (cardOnHand.getCardScore() == 11) {
					numberAces+=1;
				}
			if (firstCard) { //allows user to begin with two cards like a real black jack game. 
				Card cardOnHandTwo = new Card();
				cardOnHandTwo.getCardScore();
				cardOnHandTwo.toString();
				System.out.println("Your card is "+ cardOnHandTwo);
				total += cardOnHandTwo.getCardScore(); 
					if (cardOnHandTwo.getCardScore() == 11) {
						numberAces+=1;
					}
				firstCard = false; 
				}
			outPutScore = getScore(total, numberAces); 
			if (outPutScore == 21) {
				System.out.println("BLACKJACK!");
				break; //if you get a 21 in two cards this allows for a proper black jack
				}
			else {
				System.out.println("Your current score is " + outPutScore);
						if (outPutScore > 21) {
							System.out.println("BUST!");
							break;
						}
				System.out.println("Do you want to hit or stay?");
				hOS = hitOrStay.nextLine(); 
				if (hOS.equals("hit") || hOS.equals("h")|| hOS.equals("H") || hOS.equals("Hit")) {
						dealCards = true; 
					}
				else if (hOS.equals("stay") || hOS.equals("s")|| hOS.equals("S") || hOS.equals("Stay")) {
						dealCards = false; 
						break;
					}
				else { // correction for invalid input
						System.out.println("That is not a valid entry. Please indicate whether you want to hit or stay.");
						hOS = hitOrStay.nextLine(); 
					}
				}
			} while (dealCards);
			System.out.println("The final score for this hand is "+ outPutScore);
			return outPutScore;
	}
// method for dealer to play 
	public static int dealerPlay() {
		boolean dealCards = true;
		boolean firstCard = true;
		int total = 0;
		int numberAces = 0;
		int outPutScore = 0;
		//loop for the hand 
			do {
			Card cardOnHand = new Card();
			cardOnHand.getCardScore();
			cardOnHand.toString();
			System.out.println("The first dealer card is"+ cardOnHand);
			total += cardOnHand.getCardScore(); 
				if (cardOnHand.getCardScore() == 11) {
					numberAces+=1;
				}
			if (firstCard) { // loop lets dealer flip their first two cards at one time. 
				Card cardOnHandTwo = new Card();
				cardOnHandTwo.getCardScore();
				cardOnHandTwo.toString();
				System.out.println("The second dealer card is"+ cardOnHandTwo);
				total += cardOnHandTwo.getCardScore(); 
					if (cardOnHandTwo.getCardScore() == 11) {
						numberAces+=1;
					}
				firstCard = false; 
				}
			outPutScore = getScore(total, numberAces); 
// the following are all rules for the house to play by when they run through their turn.
			if (outPutScore > 21 ) { 
					dealCards = false; 
					System.out.println("The house busted!");
				}
			else if (outPutScore >= 18 || outPutScore <= 21) {
					dealCards = false; 
					System.out.println("The house will stay.");
				}
			else if (outPutScore == 17 && numberAces>= 1){
					dealCards = true;
					System.out.println("The house will hit.");
				}
			else if (outPutScore == 17 && numberAces == 0) {
					dealCards = false; 
					System.out.println("The house will stay.");
				}
			else {
					dealCards = true;
					System.out.println("The house will hit.");
				}
			} while (dealCards);
			System.out.println("The final score for the dealer's hand is "+ outPutScore);
			return outPutScore;
	}
//method for scoring of each hand
	public static int getScore(int points, int numAces) {
			
		int score = 0;

		if (numAces == 0 || points <= 21) {
			score = points;
		} 
		else {
			
			int bestScore = -1;
			int potentialScore = points;

			for (int j = 0; j <= numAces; j++) {
			potentialScore = (points - (10 * j));
			
				if (potentialScore > bestScore && potentialScore <= 21) {
					bestScore = potentialScore;
				}
			}
			
			if (bestScore == -1) {
			score = potentialScore;
			} 
			else {
			score = bestScore;
			}
		}
		return score;
	}
}