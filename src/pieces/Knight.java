package pieces;
import static board.Piecetype.KNIGHT;
import board.Board;
import board.Tile;
import board.Type;

public class Knight extends Piece {
    public Knight(int x,int y,boolean available,Type pieceColor){
        super(x, y, available, pieceColor,KNIGHT);
    }

    @Override
    public boolean isValidMove(Board board, Tile start, Tile end) {
        
        if(end.getPiece() != null && start.getPiece()!=null && end.getPiece().getPieceColor() == this.getPieceColor()){
            return false;
        }
        
        


        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return x*y == 2;
    }
      
}
