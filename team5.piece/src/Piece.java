import java.util.Random;

public class Piece {
    protected int row;
    protected int col;
    //color is randomly generated total of 5 colors (1-5)
    protected int color;
    private static int TOTAL_NUM_COLOR = 5;
    public boolean settled = false;

    public Piece(int row, int col){
        this.row = row;
        this.col = col;
        this.color = generateColor();
    }

    public Piece(int col){
        this.row = 0;
        this.col = col;
        this.color = generateColor();
    }

    //makes the piece fall down one
    //does NOT check if the piece has landed
    public void fall(){
        row++;
    }

    //updates the piece when landed, changes settled
    public void landed(){
        settled = true;
    }

    public int getRow(){return row;}
    public int getCol(){return col;}
    public int getColor(){return color;}
    public int generateColor() {
        Random rand = new Random();
        // Generate random integers in range 1 to 5 (inclusive)
        return rand.nextInt(TOTAL_NUM_COLOR)+1;
    }

}
