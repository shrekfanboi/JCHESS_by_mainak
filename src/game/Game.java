package game;
import java.util.List;
import board.Board;
import board.Move;
import board.Piecetype;
import board.Type;
import gui.CliGui;
import pieces.Piece;
import player.Player;
import board.Tile;
public class Game {
    private Player whitePlayer;
    private Player blackPlayer;
    private Board chessboard;
    private Player currentTurn;
    private GameStatus status;
    private Piece pieceKilled;
    private boolean isWhiteKingChecked;
    private boolean isBlackKingChecked;
    
    public Game(String whitePlayerName,String blackPlayerName,Board chessBoard){
        this.chessboard = chessBoard;
        this.whitePlayer = new Player(Type.WHITE, true, whitePlayerName);
        this.blackPlayer = new Player(Type.BLACK, true, blackPlayerName);
        this.currentTurn = this.whitePlayer;
        this.isWhiteKingChecked = false;
        this.isBlackKingChecked = false;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
  
    public void playerMove(int startx,int starty,int endx,int endy){
        Tile start = chessboard.getTile(startx, starty);
        Tile end = chessboard.getTile(endx, endy);
        Move move = new Move(this.currentTurn, start, end,chessboard);
        if(move.makeMove()){
            this.setCurrentTurn();
        }
        new CliGui(chessboard).drawGUI();
    }


    public void playerMove(int sourceId,int destinationId){
        Tile start = chessboard.getTile(sourceId);
        Tile end = chessboard.getTile(destinationId);
        Move move = new Move(this.currentTurn, start, end,chessboard);
        if(move.makeMove()){
            this.setCurrentTurn();
        }
    }

    public void playerMove(Tile start,Tile end){
        Move move = new Move(this.currentTurn, start, end,this.chessboard);
        System.out.print("Move-> ("+start.getX()+","+start.getY()+") to ("+end.getX()+","+end.getY()+")");
        if(move.makeMove()){
            this.setCurrentTurn();
            this.setPieceKilled(move.getPieceKilled());
            System.out.print(" Status: SUCCESS ");
            if(this.getPieceKilled()!=null){ System.out.print(" KilledPiece-> "+this.getPieceKilled().getPieceType());}
        }
        move.setPieceKilled(null);
        this.isWhiteKingChecked = false;
        this.isBlackKingChecked = false;
        if(this.isCheck(Type.BLACK)){ this.isBlackKingChecked = true;}
        if(this.isCheck(Type.WHITE)){ this.isWhiteKingChecked = true;}
        System.out.println();
    }


    public boolean isCheck(Type t){
        List<Tile> kingLoc = this.chessboard.getPieceTile("KING",t);
        Type enemySide = t==Type.WHITE ? Type.BLACK : Type.WHITE;
        for(Piecetype piece:Piecetype.values()){
            List<Tile> pieceLoc = chessboard.getPieceTile(piece.toString(),enemySide);
            for(Tile loc:pieceLoc){
                Piece p = loc.getPiece();
                if(p!=null && p.isValidMove(this.chessboard, loc, kingLoc.get(0))){
                    System.out.print(" "+t.toString()+" King is in check from "+p.getPieceType());
                    return true;
                }
            }
        }
        return false;
    }


    public Player getCurrentTurn(){
        return this.currentTurn;
    }
    public void setCurrentTurn(){
        if(this.getCurrentTurn() == this.whitePlayer) this.currentTurn = this.blackPlayer;
        else this.currentTurn = this.whitePlayer;
    }
    public Piece getPieceKilled(){
        return this.pieceKilled;
    }
    public void setPieceKilled(Piece p){
        this.pieceKilled = p;
    }
    public boolean getWhiteKingChecked(){
        return this.isWhiteKingChecked;
    }
    public boolean getBlackKingChecked(){
        return this.isBlackKingChecked;
    }
}
