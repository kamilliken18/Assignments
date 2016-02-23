import java.util.*;

public class  Card {

// sets up structure for the cards in the deck 
	public final static int spades = 1, hearts =2, diamonds = 3, clubs =4;
	
	public final static int ace= 1, jack = 11, queen =12, king = 13;

// private keeps the value, score and suit of each card from corruption, but allows access to their value
	private int suit; 
	private int cardValue; 
	private int cardScore; 
	
// sets up strings for concatenation 
	public String specificSuit;
	public String specificCardValue;
	public int specificCardScore; 
	
	
// pulls a random suit and card value for each card dealt 	
	public Card () {
		Random suitDeal = new Random(); 
		int suitSelected = suitDeal.nextInt(4)+1; 
		Random cardDeal = new Random();
		int cardSelected = cardDeal.nextInt(13)+1;
		suit = suitSelected; 
		cardValue = cardSelected; 
		setCardAsString();
	}
// reutrns values for suit,score and card value while variables protected by being private
	public int getSuit() {
		return suit;
	}
	public int getValue() {
		return cardValue; 
	}

// changes the card value into a string
	public void setCardAsString() {
		switch (suit) {
			case 1: specificSuit = "s";
					break;
			case 2: specificSuit = "h";
					break;
			case 3: specificSuit = "d";
					break;
			case 4: specificSuit = "c";
					break;
			default: specificSuit = " HOW DID YOU GET AN INVALID SUIT? ";
					break; 
		}
		switch (cardValue) {
			case 1: specificCardValue = "A";
					specificCardScore = 11;
					break;
			case 2: specificCardValue = "2";
					specificCardScore = 2;
					break;
			case 3: specificCardValue = "3";
					specificCardScore = 3;
					break;
			case 4: specificCardValue = "4";
					specificCardScore = 4;
					break;
			case 5: specificCardValue = "5";
					specificCardScore = 5;
					break;
			case 6: specificCardValue = "6";
					specificCardScore = 6;
					break;
			case 7: specificCardValue = "7";
					specificCardScore = 7;
					break;
			case 8: specificCardValue = "8";
					specificCardScore = 8;
					break;
			case 9: specificCardValue = "9";
					specificCardScore = 9;
					break;
			case 10: specificCardValue = "T";
					specificCardScore = 10;
					break;
			case 11: specificCardValue = "J";
					specificCardScore = 10;
					break;
			case 12: specificCardValue = "Q";
					specificCardScore = 10;
					break;
			case 13: specificCardValue = "K";
					specificCardScore = 10;
					break;
			default: specificCardValue = " HOW DID YOU GET AN INVALID CARD VALUE? ";
					break;
		}
	}
//makes card a String able to be represented in the main class
	public String toString() {
		return specificCardValue+specificSuit; 
	}
//makes the card Score available to be main class
// having a "specific card score" allows for the ace to be counted as 11 points when the random number generated for the face value is an Ace
//the scoring method then accounts for the ace conversion to a 1 if need be. 
	public int getCardScore() {
		return specificCardScore;
	}
 }