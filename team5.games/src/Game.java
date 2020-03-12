import java.util.ArrayList;

public abstract class Game {
	public int rows;
	public int cols;
	public int[][] board;
	public ArrayList<ArrayList<Integer>> matched;
	public static final int EMPTY = 0;
	public int score;

	public Game()
	{
		rows = 13;
		cols = 6;
		board = new int[rows][cols];
		matched = new ArrayList<ArrayList<Integer>>();
		score = 0;
	}

	public abstract void checkMatch();
	public abstract void run();
	public abstract void printBoard();
	//	public abstract void makeMove();
	//	public abstract void checkMove();
	//	public abstract void generatePiece();

	public void initializeBoard()
	{
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				board[i][j] = EMPTY;
			}
		}
	}

	public int getCell(int row, int col)
	{
		return board[row][col];
	}

	public int getRows()
	{
		return rows;
	}

	public int getCols()
	{
		return cols;
	}

	public int getNumMatched()
	{
		return matched.size();
	}

	public ArrayList<ArrayList<Integer>> getMatched()
	{
		return matched;
	}

	public int getScore()
	{
		return score;
	}
}
