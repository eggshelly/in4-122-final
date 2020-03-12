import java.util.ArrayList;
import java.util.Random;

public class Bejeweled {
    private int rows;
    private int cols;
    private boolean isMatched;
    private ArrayList<ArrayList<Integer>> matched;
    private int[][] board;
    private int score;

    // makeBoard(Board)
    // makeMove(keyStroke)
    // checkMove()
    // updateBoard()
    // printBoard()
    // generatePiece()

    public Bejeweled()
    {
        rows = 8;
        cols = 8;
        isMatched = false;
        matched = new ArrayList<ArrayList<Integer>>();
        board = new int[rows][cols];
        score = 0;
        initializeBoard();
    }

    public void initializeBoard()
    {
        Random r = new Random();

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                board[i][j] = r.nextInt(4) + 1;
            }
        }

        checkBoard();
    }

    public void checkBoard()
    {
        isMatched = false;
        for (int x = 0; x < boardWidth - 2; x++)
        {
            for (int y = 0; y < boardHeight - 2; y++)
            {
                // row match
                if (board[x][y] == board[x+1][y] && board[x+1][y] == board[x+2][y]) // 3 matched pieces
                {
                    isMatched = true;
                    ArrayList<Integer> matchPair1 = new ArrayList<Integer>();
                    matchPair.add(x);
                    matchPair.add(y);
                    matched.add(matchPair1);

                    ArrayList<Integer> matchPair2 = new ArrayList<Integer>();
                    matchPair.add(x+1);
                    matchPair.add(y);
                    matched.add(matchPair2);

                    ArrayList<Integer> matchPair3 = new ArrayList<Integer>();
                    matchPair.add(x+2);
                    matchPair.add(y);
                    matched.add(matchPair3);

                    removeMatch(board[x][y], board[x+1][y], board[x+2][y]);
                }

                // column match
                if (board[x][y] == board[x][y+1] && board[x][y+1] == board[x][y+2]) // 3 matched pieces
                {
                    isMatched = true;
                    removeMatch(board[x][y], board[x][y+1], board[x][y+2]);
                }
            }
        }
    }

    public void checkMove()
    {

    }

    //generates new pieces to fill the board
    public void generatePiece(){
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if(board[i[j]])
                board[i][j] = r.nextInt(4) + 1;
            }
        }
    }

}
