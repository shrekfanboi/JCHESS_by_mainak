package pieces;
import static board.Piecetype.KING;

import board.Board;
import board.Tile;
import board.Type;


public class King extends Piece{
    
    public King(int x,int y,boolean available,Type pieceColor){
        super(x, y, available, pieceColor,KING);
    }
    @Override
    public boolean isValidMove(Board board, Tile start, Tile end) {
        if(end.getPiece()!=null && end.getPiece().getPieceColor() == this.getPieceColor()){
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return (x+y > 0) && (x+y <= 2);
    }
    
}
