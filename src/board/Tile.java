package board;
import pieces.Piece;

public class Tile {
protected final int x;
protected final int y;
private Piece piece;

public Tile(int x,int y,Piece piece){
    this.x = x;
    this.y = y;
    this.piece = piece;
}

public boolean isValidCoordinate(int x,int y){
    return (x < 0 || x > 7 || y < 0 || y > 7);
}

public Piece getPiece(){
    return this.piece;
}

public void setPiece(Piece p)
{
    this.piece = p;
}

public int getX(){
    return this.x;
}

public int getY(){
    return this.y;
}

public int getId(){
    return this.getX()*8+this.getY();
}

}