package pieces;
import board.Type;

import board.Board;
import board.Piecetype;
import board.Tile;


public abstract class Piece {
protected int x;
protected int y;
private boolean available;
private Type color;
protected Piecetype type;

protected Piece(int x,int y,boolean available,Type pieceColor,Piecetype type){
    this.x = x;
    this.y = y;
    this.available = available;
    this.color = pieceColor;
    this.type = type;
}

public boolean isAvailable(){
    return this.available;
}

public Type getPieceColor(){
    return this.color;
}

public int getX(){
    return this.x;
}

public void setX(int x){
    this.x = x;
}

public int getY(){
    return this.y;
}

public void setCoord(Tile t){
    this.x = t.getX();
    this.y = t.getY();
}

public void setY(int y){
    this.y = y;
}

public String getPieceType(){
    return this.type.toString();
}

public abstract boolean isValidMove(Board board,Tile start,Tile end);

}
