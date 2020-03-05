import java.util.Random;

public class Piece {
    protected int row;
    protected int col;
    //color is randomly generated total of 5 colors (0-4)
    protected int color;
    private static int TOTAL_NUM_COLOR = 5;
    public boolean settled;

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
    public void fall(){

    }

    //
    public void update(){

    }

    //updates the piece when landed, changes settled
    public void landed(){

    }

    public int getRow(){return row;}
    public int getCol(){return col;}
    public int getColor(){return color;}
    public int generateColor() {
        Random rand = new Random();
        // Generate random integers in range 0 to 4 (inclusive)
        return rand.nextInt(TOTAL_NUM_COLOR);
    }

}
