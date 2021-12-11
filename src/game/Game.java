package game;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        this.status = GameStatus.ACTIVE;
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
        System.out.print("Turn-> "+this.getCurrentTurn().getSide()+"  ");
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
        if(this.isCheckMate(this.currentTurn.getSide())){
            if(this.getCurrentTurn().getSide()==Type.WHITE){ this.setStatus(GameStatus.BLACK_WIN);}
            else{ this.setStatus(GameStatus.WHITE_WIN);}
            System.out.println(this.getStatus().toString());
            return;
        }
        System.out.println();
    }


    public boolean isCheck(Type t){
        Tile kingLoc = this.chessboard.getPieceTile("KING",t).get(0);
        Type enemySide = t==Type.WHITE ? Type.BLACK : Type.WHITE;
        for(Piecetype piece:Piecetype.values()){
            List<Tile> pieceLoc = chessboard.getPieceTile(piece.toString(),enemySide);
            for(Tile loc:pieceLoc){
                Piece p = loc.getPiece();
                if(p!=null && p.isValidMove(this.chessboard, loc, kingLoc)){
                    System.out.print(" "+t.toString()+" King is in check from "+p.getPieceType());
                    return true;
                }
            }
        }
        return false;
    }

    //Too complex!!
    public boolean isCheckMate(Type t){
        if(t == Type.WHITE && !this.getWhiteKingChecked()){ 
            return false;
        }
        if(t == Type.BLACK && !this.getBlackKingChecked()){
            return false;
        }
        Tile kingLoc = this.chessboard.getPieceTile("KING", t).get(0);
        Map<Tile,Boolean> kingMoves = new HashMap<>();
        kingMoves.put(this.chessboard.getTile(kingLoc.getX()+1, kingLoc.getY()+1),false);
        kingMoves.put(this.chessboard.getTile(kingLoc.getX()+1, kingLoc.getY()),false);
        kingMoves.put(this.chessboard.getTile(kingLoc.getX()+1, kingLoc.getY()-1),false);
        kingMoves.put(this.chessboard.getTile(kingLoc.getX(), kingLoc.getY()-1),false);
        kingMoves.put(this.chessboard.getTile(kingLoc.getX(), kingLoc.getY()+1),false);
        kingMoves.put(this.chessboard.getTile(kingLoc.getX()-1, kingLoc.getY()+1),false);
        kingMoves.put(this.chessboard.getTile(kingLoc.getX()-1, kingLoc.getY()),false);
        kingMoves.put(this.chessboard.getTile(kingLoc.getX()-1, kingLoc.getY()-1),false);
        kingMoves.remove(null, false);
        kingMoves.keySet().removeIf(key-> key.getPiece()!=null);
        Type enemySide = t==Type.WHITE ? Type.BLACK : Type.WHITE;
        for(Map.Entry<Tile,Boolean> move: kingMoves.entrySet()){
            for(Piecetype piece:Piecetype.values()){
                if(Boolean.TRUE.equals(kingMoves.get(move.getKey()))){ break; }
                List<Tile> pieceLoc = chessboard.getPieceTile(piece.toString(),enemySide);
                for(Tile loc:pieceLoc){
                    Piece p = loc.getPiece();
                    if(p.isValidMove(this.chessboard, loc, move.getKey())){
                        kingMoves.replace(move.getKey(), true);
                        break;
                    }
                }
            }
        }
        System.out.println("CheckMateStats->");
        kingMoves.forEach((k,v)->System.out.print("("+k.getX()+","+k.getY()+")"+"="+v+"\t"));
        return !kingMoves.containsValue(false);
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
