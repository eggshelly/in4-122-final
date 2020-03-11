
public class Board {
	int rows;
	int cols;
	Piece[][] state;
	
	public Board(int row, int col){
		rows = row;
		cols = col;
		
		state = new Piece[rows][cols];
	}
	
	public boolean canPieceFall(int row,int col){
		if(row < rows-1){
			if(state[row+1][col] == null){
				return true;
			}
		}
		return false;
	}
	
	public boolean canPieceFall(Piece p){
		int row = p.getRow();
		int col = p.getCol();
		if(row < rows-1){
			if(state[row+1][col] == null){
				return true;
			}
		}
		return false;
	}
	
	public Piece[][] getState(){
		return state;
	}
}
