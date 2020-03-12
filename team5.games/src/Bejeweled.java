import java.util.ArrayList;
import java.util.Random;

public class Bejeweled {
    private int rows;
    private int cols;
    private boolean isMatched;
    private ArrayList<ArrayList<Integer>> matched;
    private int[][] board;
    private static final int EMPTY = 0;
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
        for (int x = 0; x < rows - 2; x++)
        {
            for (int y = 0; y < cols - 2; y++)
            {
                // row match
                if (board[x][y] == board[x+1][y] && board[x+1][y] == board[x+2][y]) // 3 matched pieces
                {
                    isMatched = true;
                    ArrayList<Integer> matchPair1 = new ArrayList<Integer>();
                    matchPair1.add(x);
                    matchPair1.add(y);
                    matched.add(matchPair1);

                    ArrayList<Integer> matchPair2 = new ArrayList<Integer>();
                    matchPair2.add(x+1);
                    matchPair2.add(y);
                    matched.add(matchPair2);

                    ArrayList<Integer> matchPair3 = new ArrayList<Integer>();
                    matchPair3.add(x+2);
                    matchPair3.add(y);
                    matched.add(matchPair3);
                }

                // column match
                if (board[x][y] == board[x][y+1] && board[x][y+1] == board[x][y+2]) // 3 matched pieces
                {
                    isMatched = true;
                    ArrayList<Integer> matchPair4 = new ArrayList<Integer>();
                    matchPair4.add(x);
                    matchPair4.add(y);
                    matched.add(matchPair4);

                    ArrayList<Integer> matchPair5 = new ArrayList<Integer>();
                    matchPair5.add(x);
                    matchPair5.add(y + 1);
                    matched.add(matchPair5);

                    ArrayList<Integer> matchPair6 = new ArrayList<Integer>();
                    matchPair6.add(x);
                    matchPair6.add(y + 2);
                    matched.add(matchPair6);
                }
            }
        }
        if (matched.size() != 0)
        {
            deleteMatched();
        }
    }

    public void deleteMatched()
    {
        for (ArrayList<Integer> match: matched)
        {
            int mRow = match.get(0);
            int mCol = match.get(1);
            for (int i = 0; i < rows; i++)
            {
                for (int j = 0; j < cols; j++)
                {
                    if (i == mRow && j == mCol)
                    {
                        board[i][j] = EMPTY;
                    }
                    //movePiecesDown();
                    //freezeFaller();
                    isMatched = false;
                }

            }
            //matched.clear();
        }
    }

    private boolean isValidRow(int row)
    {
        return (row >= 0 && row < rows);
    }

    private boolean isValidCol(int col)
    {
        return (col >= 0 && col < cols);
    }

    //generates new pieces to fill the board
    public void generatePiece()
    {

        for (int i = rows-1; i > -1; i++)
        {
            for (int j = cols-1; j > -1; j++)
            {
                if(board[i][j] == EMPTY)
                {
                    if (isValidRow(i-1)){
                        if (board[i-1][j] != EMPTY)
                        {
                            board[i][j] = board[i-1][j];
                            board[i-1][j] = EMPTY;
                        }
                        else{
                            board[i][j] = r.nextInt(4) + 1;
                        }
                    }
                    else{
                        board[i][j] = r.nextInt(4) + 1;
                    }
                }
            }
        }
    }

}
