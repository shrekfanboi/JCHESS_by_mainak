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
        int xdiff = Math.abs(start.getX() - end.getX());
        int ydiff = Math.abs(start.getY() - end.getY());
        return (xdiff == ydiff) && this.drawPath(board, start, end);
    }

    public boolean drawPath(Board board,Tile start,Tile end){
        if(start.getX()<end.getX() && start.getY()<end.getY()){
            //forward right
            int j=1;
            for(int i=start.getX()+1;i<end.getX();i++){
                if(board.getTile(i, start.getY()+j).getPiece()!=null) return false;
                j++;
            }
            return true;
        }
        else if(start.getX()<end.getX() && start.getY()>end.getY()){
            //forward left
            int j=1;
            for(int i=start.getX()+1;i<end.getX();i++){
                if(board.getTile(i, start.getY()-j).getPiece()!=null) return false;
                j++;
            }
            return true;
        }
        else if(start.getX()>end.getX() && start.getY()<end.getY()){
            //backward right
            int j=1;
            for(int i=start.getX()-1;i>end.getX();i--){
                if(board.getTile(i, start.getY()+j).getPiece()!=null) return false;
                j++;
            }
            return true;
        }
        else if(start.getX()>end.getX() && start.getY()>end.getY()){
            //backward left
            int j=1;
            for(int i=start.getX()-1;i>end.getX();i--){
                if(board.getTile(i, start.getY()-j).getPiece()!=null) return false;
                j++;
            }
            return true;
        }
        return false;
    }
    
}
