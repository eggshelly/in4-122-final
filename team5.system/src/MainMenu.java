//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Scanner;

public class MainMenu {
    public static String[] games = new String[]{"Columns", "Bejeweled"};
    private static String[] players = new String[2];
    private static int numPlayers = 0;
//    private static Game game; <- CHANGE TO THIS AFTER IMPLEMENTING COLUMNS AS GAME
    private static ColumnsGUI game; // TEMP!

    public MainMenu() {
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to The Game Environment");
        System.out.println("Please select a game from the list of games.");
        String[] var5;
        int var4 = (var5 = games).length;

        String chosenGame;
        for(int var3 = 0; var3 < var4; ++var3) {
            chosenGame = var5[var3];
            System.out.println(chosenGame);
        }

        System.out.print("Game: ");
        chosenGame = scan.nextLine();
        boolean validGame = false;

        String gameStr;
        while(!validGame) {
            String[] var7;
            int var6 = (var7 = games).length;

            for(int var10 = 0; var10 < var6; ++var10) {
                gameStr = var7[var10];
                if (chosenGame.equals(gameStr)) {
                    validGame = true;
                }
            }

            if (!validGame) {
                System.out.println(chosenGame + " is not a valid game.");
                System.out.print("Game: ");
                chosenGame = scan.nextLine();
            }
        }

        System.out.println("You have chosen " + chosenGame);
        System.out.println("Enter your profile name.");
        System.out.print("Name: ");
        gameStr = scan.nextLine();
        players[0] = gameStr;
        System.out.println("Will you have another player? Enter the name if yes. Otherwise, leave it blank and press enter.");
        System.out.print("Name: ");
        String secondPlayer = scan.nextLine();
        if (secondPlayer.equals("")) {
            System.out.println("Starting a single player game.");
            numPlayers = 1;
            game = new ColumnsGUI();
            game.run();
        } else {
            System.out.println(secondPlayer + " has joined. Starting two-player game.");
            players[1] = secondPlayer;
            numPlayers = 2;
        }

        scan.close();
    }
}
