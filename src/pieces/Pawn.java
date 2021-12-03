package pieces;
import static board.Piecetype.PAWN;

import board.Board;
import board.Tile;
import board.Type;

public class Pawn extends Piece{
    public Pawn(int x,int y,boolean available,Type pieceColor){
        super(x, y, available, pieceColor,PAWN);
    }

    @Override
    public boolean isValidMove(Board board, Tile start, Tile end) {
        if(end.getPiece()!=null && end.getPiece().getPieceColor() == this.getPieceColor()){
            return false;
        }
    
        else{
            int x = Math.abs(start.getX() - end.getX());
            int y = Math.abs(start.getY() - end.getY());
            if(end.getPiece() == null){
                
                return x == 1 && y == 0;
            }
            else{
                //enemy piece
                return x == 1 && y == 1;
            }
        }
    }
}