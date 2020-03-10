import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class GameManager {
    private final static String PlayerFile = "team5.system\\src\\UserNames.txt";
    private ArrayList<Player> Players;

    public GameManager() {
        String os = System.getProperty("os.name");
        System.out.println(os);
        this.Players = new ArrayList<Player>();

    }


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
        } catch (FileNotFoundException f) {
            PrintWriter out;
            try {
                out = new PrintWriter(PlayerFile);
                out.print("");
                out.close();
            } catch (FileNotFoundException e) { e.printStackTrace(); }
        }
    }

    public static void main(String[] args) {
        GameManager a = new GameManager();
        a.loadPlayers();
    }
}