import java.util.*;
import java.io.*;

public class Quiz {

	public static void main(String[] args) throws IOException {
	File f = new File("katiequiz.txt");
	Scanner sc = new Scanner(f);
	ArrayList<Question> quizQuestions = new ArrayList<>();
	int numQuestions = 0;
	int userScore = 0;
	double quizScore = 0;
	//Loads quiz onto program and places each question into Question objects and those Objects into the ArrayList 
		if (f.exists()) {
			while (sc.hasNextLine()) {
			quizQuestions.add(new Question(sc));
			numQuestions++;
			}
		} 
		else {
			System.out.println("Please acquire katiequiz.txt on your computer to continue. ");
		}
	sc.close();
	//Start of Quiz Module 
	Scanner userInput = new Scanner(System.in); 
	System.out.println("Welcome to Katie's Quiz. Good luck!"); 
	boolean invalidInput = false;
	//Reads each question out to user and gets user's answer
		for (int j = 0; j < quizQuestions.size(); j++) { 	
			System.out.println(quizQuestions.get(j).getQuestion());
			int numAnswers = quizQuestions.get(j).getNumAnswers();
			quizQuestions.get(j).setNumTries();
			//Reads out all possible answers for each question
			for (int k = 0; k < numAnswers; k++) {
				System.out.println("Answer "+k+": "+quizQuestions.get(j).getAnswers(k)); 	
			}
			//Gets user response and checks for invalid input
			do {
			System.out.println("Your answer: "); 
			//Catch block for invalid input for questions 
				try { 
					int userInt = Integer.parseInt(userInput.nextLine());
					quizQuestions.get(j).setUserAnswer(userInt);
					if (userInt > numAnswers-1) {
						invalidInput = true;
					}
					else if (userInt < 0) {
						invalidInput = true; 
					}
					else {
						invalidInput = false;
					}
				} 
				catch(Exception e) {
					invalidInput = true;
				}
			} while (invalidInput);
		}
	userInput.close();
	System.out.println("Here are your results!");
	//Reads each question out again with correct answer and whether user was correct
		for (int j = 0; j < quizQuestions.size(); j++) {  
			System.out.println(quizQuestions.get(j).getQuestion());
			int tempCorrect = quizQuestions.get(j).getCorrectAnswer();
			System.out.println("Correct Answer: "+tempCorrect);
			int tempUserA = quizQuestions.get(j).getUserAnswer();
			System.out.println("Your Answer: "+tempUserA);
			//checks each answer for 
				if (tempCorrect == tempUserA) {
					System.out.println("You were Correct!");
					userScore++;
					quizQuestions.get(j).setCorrectTries();
				}
				else {
					System.out.println("You were Incorrect.");
				}
		}
	//Gets overall user score
	System.out.println("Number Correct: "+userScore);
	System.out.println("Number Incorrect: "+(numQuestions-userScore));
	quizScore = ( ((double) userScore) / numQuestions )* 100;
	System.out.println("Your score is: "+ quizScore +" percent.");
	//Reads out the Cumulative Statistics for each question
	System.out.println("Here are the cumulative statistics for this quiz! ");
		for (int j = 0; j < quizQuestions.size(); j++) {  
			System.out.println(quizQuestions.get(j).getQuestion());
			int numTries = quizQuestions.get(j).getNumTries();
			System.out.println("Times Tried: "+numTries);
			int numCorrect = quizQuestions.get(j).getCorrectTries();
			System.out.println("Times Correct: "+numCorrect);
			double percent = quizQuestions.get(j).getStatistic();
			System.out.println("Percent Correct: "+percent+"%");
		}
	//Calculates easiest and hardest questions
			Question min = null;
			Question max = null;
		for (int j = 0; j < quizQuestions.size(); j++) { 
			if (min == null) {
				min = quizQuestions.get(j);
			}
			if (max == null) {
				max = quizQuestions.get(j);
			}
			if (quizQuestions.get(j).getStatistic() < min.getStatistic()) {
				min = quizQuestions.get(j);
			}
			else if (quizQuestions.get(j).getStatistic() > max.getStatistic()) {
				max = quizQuestions.get(j);
			}
		}
	//Reads out easiest and hardest questions 
	System.out.println("Easiest question: "+max.getQuestion());
		int numTries = max.getNumTries();
		System.out.println("Times Tried: "+numTries);
		int numCorrect = max.getCorrectTries();
		System.out.println("Times Correct: "+numCorrect);
		double percent = max.getStatistic();
		System.out.println("Percent Correct: "+percent+"%");
	System.out.println("Hardest question: "+min.getQuestion());
		numTries = min.getNumTries();
		System.out.println("Times Tried: "+numTries);
		numCorrect = min.getCorrectTries();
		System.out.println("Times Correct: "+numCorrect);
		percent = min.getStatistic();
		System.out.println("Percent Correct: "+percent+"%");
	//Writes everything back out to the file at the end of the quiz
	PrintWriter revision = new PrintWriter(f); 
		for (int j = 0; j < quizQuestions.size(); j++) { 
			revision.println(quizQuestions.get(j).getQuestion());
			revision.println(quizQuestions.get(j).getNumAnswers());
				for (int k = 0; k < quizQuestions.get(j).getNumAnswers(); k++) {
					revision.println(quizQuestions.get(j).getAnswers(k)); 	
				}
			revision.println(quizQuestions.get(j).getCorrectAnswer());
			revision.println(quizQuestions.get(j).getNumTries());
			revision.println(quizQuestions.get(j).getCorrectTries());
		}
	revision.close();
	}
}