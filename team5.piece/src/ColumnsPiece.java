public class ColumnsPiece extends Piece{

    private Piece[] pieceArr;

    //default constructor, makes a piece at 0,0
    public ColumnsPiece(){
        super(0,0);
        pieceArr = new Piece[]{new Piece(0, 0), new Piece(1, 0), new Piece(2, 0)};
    }

    public ColumnsPiece(int col){
        super(0,col);
        pieceArr = new Piece[]{new Piece(0, col), new Piece(1, col), new Piece(2, col)};
    }

    @Override
    public void fall(){
        for(Piece p : pieceArr)
        {
            p.fall();
        }
    }

    @Override
    public void update() {
        for(Piece p : pieceArr)
        {
            p.update();
        }
    }

    //rotates the piece, bottom piece goes to top
    public void rotate(){
        if(pieceArr != null){
            Piece temp = pieceArr[pieceArr.length-1];
            for(int i = pieceArr.length-1; i>0; i--){
                pieceArr[i] = pieceArr[i-1];
            }
            pieceArr[0] = temp;
        }
    }

}
