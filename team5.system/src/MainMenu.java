import java.util.Scanner;

public class MainMenu {
    public static String[] games = {"Columns", "Bejeweled"};
    private static String[] players = new String[2];
    private static int numPlayers = 0;
    private static Game game;

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
        players[0] = playerName;
        System.out.println("Will you have another player? Enter the name if yes. Otherwise, leave it blank and press enter.");
        System.out.print("Name: ");
        String secondPlayer = scan.nextLine();
        if(secondPlayer.equals("")){
            System.out.println("Starting a single player game.");
            numPlayers = 1;
            game = new Columns();
            game.run();
        }
        else{
            System.out.println(secondPlayer + " has joined. Starting two-player game.");
            players[1] = secondPlayer;
            numPlayers = 2;
        }
        scan.close();

    }
}
