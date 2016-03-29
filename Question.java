import java.util.*;
import java.io.*;

public class Question{

String st;
int numAnswers, correctAnswer, numTries, numCorrect, userAnswer; 
double percent; 
ArrayList<String> answers = new ArrayList<>();


	public Question(Scanner sc) throws IOException{ //CONSTRUCTOR!!
		st = sc.nextLine(); 
		numAnswers = Integer.parseInt(sc.nextLine().trim()); 
		for (int i = 0; i < numAnswers; i++) {
			String temp = sc.nextLine();
			answers.add(temp);
		} 
		correctAnswer = Integer.parseInt(sc.nextLine());
		numTries = Integer.parseInt(sc.nextLine());
		numCorrect = Integer.parseInt(sc.nextLine());
	}
	public String getQuestion() {
		return st;
	}
	public String getAnswers(int x) {
		return answers.get(x);
	}
	public int getNumAnswers() {
		return numAnswers; 
	}
	public void setUserAnswer(int b) {
		userAnswer = b;
	}
	public int getUserAnswer() {
		return userAnswer;
	}
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	public boolean checkAnswer() {
		if (userAnswer == correctAnswer) {
			return true;
		}
		else {
			return false; 
		}
	}
	public void setNumTries() {
		numTries++;
	}
	public int getNumTries() {
		return numTries;
	}
	public void setCorrectTries() {
		numCorrect++;
	}
	public int getCorrectTries() {
		return numCorrect; 
	}
	public double getStatistic() {
		percent = (((double)numCorrect) / numTries )* 100;
		return percent;
	}
}