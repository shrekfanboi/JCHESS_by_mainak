package pieces;
import static board.Piecetype.QUEEN;
import board.Board;
import board.Tile;
import board.Type;

public class Queen extends Piece {
    public Queen(int x,int y,boolean available,Type pieceColor){
        super(x, y, available, pieceColor,QUEEN);
    }

    @Override
    public boolean isValidMove(Board board, Tile start, Tile end) {
        if(end.getPiece()!=null && end.getPiece().getPieceColor() == this.getPieceColor()){
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return ((x>0 && y==0) || (x==0 && y > 0)) || (x == y);
    }
}
