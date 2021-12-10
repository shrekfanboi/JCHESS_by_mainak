package board;
import pieces.Piece;
import player.Player;

public class Move {
    private Player player;
    private Tile start;
    private Tile end;
    private Piece pieceMoved;
    private Piece pieceKilled;
    private Board board;


    public Move(Player p,Tile start,Tile end,Board board){
        this.player = p;
        this.start = start;
        this.end = end;
        this.pieceMoved = start.getPiece();
        this.board = board;
        this.pieceKilled = null;
    }

    public boolean makeMove(){
        if(this.player.getSide() != this.pieceMoved.getPieceColor()){
            System.out.print(" ERR: Cannot move enemy piece");
            return false;
        }

        else if(this.pieceMoved.isValidMove(board, this.start, this.end)){
            if(this.end.getPiece() == null){
                this.pieceMoved.setCoord(end);
                this.board.setTile(this.start,null);
                this.board.setTile(this.end, this.pieceMoved);
                return true;
            }
            else if(this.end.getPiece()!=null && this.end.getPiece().getPieceColor()!=this.player.getSide()){

                if(this.end.getPiece().getPieceType().equals("KING")){
                    System.out.print("ERR: KING IS ALREADY IN CHECK");   
                }
                else{
                    this.pieceMoved.setCoord(end);
                    this.board.setTile(this.start,null);
                    this.setPieceKilled(this.end.getPiece());
                    this.board.setTile(this.end, this.pieceMoved);
                    this.player.registerMove(this);
                }
                return true;
            }
            else{  
                return false;
            }
        }
        
        else{
            System.err.println(" ERR: This move is not valid");
            this.setPieceMoved(null);
            return false;
        }
    }

    public Piece getPieceKilled(){
        return this.pieceKilled;
    }
    public Piece getPieceMoved(){
        return this.pieceMoved;
    }
    public void setPieceKilled(Piece p){
        this.pieceKilled = p;
    }
    public void setPieceMoved(Piece p){
        this.pieceMoved = p;
    }

}
