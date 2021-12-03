package pieces;
import board.Board;
import board.Piecetype;
import board.Tile;
import board.Type;

public class Bishop extends Piece {

    public Bishop(int x,int y,boolean available,Type pieceColor){
        super(x, y, available, pieceColor,Piecetype.BISHOP);
    }

    @Override
    public boolean isValidMove(Board board, Tile start, Tile end) {
        if(end.getPiece()!=null && end.getPiece().getPieceColor() == this.getPieceColor()){
            return false;
        }
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return x == y;
    }
    
}
