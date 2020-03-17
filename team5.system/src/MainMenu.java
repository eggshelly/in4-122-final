import java.util.Scanner;

public class MainMenu {
    public static String[] games = {"Columns", "Bejeweled"};
    private static String[] players = new String[2];
    private static int numPlayers = 0;
    private static Game game1;
    private static Game game2;
    private static GameManager gameManager = new GameManager();

    public static void resolveScores(int p1, int p2) {
        System.out.println("Final Scores:");
        System.out.println(players[0] + ": " + p1 + " vs " + players[1] + ": " + p2);
        if (p1 > p2) {
            System.out.println(players[0] + " Wins!");
        } else if (p2 > p1) {
            System.out.println(players[1] + " Wins!");
        } else {
            System.out.println("It's a Tie!");
        }
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to The Game Environment");
        System.out.println("Please select a game from the list of games.");
        for(String game: games){
            System.out.println(game);
        }
        System.out.print("Game: ");
        String chosenGame = scan.nextLine();
        boolean validGame = false;
        while(!validGame){
            for(String game:games){
                if(chosenGame.equals(game)){
                    validGame = true;
                }
            }
            if(!validGame){
                System.out.println(chosenGame + " is not a valid game.");
                System.out.print("Game: ");
                chosenGame = scan.nextLine();
            }
        }
        System.out.println("You have chosen " + chosenGame);
        System.out.println("Enter your profile name.");
        System.out.print("Name: ");
        String playerName = scan.nextLine();
        if (!(gameManager.contains(playerName))) {
            gameManager.addPlayer(playerName);
        }
        players[0] = playerName;
        System.out.println("Will you have another player? Enter the name if yes. Otherwise, leave it blank and press enter.");
        System.out.print("Name: ");
        String secondPlayer = scan.nextLine();
        if(secondPlayer.equals("")){
            System.out.println("Starting a single player game.");
            numPlayers = 1;
            if (chosenGame.equals("Columns"))
            {
                game1 = new Columns(playerName);
                game1.run();
            }
            else if (chosenGame.equals("Bejeweled"))
            {
                game1 = new Bejeweled(playerName);
                game1.run();
                System.out.println("Final Score: " + game1.getScore());
            }
        }
        else{
            if (!(gameManager.contains(secondPlayer))) {
                gameManager.addPlayer(secondPlayer);
            }
            System.out.println(secondPlayer + " has joined. Starting two-player game.");
            players[1] = secondPlayer;
            numPlayers = 2;
            if (chosenGame.equals("Columns"))
            {
                game1 = new Columns(playerName);
                game1.playConsoleGame();
                game2 = new Columns(secondPlayer);
                game2.playConsoleGame();
            } else if (chosenGame.equals("Bejeweled"))
            {
                game1 = new Bejeweled(playerName);
                game1.run();
                game2 = new Bejeweled(secondPlayer);
                game2.run();
            }
            int p1Score = game1.getScore();
            int p2Score = game2.getScore();
            resolveScores(p1Score,p2Score);
        }
        System.out.println("Updating Player(s) Info");
        for (String pname : players) {
            gameManager.increment(pname);
        }
        System.out.println("Player(s) Info Updated.");
        scan.close();

    }
}
