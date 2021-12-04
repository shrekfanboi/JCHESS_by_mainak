package game;
import board.Board;
import board.Move;
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
    
    public Game(String whitePlayerName,String blackPlayerName,Board chessBoard){
        this.chessboard = chessBoard;
        this.whitePlayer = new Player(Type.WHITE, true, whitePlayerName);
        this.blackPlayer = new Player(Type.BLACK, true, blackPlayerName);
        this.currentTurn = this.whitePlayer;
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
        if(move.makeMove()){
            this.setCurrentTurn();
            this.setPieceKilled(move.getPieceKilled());
            System.out.println("Move is successful");
        }
        // if(this.getPieceKilled()!=null) System.out.println(this.getPieceKilled().toString());
        // else {System.out.println("null");} 
        move.setPieceKilled(null);
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
}
