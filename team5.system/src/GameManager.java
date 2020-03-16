import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class GameManager {
    private final String PlayerFile;  // = "team5.system\\src\\UserNames.txt";
    private ArrayList<Player> Players = new ArrayList<Player>();

    public GameManager() {
        String os = System.getProperty("os.name");
        if( os.contains("Mac") ) { this.PlayerFile = "team5.system/src/UserNames.txt"; }
        else if (os.contains("Windows") ) { this.PlayerFile = "team5.system\\src\\UserNames.txt"; }
        else { this.PlayerFile = "UserNames.txt"; }
        loadPlayers();
    }


    private void loadPlayers() {
        String[] info;
        try {
            String line = "";
            FileReader reader = new FileReader(PlayerFile);
            Scanner in = new Scanner(reader);
            ArrayList<Player> players = new ArrayList<Player>();
            while (in.hasNextLine()) {
                line = in.nextLine();
                info = line.split(",", 2);
                String userName = info[0];
                int numGames = Integer.parseInt(info[1]);
                players.add(new Player(userName, numGames));
            }
            in.close();
            Players.clear();
            Players.addAll(players);
        } catch (FileNotFoundException f) {
            PrintWriter out;
            try {
                out = new PrintWriter(PlayerFile);
                out.print("");
                out.close();
            } catch (FileNotFoundException e) { e.printStackTrace(); }
        }
    }

    public void savePlayers() {
        PrintWriter out;
        try {
            out = new PrintWriter(PlayerFile);
            String output = "";
            for(Player p: Players){ output += p.toString(); }
            out.print(output.trim());
            out.close();
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    public void increment(String other) {
        for(Player a: Players) {
            if(a.equals(other)) {
                a.increment();
                break;
            }
        }
        savePlayers();
    }
    
    public void increment(Player other) {
        for(Player a: Players) {
            if(a.equals(other)) {
                a.increment();
                break;
            }
        }
        savePlayers();
    }

    public boolean contains (String player) {
        for(Player p : Players ) { if( p.getUserName().equals(player)) { return true; } }
        return false;
    }

    public void addPlayer(String player) {
        Players.add(new Player(player,0));
        savePlayers();
    }

    public static void main(String[] args) {
        GameManager a = new GameManager();
    }
}