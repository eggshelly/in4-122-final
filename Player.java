public class Player {
	private String UserName = "UserName";
	private int NumGames = 0;
	
	public Player(String userName) {
		this.UserName = userName;
		this.NumGames = 0;
	}
	
	public Player(String userName, int numGames) {
		this(userName);
		this.NumGames = numGames;
	}
	
	public String getUserName() { return this.UserName; }
	public int getNumGames() { return this.NumGames; }
	public String toString() { return String.format("%1$, %2$s\n", getUserName(), getNumGames()); }
	public void increment() { ++this.NumGames; }
}
