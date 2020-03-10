import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class GameManager {
    private final static String PlayerFile = "UserNames.txt";
    private ArrayList<Player> Players;


    private void loadPlayers() {
        String[] info;
        try {
            String line = "";
            FileReader reader = new FileReader(PlayerFile);
            Scanner in = new Scanner(reader);
            Players.clear();
            while (in.hasNextLine()) {
                line = in.nextLine();
                info = line.split(",", 2);
                String userName = info[0];
                int numGames = Integer.parseInt(info[1]);
                Players.add(new Player(userName, numGames));
            }
            in.close();
        } catch (Exception e) {

        }
    }
}