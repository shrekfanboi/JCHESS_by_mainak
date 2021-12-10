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

                if(this.getPieceColor()==Type.BLACK){
                    return x == 1 && y == 0 && start.getX() > end.getX();
                }
                else{
                    return x == 1 && y == 0 && start.getX() < end.getX();
                }
                
            }
            else{
                //enemy piece
                if(start.getPiece().getPieceColor() == Type.WHITE) return (start.getX()<end.getX()) &&(x == 1 && y == 1);
                else return (start.getX()>end.getX()) &&(x == 1 && y == 1);
            }
        }
    }
}
