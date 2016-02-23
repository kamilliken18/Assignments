import java.io.*; 
import java.util.*;

public class Player {
	
	private double money;
	private int handsPlayed;
	private int handsWon;
		
	public void loadReturningPlayerInformation(String userName) throws IOException {
		String fileName = userName + ".txt";
		File f = new File(fileName);
		Scanner sc = new Scanner(f);
		double m = sc.nextFloat();
		int ph = sc.nextInt();
		int w = sc.nextInt();
		setPlayerMoney(m); 
		setPlayerHands(ph);
		setPlayerWins(w);
		sc.close();

	}
	public void savePlayerInformation (String userName) throws IOException {
		PrintWriter saveForNextTime = new PrintWriter(userName + ".txt");
		saveForNextTime.println(getPlayerMoney());
		saveForNextTime.println(getPlayerHands());
		saveForNextTime.println(getPlayerWins());
		saveForNextTime.close();
	}
	public double getPlayerMoney() {
		return money;
	}
	public void setPlayerMoney(double daMoney) {
		money = daMoney; 
	}
	public int getPlayerHands() {
		return handsPlayed;
	}
	public void setPlayerHands(int daHandsPlayed) { 
		handsPlayed = daHandsPlayed;
	}
	public int getPlayerWins() {
		return handsWon;
	}
	public void setPlayerWins(int daHandsWon) {
		handsWon = daHandsWon; 
	}
}
