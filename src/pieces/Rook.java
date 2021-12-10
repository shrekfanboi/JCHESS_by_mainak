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

        int xdiff = Math.abs(start.getX() - end.getX());
        int ydiff = Math.abs(start.getY() - end.getY());
        
        return ((xdiff>0 && ydiff==0) || (xdiff==0 && ydiff > 0)) && this.isValidPath(board, start, end, xdiff, ydiff);
    }

    public boolean isValidPath(Board board,Tile start,Tile end,int x,int y){
        int i;
        int j;
        if(y == 0 && start.getX() < end.getX()){
            //vertically up
            for(i=start.getX()+1;i<end.getX();i++){
                if(board.getTile(i, start.getY()).getPiece()!=null ) return false;
            }
            return true;
        }
        else if(y == 0 && start.getX() > end.getX()){
            //vertically down
            for(i=start.getX()-1;i>end.getX();i--){
                if(board.getTile(i, start.getY()).getPiece()!=null) return false;
            }
            return true;
        }
        else if(x == 0 && start.getY() < end.getY()){
            //horizantally right
            for(j=start.getY()+1;j<end.getY();j++){
                if(board.getTile(start.getX(), j).getPiece()!=null) return false;
            }
            return true;
        }
        else if(x == 0 && start.getY() > end.getY()){
            //horizantally lefisValidPath
            for(j=start.getY()-1;j>end.getY();j--){
                if(board.getTile(start.getX(), j).getPiece()!=null) return false;
            }
            return true;
        }
        return false;
    }
}
