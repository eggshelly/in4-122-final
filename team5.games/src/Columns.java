public class Columns {
    private int rows;
    private int cols;
    private boolean changed;
    private boolean is_matched;
//    private Piece faller;
    private int[][] board;
    private static final int EMPTY = 0;

    public Columns()
    {
        rows = 4;
        cols = 3;
        changed = false;
        is_matched = false;
        board = new int[rows][cols];
        initializeBoard();
    }

    public void printBoard()
    {
        int num_spaces = 0;
        String dash = "-";
        String chars = "   ";
        for (int i = 0; i < rows; i++)
        {
            System.out.print("|");
            for (int j = 0; j < cols; j++)
            {
                num_spaces = 3 * cols;
                if (board[i][j] == EMPTY)
                {
                    System.out.print(chars);
                }
            }
            System.out.println("|");
        }
        dash = dash.repeat(num_spaces);
        System.out.println(" " + dash + " ");
    }

    private void initializeBoard()
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                board[i][j] = EMPTY;
            }
        }
    }
}