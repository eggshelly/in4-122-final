public class ColumnsPiece extends Piece{
    private Piece[] pieceArr;

    public ColumnsPiece(int col){
        super(0, col);
        pieceArr = new Piece[]{new Piece(col), new Piece(col), new Piece(col)};
    }

    public void fall(){
        super.fall();
        for(Piece p : pieceArr){
            p.fall();
        }
    }

    public int getBottomColor()
    {
        return pieceArr[2].color;
    }

    public int getMiddleColor()
    {
        return pieceArr[1].color;
    }

    public int getTopColor()
    {
        return pieceArr[0].color;
    }

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
