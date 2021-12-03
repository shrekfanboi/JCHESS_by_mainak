package pieces;
import static board.Piecetype.ROOK;
import board.Board;
import board.Tile;
import board.Type;

public class Rook extends Piece {
    public Rook(int x,int y,boolean available,Type pieceColor){
        super(x, y, available, pieceColor,ROOK);
    }

    @Override
    public boolean isValidMove(Board board, Tile start, Tile end) {
        if(end.getPiece() != null && start.getClass()!=null && end.getPiece().getPieceColor() == this.getPieceColor()){
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return (x>0 && y==0) || (x==0 && y > 0);
    }
}
