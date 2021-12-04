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
            System.out.println("Cannot move enemy piece");
            return false;
        }

        else if(this.pieceMoved.isValidMove(board, this.start, this.end)){
            System.out.println("valid move-->");
            this.pieceMoved.setCoord(end);
            this.board.setTile(this.start,null);
            if(this.end.getPiece()!=null) this.setPieceKilled(this.end.getPiece());
            this.board.setTile(this.end, this.pieceMoved);
            this.player.registerMove(this);
            return true;
        }
        
        else{
            System.err.println("This move is not valid");
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
