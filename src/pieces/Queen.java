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

        int xdiff = Math.abs(start.getX() - end.getX());
        int ydiff = Math.abs(start.getY() - end.getY());
        return (((xdiff>0 && ydiff==0) || (xdiff==0 && ydiff > 0)) || (xdiff == ydiff)) && this.isValidPath(board, start, end, xdiff, ydiff);
    }

    public boolean isValidPath(Board board,Tile start,Tile end,int xdiff,int ydiff){
        int i;
        int j;
        if((xdiff>0 && ydiff==0) || (xdiff==0 && ydiff > 0))
        {
            //Rook's movement
            if(ydiff == 0 && start.getX() < end.getX()){
                //vertically up
                for(i=start.getX()+1;i<end.getX();i++){
                    if(board.getTile(i, start.getY()).getPiece()!=null) return false;
                }
                return true;
            }
            else if(ydiff == 0 && start.getX() > end.getX()){
                //vertically down
                for(i=start.getX()-1;i>end.getX();i--){
                    if(board.getTile(i, start.getY()).getPiece()!=null) return false;
                }
                return true;
            }
            else if(xdiff == 0 && start.getY() < end.getY()){
                //horizantally right
                for(j=start.getY()+1;j<end.getY();j++){
                    if(board.getTile(start.getX(), j).getPiece()!=null) return false;
                }
                return true;
            }
            else if(xdiff == 0 && start.getY() > end.getY()){
                //horizantally left
                for(j=start.getY()-1;j>end.getY();j--){
                    if(board.getTile(start.getX(), j).getPiece()!=null) return false;
                }
                return true;
            }
        }
    

        else if(xdiff==ydiff)
        {
            //Bishop's movement
            if(start.getX()<end.getX() && start.getY()<end.getY()){
                //forward right
                j=1;
                for(i=start.getX()+1;i<end.getX();i++){
                    if(board.getTile(i, start.getY()+j).getPiece()!=null) return false;
                    j++;
                }
                return true;
            }
            else if(start.getX()<end.getX() && start.getY()>end.getY()){
                //forward left
                j=1;
                for(i=start.getX()+1;i<end.getX();i++){
                    if(board.getTile(i, start.getY()-j).getPiece()!=null) return false;
                    j++;
                }
                return true;
            }
            else if(start.getX()>end.getX() && start.getY()<end.getY()){
                //backward right
                j=1;
                for(i=start.getX()-1;i>end.getX();i--){
                    if(board.getTile(i, start.getY()+j).getPiece()!=null) return false;
                    j++;
                }
                return true;
            }
            else if(start.getX()>end.getX() && start.getY()>end.getY()){
                //backward left
                j=1;
                for(i=start.getX()-1;i>end.getX();i--){
                    if(board.getTile(i, start.getY()-j).getPiece()!=null) return false;
                    j++;
                }
                return true;
            }
        }
        return false;
    }
}
