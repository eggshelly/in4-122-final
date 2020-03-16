import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Bejeweled extends Game{
    private boolean isMatched;

    public Bejeweled(String playerName)
    {
        super(8, 8, playerName);
        isMatched = false;
        initializeBoard();
    }

    @Override
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

        checkMatch();
    }

    public boolean makeMove(int row1, int col1, int row2, int col2){
        int temp = 0;
        if(isValidRow(row1) && isValidCol(col1) && isValidRow(row2) && isValidCol(col2)) {
            temp = board[row1][col1];
            board[row1][col1] = board[row2][col2];
            board[row2][col2] = temp;
            printBoard();

            checkMatch();
            if (matched.size() != 0) {
                deleteMatched();
                return true;
            } else {
                //moving pieces back after no match is found
                //System.out.println("No matches found");
                board[row2][col2] = board[row1][col1];
                board[row1][col1] = temp;
                return false;
            }
        }
        return false;


    }

    @Override
    public void run() {
        initializeBoard();
        System.out.println("WELCOME TO BEJEWELED");
        playConsoleGame();
    }

    private void playConsoleGame()
    {
        printBoard();
        Scanner input = new Scanner(System.in);
        String userInput = "";
        while(!userInput.equals("Q")){
            System.out.println("Press 'Q' to quit game, \n'M' to make move:");
            userInput = input.nextLine();
            while(!userInput.equals("Q") && !userInput.equals("M")){
                System.out.println("Please enter either 'Q' to quit game or 'M' to make move:");
                userInput = input.nextLine();
            }
            if(userInput.equals("M")){
                System.out.println("Please enter first row:");
                String row1 = input.nextLine();
                System.out.println("Please enter first column:");
                String col1 = input.nextLine();
                System.out.println("Please enter second row:");
                String row2 = input.nextLine();
                System.out.println("Please enter second column:");
                String col2 = input.nextLine();

                try {
                    int intRow1 = Integer.parseInt(row1);
                    int intRow2 = Integer.parseInt(row2);
                    int intCol1 = Integer.parseInt(col1);
                    int intCol2 = Integer.parseInt(col2);
                    if (!isValidRow(intRow1) || !isValidCol(intCol1) || !isValidRow(intRow2) || !isValidCol(intCol2))
                        System.out.println("Please enter a correct row or column.");
                    else if(intRow1 == intRow2 || intCol1 == intCol2) {
                        boolean move = makeMove(intRow1, intCol1, intRow2, intCol2);
                        if(move){
                            printBoard();
                            movePiecesDown();
                            //printBoard();
                        }
                        else
                            System.out.println("No matches found");
                        printBoard();
                    }
                    else {
                        System.out.println("Both pieces must be next to each other");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a correct row or column.");
                }


            }
        }

    }

    @Override
    public void checkMatch()
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
        //return isMatched;
//        if (matched.size() != 0)
//        {
//            deleteMatched();
//        }
    }

    private void checkBoard(){
        checkMatch();
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
        }
        matched.clear();
        checkBoard();
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
    public void movePiecesDown()
    {
        Random r = new Random();

        for (int i = rows-1; i > -1; i--)
        {
            for (int j = cols-1; j > -1; j--)
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

    @Override
    public void printBoard()
    {
        System.out.println("   0 1 2 3 4 5 6 7");
        System.out.println("  -----------------");
        for (int i = 0; i < rows; i++)
        {
            System.out.print(i + " |");
            for (int j = 0; j < cols; j++)
            {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("  ---------------");
    }


}
